package com.telran.oscar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class HomePage extends PageBase{
    public HomePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(className = "wicon")
    WebElement confirmDeleteProfileMessage;



    public String getConfirmDeleteProfileMessage(){
        return confirmDeleteProfileMessage.getText();
    }
}
