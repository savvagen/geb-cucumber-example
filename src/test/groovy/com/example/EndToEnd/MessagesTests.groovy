package com.example.EndToEnd

import com.example.TestBase
import com.example.listeners.TestListener
import com.example.pages.AccountPage
import com.example.pages.GmailPage
import com.example.pages.LoginPage
import com.example.users.TestUser
import geb.Browser
import org.testng.annotations.AfterMethod
import org.testng.annotations.Listeners
import org.testng.annotations.Test
import static org.testng.Assert.*


@Listeners(TestListener.class)
class MessagesTests extends TestBase{



    @AfterMethod
    void tearDownTest(){
        go "https://accounts.google.com/Logout"
        browser.getDriver().manage().deleteAllCookies()
    }




    @Test
    void messageTest1() {
        Browser.drive {
            to LoginPage
            loginAs(testUser).goMail()
            isAt new GmailPage(forEmail: testUser.getEmail())
            writeMessage().withAttachment(testUser.getEmail(),
                    'Test Message',
                    "Hello Savva",
                    "src/main/groovy/com/example/data/HelloWorld.txt")
            waitFor {
                successMessage.displayed
                successMessage.text() == "Письмо отправлено. Просмотреть сообщение"
            }
            getDriver().navigate().refresh()
            assert messages.get(0).subject.text() == "Test Message"
            assert messages[0].messageStart.text().contains("Hello Savva")
        }
    }


    @Test
    void messageTest2(){
        to LoginPage
        login(testUser.getEmail(), testUser.getPassword())
        isAt AccountPage
        to GmailPage
        writeMessage().with(testUser.getEmail(), 'Test Message', "Hello Savva")
        waitFor {
            successMessage.displayed
            successMessage.text() == "Письмо отправлено. Просмотреть сообщение"
        }
        getDriver().navigate().refresh()
        assert header.accountButton.displayed
        assert messages.get(0).subject.text() == "Test Message"
        assert messages[0].messageStart.text().contains("Hello Savva")
    }



    @Test
    void messageTest3(){
        TestUser user = new TestUser()
        def gmailPage = to(new LoginPage()).loginAs(user)
                .goMail()
                .writeMessage()
                .with(user.getEmail(), 'Test Message', "Hello Savva")
        browser.waitFor{
            gmailPage.successMessage.displayed
            gmailPage.successMessage.text() == "Письмо отправлено. Просмотреть сообщение"
        }
        browser.getDriver().navigate().refresh()
        assert gmailPage.messages.get(0).subject.text() == "Test Message"
        assert gmailPage.messages[0].messageStart.text().contains("Hello Savva")
    }


    @Test
    void messageTest4(){
        TestUser user = new TestUser()
        loginPage.open().loginAs(user)
                .goMail()
                .writeMessage()
                .with(user.getEmail(), 'Test Message', "Hello Savva")
        browser.waitFor{
            gmailPage.successMessage.displayed
            gmailPage.successMessage.text() == "Письмо отправлено. Просмотреть сообщение"
        }
        browser.getDriver().navigate().refresh()
        assert gmailPage.header.accountButton.displayed
        assert gmailPage.messages.get(0).subject.text() == "Test Message"
        assert gmailPage.messages[0].messageStart.text().contains("Hello Savva")
    }


    @Test
    void messageTestWithFileUpload(){
        TestUser user = new TestUser()
        loginPage.open().loginAs(user)
                .goMail()
                .writeMessage()
                .withAttachment(user.getEmail(),
                'Test Message',
                "Hello Savva",
                "src/main/groovy/com/example/data/HelloWorld.txt")
        browser.waitFor{
            gmailPage.successMessage.displayed
            gmailPage.successMessage.text() == "Письмо отправлено. Просмотреть сообщение"
        }
        browser.getDriver().navigate().refresh()
        assert gmailPage.messages.get(0).subject.text() == "Test Message"
        assert gmailPage.messages[0].messageStart.text().contains("Hello Savva")
    }


}