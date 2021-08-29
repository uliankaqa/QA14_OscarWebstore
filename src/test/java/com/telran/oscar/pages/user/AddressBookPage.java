package com.telran.oscar.pages.user;

import com.telran.oscar.pages.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AddressBookPage extends PageBase {
    public AddressBookPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//a[.='Add a new address']")
    WebElement addNewAddressBtn;

    @FindBy(xpath = "//button[.='Save']")
    WebElement saveNewAddressBtn;

    @FindBy(id = "id_title")
    WebElement titleSelect;

    @FindBy(id = "id_first_name")
    WebElement firstNameInput;

    @FindBy(id = "id_last_name")
    WebElement lastNameInput;

    @FindBy(id = "id_line1")
    WebElement firstLineOfAddressInput;

    @FindBy(id = "id_line2")
    WebElement secondLineOfAddressInput;

    @FindBy(id = "id_line3")
    WebElement thirdLineOfAddressInput;

    @FindBy(id = "id_line4")
    WebElement cityInput;

    @FindBy(id = "id_postcode")
    WebElement zipCodeInput;

    @FindBy(id = "id_country")
    WebElement countrySelect;

    @FindBy(id = "id_phone_number")
    WebElement phoneNumberInput;

    @FindBy(id = "id_notes")
    WebElement notesTextArea;

    @FindBy(className = "wicon")
    WebElement messageFild;

    public AddressBookPage clickOnAddNewAddressBtn(){
        addNewAddressBtn.click();
        return this;
    }

    public AddressBookPage clickOnSaveNewAddressBtn(){
        saveNewAddressBtn.click();
        return this;
    }

    public AddressBookPage fillAddNewAddressForm(String title,
                                                 String fName, String lName,
                                                 String fLineAddress, String secLineAddress, String thLineAddress,
                                                 String city, String zipCode, String country,
                                                 String phone, String note){
        selectTitle(title);
        type(firstNameInput, fName);
        type(lastNameInput, lName);
        type(firstLineOfAddressInput, fLineAddress);
        type(secondLineOfAddressInput, secLineAddress);
        type(thirdLineOfAddressInput, thLineAddress);
        type(cityInput, city);
        type(zipCodeInput, zipCode);
        selectCountry(country);
        type(phoneNumberInput, phone);
        type(notesTextArea, note);

        return this;
    }

    private void selectCountry(String country) {
        Select select = new Select(countrySelect);//HTML <select> tag
        select.selectByVisibleText(country);
    }

    public void selectTitle(String title){
        Select select = new Select(titleSelect);//HTML <select> tag
        select.selectByVisibleText(title);

    }

    public String getMessageText(){
        return messageFild.getText();
    }
}
