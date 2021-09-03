package com.telran.oscar.tests;

import com.telran.oscar.helpers.MyListener;
import com.telran.oscar.helpers.MyListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {

    protected EventFiringWebDriver driver;

    @BeforeMethod
    public void setUp(){

        // ChromeOptions options = new ChromeOptions();
        // options.addArguments("headless");
        //  options.addArguments("window-size=1200x800");
//        if (browser.equals(BrowserType.CHROME)) {
       // driver = new ChromeDriver();
//        }else if (browser.equals(BrowserType.FIREFOX)){
//            driver = new FirefoxDriver(options);
        //       }
        // driver.manage().window().maximize();
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new MyListener());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://selenium1py.pythonanywhere.com/");

    }

    @AfterMethod(enabled = true)
    public void tearDown(ITestResult result){

        driver.quit();

    }

}
