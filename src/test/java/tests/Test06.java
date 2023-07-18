package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

public class Test06 extends BaseMethod {
    @DataProvider(name = "getData")
    public Object[][] getData() {
        return new Object[][]{{"Teodor123", "teodor123"}};
    }

    @Test(dataProvider = "getData")
    public void testUnfollowingSpecificUser(String username, String password) {
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

        System.out.println("4. Get number of current followers.");
        ProfilePage profilePage = new ProfilePage(driver);
        int currentFollowingCount = profilePage.getFollowingCount();

        System.out.println("5. Go to search field.");
        SearchPage searchPage = new SearchPage(driver);
        searchPage.goToSearchField();

        System.out.println("6. Find specific person and unfollow him/her."); // the user will be "zelot"
        UserProfilePage userProfilePage = new UserProfilePage(driver);
        searchPage.searchUser("zelot");
        searchPage.waitForUserInDropdown();
        searchPage.clickOnUser(0);
        userProfilePage.clickUnfollow();

        System.out.println("7. Go to  and verify that following number is decreased.");
        header.goToProfile();
        profilePage.waitForFollowingCountDecrease(currentFollowingCount);

        int newFollowersCount = profilePage.getFollowingCount();
        Assert.assertEquals(newFollowersCount, currentFollowingCount - 1, "Following number is not decreased.");
    }
}
