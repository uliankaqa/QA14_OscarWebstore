package com.telran.oscar.tests.home;

import com.telran.oscar.pages.basket.BasketPage;
import com.telran.oscar.pages.home.BrowseStorePage;
import com.telran.oscar.pages.home.HeaderPage;
import com.telran.oscar.pages.home.HomePage;
import com.telran.oscar.pages.products.AllProductsPage;
import com.telran.oscar.pages.products.BooksPage;
import com.telran.oscar.pages.products.ClothingPage;
import com.telran.oscar.pages.products.OffersPage;
import com.telran.oscar.pages.user.RegistrationAndLoginPage;
import com.telran.oscar.tests.TestBase;
import com.telran.oscar.utils.RegisteredUserData;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    private HeaderPage header;
    private BrowseStorePage browseStore;


    @BeforeMethod
    public void initPages(){
        header = PageFactory.initElements(driver, HeaderPage.class);
        browseStore = PageFactory.initElements(driver, BrowseStorePage.class);

    }
    @Test
    public void allElementsVisibleUnregisteredUserTest(){

        Assert.assertTrue(header.isLoginOrRegisterBtnVisible());
        Assert.assertTrue(header.isViewBasketBtnVisible());
        Assert.assertTrue(header.isOscarLogoLinkVisible());
        Assert.assertTrue(browseStore.isAllProductsTabVisible());
        Assert.assertTrue(browseStore.isBooksTabVisible());
        Assert.assertTrue(browseStore.isClothingTabVisible());
        Assert.assertTrue(browseStore.isOffersTabVisible());

    }

    @Test
    public void allElementsVisibleRegisteredUserTest(){

        Assert.assertTrue(header.isAccountBtnVisible());
        Assert.assertTrue(header.isLogoutBtnVisible());
        Assert.assertTrue(header.isViewBasketBtnVisible());
        Assert.assertTrue(header.isOscarLogoLinkVisible());
        Assert.assertTrue(browseStore.isAllProductsTabVisible());
        Assert.assertTrue(browseStore.isBooksTabVisible());
        Assert.assertTrue(browseStore.isClothingTabVisible());
        Assert.assertTrue(browseStore.isOffersTabVisible());

    }

    @Test
    public void allElementsClickableUnregisteredUserTest(){

        Assert.assertTrue(header.isLoginOrRegisterBtnClickable());
        Assert.assertTrue(header.isViewBasketBtnClickable());
        Assert.assertTrue(header.isOscarLogoLinkClickable());
        Assert.assertTrue(browseStore.isAllProductsTabClickable());
        Assert.assertTrue(browseStore.isBooksTabClickable());
        Assert.assertTrue(browseStore.isClothingTabClickable());
        Assert.assertTrue(browseStore.isOffersTabClickable());
    }

    @Test
    public void allElementsClickableRegisteredUserTest(){
        Assert.assertTrue(header.isAccountBtnClickable());
        Assert.assertTrue(header.isLogoutBtnClickable());
        Assert.assertTrue(header.isViewBasketBtnClickable());
        Assert.assertTrue(header.isOscarLogoLinkClickable());
        Assert.assertTrue(browseStore.isAllProductsTabClickable());
        Assert.assertTrue(browseStore.isBooksTabClickable());
        Assert.assertTrue(browseStore.isClothingTabClickable());
        Assert.assertTrue(browseStore.isOffersTabClickable());
    }

    @Test
    public void loginOrRegisterBtnRedirectToCorrectPageTest(){
        header.clickOnLoginOrRegisterBtn();
        Assert.assertTrue(header.getHeaderLink2Text().contains("Login or register"));
    }

    @Test
    public void AccountBtnRedirectToCorrectPageTest(){
        header.clickOnLoginOrRegisterBtn();
        new RegistrationAndLoginPage(driver)
                .fillLogInForm(RegisteredUserData.email, RegisteredUserData.password)
                .clickOnLogInBtn();
        header.clickOnAccountBtn();
        Assert.assertTrue(header.getHeaderLink2Text().contains("Account"));
    }

    @Test
    public void logoutBtnRedirectToCorrectPageTest(){
        header.clickOnLoginOrRegisterBtn();
        new RegistrationAndLoginPage(driver)
                .fillLogInForm(RegisteredUserData.email, RegisteredUserData.password)
                .clickOnLogInBtn();
        header.clickOnLogoutBtn();
        Assert.assertTrue(new HomePage(driver).isHomePagePresent());
    }

    @Test
    public void  oscarLogoLinksRedirectToCorrectPageTest(){
        browseStore.clickOnBooksTab();
        header.clickOnOscarLogoLink();
        Assert.assertTrue(new HomePage(driver).isHomePagePresent());
    }

    @Test
    public void viewBasketBtnRedirectToCorrectPageTest(){
        header.clickOnViewBasketBtn();
        Assert.assertEquals(new BasketPage(driver).getBasketPageTitle(), "Basket");
    }

    @Test
    public void allProductTabRedirectToCorrectPageTest(){
        browseStore.clickOnAllProductsTab();
        Assert.assertEquals(new AllProductsPage(driver).getAllProductPageTitle(), "All products");
    }

    @Test
    public void clothingTabRedirectToCorrectPageTest(){
        browseStore.clickOnClothingTab();
        Assert.assertEquals(new ClothingPage(driver).getClothingPageTitle(), "Clothing");
    }

    @Test
    public void booksTabRedirectToCorrectPageTest(){
        browseStore.clickOnBooksTab();
        Assert.assertEquals(new BooksPage(driver).getBooksPageTitle(), "Books");
    }

    @Test
    public void offersTabRedirectToCorrectPageTest(){
        browseStore.clickOnOffersTab();
        Assert.assertEquals(new OffersPage(driver).getOffersPageTitle(), "Offers");
    }

    @Test
    public void searchPositiveTest(){
        header.search("Agile");
        Assert.assertTrue(new HomePage(driver).getHomePageTitle().contains("Agile"));
        Assert.assertEquals("Search", header.getHeaderLink2Text());
        Assert.assertTrue(header.getHeaderLink3Text().contains("Agile"));
    }
    
}
