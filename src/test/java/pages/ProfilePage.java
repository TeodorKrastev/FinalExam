package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class ProfilePage extends BasePage {
    private final String BASE_URL = "http://training.skillo-bg.com:4200/users/";

    @FindBy(css = ".profile-user-settings > h2")
    WebElement usernameHeader;

    @FindBy(css="app-post")
    List<WebElement> existingPosts;

    @FindBy(css = "span.following-count")
    List<WebElement> followerCount;

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

    public  void openPostByIndex(int index){
        clickElement(existingPosts.get(index));
    }

    public int getFollowingCount() {
        WebElement followersElement = driver.findElement(By.id("following"));
        String followingText = followersElement.getText();
        String cleanedText = followingText.replaceAll("\\D+", "");
        return Integer.parseInt(cleanedText);
    }

}