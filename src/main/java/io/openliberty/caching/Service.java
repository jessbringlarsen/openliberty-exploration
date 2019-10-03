package io.openliberty.caching;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheResult;
import javax.ejb.Singleton;
import java.util.UUID;

@Singleton
@CacheDefaults(cacheName = "io.openliberty.caching")
public class Service {

    @CacheResult
    public String getARandomStringFrom(@CacheKey String name) {
        System.out.println("Cache miss!");
        return UUID.randomUUID().toString();
    }
}