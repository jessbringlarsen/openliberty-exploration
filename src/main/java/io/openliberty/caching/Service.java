package io.openliberty.caching;

import java.util.UUID;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheResult;

@CacheDefaults(cacheName = "io.openliberty.caching")
public class Service {

    @CacheResult
    public String getARandomStringFrom(@CacheKey String name) {
        System.out.println("Cache miss!");
        return UUID.fromString(name).toString();
    }
}