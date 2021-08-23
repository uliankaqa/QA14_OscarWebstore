package com.telran.oscar.helpers;

import com.telran.oscar.pages.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyListener extends AbstractWebDriverEventListener {

    public MyListener() {
    }

    Logger logger = LoggerFactory.getLogger(MyListener.class);

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {

        logger.info("Start search " + by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {

        logger.info(by + " found.");
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        //logger.info("Click on element " + element + " is " + element.isSelected());
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        logger.error(throwable.toString());
        String screen = "screenshots/screen-" + (System.currentTimeMillis()/1000%3600) + ".png";
        new PageBase(driver).takeScreenshotWithScrollDown(screen);
        logger.error(screen);

    }
}