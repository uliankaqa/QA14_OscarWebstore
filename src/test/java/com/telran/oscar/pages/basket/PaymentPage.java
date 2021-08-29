package com.telran.oscar.pages.basket;

import com.telran.oscar.pages.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends PageBase {

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "view_preview")
    WebElement continueBtn;

    public PreviewOrderPage clickOnContinueBtn(){
        continueBtn.click();
        return new PreviewOrderPage(driver);
    }
}
