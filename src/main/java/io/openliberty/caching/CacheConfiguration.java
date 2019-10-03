package io.openliberty.caching;


import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class CacheConfiguration {

    public void initialize(@Observes @Initialized(ApplicationScoped.class) Object object) {
        CacheManager manager = Caching.getCachingProvider().getCacheManager();
        MutableConfiguration<Object, String> configuration = new MutableConfiguration<Object, String>()
                .setTypes(Object.class, String.class).setStoreByValue(false)
                .setStatisticsEnabled(true)
                .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 5)));

        manager.createCache("io.openliberty.caching", configuration);

        System.out.println("Cache initialized.");
    }
}