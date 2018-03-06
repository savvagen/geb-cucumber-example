package com.example.SpockTest

import com.example.pages.AccountPage
import com.example.pages.GmailPage
import com.example.pages.LoginPage
import com.example.pages.SearchPage
import com.example.users.TestUser
import geb.spock.GebSpec
import groovy.util.logging.Slf4j


class AcceptanceTests extends GebSpec {


    static TestUser user

    def setupSpec() {
        user = new TestUser()
        println "Importing user: ${user.getEmail()} / ${user.getPassword()}"
        println 'base setupSpec()'
    }

    def cleanupSpec() {
        println 'base cleanupSpec()'
    }

    def setup() {
        println 'base setup()'
    }

    def cleanup() {
        println 'base cleanup()'
        go "https://accounts.google.com/Logout"
        driver.manage().deleteAllCookies()
    }




    def "user can search"(){
        given:
        "Go to the Search page"
        to SearchPage

        when:
        "Search for: Selenium Webdriver"
        search("Selenium WebDriver")

        then:
        "verify results results"
        //We can write conditions without assert
        title == "Selenium WebDriver - Пошук Google"
        searchResults.results.size() <= 10
        searchResults.get(1).title.text() == "Selenium WebDriver"
        searchResults.resultsList.each {element -> element.displayed}
        searchResults.results.each {element -> element.title.text().contains("Selenium")}
    }



    def "user can make authorization"(){
        given:
        "go to Login page"
        to LoginPage

        when:
        "login as test user"
        loginAs(user)

        then :
        "user should be at Account page"
        isAt AccountPage

        and:
        "account button should be visible"
        accountButton.displayed
    }



    def "user can send the message"(){
        given:
        to LoginPage
        loginAs(user)
        to(new GmailPage(forEmail: user.getEmail()))

        when:
        writeMessage().withAttachment(user.getEmail(),
                'Test Message',
                "Hello Savva",
                "src/main/groovy/com/example/data/HelloWorld.txt")

        then:
        waitFor { successMessage.displayed }
        successMessage.text() == "Письмо отправлено. Просмотреть сообщение"

        then:
        getDriver().navigate().refresh()
        messages.get(0).subject.text() == "Test Message"
        messages[0].messageStart.text().contains("Hello Savva")
    }


}
