package com.telran.oscar.pages.user;

import com.telran.oscar.pages.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends PageBase {
    public RegisterPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "id_email")
    WebElement emailInput;

    @FindBy(id = "id_password1")
    WebElement passwordInput;

    @FindBy(id = "id_password2")
    WebElement passwordConfirmInput;

    @FindBy(css= ".btn-lg" )
    WebElement registerBtn;

    public RegisterPage typeEmail(String email){
        type(emailInput, email);
        return this;
    }

    public RegisterPage typePassword(String password){
        type(passwordInput, password);
        return this;

    }

    public RegisterPage typeConfirmPassword(String password){
        type(passwordConfirmInput, password);
        return this;
    }

    public void clickOnRegisterBtn(){
        registerBtn.click();
    }
}
