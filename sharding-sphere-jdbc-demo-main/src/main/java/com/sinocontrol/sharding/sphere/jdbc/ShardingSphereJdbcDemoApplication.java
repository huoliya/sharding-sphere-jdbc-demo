package com.sinocontrol.sharding.sphere.jdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 */
@MapperScan({"com.sinocontrol.sharding.sphere.jdbc.demo.mapper","com.sinocontrol.sharding.sphere.jdbc.demo.mapper2","com.sinocontrol.sharding.sphere.jdbc.demo.mapper3"})
@EntityScan({"com.sinocontrol.sharding.domain"})
@ComponentScan({"com.sinocontrol.sharding"})
@SpringBootApplication
@EnableTransactionManagement
public class ShardingSphereJdbcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingSphereJdbcDemoApplication.class, args);
    }

}
