package io.openliberty.dependencyinjection;

import javax.enterprise.inject.Default;

import io.openliberty.dependencyinjection.MessageProvider;

@Default // Default annotation is optional and applied by default
public class FixedMessageProvider implements MessageProvider {

    @Override
    public String message() {
        return "42";
    }
}