package com.psfd.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author quanshaoshun
 * @date 2020/7/9 - 18:16
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.psfd.springboot.mapper"})
public class Main01 {
    public static void main(String[] args) {
        SpringApplication.run(Main01.class,args);
    }
}
