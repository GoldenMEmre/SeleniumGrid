package tests;

import manager.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Grid_01 {

    DriverManager driverManager = new DriverManager();

    static WebDriver driver;

    public static void main(String[] args) throws MalformedURLException {

        driver = new RemoteWebDriver(new URL("http://192.168.1.149:4444"), new ChromeOptions());
        driver.get("https://www.google.com");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
    }

    @Test
    void remoteChromeDriverTest() {
        driver = driverManager.setUpChromeDriver();//Satır 14'de objesini oluşturup manager/DriverManager
        driver.get("https://www.amazon.com");//altında (satır 14-37) oluşturduğumuz ChromDriver'a ulaşıyoruz.
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
    }
    @Test
    void remoteFirefoxDriverTest(){
        driver = driverManager.setUpFirefoxDriver();
        driver.get("https://www.youtube.com");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
    }
    @Test
    void remoteEdgeDriverTest(){
        driver = driverManager.setUpEdgeDriver();
        driver.get("https://www.wisequarter.com");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
    }
}
