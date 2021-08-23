package com.telran.oscar.tests.user;

import com.telran.oscar.pages.NavigationPage;
import com.telran.oscar.pages.user.PasswordResetPage;
import com.telran.oscar.pages.user.RegistrationAndLoginPage;
import com.telran.oscar.tests.TestBase;
import com.telran.oscar.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        new NavigationPage(driver).clickOnLoginOrRegisterBtn();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider ="registrationAndLoginPositive")
    public void loginPositiveTest(String email, String pass){
        new RegistrationAndLoginPage(driver).fillLogInForm(email, pass).clickOnLogInBtn();
        Assert.assertTrue(new RegistrationAndLoginPage(driver).isLogOutBtnDisplayed());
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider ="loginNegativeIncorrectEmail")
    public void loginNegativeWrongEmailTest(String email, String pass){
        new RegistrationAndLoginPage(driver).fillLogInForm(email, pass).clickOnLogInBtn();
        Assert.assertTrue(new RegistrationAndLoginPage(driver).isRegisterFormDisplayed());
        Assert.assertTrue(new RegistrationAndLoginPage(driver).getTopMessage().contains("Oops! We found some errors "));
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "loginNegativeIncorrectPassword")
    public void loginNegativeWrongPasswordTest(String email, String pass){
        new RegistrationAndLoginPage(driver).fillLogInForm(email, pass).clickOnLogInBtn();
        Assert.assertTrue(new RegistrationAndLoginPage(driver).isRegisterFormDisplayed());
        Assert.assertTrue(new RegistrationAndLoginPage(driver).getTopMessage().contains("Oops! We found some errors "));
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider ="registrationAndLoginPositive")
    public void loginForgottenPassword(String email, String pass) {
        new RegistrationAndLoginPage(driver).clickOnForgottenPasswordLink();
        new PasswordResetPage(driver).fillEmail(email).clickOnSendResetEmail();
        Assert.assertTrue(new PasswordResetPage(driver).getTitleEmailSend().contains("Email sent"));
    }

}
