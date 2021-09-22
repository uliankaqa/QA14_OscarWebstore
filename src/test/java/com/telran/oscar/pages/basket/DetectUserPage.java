package com.telran.oscar.pages.basket;

import com.telran.oscar.pages.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DetectUserPage extends PageBase {
    public DetectUserPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "id_username")
    WebElement usernameInput;

    @FindBy(id = "id_password")
    WebElement passwordInput;

    @FindBy(css = ".btn-primary")
    WebElement continueBtn;

    @FindBy(id = "id_options_1")
    WebElement registrationOptionInput;

    public DetectUserPage typeEmail(String email){
        type(usernameInput, email);
        return this;
    }

    public DetectUserPage typePassword(String password){
        type(passwordInput, password);
        return this;
    }

    public ShippingAddressPage clickOnContinueBtn(){
        continueBtn.click();
        return new ShippingAddressPage(driver);
    }

    public DetectUserPage selectRegistrationOption(){
        registrationOptionInput.click();
        return this;
    }
}
