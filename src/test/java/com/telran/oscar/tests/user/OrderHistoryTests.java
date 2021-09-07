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
import com.telran.oscar.utils.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderHistoryTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        new HeaderPage(driver).clickOnLoginOrRegisterBtn()
                .fillLogInForm(UserData.email, UserData.password)
                .clickOnLogInBtn();
    }

    @Test
    public void oderCreatePositiveTest(){

        new BrowseStorePage(driver).clickOnBooksTab();
        new BooksPage(driver).clickOnAddToBaskedOnProductItem(ProductsData.byeBookName1);
        new HeaderPage(driver).clickOnViewBasketBtn().clickOnProceedToCheckoutBtn();
        new ShippingAddressPage(driver).shipToAddress(UserData.title,
                UserData.userFirstName, UserData.userLastName,
                UserData.firstAddressLine, UserData.secondAddressLine,
                UserData.thirdAddressLine, UserData.city, UserData.zipCode,
                UserData.country, UserData.phoneNumber, UserData.instructions);
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
