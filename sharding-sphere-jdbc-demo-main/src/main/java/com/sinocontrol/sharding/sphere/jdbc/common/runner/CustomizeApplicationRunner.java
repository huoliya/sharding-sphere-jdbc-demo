package com.sinocontrol.sharding.sphere.jdbc.common.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @description: 自定义运行器
 * @date 2022-05-12 11:39
 */
@Slf4j
@Component
public class CustomizeApplicationRunner implements CommandLineRunner {

    /**
     * 端口号
     */
    @Value("${server.port}")
    private String port;

    @Override
    public void run(String... args) throws UnknownHostException {
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        log.info("---------------------部署完成---------------------");
        log.info(String.join("", "接口文档地址为：", "http://", hostAddress, ":", port, "/doc.html"));
    }
}
