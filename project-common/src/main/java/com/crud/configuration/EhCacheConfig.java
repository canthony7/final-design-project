package com.crud.configuration;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EhCacheConfig {

    @Bean
    public Cache getCache(){
        CacheManager cacheManager = new CacheManager();
        Cache cache = cacheManager.getCache("usualCache");
        return cache;
    }

}
