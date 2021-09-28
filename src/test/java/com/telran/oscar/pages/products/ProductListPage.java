package com.telran.oscar.pages.products;

import com.telran.oscar.pages.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductListPage extends PageBase {
    @FindBy(xpath= "//a[.='next']")
    WebElement nextBtn;

    @FindBy(xpath = "//a[.='previous']")
    WebElement previousBtn;

    @FindBy(tagName = "h1")
    WebElement productPageTitle;

    @FindBy(className = "current")
    WebElement pageInfo;

    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    public String getItemNameFromList(int itemNum) {
        return  driver.findElement(By.cssSelector(".col-xs-6:nth-child("+itemNum+") h3 > a")).getText();
    }

    public void clickOnChosenProduct(int itemNum) {
        driver.findElement(By.cssSelector(".col-xs-6:nth-child("+itemNum+") h3 > a")).click();

    }

    public ProductListPage clickOnAddToBaskedOnProductItem(String booksTitle){
        try {
            WebElement book = getProductFromListByTitle(booksTitle);
            WebElement addToBasketBtn = book.findElement(By.tagName("button"));
            addToBasketBtn.click();
        }catch (Exception e){
            System.out.println("My Error: " + e.getMessage());
        }

        return this;
    }

    public ProductListPage clickOnProductItem(String booksTitle){
        try {
            WebElement book = getProductFromListByTitle(booksTitle);
            WebElement productImg = book.findElement(By.tagName("img"));
            productImg.click();
        }catch (Exception e){
            System.out.println("My Error: " + e.getMessage());
        }

        return this;
    }

    public WebElement getProductFromListByTitle(String title){

        do {
            List<WebElement> booksList = driver.findElements(By.xpath("//ol[@class='row']/li"));
            System.out.println("BOOKS LIST SIZE = " + booksList.size());
            for (WebElement elem: booksList) {
                String bookTitle = elem.findElement(By.tagName("h3")).getText();
                if (bookTitle.contains(title)) {
                    return elem;
                }
            }
            clickOnNextBtn();
       }while (nextBtn.isDisplayed());
        return null;
    }

    public ProductListPage clickOnNextBtn(){
        if(nextBtn.isDisplayed()) {
            clickWithAction(nextBtn, 0, 500);
            pause(500);
        }
        return this;
    }

    public ProductListPage clickOnPreviousBtn(){
        if(previousBtn.isDisplayed()) {
            clickWithAction(previousBtn, 0, 500);
            pause(500);
        }
        return this;
    }

    public String getProductPageTitle(){
        return productPageTitle.getText();
    }

    public int getCurrentPage(){
        String [] info = pageInfo.getText().split(" ");
        try{
            return Integer.parseInt(info[1]);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public int getTotalPages(){
        String [] info = pageInfo.getText().split(" ");
        System.out.println("total pages : "+ info[3]);

        try{
            return Integer.parseInt(info[3]);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public boolean isNextBtnDisplayed() {
        try{
            return  nextBtn.isDisplayed();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean isPreviousBtnDisplayed(){
        try {
            return  previousBtn.isDisplayed();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public double getPriceOfChosenProduct(String bookName) {
        try {
            WebElement book = getProductFromListByTitle(bookName);
            //Double.parseDouble(price.replace("£", ""));
           return Double.parseDouble(book.findElement(By.cssSelector(".price_color")).getText().replace("£", ""));
        }catch (Exception e){
            System.out.println("My Error: " + e.getMessage());
        }
       return -1.1;
    }
}
