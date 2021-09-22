package com.telran.oscar.tests.basket;

import com.telran.oscar.pages.PageBase;
import com.telran.oscar.pages.basket.BasketPage;
import com.telran.oscar.pages.basket.ConfirmationPaymentPage;
import com.telran.oscar.pages.basket.DetectUserPage;
import com.telran.oscar.pages.basket.ShippingAddressPage;
import com.telran.oscar.pages.home.BrowseStorePage;
import com.telran.oscar.pages.home.HeaderPage;
import com.telran.oscar.pages.products.AllProductsPage;
import com.telran.oscar.pages.user.AccountSidePanelPage;
import com.telran.oscar.pages.user.OrderHistoryPage;
import com.telran.oscar.pages.user.RegisterPage;
import com.telran.oscar.tests.TestBase;
import com.telran.oscar.utils.NewUserData;
import com.telran.oscar.utils.ProductsData;
import com.telran.oscar.utils.UserData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BasketUnregisteredUserTests extends TestBase {

    private BasketPage basket;
    private HeaderPage headerPage;

    @BeforeMethod
    public void ensurePrecondition(){
        basket = PageFactory.initElements(driver, BasketPage.class);
        headerPage = PageFactory.initElements(driver, HeaderPage.class);
        new BrowseStorePage(driver).clickOnAllProductsTab();
    }

    @Test
    public void createOrderWithRegistrationTestPositive(){
        //Add one product to basket
        new AllProductsPage(driver).clickOnAddToBaskedOnProductItem(ProductsData.byeBookName4);
        //view basket and checkOut order
        headerPage.clickOnViewBasketBtn().clickOnProceedToCheckoutBtn();
        //fill required data and select oprion with registration
        new DetectUserPage(driver).typeEmail(NewUserData.email)
                .typePassword(NewUserData.password)
                .selectRegistrationOption()
                .clickOnContinueBtn();
        //register new user
        new RegisterPage(driver).typeEmail(NewUserData.email)
                .typePassword(NewUserData.password)
                .typeConfirmPassword(NewUserData.password).clickOnRegisterBtn();
        //fill shipping address
        new ShippingAddressPage(driver).shipToAddress(NewUserData.title,
                        NewUserData.userFirstName, NewUserData.userLastName,
                        NewUserData.firstAddressLine, NewUserData.secondAddressLine,
                        NewUserData.thirdAddressLine, NewUserData.city, NewUserData.zipCode,
                        NewUserData.country, NewUserData.phoneNumber, NewUserData.instructions)
                .clickOnContinueBtn()
        .clickOnPlaceOrderBtn();
        ConfirmationPaymentPage confirmationPage = new ConfirmationPaymentPage(driver);
        //save the order number and total order
        String orderNumber = confirmationPage.getOrderNumber();
        String orderTotal = confirmationPage.getOrderTotal();
        confirmationPage.clickOnContinueShoppingBtn();
        // go to Account ->Order  History
        headerPage.clickOnAccountBtn();
        AccountSidePanelPage accountMenu = new AccountSidePanelPage(driver);
        accountMenu.clickOnOrderHistoryBtn();
        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
        //Assert saved order number und order total with existed
        Assert.assertTrue(orderHistoryPage.getFirstOrderNumberFromTable().contains(orderNumber));
        Assert.assertTrue(orderHistoryPage.getFirstOrderTotalFromTable().contains(orderTotal));
        //clean registered Account:
        accountMenu.clickOnProfileBtn().clickOnDeleteProfileBtn().typePassword(NewUserData.password)
                .clickOnDeleteProfileConfirmBtn();

    }
}
