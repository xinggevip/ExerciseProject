package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * springsecurity5之后强制使用加密,这里不加密提示方法已过期
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 在内存配置多个用户可以.and()加
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("admin")
                .roles("admin");
    }

    /**
     * 放行静态资源
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**","/images/**");
    }

    /**
     * 自定义登录页面
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() // 开启配置
                .anyRequest().authenticated() // 任何请求都要认证之后才能访问
                .and()
                .formLogin()
                .loginPage("/login.html")  // 自定义登录页面后，如果没有指定form的action，会默认和自定义的登录页值一样即action也是/login.html
                .loginProcessingUrl("/doLogin")  // 请求接口
                .usernameParameter("name")    // 用户名请求属性
                .passwordParameter("passwd")  // 密码请求属性
                .defaultSuccessUrl("/index")  // 重定向到登录前地址，记录登录前的地址
                //.successForwardUrl("/index")  // 无视登录前的地址，访问指定请求
                //.failureForwardUrl("/f2")     // 登录失败访问请求
                .failureUrl("/f1")
                .permitAll()
                .and()
                .logout()  //配置注销登录
//                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
                .logoutSuccessUrl("/index")
                .deleteCookies()              // 清除cookies  默认
                .clearAuthentication(true)   // 清除身份信息  默认
                .invalidateHttpSession(true) // 清除session   默认
                .permitAll()
                .and()
                .csrf().disable();
    }
}
