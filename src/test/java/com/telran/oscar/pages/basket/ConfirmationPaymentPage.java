package com.telran.oscar.pages.basket;

import com.telran.oscar.pages.home.HomePage;
import com.telran.oscar.pages.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmationPaymentPage extends PageBase {

    public ConfirmationPaymentPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[.='Continue shopping']")
    WebElement continueShoppingBtn;

    public String getOrderNumber(){
        return driver.findElement(By.xpath("//p[@class='lead']/strong")).getText();
    }

    public String getOrderTotal() {
        return driver.findElement(By.xpath("//h3[@class='price_color']")).getText();
    }

    public HomePage clickOnContinueShoppingBtn() {
        clickWithAction(continueShoppingBtn, 0, 500);
        return new HomePage(driver);
    }
}
