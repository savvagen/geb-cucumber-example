package steps

import com.example.pages.*
import static cucumber.api.groovy.EN.*


Given(~/User go to search page/){ ->
    to SearchPage
}

Then(~/User is at search page/){->
    at SearchPage
}

When(~/User search for '(.*)'/){String text ->
    page.search(text)
}

Then(~/First result contains text '(.*)'/){ String text ->
    assert page.searchResults.resultsList.each {element -> element.displayed}
    assert page.searchResults.get(1).title.text() == "$text"
}

Then(~/All results contains text '(.*)'/){ String text ->
    assert page.searchResults.resultsList.each {element -> element.displayed}
    assert page.searchResults.results.each {element -> element.title.text().contains(text)}
}

Then(~/Number of results is '(.*)'/){ Integer number ->
    assert page.searchResults.results.size() == number
}