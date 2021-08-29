package com.telran.oscar.pages.products;

import com.telran.oscar.pages.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.List;

public class BooksPage extends PageBase
{
    public BooksPage(WebDriver driver) {
        super(driver);
    }



    public BooksPage clickOnAddToBaskedOnBookItem(String booksTitle){
        try {
            WebElement book = getBookElementFromListByTitle(booksTitle);
            WebElement addToBasketBtn = book.findElement(By.tagName("button"));
            addToBasketBtn.click();
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

        return this;
    }


    @FindBy(xpath= "//a[.='next']")
    WebElement nextBtn;

    private WebElement getBookElementFromListByTitle(String title){

        do {
            List <WebElement> booksList = driver.findElements(By.xpath("//ol[@class='row']/li"));
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

    public BooksPage clickOnNextBtn(){
        if(nextBtn.isDisplayed()) {
            clickWithAction(nextBtn, 0, 500);
            pause(500);
        }
        return this;
    }


}
