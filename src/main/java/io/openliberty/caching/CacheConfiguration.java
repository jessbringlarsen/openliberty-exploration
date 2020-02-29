package io.openliberty.caching;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
class CacheConfiguration {

    private Logger logger = LoggerFactory.getLogger(CacheConfiguration.class);

    public void initialize(@Observes @Initialized(ApplicationScoped.class) Object object) {
        CacheManager manager = Caching.getCachingProvider().getCacheManager();
        MutableConfiguration<Object, BigDecimal> configuration = new MutableConfiguration<Object, BigDecimal>()
                .setTypes(Object.class, BigDecimal.class).setStoreByValue(false)
                .setStatisticsEnabled(true)
                .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 5)));

        manager.createCache("io.openliberty.caching", configuration);

        logger.info("Cache initialized.");
        System.out.println("Cache initialized-2");
    }
}