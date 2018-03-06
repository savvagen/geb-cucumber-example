package com.example.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class User {

    @JsonProperty("userId")
    Long userId
    @JsonProperty
    String firstName
    @JsonProperty
    String lastName
    @JsonProperty("email")
    String email
    @JsonIgnore
    String password
    @JsonProperty
    List<String> phones

    User(Long userId, String firstName, String lastName, String email, String password, List<String> phones){
        this.userId = userId
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
        this.phones = phones
    }

    User(){}

}
