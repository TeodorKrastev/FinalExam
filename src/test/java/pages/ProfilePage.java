package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProfilePage extends BasePage {
    private final String BASE_URL = "http://training.skillo-bg.com:4200/users/";

    @FindBy(css = "app-post")
    List<WebElement> existingPosts;

    @FindBy(id = "following")
    WebElement followersElement;

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyUrl() {
        verifyUrlContains(BASE_URL);
    }

    public int getExistingPostCount() {
        smallWait.until(ExpectedConditions.visibilityOfAllElements(existingPosts));

        return existingPosts.size();
    }

    public void openPostByIndex(int index) {
        clickElement(existingPosts.get(index));
    }

    public int getFollowingCount() {
        waitForVisibility(followersElement);
        String followingText = followersElement.getText();
        String cleanedText = followingText.replaceAll("\\D+", "");
        return Integer.parseInt(cleanedText);
    }

    public void waitForFollowingCountIncrease(int initialCount) {
        smallWait.until(ExpectedConditions.textToBePresentInElement(followersElement, Integer.toString(initialCount + 1)));
    }
    public void waitForFollowingCountDecrease(int initialCount) {
        smallWait.until(ExpectedConditions.textToBePresentInElement(followersElement, Integer.toString(initialCount - 1)));
    }
}
