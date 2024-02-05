//package com.seyhavorn.springbootecommerce.config;
//
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableCaching
//public class RedisCacheConfig {
//
//    @Bean
//    public RedisCacheManager redisCacheManager() {
//        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
//
//        cacheConfigurations.put("userCache",
//                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(20)));
//
//        cacheConfigurations.put("dataCache",
//                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)));
//
//        return RedisCacheManager.builder()
//                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig())
//                .withInitialCacheConfigurations(cacheConfigurations)
//                .build();
//    }
//}
