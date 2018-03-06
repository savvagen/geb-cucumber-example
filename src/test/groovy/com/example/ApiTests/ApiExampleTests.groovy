package com.example.ApiTests

import com.example.models.User
import com.example.models.UserStatus
import groovy.json.JsonBuilder
import groovyx.net.http.HTTPBuilder
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*


class ApiExampleTests {

    def http = new HTTPBuilder()

    User user
    String baseUri = "http://localhost:8087"

    @BeforeClass
    void setUpTests(){
        List<String> phones = new ArrayList<>()
        phones.add("+380995475717")
        phones.add("+380995475718")
        user = new User(Long.valueOf(1), "Test",
                "TestUser",
                "genchevskiy.test@gmail.com",
                "s.g19021992", phones)
    }


    @Test
    void getUser(){
        http.request("$baseUri/users/1", GET, JSON){ request ->
            headers.Accept = JSON
            response.success = { resp, json ->
                assert resp.statusLine.statusCode == 200
                assert json.userId == 1
                assert json.phones.size() == 2
                println "Got response: ${resp.statusLine}"
                println "Content-Type: ${resp.headers.'Content-Type'}"
                println new JsonBuilder(json).toPrettyString()
                println json.userId
                println json.phones[0]
                User client = json.asType(User)
                println client.getEmail()
            }
        }
    }

    @Test
    void postUser() {
        http.request("$baseUri/users/register", POST, JSON) { request ->
            body = user
            response.success = { resp, json ->
                assert resp.statusLine.statusCode == 200
                assert json.status == "success"
                assert json.message == "User have been registered successfully!"
                println "Got response: ${resp.statusLine}"
                println "Content-Type: ${resp.headers.'Content-Type'}"
                println new JsonBuilder(json).toPrettyString()
                def status = (json as UserStatus)
                println status.status
                println status.message
            }
        }

    }
}
