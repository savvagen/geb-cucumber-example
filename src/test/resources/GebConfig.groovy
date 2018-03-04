import io.github.bonigarcia.wdm.ChromeDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.DesiredCapabilities

// Use firefox as the default
// See: http://code.google.com/p/selenium/wiki/FirefoxDriver
driver = {
    new FirefoxDriver()
}


environments {

    // run as “gradle -Dgeb.env=chrome cucumber”
    // See: http://code.google.com/p/selenium/wiki/ChromeDriver
    chrome {
        ChromeOptions options = new ChromeOptions()
        Map<String, Object> prefs = new HashMap<String, Object>()
        prefs.put("credentials_enable_service", false)
        prefs.put("profile.password_manager_enabled", false)
        options.setExperimentalOption("prefs", prefs)
        options.addArguments("use-fake-device-for-media-stream")
        options.addArguments("use-fake-ui-for-media-stream")
        options.addArguments("--disable-notifications")
        options.addArguments("--lang=es")
        options.addArguments("--disable-confirmations")
        options.addArguments("disable-infobars")
        options.addArguments("window-size=1600,1200")
        driver = { //new ChromeDriver()
            def driverInstance = new ChromeDriver(options)
            driverInstance.manage().window().maximize()
            driverInstance
        }
    }

    chromeHeadless {
        ChromeOptions options = new ChromeOptions()
        Map<String, Object> prefs = new HashMap<String, Object>()
        prefs.put("credentials_enable_service", false)
        prefs.put("profile.password_manager_enabled", false)
        options.setExperimentalOption("prefs", prefs)
        options.addArguments("use-fake-device-for-media-stream")
        options.addArguments("use-fake-ui-for-media-stream")
        options.addArguments("--disable-notifications")
        options.addArguments("--lang=es")
        options.addArguments("--disable-confirmations")
        options.addArguments("disable-infobars")
        options.addArguments("window-size=1600,1200")

        DesiredCapabilities capabilities = DesiredCapabilities.chrome()
        capabilities.setCapability(ChromeOptions.CAPABILITY, options)
        driver = { new ChromeDriver(capabilities: capabilities) }
    }

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
baseNavigatorWaiting = true

