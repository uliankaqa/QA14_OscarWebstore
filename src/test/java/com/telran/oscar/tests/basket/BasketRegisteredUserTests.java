package com.telran.oscar.tests.basket;

import com.telran.oscar.pages.home.BrowseStorePage;
import com.telran.oscar.pages.home.HeaderPage;
import com.telran.oscar.pages.basket.BasketPage;
import com.telran.oscar.pages.products.BooksPage;
import com.telran.oscar.pages.products.ProductListPage;
import com.telran.oscar.tests.TestBase;
import com.telran.oscar.utils.ProductsData;
import com.telran.oscar.utils.UserData;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BasketRegisteredUserTests extends TestBase {
    private BasketPage basket;
    private String quantityErrorMessage = "This field is required.";
    @BeforeMethod
    public void ensurePrecondition(){
        basket = PageFactory.initElements(driver, BasketPage.class);
        new HeaderPage(driver).clickOnLoginOrRegisterBtn()
                .fillLogInForm(UserData.email, UserData.password)
                .clickOnLogInBtn();
        new BrowseStorePage(driver).clickOnAllProductsTab();
    }

    @Test
    public void addToBasketBookWithNamePositiveTest() {
        String byeBook = ProductsData.byeBookName1;
        new BooksPage(driver).clickOnAddToBaskedOnProductItem(byeBook);
        new HeaderPage(driver).clickOnViewBasketBtn();
        Assert.assertTrue(basket.isProductAddedToBasket(byeBook));
    }

    @Test
    public void checkTotalPriceWithQuantityTwoTest(){
        String byeBook = ProductsData.byeBookName2;
        new ProductListPage(driver).clickOnAddToBaskedOnProductItem(byeBook);
        new HeaderPage(driver).clickOnViewBasketBtn();
        basket.setQuantityByProductName(byeBook, "2");
        double productPrice = basket.getPriceByProductName(byeBook);
        double productTotalPrice = basket.getTotalPriceByProductName(byeBook);

        System.out.println("Price : " + productPrice + "    total price: " + productTotalPrice);
        Assert.assertEquals(productTotalPrice/productPrice, 2);

    }

    @Test
    public void checkIfQuantityChangeWhenDeleteOneProductFromBasketList(){

        new ProductListPage(driver).clickOnAddToBaskedOnProductItem(ProductsData.byeBookName1)
        .clickOnAddToBaskedOnProductItem(ProductsData.byeBookName2)
        .clickOnAddToBaskedOnProductItem(ProductsData.byeBookName3);
        new HeaderPage(driver).clickOnViewBasketBtn();
        basket.setQuantityByProductName(ProductsData.byeBookName2 , "0");
        Assert.assertEquals(basket.getCountItemsList(), 2);
    }

    @Test
    public void emptyQuantityNegativeTest(){
        String byeBook = ProductsData.byeBookName1;
        new ProductListPage(driver).clickOnAddToBaskedOnProductItem(byeBook);
        new HeaderPage(driver).clickOnViewBasketBtn();
        basket.setQuantityByProductName(byeBook , "");
        Assert.assertTrue(basket.getQuantityFailMessage(byeBook).contains(quantityErrorMessage));
    }

    @AfterMethod
    public void cleanBasket(){
        new BasketPage(driver).clean();
    }
}
