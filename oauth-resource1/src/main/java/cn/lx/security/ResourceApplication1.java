package cn.lx.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ResourceApplication1 {
    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication1.class, args);
    }

}
