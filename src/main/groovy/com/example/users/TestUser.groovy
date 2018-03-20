package com.example.users

import jdk.nashorn.internal.ir.annotations.Immutable

@Immutable
class TestUser {

    String email
    String password

    TestUser(String email = "genchevskiy.test@gmail.com", String password = "s.g19021992"){
        this.email = email
        this.password = password
    }


}
