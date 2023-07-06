package tests;

import manager.DriverManager;
import org.testng.annotations.Test;

public class GridCrossBrowser {
    DriverManager driverManager = new DriverManager();
    @Test
    void test_01(){driverManager.setUpChromeDriver().get("https://www.amazon.com");}
    @Test void test_02(){driverManager.setUpEdgeDriver().get("https://www.amazon.com");}
    @Test void test_03(){driverManager.setUpFirefoxDriver().get("https://www.amazon.com");}
    @Test void test_05(){driverManager.setUpChromeDriver().get("https://www.wisequarter.com");}
    @Test void test_06(){driverManager.setUpEdgeDriver().get("https://www.wisequarter.com");}
    @Test void test_07(){driverManager.setUpFirefoxDriver().get("https://www.wisequarter.com");}
}
