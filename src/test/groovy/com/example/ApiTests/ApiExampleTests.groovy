package com.example.ApiTests

import com.example.models.User
import com.example.models.UserStatus
import groovy.json.JsonBuilder
import groovyx.net.http.HTTPBuilder
import org.testng.annotations.Test

import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*


class ApiExampleTests {

    def http = new HTTPBuilder()


    @Test
    void getUser(){
        http.request("http://localhost:8087/users/1", GET, JSON){ request ->
            headers.Accept = 'application/json'
            response.success = { resp, json ->
                assert resp.statusLine.statusCode == 200
                assert json.userId == 1
                assert json.phones.size() == 2
                println "Got response: ${resp.statusLine}"
                println "Content-Type: ${resp.headers.'Content-Type'}"
                println new JsonBuilder(json)
                println json.userId
                println json.phones[0]
                User client = json.asType(User)
                println client.getEmail()
            }
        }
    }

    @Test
    void postUser() {
        http.request("http://localhost:8087/users/register", POST, JSON) { request ->
            List<String> phones = new ArrayList<>()
            phones.add("+380995475717")
            phones.add("+380995475718")
            body = new User(Long.valueOf("1"), "Test", "TestUser", "genchevskiy.test@gmail.com", "s.g19021992", phones)
            //body = ["userId":1,"firstName":"Test","lastName":"TestUser","email":"genchevskiy.test@gmail.com", "password": "s.g19021992", "phones":["+380995475717","+380995475718"]]
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
