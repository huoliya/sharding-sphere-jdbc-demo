package com.sinocontrol.sharding.sphere.jdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 */
@MapperScan("com.sinocontrol.sharding.sphere.jdbc.demo.mapper")
@SpringBootApplication
public class ShardingSphereJdbcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingSphereJdbcDemoApplication.class, args);
    }

}
