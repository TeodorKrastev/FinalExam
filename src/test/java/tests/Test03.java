package tests;

import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

public class Test03 extends BaseMethod {
    @DataProvider(name = "getData")
    public Object[][] getData() {
        return new Object[][]{{"Teodor123", "teodor123",}};
    }

    @Test(dataProvider = "getData")
    public void testLikeOnePost(String username, String password) {
        System.out.println("1. Open homepage.");
        HomePage homePage = new HomePage(driver);
        homePage.openSiteURl();

        System.out.println("2. Login with existing user.");
        Header header = new Header(driver);
        header.goToLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyUrl();
        loginPage.login(username, password);

        System.out.println("3. Go to profile page.");
        header.goToProfile();

        System.out.println("4. Open the latest post.");
        ProfilePage profilePage = new ProfilePage(driver);
        int currentPostCount = profilePage.getExistingPostCount();
        profilePage.openPostByIndex(currentPostCount - 1);
        PostModal postModal = new PostModal(driver);
        postModal.waitForDialogAppear();

        System.out.println("5. Like the post.");
        postModal.likePost();

        System.out.println("6. Check if the pop-up confirmation has appeared.");
        Assert.assertTrue(driver.findElement(By.id("toast-container")).isDisplayed(), "Confirmation does not appear.");

        System.out.println("7. Verify that the icon is changed.");
        postModal.verifyLikeIconChanged();
    }

}