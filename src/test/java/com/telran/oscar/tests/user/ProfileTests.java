package com.telran.oscar.tests.user;

import com.telran.oscar.pages.HomePage;
import com.telran.oscar.pages.NavigationPage;
import com.telran.oscar.pages.PageBase;
import com.telran.oscar.pages.user.ProfilePage;
import com.telran.oscar.pages.user.RegistrationAndLoginPage;
import com.telran.oscar.tests.TestBase;
import com.telran.oscar.utils.DataProviders;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProfileTests extends TestBase {

    private final String deleteProfileConfirmMessage = "Your profile has now been deleted. Thanks for using the site.";
    private final String editConfirmMessage = "Profile updated";
    private final String changePasswordConfirmationMessage = "Password updated";
    @BeforeMethod
    public void ensurePrecondition(){
        new NavigationPage(driver).clickOnLoginOrRegisterBtn();
    }

  @Test(dataProviderClass = DataProviders.class, dataProvider = "registrationAndLoginPositive")
    public void deleteUserProfilePositive(String email, String password){
        new RegistrationAndLoginPage(driver).fillLogInForm(email, password).clickOnLogInBtn();

        new NavigationPage(driver).clickOnAccountBtn()
                .clickOnDeleteProfileBtn()
                .typePassword(password)
                .clickOnDeleteProfileConfirmBtn();
      Assert.assertTrue(new HomePage(driver).getConfirmDeleteProfileMessage().contains(deleteProfileConfirmMessage));
    }

    @Test(priority = 1, dataProviderClass = DataProviders.class, dataProvider = "registrationAndLoginPositive")
    public void editProfilePositiveTest(String email, String password){
        new RegistrationAndLoginPage(driver).fillLogInForm(email, password).clickOnLogInBtn();
        new NavigationPage(driver).clickOnAccountBtn();

        new ProfilePage(driver).clickOnEditeProfileBtn()
                .fillEditForm("Puli", "Guli", "")
                .clickOnSaveBtn();
        Assert.assertTrue(new ProfilePage(driver).getMessage().contains(editConfirmMessage));
        Assert.assertTrue(new ProfilePage(driver).isNameChanged("Puli Guli"));

    }

    @Test(priority = 1, dataProviderClass = DataProviders.class, dataProvider = "registrationAndLoginPositive")
    public void changePasswordPositiveTest(String email, String password){
        new RegistrationAndLoginPage(driver).fillLogInForm(email, password).clickOnLogInBtn();
        new NavigationPage(driver).clickOnAccountBtn();

        new ProfilePage(driver).clickOnChangePasswordBtn()
                .fillChangePasswordForm(password, "newPassword!1", "newPassword!1")
                .clickOnSaveBtn();
        Assert.assertTrue(new ProfilePage(driver).getMessage().contains(changePasswordConfirmationMessage));

    }
}
