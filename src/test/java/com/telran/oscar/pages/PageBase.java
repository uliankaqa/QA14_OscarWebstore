package com.telran.oscar.pages;

import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class PageBase {
   protected WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void type(WebElement elem, String text){
        if(text != null){
            elem.click();
            elem.clear();
            elem.sendKeys(text);
        }
    }

    public void clickWithAction(WebElement elem, int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy("+x + ", "+ y + ")");
        elem.click();

    }
    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void takeScreenshotField(WebElement element) throws IOException {
        element.isSelected();
        File screenshotEmptyField = element.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotEmptyField, new File(System
                .getProperty("user.dir") + "/screenshots/" + new Random().nextInt() + ".png"));
    }

    public void takeScreenshot(String pathToFile) {
        File tmp = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File(pathToFile);
        try {
            Files.copy(tmp,screenshot);
        }catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public String takeScreenshotWithScrollDown(String pathToFile){
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies
                        .viewportPasting(ShootingStrategies.scaling(2f),1000))
                .takeScreenshot(driver);
        try{
            ImageIO.write(screenshot.getImage(), "PNG", new File(pathToFile));
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public void typeWithAction(WebElement element, int x, int y, String text) {
        if (text != null) {
            clickWithAction(element,x,y);
            element.clear();
            element.sendKeys(text);
        }
    }

    public void closeBanner() {
        driver.findElement(By.id("close-fixedban")).click();
    }

    public boolean isElementClickable(WebElement elem ){
        try{
            new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(elem));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElements(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
