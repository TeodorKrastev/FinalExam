package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = ".post-feed-img")
    WebElement userPost;

    private final String URL = "http://training.skillo-bg.com:4200/posts/all";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openSiteURl() {
        driver.get(URL);
    }

    public void verifyUrl() {
        verifyUrl(URL);
    }

}