package com.telran.oscar.pages.home;

import com.telran.oscar.pages.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class HomePage extends PageBase {
    public HomePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(className = "wicon")
    WebElement confirmDeleteProfileMessage;

    @FindBy(xpath = "//h2[.='Welcome!']")
    WebElement welcome;

    @FindBy(tagName = "h1")
    WebElement homeTitle;

    public String getHomePageTitle(){
        return homeTitle.getText();
    }

    public String getConfirmDeleteProfileMessage(){
        return confirmDeleteProfileMessage.getText();
    }

    public boolean isHomePagePresent() {
        return welcome.isDisplayed();
    }
}
