package tests;

import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import java.io.File;

public class TestTwo extends BaseMethod {
    @DataProvider(name = "getData")
    public Object[][] getData() {
        return new Object[][]{{"auto_user", "auto_pass"}};
    }

    @Test(dataProvider = "getData")
    public void deleteOnePhoto(String username, String password){
        System.out.println("1. Open homepage");
        HomePage homePage = new HomePage(driver);
        homePage.openSiteURl();

        System.out.println("2. Login with existing user");
        Header header = new Header(driver);
        header.goToLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyUrl();
        loginPage.login(username, password);

        System.out.println("3. Go to profile page and get current posts count");
        header.goToProfile();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.verifyUrl();
        int existingPosts = profilePage.getExistingPostCount();

        System.out.println("4. Open the latest post");
        int currentPostCount = profilePage.getExistingPostCount();
        profilePage.openPostByIndex(currentPostCount - 1);
        PostModal postModal = new PostModal(driver);
        postModal.waitForDialogAppear();

        System.out.println("5. Click delete and confirm.");
        header.setDeleteBtn();

        System.out.println("6. Verify the post number has decreased");
        Assert.assertEquals(currentPostCount, existingPosts - 1, "Incorrect post number");


    }


}
