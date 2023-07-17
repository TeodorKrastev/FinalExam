package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import java.io.File;

public class Test06 extends BaseMethod {
    @DataProvider(name = "getData")
    public Object[][] getData() {
        return new Object[][]{{"Teodor123", "teodor123", new File("src/test/java/upload/Road.jpg")}};
    }

    @Test(dataProvider = "getData")
    public void testChangeProfilePhoto(String username, String password, File file) {
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

        System.out.println("4. Click on change profile picture button.");
        NewPostPage postPage = new NewPostPage(driver);

        System.out.println("5. Upload a new picture.");
        postPage.uploadProfilePicture(file);

        System.out.println("6. Check if the pop-up confirmation has appeared.");
        Assert.assertTrue(driver.findElement(By.id("toast-container")).isDisplayed(), "Confirmation does not appear.");
    }
}
