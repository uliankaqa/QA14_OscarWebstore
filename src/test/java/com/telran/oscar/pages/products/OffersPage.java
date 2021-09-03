package com.telran.oscar.pages.products;

import com.telran.oscar.pages.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OffersPage extends PageBase {
    public OffersPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(tagName = "h1")
    WebElement offersTitle;

    public String getOffersPageTitle(){
        return offersTitle.getText();
    }
}
