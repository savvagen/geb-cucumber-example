package steps
import com.example.pages.*
import static cucumber.api.groovy.EN.*


Given(~/User go to login page/){ ->
    to LoginPage
    at LoginPage
}

When(~/User login with email: '(.*)' and password: '(.*)'/){String email, String password ->
    page.loginWith(email, password)
}


Then(~/User is at account page/){ ->
    at AccountPage
    assert page.header.accountButton.displayed
}