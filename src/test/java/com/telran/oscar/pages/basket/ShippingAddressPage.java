package com.telran.oscar.pages.basket;

import com.telran.oscar.pages.user.AddressBookPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShippingAddressPage extends AddressBookPage {
    public ShippingAddressPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='col-sm-6']")
    List<WebElement> addressList;

    @FindBy(xpath = "//button[.='Continue']")
    WebElement continueBtn;

    public int indexOfAddedAddress(String zipCode){
        for (WebElement address: addressList
             ) {
            List<WebElement> addressData = address.findElements(By.tagName("span"));
            if(zipCode.equals(addressData.get(3).getText())) return addressList.indexOf(address);
        }
        return -1;
    }

    public PaymentPage shipToAddress(String title, String userFirstName, String userLastName,
                                              String firstAddressLine, String secondAddressLine,
                                              String thirdAddressLine, String city,
                                              String zipCode, String country, String phoneNumber, String instructions) {
        int shipAddressIndex = indexOfAddedAddress(zipCode);
        if(shipAddressIndex >= 0){
            clickOnShipToThisAddressBtn(shipAddressIndex);
        }else{
            fillAddNewAddressForm( title, userFirstName, userLastName,
                    firstAddressLine, secondAddressLine,
                    thirdAddressLine, city,
                    zipCode, country, phoneNumber,instructions);
           clickOnContinueBtn();
        }

        return new PaymentPage(driver);
    }

    private PaymentPage clickOnShipToThisAddressBtn(int shipAddressIndex) {
        WebElement shipToThisAddressBtn = addressList.get(shipAddressIndex).findElement(By.tagName("button"));
        shipToThisAddressBtn.click();
        return  new PaymentPage(driver);
    }

    private PaymentPage clickOnContinueBtn(){
        continueBtn.click();
        return new PaymentPage(driver);
    }
}
