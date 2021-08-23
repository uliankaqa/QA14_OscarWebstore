package com.telran.oscar.pages.user;

import com.telran.oscar.pages.HomePage;
import com.telran.oscar.pages.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends PageBase {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "delete_profile")
    WebElement deleteProfileBtn;

    @FindBy(id = "id_password")
    WebElement passwordInput;

    @FindBy(className = "btn-danger")
    WebElement deleteConfirmBtn;

    @FindBy(xpath = "//a[.='Edit profile']")
    WebElement editProfileBtn;

    @FindBy(id = "id_first_name")
    WebElement editFirstNameFild;

    @FindBy(id = "id_last_name")
    WebElement editLastNameField;

    @FindBy(id = "id_email")
    WebElement editEmailField;

    @FindBy(xpath = "//button[.='Save']")
    WebElement saveEditBtn;

    @FindBy(className = "wicon")
    WebElement message;

    public ProfilePage clickOnDeleteProfileBtn(){
        deleteProfileBtn.click();
        return this;
    }
    public ProfilePage typePassword (String pass) {
        type(passwordInput, pass );
        return this;
    }

    public HomePage clickOnDeleteProfileConfirmBtn(){
        deleteConfirmBtn.click();
        return new HomePage(driver);
    }

    public ProfilePage clickOnEditeProfileBtn() {
        editProfileBtn.click();
        return this;
    }

    public ProfilePage fillEditForm( String firstName, String lastName, String email){
        if(firstName != "" && !firstName.equals(editFirstNameFild.getText())){
           type(editFirstNameFild, firstName);
        }
        if(lastName != "" && !lastName.equals(editLastNameField.getText())){
            type(editLastNameField, lastName);
        }

        if(email !="" && !email.equals(editEmailField.getText())){
            type(editEmailField, email);
        }
        return this;
    }

    public ProfilePage clickOnSaveEditBtn(){
        saveEditBtn.click();
        return this;
    }

    public String getMessage(){
        return  message.getText();
    }

    /*public boolean isNameChanged(String name){
        return (Boolean) driver.findElement(By.xpath(String.format("//td[.='%s']", name)));
    }*/
}
