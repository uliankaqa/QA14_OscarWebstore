package com.telran.oscar.pages.user;

import com.telran.oscar.pages.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountSidePanelPage extends PageBase {
    public AccountSidePanelPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[.='Profile']")
    WebElement profileBtn;

    @FindBy(xpath = "//a[.='Order History']")
    WebElement orderHistoryBtn;

    @FindBy(xpath = "//a[.='Address Book']")
    WebElement addressBookBtn;

    @FindBy(xpath = "//a[.='Email History']")
    WebElement emailHistoryBtn;

    @FindBy(xpath = "//a[.='Product Alerts']")
    WebElement productAlertsBtn;

    @FindBy(xpath = "//a[.='Notifications']")
    WebElement notificationsBtn;

    @FindBy(xpath = "//a[.='Wish Lists']")
    WebElement wishListsBtn;

    public ProfilePage clickOnProfileBtn (){
        profileBtn.click();
        return  new ProfilePage(driver);
    }

    public OrderHistoryPage clickOnOrderHistoryBtn(){
        orderHistoryBtn.click();
        return new OrderHistoryPage(driver);
    }

    public AddressBookPage clickOnAddressBookBtn(){
        addressBookBtn.click();
        return  new AddressBookPage(driver);
    }

}
