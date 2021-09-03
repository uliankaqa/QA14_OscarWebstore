package com.telran.oscar.pages.home;

import com.telran.oscar.pages.PageBase;
import com.telran.oscar.pages.basket.BasketPage;
import com.telran.oscar.pages.user.ProfilePage;
import com.telran.oscar.pages.user.RegistrationAndLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderPage extends PageBase {

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "login_link")
    WebElement loginOrRegisterBtn;

    @FindBy(id = "logout_link")
    WebElement logoutBtn;

    @FindBy(xpath = "//a[.=' Account']")
    //a[.=' Account']
    WebElement accountBtn;

    @FindBy(xpath = "//span/a[.='View basket']")
    WebElement viewBasketBtn;

    @FindBy(className = "hidden-xs")
    WebElement basketTotal;

    @FindBy(id = "id_q")
    WebElement searchInput;

    @FindBy(xpath = "//input[@value='Search']")
    WebElement searchBtn;

    @FindBy(xpath = "//select[@name='language']")
    WebElement languageSelect;

    @FindBy(xpath = "//button[.='Go']")
    WebElement goBtn;

    @FindBy(xpath = "//a[.='Oscar']")
    WebElement oscarlogoLink;

    @FindBy(xpath = "//ul[@class='breadcrumb']/li[1]")
    WebElement headerLink1;

    @FindBy(xpath = "//ul[@class='breadcrumb']/li[2]")
    WebElement headerLink2;

    @FindBy(xpath = "//ul[@class='breadcrumb']/li[3]")
    WebElement headerLink3;

    public RegistrationAndLoginPage clickOnLoginOrRegisterBtn(){
        loginOrRegisterBtn.click();
        pause(500);
        return new RegistrationAndLoginPage(driver);
    }

    public boolean isLoginOrRegisterBtnVisible(){
        return loginOrRegisterBtn.isDisplayed();
    }
    public boolean isLoginOrRegisterBtnClickable() {
        return loginOrRegisterBtn.isEnabled();
    }

    public HomePage clickOnLogoutBtn(){
        logoutBtn.click();
        pause(500);
        return new HomePage(driver);
    }

    public boolean isLogoutBtnVisible(){
        return logoutBtn.isDisplayed();
    }
    public boolean isLogoutBtnClickable() {
        return logoutBtn.isEnabled();
    }

    public ProfilePage clickOnAccountBtn(){
        accountBtn.click();
        return new ProfilePage(driver);
    }

    public boolean isAccountBtnVisible(){
        return accountBtn.isDisplayed();
    }
    public boolean isAccountBtnClickable() {
        return accountBtn.isEnabled();
    }

    public BasketPage clickOnViewBasketBtn(){
        viewBasketBtn.click();
        pause(500);
        return new BasketPage(driver);
    }

    public boolean isViewBasketBtnVisible(){
        return viewBasketBtn.isDisplayed();
    }

    public boolean isViewBasketBtnClickable(){
        return viewBasketBtn.isEnabled();
    }

    public HomePage clickOnOscarLogoLink(){
        oscarlogoLink.click();
        return  new HomePage(driver);
    }


    public boolean isOscarLogoLinkVisible(){
        return oscarlogoLink.isDisplayed();
    }
    public boolean isOscarLogoLinkClickable(){
        return oscarlogoLink.isEnabled();
    }

    public HomePage search(String text) {
        type(searchInput, text);
        searchBtn.click();
        return new HomePage(driver);
    }
    public String getHeaderLink1Text(){
        return headerLink1.getText();
    }
    public String getHeaderLink2Text(){
        return headerLink2.getText();
    }
    public String getHeaderLink3Text(){
        return headerLink3.getText();
    }
}
