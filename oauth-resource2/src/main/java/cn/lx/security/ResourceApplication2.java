package cn.lx.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ResourceApplication2 {
    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication2.class, args);
    }

}
