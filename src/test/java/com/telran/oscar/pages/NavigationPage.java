package com.telran.oscar.pages;

import com.telran.oscar.pages.basket.BasketPage;
import com.telran.oscar.pages.products.BooksPage;
import com.telran.oscar.pages.user.ProfilePage;
import com.telran.oscar.pages.user.RegistrationAndLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class NavigationPage extends PageBase {
    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "login_link")
    WebElement loginOrRegisterBtn;

    @FindBy(id = "browse")
    WebElement browseStoreBtn;

    @FindBy(xpath = "//a[.=' Account']")
    //a[.=' Account']
    WebElement accountBtn;

    @FindBy(xpath = "//a[.='Profile']")
    WebElement profileBtn;

    @FindBy(xpath = "//a[.='Order History']")
    WebElement orderHistoryBtn;

    @FindBy(xpath = "//a[.='Books']")
    WebElement sidePanelBooksTab;

    @FindBy(xpath = "//span/a[.='View basket']")
    WebElement viewBasketBtn;

    public BooksPage clickOnBooksTabOnSidePanel() {
        sidePanelBooksTab.click();
        pause(500);
        return new BooksPage(driver);
    }

    public RegistrationAndLoginPage clickOnLoginOrRegisterBtn(){
        loginOrRegisterBtn.click();
        pause(500);
        return new RegistrationAndLoginPage(driver);
    }

    public ProfilePage clickOnAccountBtn(){
        accountBtn.click();
        return new ProfilePage(driver);
    }

    public BasketPage clickOnViewBasketBtn(){
        viewBasketBtn.click();
        pause(500);
        return new BasketPage(driver);
    }
}
