package com.telran.oscar.pages.basket;

import com.telran.oscar.pages.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BasketPage extends PageBase {
    public BasketPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//form[@id='basket_formset']/div")
    List <WebElement> baskedFormList;

    @FindBy(xpath = "//a[.='Proceed to checkout']")
    WebElement proceedToCheckoutBtn;

    @FindBy(tagName = "h1")
    WebElement basketTitle;

    public String getBasketPageTitle(){
        return basketTitle.getText();
    }

    public Boolean isProductAddedToBasket(String productTitle){
        for (WebElement elem : baskedFormList) {
            String title = elem.findElement(By.tagName("h3")).getText();
            if(title.contains(productTitle)) return true;
        }
        return false;
    }

    public ShippingAddressPage clickOnProceedToCheckoutBtn() {
        proceedToCheckoutBtn.click();
        return new ShippingAddressPage(driver);
    }
}
