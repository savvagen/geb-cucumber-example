package com.example.pages

import geb.Page
import io.qameta.allure.Step

class AccountPage extends BasePage {

    static atCheckWaiting = 10

    static url = "/myaccount.google.com/"

    static at = {
        title == "Мой аккаунт"
        header.accountButton.displayed
    }

    static content = {
        gmailButton(wait: 8) { $("a[data-track-as='Welcome Header Mail']")}
        searchButton(wait: 8) { $("a[data-track-as='Welcome Header Search']")}
    }

    @Step
    def goSearch(){
        searchButton.click()
        browser.at SearchPage
    }

    @Step
    GmailPage goMail(){
        gmailButton.click()
        browser.at new GmailPage(forEmail: header.getEmail())
    }

}
