package com.example.EndToEnd

import com.example.TestBase
import com.example.listeners.TestListener
import com.example.pages.SearchPage
import geb.Browser
import org.openqa.selenium.Keys
import org.testng.annotations.Listeners
import org.testng.annotations.Test

import static geb.Browser.*
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.is
import static org.openqa.selenium.Keys.*
import static org.testng.Assert.assertEquals



@Listeners(TestListener.class)
class SearchTests  extends TestBase {



    @Test
    void testExample(){
        to SearchPage
        $("input", name: 'q') << "selenium webdriver" << ENTER
        isAt SearchPage
        waitFor{
            $(".srg .g", 1).displayed
        }
        assert $(".srg .g").size() == 10
        assert title.contains("selenium webdriver")
        assert $(".srg .g")*.each {element -> element.displayed}
        assert $(".srg .g h3 > a").each { element -> element.text().contains("Selenium") }
        assert $(".srg .g h3 > a", 0..1)*.text() == (["Selenium WebDriver", "Selenium WebDriver — Selenium Documentation"])
    }



    @Test
    void searchTestExample1() {
        drive {
            to SearchPage
            search("Selenium Webdriver")
            assert searchResults.results.size() > 2
            assert searchResults.get(1).title.text() == "Selenium WebDriver"
            assert searchResults.resultsList.each { element -> element.displayed }
            assert searchResults.results.each { element -> element.title.text().contains("Selenium") }
        }
    }

    @Test
    void searchTestExample2() {
        to SearchPage
        search("Selenium Webdriver")
        assert searchResults.results.size() > 2
        assert searchResults.get(1).title.text() == "Selenium WebDriver"
        assert searchResults.resultsList.each { element -> element.displayed }
        assert searchResults.results.each { element -> element.title.text().contains("Selenium") }
    }

    @Test
    void searchTestExample3() {
        def results = searchPage.open().search("Selenium WebDriver")
        assert results.get(1).title.text() == "Selenium WebDriver"
        assert results.results[0].title.text() == 'Selenium WebDriver'
        assert results.results.size() == 10
        assert results.resultsCollection.each { element -> element.displayed }
        assert results.results.each { element -> element.title.text().contains("Selenium") }
    }

    @Test
    void searchTestExample4() {
        searchPage.open().search("Selenium WebDriver")
        assertEquals(searchPage.searchResults.results[0].title.text(), 'Selenium WebDriver')
        assertEquals(searchPage.searchResults.results.size(), 10)
    }

    @Test
    void searchTestExample5() {
        searchPage.open().search("Selenium WebDriver")
        assertThat(searchPage.searchResults.get(1).title.text(), equalTo('Selenium WebDriver'))
        assertThat(searchPage.searchResults.results.size(), is(10))
    }


}