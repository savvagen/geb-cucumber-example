package com.example.modules

import com.example.pages.LoginPage
import geb.Module
import io.qameta.allure.Step

class Header extends Module {

    String email = "genchevskiy.test@gmail.com"

    static content = {
        accountButton(wait: 10) { $("a", title: contains("($email)")) ?: $("a[aria-label*='($email)']") }
        submitLogout(wait: 5){ $("a", text: "Выйти").click() }
    }


    @Step("Logout")
    LoginPage logOut(){
        accountButton.click()
        submitLogout
        browser.isAt LoginPage
    }

}
