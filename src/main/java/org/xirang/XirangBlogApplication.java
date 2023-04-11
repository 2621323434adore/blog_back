package org.xirang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.xirang.Mapper")
public class XirangBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(XirangBlogApplication.class, args);
    }

}
