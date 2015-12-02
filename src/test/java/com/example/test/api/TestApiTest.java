package com.example.test.api;

import com.example.test.Application;
import com.example.test.Configuration;
import com.example.test.resources.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.net.URI;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matcher.*;

public class TestApiTest {

    @ClassRule
    public static final DropwizardAppRule<Configuration> RULE =
            new DropwizardAppRule<>(Application.class, ResourceHelpers.resourceFilePath("config.yml"));

    @Test
    public void testUser() throws Exception {
        HttpClient client =  new HttpClientBuilder(RULE.getEnvironment())
                .build("test");

        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("127.0.0.1")
                .setPort(RULE.getLocalPort())
                .setPath("/test/user")
                .build();

        HttpPost postUser = new HttpPost(uri);
        postUser.setHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
        postUser.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        ObjectMapper mapper = new ObjectMapper();
        User user = new User("", null);
        postUser.setEntity(new StringEntity(mapper.writeValueAsString(user)));

        HttpResponse response = client.execute(postUser);

        assertEquals(200, response.getStatusLine().getStatusCode());

        User userResponse = mapper.readValue(response.getEntity().getContent(), User.class);
        assertNull(userResponse.getName());
        assertNull(userResponse.getSurname());
    }

}