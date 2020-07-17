package com.xinggevip;

import com.xinggevip.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private User user;

    @Test
    void contextLoads() {
        user.eat("麻辣香锅");
        /**
         *
         * 执行结果：
         *
         * TestAspect=>前置通知
         * 获取第一个请求参数：麻辣香锅
         * User------前置通知
         * =========BeforeStart=========
         * 前置通知====方法名字为：eat方法参数为：[麻辣香锅]
         * =========BeforeEnd=========
         * 吃麻辣香锅...
         * user----后置通知
         *
         * 分析：
         * 环绕通知try catch写法，包含所有通知
         * @Order(1) // 多个切面，指定执行顺序，数字越小，执行越靠前
         * 如果一个切面既有环绕通知又有前置通知  则优先执行环绕通知
         */
    }

}
