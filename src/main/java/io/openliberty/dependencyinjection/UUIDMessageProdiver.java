package io.openliberty.dependencyinjection;

import java.util.UUID;

import javax.enterprise.inject.Alternative;

import io.openliberty.dependencyinjection.MessageProvider;

@Alternative // By default, @Alternative beans are disabled. We need to enable an alternative in the beans.xml
public class UUIDMessageProdiver implements MessageProvider {

    @Override
    public String message() {
        return UUID.randomUUID().toString();
    }  
}