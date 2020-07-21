package org.javaboy.formlogin.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @author xinggevip
 * @date 2020/5/3 21:07
 */
@Service
public class MethodService {

    /**
     * 应为配置了"ROLE_admin > ROLE_user"
     * 所以admin可以访问user
     * @return
     */
    @PreAuthorize("hasRole('user')")
    public String admin() {
        return "hello admin";
    }

    /**
     * 只能是user访问，admin也不行
     * @return
     */
    @Secured("ROLE_user")
    public String user() {
        return "hello user";
    }

    /**
     * 可以是任意其中一种身份
     * @return
     */
    @PreAuthorize("hasAnyRole('admin','user')")
    public String hello() {
        return "hello hello";
    }


}
