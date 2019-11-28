//: com.yulikexuan.cache.redis.UltimateRedisCacheApplication.java


package com.yulikexuan.cache.redis;


import com.yulikexuan.cache.redis.app.services.CacheService;
import com.yulikexuan.cache.redis.app.services.ControlledCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@Slf4j
@EnableCaching
@SpringBootApplication
public class UltimateRedisCacheApplication implements CommandLineRunner {

    private final CacheService cacheService;
    private final ControlledCacheService controlledCacheService;

    public static final String LINE_SEPARATOR = System.lineSeparator();

    @Autowired
    public UltimateRedisCacheApplication(
            CacheService cacheService,
            ControlledCacheService controlledCacheService) {

        this.cacheService = cacheService;
        this.controlledCacheService = controlledCacheService;
    }

    public static void main(String[] args) {
        SpringApplication.run(UltimateRedisCacheApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        log.info(">>>>>>> General Usage: ------------------------");
//        generalUsage();

        // More Control
        log.info(
                ">>>>>>> Starting controlled cache: ------------------------{}",
                LINE_SEPARATOR);

        String controlledFirst = this.getFromControlledCache();
        log.info(">>>>>>> Controlled First: {} {}", controlledFirst,
                LINE_SEPARATOR);

        String controlledSecond = this.getFromControlledCache();
        log.info(">>>>>>> Controlled Second: {} {}", controlledSecond,
                LINE_SEPARATOR);

        log.info(">>>>>>> Clearing all cache entries: ");
        this.clearCaches();
    }

    private void generalUsage() {

        // General Usage:
        log.info(">>>>>>> Application initialized!");

        String firstString = cacheService.cacheThis();
        log.info(">>>>>>> First: {}", firstString);

        String secondString = cacheService.cacheThis();
        log.info(">>>>>>> Second: {}", secondString);
    }

    private String getFromControlledCache() {

        String fromCache = this.controlledCacheService.getFromCache()
                .orElseGet(() -> {
                    log.info(">>>>>>> Oups - Cache was empty. Going to populate it.");
                    String newValue = controlledCacheService.populateCache();
                    log.info(">>>>>>> Populated Cache with {}", newValue);
                    return newValue;
                });

        log.info(">>>>>>> Returning from Cache: {}", fromCache);

        return fromCache;
    }

    private void clearCaches() {
        this.cacheService.forgetAboutThis();
        this.controlledCacheService.removeFromCache();
    }

}///:~