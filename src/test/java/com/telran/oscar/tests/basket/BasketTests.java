package com.telran.oscar.tests.basket;

import com.telran.oscar.pages.home.BrowseStorePage;
import com.telran.oscar.pages.home.HeaderPage;
import com.telran.oscar.pages.basket.BasketPage;
import com.telran.oscar.pages.products.BooksPage;
import com.telran.oscar.tests.TestBase;
import com.telran.oscar.utils.ProductsData;
import com.telran.oscar.utils.RegisteredUserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BasketTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        new HeaderPage(driver).clickOnLoginOrRegisterBtn()
                .fillLogInForm(RegisteredUserData.email, RegisteredUserData.password)
                .clickOnLogInBtn();
    }

    @Test
    public void addToBasketBookWithNamePositiveTest() {
        new BrowseStorePage(driver).clickOnBooksTab();
        new BooksPage(driver).clickOnAddToBaskedOnBookItem(ProductsData.byeBookName1);
        new HeaderPage(driver).clickOnViewBasketBtn();
        Assert.assertTrue(new BasketPage(driver).isProductAddedToBasket(ProductsData.byeBookName1));
    }
}
