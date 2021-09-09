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

    @FindBy(className = "basket-items")
    List <WebElement> baskedFormList;

    @FindBy(xpath = "//a[.='Proceed to checkout']")
    WebElement proceedToCheckoutBtn;

    @FindBy(tagName = "h1")
    WebElement basketTitle;




    public String getBasketPageTitle(){
        return basketTitle.getText();
    }

    public boolean isProductAddedToBasket(String productTitle){
        if( getProductElementByTitle(productTitle) != null) return true;
        return false;
    }
    public double getTotalPriceByProductName(String productTitle){
        WebElement elem = getProductElementByTitle(productTitle);
        if(elem != null) {
            String total = elem.findElement(By.xpath("//div[@class='col-sm-2']/p")).getText();
            return Double.parseDouble(total.replace("£", ""));
        }
        return -1.1;
    }

    public double getPriceByProductName(String productTitle){
        WebElement elem = getProductElementByTitle(productTitle);
        if(elem != null) {
            String price = elem.findElement(By.cssSelector(".col-sm-1 .price_color")).getText();
            return Double.parseDouble(price.replace("£", ""));
        }
        return -1.1;
    }

    public BasketPage setQuantityByProductName(String productTitle, String quantityOfProduct){
        WebElement elem = getProductElementByTitle(productTitle);
        if(elem != null) {
            setQuantityByElement(elem, quantityOfProduct);
        }
        return this;
    }

    private void setQuantityByElement(WebElement elem, String quantityOfProduct){
        WebElement  quantity = elem.findElement(By.cssSelector(".input-group  input"));
        type(quantity, quantityOfProduct);
        elem.findElement(By.cssSelector(".input-group-btn .btn")).click();
    }

    public String getQuantityFailMessage(String productTitle){
        WebElement elem = getProductElementByTitle(productTitle);
        return elem.findElement(By.xpath("//span[@class='error-block']")).getText();
    }
    private WebElement getProductElementByTitle(String productTitle){
        for (WebElement elem : baskedFormList) {
            String title = elem.findElement(By.tagName("h3")).getText();
            if( title.contains(productTitle)) {
                return elem;
            }
        }
        return null;
    }

    public ShippingAddressPage clickOnProceedToCheckoutBtn() {
        proceedToCheckoutBtn.click();
        return new ShippingAddressPage(driver);
    }

    public void clean() {
       while(baskedFormList.size() > 0){
            WebElement elem = baskedFormList.get(0);
            setQuantityByElement(elem, "0");
        }
    }

    public int getCountItemsList() {
        return baskedFormList.size();
    }
}
