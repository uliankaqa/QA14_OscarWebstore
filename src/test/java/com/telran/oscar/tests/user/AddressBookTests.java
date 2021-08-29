package com.telran.oscar.tests.user;

import com.telran.oscar.pages.NavigationPage;
import com.telran.oscar.pages.user.AccountSidePanelPage;
import com.telran.oscar.pages.user.AddressBookPage;
import com.telran.oscar.pages.user.RegistrationAndLoginPage;
import com.telran.oscar.tests.TestBase;
import com.telran.oscar.utils.RegisteredUserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddressBookTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        new NavigationPage(driver).clickOnLoginOrRegisterBtn()
                .fillLogInForm(RegisteredUserData.email, RegisteredUserData.password)
                .clickOnLogInBtn();
        new NavigationPage(driver).clickOnAccountBtn();
        new AccountSidePanelPage(driver).clickOnAddressBookBtn();
    }

    @Test
    public void addNewAddressPositiveTest(){
        new AddressBookPage(driver).clickOnAddNewAddressBtn()
                .fillAddNewAddressForm(RegisteredUserData.title,
                RegisteredUserData.userFirstName, RegisteredUserData.userLastName,
                RegisteredUserData.firstAddressLine, RegisteredUserData.secondAddressLine, RegisteredUserData.thirdAddressLine,
                RegisteredUserData.city, RegisteredUserData.zipCode, RegisteredUserData.country,
                RegisteredUserData.phoneNumber, RegisteredUserData.instructions)
                .clickOnSaveNewAddressBtn();
        String message = "Address '"+ RegisteredUserData.title + " "
                + RegisteredUserData.userFirstName + " "
                + RegisteredUserData.userLastName + ", "
                + RegisteredUserData.firstAddressLine + ", "
                + RegisteredUserData.city + ", "
                + RegisteredUserData.zipCode + ", "
                + RegisteredUserData.country + "' created";
        Assert.assertTrue(new AddressBookPage(driver).getMessageText().contains(""));


    }
}
