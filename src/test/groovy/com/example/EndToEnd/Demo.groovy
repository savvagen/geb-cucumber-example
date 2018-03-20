package com.example.EndToEnd

import com.example.TestBase
import org.testng.annotations.Test

import static geb.Browser.drive
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.equalTo
import static org.openqa.selenium.Keys.CONTROL
import static org.openqa.selenium.Keys.ENTER
import static org.openqa.selenium.Keys.LEFT_CONTROL
import static org.openqa.selenium.Keys.chord

class Demo extends TestBase {

    @Test
    void searchDemo(){
        drive {
            go 'https://google.com'
            waitFor {title == "Google" }
            $('form', name: 'f').with {
                q = "selenium webdriver" << ENTER
                //btnK().click()
            }
            waitFor{ $(".srg .g", 1).displayed }
            assert $(".srg .g").size() == 10
            assert title.contains("selenium webdriver")
            assert $(".srg .g")[0].$("h3 > a").text() == "Selenium WebDriver"
            println $(".srg .g h3 > a").filter(text: ~/[sS]elenium [Ww]eb[dD]river/)*.text()
            //Collections of elements
            println $(".srg .g h3").eq(0).text()
            println find(".srg .g h3").getAt(1).$("a").text()
            println find(".srg .g h3")[2].$("a").text()
            println find(".srg .g h3").findAll().get(3).$("a").text()

            println "###############"
            println find(".srg .g h3").findAll {
                println it.$("a").text()
            }
            println "###############"
            println find(".srg .g h3").findAll {
                el -> el.text().contains("kreisfahrer")
            }.each {
                println it.$("a").text()
            }
        }
    }


    @Test
    void chalangedTest(){
        go "https://google.com"
        $("input", name: 'q').value "Selenium WebDriver"
        $("input", name: 'q') << chord(CONTROL, 'a') << chord(CONTROL, "c")
        browser.getDriver().navigate().refresh()
        waitFor {$("input", name: 'q').value() == ""}
        $("input", name: 'q') << chord(LEFT_CONTROL, "v") << ENTER
        waitFor{ $(".srg .g", 1).displayed }
        assertThat($(".srg .g").size(), equalTo(10))
    }



}
