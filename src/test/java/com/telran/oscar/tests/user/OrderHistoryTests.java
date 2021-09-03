package com.telran.oscar.tests.user;

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
import com.telran.oscar.utils.RegisteredUserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderHistoryTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        new HeaderPage(driver).clickOnLoginOrRegisterBtn()
                .fillLogInForm(RegisteredUserData.email, RegisteredUserData.password)
                .clickOnLogInBtn();
    }

    @Test
    public void oderCreatePositiveTest(){

        new BrowseStorePage(driver).clickOnBooksTab();
        new BooksPage(driver).clickOnAddToBaskedOnBookItem(ProductsData.byeBookName1);
        new HeaderPage(driver).clickOnViewBasketBtn().clickOnProceedToCheckoutBtn();
        new ShippingAddressPage(driver).shipToAddress(RegisteredUserData.title,
                RegisteredUserData.userFirstName, RegisteredUserData.userLastName,
                RegisteredUserData.firstAddressLine, RegisteredUserData.secondAddressLine,
                RegisteredUserData.thirdAddressLine, RegisteredUserData.city, RegisteredUserData.zipCode,
                RegisteredUserData.country, RegisteredUserData.phoneNumber, RegisteredUserData.instructions);
        new PaymentPage(driver).clickOnContinueBtn().clickOnPlaceOrderBtn();

        String orderNumber = new ConfirmationPaymentPage(driver).getOrderNumber();
        String orderTotal = new ConfirmationPaymentPage(driver).getOrderTotal();;

       new ConfirmationPaymentPage(driver).clickOnContinueShoppingBtn();
       new HeaderPage(driver).clickOnAccountBtn();
       new AccountSidePanelPage(driver).clickOnOrderHistoryBtn().filterOrder(orderNumber);

       Assert.assertTrue(new OrderHistoryPage(driver).getOrderNumber().contains(orderNumber));
       Assert.assertTrue(new OrderHistoryPage(driver).getOrderTotal().contains(orderTotal));
    }

}
