package com.example

import com.example.pages.AccountPage
import com.example.pages.GmailPage
import com.example.pages.LoginPage
import com.example.pages.SearchPage
import com.example.users.TestUser
import geb.Browser
import geb.testng.GebTestTrait
import io.github.bonigarcia.wdm.ChromeDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass

class TestBase implements GebTestTrait{

    SearchPage searchPage
    LoginPage loginPage
    GmailPage gmailPage
    AccountPage accountPage
    Browser browser
    TestUser testUser


    @BeforeClass
    void setUp(){
        testUser = new TestUser()

        //Create browser instance: variant-1
        //ChromeDriverManager.getInstance().setup()
        //browser = new Browser(driver: new ChromeDriver())
        //browser.driver.manage().window().maximize()


        //Create browser instance: variant-2
        //browser = new Browser()
        //browser.driver = new ChromeDriver()


        browser = new Browser()
        searchPage = new SearchPage().init(browser)
        loginPage = new LoginPage().init(browser)
        gmailPage = new GmailPage(forEmail: testUser.getEmail()).init(browser)
        accountPage = new AccountPage().init(browser)

        //Manual configuration
        //browser.config.baseUrl = "http://"
        //browser.config.baseNavigatorWaiting = 10

    }

    @AfterClass
    void tearDown(){
        //browser.quit()
    }


}