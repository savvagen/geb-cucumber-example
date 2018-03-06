package com.example.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class UserStatus {

    @JsonProperty
    User user
    @JsonProperty
    String status
    @JsonProperty
    String message

    UserStatus(User user, String status, String message){
        this.user = user
        this.status = status
        this.message = message
    }

    UserStatus(){
    }

}


