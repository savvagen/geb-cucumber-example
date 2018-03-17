package com.example.pages

import geb.Page
import io.qameta.allure.Step
import com.example.modules.SearchResults
import org.openqa.selenium.By
import org.openqa.selenium.Keys

class SearchPage extends Page{

    static atCheckWaiting = 10

    static at = {
        title == "Google"
        searchField.displayed
    }

    static url = "/google.com"

    static content = {
        searchField(wait: 8, required: true) { $(By.name("q"))}
        searchResults{ module(SearchResults)}
        //Creating elements with parameter
        pElement {pName -> $("input", name: pName)}
        pTitle1 {pIndex -> $(".srg .g h3 > a", pIndex)}
        pTitle2 {pIndex -> $(".srg .g:nth-child(${pIndex}) h3 > a") }
        pTitle3 {pIndex -> $(By.xpath("//div[@class='srg']/g[${pIndex}]//h3/a")) }
    }

    @Step
    SearchPage open(){
        browser.go url
        browser.at SearchPage
    }

    @Step
    SearchResults search(String searchQuery){
        searchField.value(searchQuery) << Keys.ENTER
        return module(SearchResults)
    }

}



