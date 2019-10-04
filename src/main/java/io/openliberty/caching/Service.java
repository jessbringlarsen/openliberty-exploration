package io.openliberty.caching;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheResult;

@CacheDefaults(cacheName = "io.openliberty.caching")
public class Service {

    @CacheResult
    public BigDecimal getARandomNumberFrom(@CacheKey LocalDate day) {
        System.out.println("Cache miss!");
        return new BigDecimal("1.0");
    }

    @CacheResult
    public BigDecimal getARandomNumber() {
        System.out.println("Cache miss latest!");
        return new BigDecimal("2.0");
    }
}