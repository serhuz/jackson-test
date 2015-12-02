package com.example.test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import static org.junit.Assert.*;


public class ApplicationTest {

    @ClassRule
    public static final DropwizardAppRule<Configuration> RULE =
            new DropwizardAppRule<>(Application.class, ResourceHelpers.resourceFilePath("config.yml"));

    @Test
    public void testObjectMapperConfig() throws Exception {
        boolean enabled = RULE.getEnvironment()
                .getObjectMapper()
                .getDeserializationConfig()
                .isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        assertTrue(enabled);
    }

}