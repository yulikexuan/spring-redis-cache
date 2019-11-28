//: com.yulikexuan.cache.redis.app.services.CacheService.java


package com.yulikexuan.cache.redis.app.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class CacheService {

    @Cacheable(cacheNames = "yulCache")
    public String cacheThis() {
        log.info(">>>>>>> Returning NOT from cache!");
        return "This Is it.";
    }

}///:~