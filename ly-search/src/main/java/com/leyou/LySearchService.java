package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @auther:XUJIAN
 * @date=${DATA}11:51
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class LySearchService {
    public static void main(String[] args) {
        SpringApplication.run(LySearchService.class);
    }
}
