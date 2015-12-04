package com.example.test;

import com.example.test.resources.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Test
    public void testDeserialization() throws Exception {
        ObjectMapper mapper = RULE.getEnvironment().getObjectMapper()
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        String json = mapper.writeValueAsString(new User("", ""));
        System.out.println(json);

        User testUser = mapper.readValue(json, User.class);
        assertNull(testUser.getName());
        assertNull(testUser.getSurname());
    }

    @Test
    public void testIsEmptyStringEmpty() throws Exception {
        String emptyStr = "";
        assertTrue(emptyStr.isEmpty());
    }
}