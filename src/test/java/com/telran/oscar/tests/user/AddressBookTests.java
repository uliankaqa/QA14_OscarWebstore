package com.telran.oscar.tests.user;

import com.telran.oscar.pages.home.HeaderPage;
import com.telran.oscar.pages.user.AccountSidePanelPage;
import com.telran.oscar.pages.user.AddressBookPage;
import com.telran.oscar.tests.TestBase;
import com.telran.oscar.utils.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddressBookTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        new HeaderPage(driver).clickOnLoginOrRegisterBtn()
                .fillLogInForm(UserData.email, UserData.password)
                .clickOnLogInBtn();
        new HeaderPage(driver).clickOnAccountBtn();
        new AccountSidePanelPage(driver).clickOnAddressBookBtn();
    }

    @Test
    public void addNewAddressPositiveTest(){
        new AddressBookPage(driver).clickOnAddNewAddressBtn()
                .fillAddNewAddressForm(UserData.title,
                UserData.userFirstName, UserData.userLastName,
                UserData.firstAddressLine, UserData.secondAddressLine, UserData.thirdAddressLine,
                UserData.city, UserData.zipCode, UserData.country,
                UserData.phoneNumber, UserData.instructions)
                .clickOnSaveNewAddressBtn();
        String message = "Address '"+ UserData.title + " "
                + UserData.userFirstName + " "
                + UserData.userLastName + ", "
                + UserData.firstAddressLine + ", "
                + UserData.city + ", "
                + UserData.zipCode + ", "
                + UserData.country + "' created";
        Assert.assertTrue(new AddressBookPage(driver).getMessageText().contains(""));


    }
}
