package com.example.pages

import geb.Page
import io.qameta.allure.Step
import com.example.users.User
import org.openqa.selenium.Keys

class LoginPage extends Page {

    static atCheckWaiting = 10

    static url = "/accounts.google.com/signin/v2/identifier"

    static content = {
        emailField(required: true, wait: 8) { $("input[type='email']") }
        passwordField(required: true, wait: 8) { $('input[type="password"]') }
        passwordSubmit { $("#passwordNext").click() }
        accountsForm { $("form[role='presentation']")}
        //Another implementation
        loginField {$("form").identifier()}
        passFieldd {$("form").password()}

    }

    static at = {
        title == "Sign in - Google Accounts"
        //emailField.displayed || passwordField.displayed
    }

    @Step('Authorize with credentials')
    AccountPage login(String email, String password){
        emailField << email << Keys.ENTER
        waitFor {passwordField.displayed}
        passwordField.value(password)
        passwordSubmit
        browser.at(AccountPage)
    }

    //Another implementation
    AccountPage loginWith(String email, String pass){
        loginField = email << Keys.ENTER
        waitFor {passwordField.displayed}
        passFieldd = pass << Keys.ENTER
        browser.page AccountPage
    }


    // Fluent object realization

    @Step("Open Login page")
    LoginPage open(){
        browser.go url
        browser.at LoginPage
    }

    @Step("Login with credentials: {user.email} / {user.password} ")
    AccountPage loginAs(User user){
        emailField << user.getEmail() << Keys.ENTER
        waitFor {passwordField.displayed}
        passwordField << user.getPassword() << Keys.ENTER
        browser.at AccountPage
    }


}
