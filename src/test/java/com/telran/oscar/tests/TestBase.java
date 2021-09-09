package com.telran.oscar.tests;

import com.telran.oscar.helpers.MyListener;
import com.telran.oscar.helpers.MyListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class TestBase {

    protected EventFiringWebDriver driver;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
   public void setUp(String browser){
    //public void setUp(){
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions()
            .addArguments("headless")
            .addArguments("window-size=1200x800");
            driver = new EventFiringWebDriver(new ChromeDriver(options));
        }else if (browser.equalsIgnoreCase("firefox")){
            FirefoxOptions options = new FirefoxOptions()
                    .addArguments("headless")
                    .addArguments("window-size=1200x800");
            driver = new EventFiringWebDriver( new FirefoxDriver(options));
        }
        // driver.manage().window().maximize();
        //driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new MyListener());
       // driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://selenium1py.pythonanywhere.com/");

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        driver.quit();
    }

}
