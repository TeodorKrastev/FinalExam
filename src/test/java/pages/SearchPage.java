package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(id = "search-bar")
    WebElement searchField;

    @FindBy(xpath = "//*[contains(text(), 'zelot')]")
    List<WebElement> searchResults;


    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goToSearchField() {
        largeWait.until(ExpectedConditions.elementToBeClickable(searchField)).click();
    }

    public void searchUser(String username) {
        largeWait.until(ExpectedConditions.visibilityOf(searchField)).sendKeys(username);

    }

    public void waitForUserInDropdown() {
        largeWait.until(ExpectedConditions.visibilityOfAllElements(searchResults));
    }

    public void clickOnUser(int index) {
        WebElement user = searchResults.get(index);
        user.click();
    }

}

