package com.example.test.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String name;
    private String surname;

    public User() {
    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getSurname() {
        return surname;
    }

    @JsonProperty
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
