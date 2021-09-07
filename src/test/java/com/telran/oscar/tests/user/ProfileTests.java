package com.telran.oscar.tests.user;

import com.telran.oscar.pages.home.HomePage;
import com.telran.oscar.pages.home.HeaderPage;
import com.telran.oscar.pages.user.ProfilePage;
import com.telran.oscar.pages.user.RegistrationAndLoginPage;
import com.telran.oscar.tests.TestBase;
import com.telran.oscar.utils.DataProviders;
import com.telran.oscar.utils.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProfileTests extends TestBase {

    private final String deleteProfileConfirmMessage = "Your profile has now been deleted. Thanks for using the site.";
    private final String editConfirmMessage = "Profile updated";
    private final String changePasswordConfirmationMessage = "Password updated";
    @BeforeMethod
    public void ensurePrecondition(){
        new HeaderPage(driver).clickOnLoginOrRegisterBtn();
    }


    @Test
    public void editProfilePositiveTest(){
        UserData.userFirstName = "Puli";
        UserData.userLastName = "Guli";
        new RegistrationAndLoginPage(driver).fillLogInForm(UserData.email, UserData.password).clickOnLogInBtn();
        new HeaderPage(driver).clickOnAccountBtn();
        ProfilePage profile = new ProfilePage(driver);
        profile.clickOnEditProfileBtn()
                .fillEditForm(UserData.userFirstName, UserData.userLastName, "")
                .clickOnSaveBtn();
        Assert.assertTrue(profile.getMessage().contains(editConfirmMessage));
        Assert.assertTrue(profile.isNameChanged(UserData.userFirstName + " " +  UserData.userLastName));

    }

    @Test
    public void changePasswordPositiveTest(String email, String password){
        UserData.password = "newPassword!1";
        new RegistrationAndLoginPage(driver).fillLogInForm(email, password).clickOnLogInBtn();
        new HeaderPage(driver).clickOnAccountBtn()
                .clickOnChangePasswordBtn()
                .fillChangePasswordForm(password, UserData.password, UserData.password)
                .clickOnSaveBtn();
        Assert.assertTrue(new ProfilePage(driver).getMessage().contains(changePasswordConfirmationMessage));

    }

    @Test
    public void deleteUserProfilePositive(){
        new RegistrationAndLoginPage(driver).fillLogInForm(UserData.email, UserData.password).clickOnLogInBtn();
        new HeaderPage(driver).clickOnAccountBtn()
                .clickOnDeleteProfileBtn()
                .typePassword(UserData.password)
                .clickOnDeleteProfileConfirmBtn();
        Assert.assertTrue(new HomePage(driver).getConfirmDeleteProfileMessage().contains(deleteProfileConfirmMessage));
    }
}
