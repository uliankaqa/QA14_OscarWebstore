package com.telran.oscar.pages.products;

import com.telran.oscar.pages.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AllProductsPage extends PageBase {
    public AllProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(tagName = "h1")
    WebElement allProductTitle;

    public String getAllProductPageTitle(){
        return allProductTitle.getText();
    }
}
