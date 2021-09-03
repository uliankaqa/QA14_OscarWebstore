package com.telran.oscar.pages.home;

import com.telran.oscar.pages.PageBase;
import com.telran.oscar.pages.products.AllProductsPage;
import com.telran.oscar.pages.products.BooksPage;
import com.telran.oscar.pages.products.ClothingPage;
import com.telran.oscar.pages.products.OffersPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BrowseStorePage extends PageBase {


    public BrowseStorePage(WebDriver driver) {
        super(driver);

    }

    @FindBy(xpath = "//a[.='All products']")
    WebElement allProductsTab;


    @FindBy(xpath = "//a[.='Books']")
    WebElement booksTab;

    @FindBy(xpath = "//a[.='Clothing']")
    WebElement clothingTab;


    @FindBy(xpath = "//a[.='Offers']")
    WebElement offersTab;

    public AllProductsPage clickOnAllProductsTab() {
        allProductsTab.click();
        pause(500);
        return new AllProductsPage(driver);
    }

    public boolean isAllProductsTabVisible(){
        return allProductsTab.isDisplayed();
    }

    public BooksPage clickOnBooksTab() {
        booksTab.click();
        pause(500);
        return new BooksPage(driver);
    }

    public boolean isBooksTabVisible() {
        return booksTab.isDisplayed();
    }

    public ClothingPage clickOnClothingTab() {
        clothingTab.click();
        pause(500);
        return new ClothingPage(driver);
    }

    public boolean isClothingTabVisible(){
        return clothingTab.isDisplayed();
    }

    public OffersPage clickOnOffersTab() {
        offersTab.click();
        pause(500);
        return new OffersPage(driver);
    }

    public boolean isOffersTabVisible(){
        return offersTab.isDisplayed();
    }

    public boolean isAllProductsTabClickable() {
        return  allProductsTab.isEnabled();
    }

    public boolean isBooksTabClickable() {
        return booksTab.isEnabled();
    }

    public boolean isClothingTabClickable() {
        return  clothingTab.isEnabled();
    }

    public boolean isOffersTabClickable() {
        return offersTab.isEnabled();
    }
}