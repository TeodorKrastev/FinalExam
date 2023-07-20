package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

public class Test08 extends BaseMethod {
    @DataProvider(name = "getData")
    public Object[][] getData() {
        return new Object[][]{{"Teodor123", "teodor123"}};
    }

    @Test(dataProvider = "getData")
    public void deleteOnePhoto(String username, String password) {
        System.out.println("1. Open homepage.");
        HomePage homePage = new HomePage(driver);
        homePage.openSiteURl();

        System.out.println("2. Login with existing user.");
        Header header = new Header(driver);
        header.goToLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyUrl();
        loginPage.login(username, password);

        System.out.println("3. Go to profile page on all posts and get current posts count.");
        header.goToProfile();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.goToAllPosts();
        profilePage.verifyUrl();

        int currentPostCount = profilePage.getExistingPostCount();

        if (currentPostCount == 0) {
            System.out.println("No posts available for deletion.");
            return;
        }

        System.out.println("4. Open the latest post.");
        profilePage.openPostByIndex(0);

        System.out.println("5. Click delete and confirm.");
        header.clickDeleteBtn();
        header.clickConfirmBtn();

        System.out.println("6. Check if the pop-up confirmation has appeared.");
        Assert.assertTrue(driver.findElement(By.id("toast-container")).isDisplayed(), "Confirmation does not appear.");

        System.out.println("7. Confirm that there are no posts.");
        profilePage.goToAllPosts();
        int existingPosts = profilePage.getExistingPostCount();
        Assert.assertEquals(existingPosts, currentPostCount - 1, "Incorrect post number.");
    }
}
