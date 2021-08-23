package com.telran.oscar.tests.user;

import com.telran.oscar.pages.NavigationPage;
import com.telran.oscar.pages.user.RegistrationAndLoginPage;
import com.telran.oscar.tests.TestBase;
import com.telran.oscar.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {
    private final String wrongConfirmPasswordMessage = "The two password fields didn't match";
    private final String errorMessage = "Oops! We found some errors";
    private final String wrongEmailMessage = "Enter a valid email address.";

    @BeforeMethod
    public void ensurePrecondition(){
        new NavigationPage(driver).clickOnLoginOrRegisterBtn();
    }

    @Test(priority = 1, groups = "functional", dataProviderClass = DataProviders.class, dataProvider = "registrationAndLoginPositive")
    public void newUserRegistrationPositiveTest(String email, String pass){
        new RegistrationAndLoginPage(driver).fillRegistrationForm(email, pass, pass)
                .clickOnRegisterBtn();
        Assert.assertTrue(new RegistrationAndLoginPage(driver).isLogOutBtnDisplayed());
    }


    @Test(dataProviderClass = DataProviders.class, dataProvider = "loginNegativeIncorrectEmail")
    public  void newUserRegistrationWrongEmailTest(String email, String password){
        new RegistrationAndLoginPage(driver).fillRegistrationForm(email, password, password)
                .clickOnRegisterBtn();
        Assert.assertTrue(new RegistrationAndLoginPage(driver).isRegisterFormDisplayed());
       // System.out.println("Message: " + new RegistrationAndLoginPage(driver).getErrorMessage());
        // Assert.assertTrue(new RegistrationAndLoginPage(driver).getTopMessage().contains("Oops! We found some errors "));
      // Assert.assertTrue(new RegistrationAndLoginPage(driver).getErrorMessage().contains(" Enter a valid email address"));
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "loginNegativeIncorrectPassword")
    public void newUserRegistrationWrongPasswordTest(String email, String password){
        new RegistrationAndLoginPage(driver).fillRegistrationForm(email, password, password)
                .clickOnRegisterBtn();
        Assert.assertTrue(new RegistrationAndLoginPage(driver).isRegisterFormDisplayed());
    }

    @Test(priority = 2, groups = "functional", dataProviderClass = DataProviders.class, dataProvider = "loginNegativeIncorrectConfirmPassword")
    public void newUserRegistrationWrongConfirmPassword(String email, String password, String confirmPassword){
        new RegistrationAndLoginPage(driver).fillRegistrationForm(email, password, confirmPassword)
                .clickOnRegisterBtn();
        Assert.assertTrue(new RegistrationAndLoginPage(driver).isRegisterFormDisplayed());
        //System.out.println("Wrong confirm pass message: " + new RegistrationAndLoginPage(driver).getErrorMessage());
        Assert.assertTrue(new RegistrationAndLoginPage(driver).getErrorMessage().contains(wrongConfirmPasswordMessage));

    }
}
