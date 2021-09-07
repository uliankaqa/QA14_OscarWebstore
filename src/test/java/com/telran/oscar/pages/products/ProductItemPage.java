package com.telran.oscar.pages.products;

import com.telran.oscar.pages.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductItemPage extends PageBase {


    public ProductItemPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(tagName = "h1")
    WebElement itemTitle;

    @FindBy(css = ".price_color:nth-child(2)")
    WebElement price;

    @FindBy(id = "write_review")
    WebElement writeReviewBtn;

    @FindBy(xpath = "//button[@value='Add to basket']")
    WebElement addToBasketBtn;

    @FindBy(xpath = "//button[.='Add to wish list']")
    WebElement addToWishListBtn;

    public String getBookName(){
        return itemTitle.getText();
    }

    public String getPrice(){
        return price.getText();
    }

    public ProductItemPage clickOnWriteReviewBtn(){
        writeReviewBtn.click();
        return this;
    }

    public ProductItemPage clickOnAddToBasketBtn(){
        addToBasketBtn.click();
        return this;
    }

    public ProductItemPage clickOnAddToWishListBtn(){
        addToWishListBtn.click();
        return this;
    }

}
