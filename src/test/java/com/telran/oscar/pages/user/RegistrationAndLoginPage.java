package com.telran.oscar.pages.user;

import com.telran.oscar.pages.HomePage;
import com.telran.oscar.pages.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationAndLoginPage extends PageBase {
    public RegistrationAndLoginPage(WebDriver driver) {
        super(driver);
    }

    //Registration elements
    //input[@name='registration-email']
    @FindBy(name ="registration-email")
    WebElement registrationEmailInput;

    @FindBy(name = "registration-password1")
    WebElement registrationPasswordInput;

    @FindBy(name = "registration-password2")
    WebElement registrationPasswordConfirmInput;

    //button[@name='registration_submit']
    @FindBy(name = "registration_submit")
    WebElement registerBtn;

    //Log In elements

    @FindBy(name = "login-username")
    WebElement loginUserNameInput;

    @FindBy(name = "login-password")
    WebElement loginPasswordInput;

    @FindBy(name = "login_submit")
    WebElement logInBtn;

    @FindBy(id = "register_form")
    WebElement registerForm;

    @FindBy(id = "logout_link")
    WebElement logOutBtn;

    @FindBy(className = "alert-danger")
    WebElement messageElement;

    @FindBy(className = "error-block")
    WebElement errorBlock;

    @FindBy(xpath = "//a[contains(text(),'forgotten my password')]")
    WebElement forgottenPassword;

    public RegistrationAndLoginPage fillRegistrationForm(String email, String password, String confirmPassword){
        type(registrationEmailInput, email);
        type(registrationPasswordInput, password);
        type(registrationPasswordConfirmInput, confirmPassword);
        return this;
    }

    public RegistrationAndLoginPage fillLogInForm(String username, String password){
        type(loginUserNameInput, username);
        type(loginPasswordInput, password);
        return this;
    }

    public void clickOnRegisterBtn() {

        registerBtn.click();
        pause(2000);
    }

    public void clickOnLogInBtn(){
        logInBtn.click();
        pause(2000);
    }

    public void clickOnForgottenPasswordLink(){
        forgottenPassword.click();
    }

    public boolean isRegisterFormDisplayed() {
        return registerForm.isDisplayed();
    }

    public boolean isLogOutBtnDisplayed() {
        return logOutBtn.isDisplayed();
    }

    public String getTopMessage() {
        return messageElement.getText();
    }

    public String getErrorMessage() {
        return errorBlock.getText();
    }

}
