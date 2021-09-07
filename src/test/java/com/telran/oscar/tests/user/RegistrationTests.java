package com.telran.oscar.tests.user;

import com.telran.oscar.pages.home.HeaderPage;
import com.telran.oscar.pages.user.RegistrationAndLoginPage;
import com.telran.oscar.tests.TestBase;
import com.telran.oscar.utils.DataProviders;
import com.telran.oscar.utils.NewUserData;
import com.telran.oscar.utils.UserData;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {
    private final String wrongConfirmPasswordMessage = "The two password fields didn't match";
    private final String errorMessage = "Oops! We found some errors";
    private final String wrongEmailMessage = "Enter a valid email address.";

    @BeforeMethod
    public void ensurePrecondition(){
        new HeaderPage(driver).clickOnLoginOrRegisterBtn();
    }

    @Test(priority = 1, groups = "functional")
    public void newUserRegistrationPositiveTest(){
        new RegistrationAndLoginPage(driver)
                .fillRegistrationForm(NewUserData.email, NewUserData.password, NewUserData.password)
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
    @AfterMethod
    public void cleanUp(){
        HeaderPage header = new HeaderPage(driver);
        if(header.isLogoutBtnVisible()) {
            header.clickOnAccountBtn()
                    .clickOnDeleteProfileBtn()
                    .typePassword(NewUserData.password)
                    .clickOnDeleteProfileConfirmBtn();
        }

    }
}
