package steps

import com.example.pages.*
import static cucumber.api.groovy.EN.*


Given(~/User go to Gmail page of user '(.*)'/){ String userEmail ->
    to new GmailPage(forEmail: userEmail)
}

Then(~/User is at gmail page of user '(.*)'/){ String userEmail ->
    at new GmailPage(forEmail: userEmail)
}

When(~/User write message to '(.*)', with subject '(.*)', with body '(.*)' and file '(.*)'/){
    String to, String subject, String message, String filePath ->
        page.writeMessage().withAttachment(to, subject, message, filePath)
        waitFor {
            page.successMessage.displayed
            page.successMessage.text() == "Письмо отправлено. Просмотреть сообщение"
        }
}

When(~/Refreshing the page/){ ->
    page.browser.getDriver().navigate().refresh()

}

Then(~/Message with number '(.*)' have subject '(.*)'/){ Integer number, String text ->
    assert page.messages.get(number-1).subject.text() == text
}


Then(~/Message with number '(.*)' contains text '(.*)'/){ Integer number, String text ->
    assert page.messages.get(number-1).messageStart.text().contains(text)
}