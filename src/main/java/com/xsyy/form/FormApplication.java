package com.xsyy.form;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@MapperScan(value = "com.xsyy.form.mapper")
@SpringBootApplication
public class FormApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormApplication.class, args);
    }

}
