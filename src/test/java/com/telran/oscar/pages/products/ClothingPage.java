package com.telran.oscar.pages.products;

import com.telran.oscar.pages.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClothingPage extends PageBase {
    public ClothingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(tagName = "h1")
    WebElement clothingTitle;

    public String getClothingPageTitle(){
        return clothingTitle.getText();
    }
}
