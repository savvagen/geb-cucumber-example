import io.github.bonigarcia.wdm.ChromeDriverManager
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.Dimension
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver

// Use firefox as the default
// See: http://code.google.com/p/selenium/wiki/FirefoxDriver

driver = {
    //new FirefoxDriver()
    new ChromeDriver()
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
        //options.addArguments("no-sandbox")
        options.addArguments("--headless")
        options.addArguments("use-fake-device-for-media-stream")
        options.addArguments("use-fake-ui-for-media-stream")
        options.addArguments("--disable-notifications")
        options.addArguments("--lang=es")
        options.addArguments("--disable-confirmations")
        options.addArguments("disable-infobars")
        options.addArguments("window-size=1600,1200")
        driver = { new ChromeDriver(options) }
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


waiting{
    timeout = 10
    includeCauseInMessage = true
    baseNavigatorWaiting = 10
}

baseUrl = "http:/"

reportsDir = "build/geb-reports"
reportOnTestFailureOnly = false
atCheckWaiting = true

