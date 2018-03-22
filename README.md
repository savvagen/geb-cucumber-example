# GEB framework example


# How does tests look like?

This framework gives you absolute freedom for writing tests on any pattern that you know:

* Standard geb pattern:
```
@Test
    void searchTestExample() {
        drive {
            to SearchPage
            search("Selenium Webdriver")
            assert searchResults.results.size() > 2
            assert searchResults.get(1).title.text() == "Selenium WebDriver"
            assert searchResults.resultsList.each { element -> element.displayed }
            assert searchResults.results.each { element -> element.title.text().contains("Selenium") }
        }
    }
```
* Selenide pattern:
```

    @Test
    void searchTestExample4() {
        searchPage.open().search("Selenium WebDriver")
        assertEquals(searchPage.searchResults.results[0].title.text(), 'Selenium WebDriver')
        assertEquals(searchPage.searchResults.results.size(), 10)
    }
    
    ...
    
    @Test
    void searchTestExample() {
        def results = searchPage.open().search("Selenium WebDriver")
        assert results.get(1).title.text() == "Selenium WebDriver"
        assert results.results[0].title.text() == 'Selenium WebDriver'
        assert results.results.size() == 10
        assert results.resultsCollection.each { element -> element.displayed }
        assert results.results.each { element -> element.title.text().contains("Selenium") }
    }

```
* BDD pattern:

``` 
 def "user can search"(){
         given:
         "Go to the Search page"
         to SearchPage
         reportInfo "Some information I want to show in the report"
 
         when:
         "Search for: Selenium Webdriver"
         search("Selenium WebDriver")
 
         then:
         "verify results results"
         //We can write conditions without assert
         title == "Selenium WebDriver - Пошук Google"
         searchResults.results.size() <= 10
         searchResults.get(1).title.text() == "Selenium WebDriver"
         searchResults.resultsList.each {element -> element.displayed}
         searchResults.results.each {element -> element.title.text().contains("Selenium")}
     }
     
```


## How to run tests locally

1. Run the testing using command:
```
./gradlew test
``` 
2. Depends on the goals, we can specify the browser name in the command, to run the testing on specific browser:
``` 
./gradlew chromeTest 
```
or
```
./gradlew firefoxTest 

```

  ## How to run tests remotely
1. First - run the Selenoid locally (or remotely) folowing this [Selenoid example](https://github.com/savvagen/Selenoid-example)
2. Run the testing using command after running of the Selenoid:
``` 
./gradlew remoteTest

```

 ## Run Cucumber test

* Use command: ``` ./gradlew cucumber ```

 ## Run Spock tests

* To run the Spock tests on different browsers use commands:

For chrome
```
./gradlew chromeSpock
```

For firefox
``` 
./gradlew firefoxSpock
```

# Configurations

 Configure the framework typing following to `/src/test/resources/GebConfig.groovy` file:


```
// Use firefox or chrome as the default
driver = {
    WebDriverManager.chromedriver().setup()
    def driverInstance = new ChromeDriver()
    driverInstance.manage().window().maximize()
    driverInstance
}

waiting{
    timeout = 10
    includeCauseInMessage = true
    baseNavigatorWaiting = 10
}

baseUrl = "http:/"

reportsDir = "build/geb-reports"
reportOnTestFailureOnly = false
atCheckWaiting = true


environments {

    chrome {
        WebDriverManager.chromedriver().setup()
        driver = { 
            def driverInstance = new ChromeDriver()
            driverInstance.manage().window().maximize()
            driverInstance
        }
    }

    chromeHeadless {
        ChromeDriverManager.getInstance().setup()
        ChromeOptions options = new ChromeOptions()
        options.setExperimentalOption("prefs", prefs)
        options.addArguments("--headless")
        options.addArguments("window-size=1600,1200")
        driver = { 
            new ChromeDriver(options) 
        }
    }
    
    firefox {
            WebDriverManager.firefoxdriver().setup()
            driver = { 
                def driverInstance = new FirefoxDriver()
                driverInstance.manage().window().maximize()
                driverInstance
            }
        }

}

environments {

    remote {

        driver = {
            def remoteWebDriverServerUrl = new URL("http://localhost:4444/wd/hub")
            DesiredCapabilities dc = DesiredCapabilities.chrome()
            dc.setBrowserName("chrome")
            dc.setVersion("63.0")
            dc.setCapability("enableVNC", true)
            dc.setCapability("enableVideo", true)
            dc.setCapability("screenResolution", "1960x1280x24")
            dc.setCapability(CapabilityType.TAKES_SCREENSHOT, true)
            //More capabilities:
            // "videoName":"selenoid851e3f5f21e59a0eb5640b9c0c9a702c.mp4",
            // "videoScreenSize":"1960x1280",
            // "videoFrameRate":0,
            // "name":"",
            // "timeZone":""
            dc.setPlatform(Platform.LINUX)
            dc.setJavascriptEnabled(true)
            def driverInstance = new RemoteWebDriver(remoteWebDriverServerUrl, dc)
            driverInstance.manage().window().setSize(new Dimension(1920, 1080))
            driverInstance
        }
    }
}
```

## Links

 If you need more details about main futures and configurations switch to [presentation](https://docs.google.com/presentation/d/1p2zcIOuFeszPDvde9Tm0fuJCLygbeke0VkP1CpmufZE/edit?usp=sharing)

[![presentation](http://dl4.joxi.net/drive/2018/03/22/0028/3766/1896118/18/bb5c7ccbdf.jpg)](https://docs.google.com/presentation/d/1p2zcIOuFeszPDvde9Tm0fuJCLygbeke0VkP1CpmufZE/edit#slide=id.p)
