package com.example.test.api;

import com.example.test.resources.User;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/test")
public class TestApi {

    @POST
    @Path("/user")
    public User testUser(@Valid User user) {
        return user;
    }

}
