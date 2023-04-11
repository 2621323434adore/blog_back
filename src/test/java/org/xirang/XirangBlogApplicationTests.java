package org.xirang;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.xirang.Utils.PasswordUtil;

@SpringBootTest
class XirangBlogApplicationTests {

    @Test
    void login() {
        System.out.println(PasswordUtil.encode("123456"));
        System.out.println(PasswordUtil.verify("123456","e10adc3949ba59abbe56e057f20f883e"));
    }
    @Test
    void token() {
        System.out.println();
    }

}
