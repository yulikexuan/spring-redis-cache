//: com.yulikexuan.cache.redis.UltimateRedisCacheApplication.java


package com.yulikexuan.cache.redis;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@EnableCaching
@SpringBootApplication
public class UltimateRedisCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(UltimateRedisCacheApplication.class, args);
    }

}///:~