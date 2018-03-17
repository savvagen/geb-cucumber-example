package com.example.pages

import geb.Page
import com.example.modules.MessageForm
import com.example.modules.Message
import io.qameta.allure.Step
import org.openqa.selenium.By

class GmailPage extends Page {

    String forEmail
    def userEmail = forEmail ?: "genchevskiy.test@gmail.com"

    static url = "/mail.google.com/mail/u/0/#inbox"

    static atCheckWaiting = 10

    static at = {

        title.contains(" - ${userEmail} - Gmail")
        accountButton.displayed
        newMessageButton.displayed
    }

    static content = {
        accountButton(wait: 10) { $("a", title: contains("($userEmail)")) }
        submitLogout(wait: 5){ $("a", text: "Выйти").click() }
        newMessageButton(wait: 5) { $(By.xpath('//div[contains(text(), "НАПИСАТЬ")]')) }
        messageForm(wait: 8){ module(MessageForm) }
        successMessage {$(By.xpath("/html/body/div[7]/div[3]/div/div[1]/div[5]/div[1]/div/div[3]/div/div/div[2]"))}
        //Create list of messages modules
        messagesContainer { $(By.xpath('//div[@role="main"]/div[6]/div/div[1]/div[2]/div/table/tbody')) }
        messagesCollection(wait: 5) { messagesContainer.find("tr").findAll()}
        messages(wait: 10, required: true) { messagesContainer.find('tr').moduleList(Message)}
    }

    @Step("Open Main page")
    GmailPage open(){
        browser.go url
        browser.at GmailPage
    }

    @Step("Logout")
    LoginPage logOut(){
        accountButton.click()
        submitLogout
        browser.at LoginPage
    }

    @Step("Press \"New Message Button\"")
    MessageForm writeMessage(){
        newMessageButton.click()
        waitFor {messageForm.subjectField.displayed}
        module(MessageForm)
    }



}
