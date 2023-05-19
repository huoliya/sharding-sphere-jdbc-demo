package com.sinocontrol.sharding.sphere.jdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 */
@MapperScan("com.sinocontrol.sharding.sphere.jdbc.demo.mapper")
@EntityScan({"com.sinocontrol.sharding.domain"})
@ComponentScan({"com.sinocontrol.sharding"})
@SpringBootApplication
public class ShardingSphereJdbcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingSphereJdbcDemoApplication.class, args);
    }

}
