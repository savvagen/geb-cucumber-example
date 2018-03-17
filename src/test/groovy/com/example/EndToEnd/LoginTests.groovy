package com.example.EndToEnd

import com.example.TestBase
import com.example.listeners.TestListener
import com.example.pages.AccountPage
import com.example.pages.GmailPage
import com.example.pages.LoginPage
import geb.Browser
import io.qameta.allure.Flaky
import org.testng.annotations.AfterMethod
import org.testng.annotations.Listeners
import org.testng.annotations.Test

@Listeners(TestListener.class)
class LoginTests extends TestBase {


    @AfterMethod
    void tearDownTest() {
        /*if (accountPage.accountButton.displayed){
            accountPage.logOut()
        }*/
        go "https://accounts.google.com/Logout"
        browser.getDriver().manage().deleteAllCookies()

    }



    @Test
    void demoLogin(){
        to LoginPage
        $("form").identifier = "genchevskiy.test@gmail.com" << Keys.ENTER
        waitFor { $("form").password().displayed }
        $("form").password = "s.g19021992" << Keys.ENTER
        isAt AccountPage
        assert driver.currentUrl.contains('https://myaccount.google.com/')
    }


    @Test
    void loginTestExample() {
        to LoginPage
        loginWith(testUser.getEmail(), testUser.getPassword())
        isAt AccountPage
        assert accountButton.displayed
    }

    @Test
    void loginTestExample1() {
        to LoginPage
        loginAs(testUser)
        isAt AccountPage
        assert accountButton.isDisplayed()
    }

    @Test
    void loginTestExample2() {
        Browser.drive {
            to LoginPage
            login(testUser.getEmail(), testUser.getPassword())
            isAt AccountPage
            assert accountButton.isDisplayed()
        }
    }

    @Test
    void loginTestExample3() {
        to LoginPage
        login(testUser.getEmail(), testUser.getPassword())
        isAt AccountPage
        assert accountButton.displayed
    }

    @Test
    void loginTestExample4() {
        LoginPage loginPage = browser.to LoginPage
        AccountPage accountPage = loginPage.loginAs(testUser)
        assert accountPage.accountButton.isDisplayed()
    }

    @Test
    void loginTestExample5() {
        AccountPage accountPage = loginPage.open().loginAs(testUser)
        assert accountPage.accountButton.isDisplayed()
    }

    @Flaky
    @Test
    void loginTestExample6() {
        loginPage.open().loginAs(testUser)
        assert accountPage.accountButton.isDisplayed()

    }


}