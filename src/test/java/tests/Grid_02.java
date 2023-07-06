package tests;

import org.testng.annotations.*;
import static manager.DriverManager.*;


public class Grid_02 {

    @Parameters("browser")
    @BeforeTest
    void beforeTest(@Optional("browser") String browser){
        setDriver("grid_chrome");//Buraya daha önce tanımladığımız herhangi bir browser'ı yazabiliriz.
//Sadece firefox yazarsak o da çalışıyor.
    }

    @Test
    void test01(){
        driver.get("https://www.wisequarter.com");
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());
    }
    @AfterTest
    void afterTest(){
        closeDriver();
    }
}
