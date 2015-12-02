package com.example.test;

import com.example.test.api.TestApi;
import com.fasterxml.jackson.databind.DeserializationFeature;
import io.dropwizard.setup.Environment;


public class Application extends io.dropwizard.Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new Application().run(args);
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        environment.getObjectMapper()
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);

        environment.jersey().register(new TestApi());
    }

    @Override
    public String getName() {
        return "test";
    }
}
