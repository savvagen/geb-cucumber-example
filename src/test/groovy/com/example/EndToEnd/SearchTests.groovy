package com.example.EndToEnd

import com.example.TestBase
import com.example.listeners.TestListener
import com.example.pages.SearchPage
import geb.Browser
import org.testng.annotations.Listeners
import org.testng.annotations.Test

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.is
import static org.testng.Assert.assertEquals
import static org.testng.Assert.assertEquals


@Listeners(TestListener.class)
class SearchTests  extends TestBase {

    /*@Test
    void testExample(){
        to SearchPage
        pElement("q") << "selenium webdriver" << Keys.chord(Keys.CONTROL, "a") << Keys.chord(Keys.CONTROL, "c")
        assert pElement("q").value() == "selenium webdriver"
        to SearchPage
        pElement("q") << Keys.chord(Keys.CONTROL, "v") << Keys.ENTER
        waitFor{
            browser.$(".srg .g", 1).displayed
        }
        println browser.$(".srg .g").size()
        println browser.$(".srg .g h3 > a", 0..2)*.text()
        println browser.$(".srg .g h3 > a", 0..1)*.height
        assert browser.$(".srg .g h3 > a", 0..1)*.text() == (["Selenium WebDriver", "Selenium WebDriver — Selenium Documentation"])
    }*/


    @Test
    void searchTestExample1() {
        Browser.drive {
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