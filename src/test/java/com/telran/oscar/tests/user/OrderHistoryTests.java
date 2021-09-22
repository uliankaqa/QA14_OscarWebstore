package com.telran.oscar.tests.user;

import com.telran.oscar.pages.basket.BasketPage;
import com.telran.oscar.pages.home.BrowseStorePage;
import com.telran.oscar.pages.home.HeaderPage;
import com.telran.oscar.pages.basket.ConfirmationPaymentPage;
import com.telran.oscar.pages.products.BooksPage;
import com.telran.oscar.pages.user.AccountSidePanelPage;
import com.telran.oscar.pages.user.OrderHistoryPage;
import com.telran.oscar.tests.TestBase;
import com.telran.oscar.pages.basket.PaymentPage;
import com.telran.oscar.pages.basket.ShippingAddressPage;
import com.telran.oscar.utils.ProductsData;
import com.telran.oscar.utils.UserData;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderHistoryTests extends TestBase {
    private HeaderPage headerPage;
    @BeforeMethod
    public void ensurePrecondition(){
        headerPage = PageFactory.initElements(driver, HeaderPage.class);
        headerPage.clickOnLoginOrRegisterBtn()
                .fillLogInForm(UserData.email, UserData.password)
                .clickOnLogInBtn();
    }

    @Test
    public void oderCreatePositiveTest(){

        new BrowseStorePage(driver).clickOnBooksTab()
                .clickOnAddToBaskedOnProductItem(ProductsData.byeBookName1);
        headerPage.clickOnViewBasketBtn()
                .clickOnProceedToCheckoutBtn()
                .shipToAddress(UserData.title,
                UserData.userFirstName, UserData.userLastName,
                UserData.firstAddressLine, UserData.secondAddressLine,
                UserData.thirdAddressLine, UserData.city, UserData.zipCode,
                UserData.country, UserData.phoneNumber, UserData.instructions)
                .clickOnContinueBtn()
                .clickOnPlaceOrderBtn();
        ConfirmationPaymentPage confirmationPage = new ConfirmationPaymentPage(driver);
        String orderNumber = confirmationPage.getOrderNumber();
        String orderTotal = confirmationPage.getOrderTotal();
        confirmationPage.clickOnContinueShoppingBtn();
        headerPage.clickOnAccountBtn();
       new AccountSidePanelPage(driver).clickOnOrderHistoryBtn().filterOrder(orderNumber);
        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
       Assert.assertTrue(orderHistoryPage.getOrderNumber().contains(orderNumber));
       Assert.assertTrue(orderHistoryPage.getOrderTotal().contains(orderTotal));
    }

}
