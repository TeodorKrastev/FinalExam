package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserProfilePage extends BasePage {

    @FindBy(xpath = "//button[text()='Follow']")
    WebElement followButton;

    @FindBy(xpath = "//button[text()='Unfollow']")
    WebElement unFollowButton;

    public UserProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickFollow() {
        largeWait.until(ExpectedConditions.elementToBeClickable(followButton)).click();
    }
    public void clickUnfollow() {
        largeWait.until(ExpectedConditions.elementToBeClickable(unFollowButton)).click();
    }
}