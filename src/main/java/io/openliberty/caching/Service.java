package io.openliberty.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheResult;
import java.math.BigDecimal;
import java.time.LocalDate;

@CacheDefaults(cacheName = "io.openliberty.caching")
public class Service {

    private Logger logger = LoggerFactory.getLogger(Service.class);

    @CacheResult
    public BigDecimal getARandomNumberFrom(@CacheKey LocalDate day) {
        logger.info("Cache miss!");
        return new BigDecimal("1.0");
    }

    @CacheResult
    public BigDecimal getARandomNumber() {
        logger.info("Cache miss latest!");
        return new BigDecimal("2.0");
    }
}