package com.telran.oscar.pages.user;

import com.telran.oscar.pages.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PasswordResetPage extends PageBase {
    public PasswordResetPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "id_email")
    WebElement emailInput;

    @FindBy(css = ".btn-primary.btn-lg")
    WebElement sendResetEmailBtn;

    @FindBy(xpath = "//h1[.='Email sent']")
    WebElement titleEmailSent;

    public PasswordResetPage fillEmail(String email) {
        type(emailInput, email);
        return this;
    }


    public PasswordResetPage clickOnSendResetEmail() {
        sendResetEmailBtn.click();
        return this;
    }

    public String getTitleEmailSend(){
        return titleEmailSent.getText();
    }

}
