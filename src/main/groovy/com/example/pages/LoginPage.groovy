package com.example.pages

import geb.Page
import io.qameta.allure.Step
import com.example.users.TestUser
import org.openqa.selenium.Keys

class LoginPage extends Page {

    static atCheckWaiting = 10

    static url = "/accounts.google.com/signin/v2/identifier"

    static content = {
        emailField(required: true, wait: 8) { $("input[type='email']") }
        passwordField(required: true, wait: 8) { $('input[type="password"]') }
        passwordSubmit { $("#passwordNext").click() }
        accountsForm { $("form[role='presentation']")}
        //2-nd implementation
        loginField { $("form").identifier() }
        passField {$("form").password()}

    }

    static at = {
        title == "Sign in - Google Accounts"
        //emailField.displayed || passwordField.displayed
    }

    //Fluent Page Objects

    @Step("Open Login page")
    LoginPage open(){
        browser.go url
        browser.at LoginPage
    }

    @Step("Login with credentials: {user.email} / {user.password} ")
    AccountPage loginAs(TestUser user){
        emailField << user.getEmail() << Keys.ENTER
        waitFor {passwordField.displayed}
        passwordField.value(user.getPassword())
        passwordSubmit
        browser.at(AccountPage)
    }

    //2-nd implementation
    @Step('Authorize with credentials')
    def loginWith(String email, String pass){
        loginField = email << Keys.ENTER
        waitFor {passwordField.displayed}
        passField = pass << Keys.ENTER
        browser.at AccountPage
    }

    //3-d implementation
    @Step('Authorize with credentials')
    def login(String email, String pass){
        emailField << email << Keys.ENTER
        waitFor {passwordField.displayed}
        passwordField << pass << Keys.ENTER
        browser.page AccountPage
        // You need to add "isAt Page" method after execution of this type of function
    }


    // Void Page Objects

    @Step
    void loginWithCreds(String email, String password){
        emailField << email << Keys.ENTER
        waitFor {passwordField.displayed}
        passwordField << password << Keys.ENTER
    }


}
