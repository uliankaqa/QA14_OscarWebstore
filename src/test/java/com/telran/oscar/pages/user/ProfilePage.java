package com.telran.oscar.pages.user;

import com.telran.oscar.pages.home.HomePage;
import com.telran.oscar.pages.PageBase;
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
    WebElement saveBtn;

    @FindBy(className = "wicon")
    WebElement message;

    @FindBy(css = "tr:nth-child(1) > td")
    WebElement firstNameCell;

    @FindBy(id = "id_old_password")
    WebElement odlPasswordInput;

    @FindBy(id = "id_new_password1")
    WebElement newPasswordInput;

    @FindBy(id = "id_new_password2")
    WebElement newPasswordConfirmInput;

    @FindBy(xpath = "//a[.='Change password']")
    WebElement changePasswordBtn;

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

    public ProfilePage clickOnEditProfileBtn() {
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


    public ProfilePage clickOnSaveBtn(){
        saveBtn.click();
        return this;
    }

    public ProfilePage fillChangePasswordForm(String oldPassword, String newPassword, String newPasswordConfirm){
        if(oldPassword != ""){
            type(odlPasswordInput, oldPassword);
        }
        if(newPassword != ""){
            type(newPasswordInput, newPassword);
        }
        if(newPasswordConfirm != ""){
            type(newPasswordConfirmInput, newPasswordConfirm);
        }
        return this;
    }
    
    public String getMessage(){
        return  message.getText();
    }

    public boolean isNameChanged(String name){
        return firstNameCell.getText().contains(name);
    }

    public ProfilePage clickOnChangePasswordBtn() {
        changePasswordBtn.click();
        return this;
    }
}
