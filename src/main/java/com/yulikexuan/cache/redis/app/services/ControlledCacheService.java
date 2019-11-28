//: com.yulikexuan.cache.redis.app.services.ControlledCacheService.java


package com.yulikexuan.cache.redis.app.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
public class ControlledCacheService {

    @Cacheable(cacheNames = "yulControlledCache")
    public Optional<String> getFromCache() {
        return Optional.empty();
    }

    /*
     * @CachePut is not checking if there is an existing one
     */
    @CachePut(cacheNames = "yulControlledCache")
    public String populateCache() {
        return "This is it again!";
    }

    @CacheEvict(cacheNames = "yulControlledCache")
    public void removeFromCache() {
        log.info(">>>>>>> Forgetting everything about this in yulControlledCache!");
    }

}///:~