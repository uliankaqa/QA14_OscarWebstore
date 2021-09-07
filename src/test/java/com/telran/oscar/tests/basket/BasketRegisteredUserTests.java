package com.telran.oscar.tests.basket;

import com.telran.oscar.pages.home.BrowseStorePage;
import com.telran.oscar.pages.home.HeaderPage;
import com.telran.oscar.pages.basket.BasketPage;
import com.telran.oscar.pages.products.BooksPage;
import com.telran.oscar.pages.products.ProductListPage;
import com.telran.oscar.tests.TestBase;
import com.telran.oscar.utils.ProductsData;
import com.telran.oscar.utils.UserData;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BasketRegisteredUserTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        new HeaderPage(driver).clickOnLoginOrRegisterBtn()
                .fillLogInForm(UserData.email, UserData.password)
                .clickOnLogInBtn();
    }

    @Test
    public void addToBasketBookWithNamePositiveTest() {
        new BrowseStorePage(driver).clickOnBooksTab();
        new BooksPage(driver).clickOnAddToBaskedOnProductItem(ProductsData.byeBookName1);
        new HeaderPage(driver).clickOnViewBasketBtn();
        Assert.assertTrue(new BasketPage(driver).isProductAddedToBasket(ProductsData.byeBookName1));
    }

    @Test
    public void checkTotalPriceWithQuantityTwoTest(){

        new BrowseStorePage(driver).clickOnBooksTab();
        new ProductListPage(driver).clickOnAddToBaskedOnProductItem(ProductsData.byeBookName2);
        new HeaderPage(driver).clickOnViewBasketBtn();
        BasketPage basket = new BasketPage(driver);
        basket.setQuantityByProductName(ProductsData.byeBookName2 , "2");
        double productPrice = basket.getPriceByProductName(ProductsData.byeBookName2);
        double productTotalPrice = basket.getTotalPriceByProductName(ProductsData.byeBookName2);

        System.out.println("Price : " + productPrice + "    total price: " + productTotalPrice);
        Assert.assertEquals(productTotalPrice/productPrice, 2);

    }

    @AfterMethod
    public void cleanBasket(){
        new BasketPage(driver).clean();
    }
}
