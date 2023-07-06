package manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.ConfigReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManager {
    public static WebDriver driver;

    DesiredCapabilities capabilities = new DesiredCapabilities();//ctrl + imleç içeriği hakkında bilgi verir

    public WebDriver setUpChromeDriver() {//Method Başlangıcı
        //Şimdi yukarıda oluşturduğumuz capabilities objesinin içeriğini dolduruyoruz.
        capabilities.setPlatform(Platform.ANY);//Windows, win11 so on...
        capabilities.setBrowserName("Chrome");
        capabilities.setVersion("114.0.5735.199");//Chrome version
        ChromeOptions chromeOptions = new ChromeOptions();//ChromeOptions objesi oluşturup,
        chromeOptions.merge(capabilities);//yukarıdaki capabilities'i Chrome/Firefox'a vs. gömüyoruz
        try {
            driver = new RemoteWebDriver(new URL(ConfigReader.getProperty("hubUrl")), chromeOptions);// new değil
        } catch (MalformedURLException e) {                            //yukarıda oluşturduğumuz chromeOptions
            throw new RuntimeException(e);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        System.out.println("*****  SETUP CHROME DRIVER  *****");
        return driver;
    }

    public WebDriver setUpFirefoxDriver() {
        capabilities.setPlatform(Platform.ANY);
        capabilities.setBrowserName("Firefox");
        capabilities.setVersion(ConfigReader.getProperty("firefox_version"));
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.merge(capabilities);
        try {
            driver = new RemoteWebDriver(new URL(ConfigReader.getProperty("hubUrl")), firefoxOptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        System.out.println("*****  SETUP FIREFOX DRIVER  *****");
        return driver;
    }

    public WebDriver setUpEdgeDriver() {
        capabilities.setPlatform(Platform.WINDOWS);
        capabilities.setBrowserName("Edge");
        capabilities.setVersion("114.0.1823.67");
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.merge(capabilities);
        try {
            driver = new RemoteWebDriver(new URL(ConfigReader.getProperty("hubUrl")), edgeOptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        System.out.println("*****  SETUP EDGE DRIVER  *****");
        return driver;
    }

    public static void setDriver(String browser) {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
        System.setProperty("webdriver.msedge.driver", "src/test/resources/drivers/msedgedriver");

        switch (browser) {

            case "grid_firefox": {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                try {
                    driver = new RemoteWebDriver(new URL(ConfigReader.getProperty("hubUrl")), firefoxOptions);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("°°°°° Selenium Grid Firefox °°°°°°");
                break;
            }
            case "grid_chrome": {
                ChromeOptions chromeOptions = new ChromeOptions();
                try {//RemoteWebDriver bir CLASS, SELENIUM GRID'den geliyor.
                    driver = new RemoteWebDriver(new URL(ConfigReader.getProperty("hubUrl")), chromeOptions);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("°°°°° Selenium Grid Chrome °°°°°°");
                break;
            }
            case "grid_edge": {
                EdgeOptions edgeOptions = new EdgeOptions();
                try {
                    driver = new RemoteWebDriver(new URL(ConfigReader.getProperty("hubUrl")), edgeOptions);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("°°°°° Selenium Grid Edge °°°°°°");
                break;
            }

            case "chrome": {// WebDriverManager bir INTERFACE, SELENIUM'dan geliyor.
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                System.out.println("°°°°° Chrome WebDriver °°°°°°");
                break;
            }
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                System.out.println("°°°°° Firefox WebDriver °°°°°°");
                break;
            }
            case "edge": {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                System.out.println("°°°°° Edge WebDriver °°°°°°");
                break;
            }
            default: {
                WebDriverManager.chromedriver().setup();
                System.out.println("°°°°° Default Chrome WebDriver °°°°°°");
                driver = new ChromeDriver();
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

    }

    public static void closeDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
