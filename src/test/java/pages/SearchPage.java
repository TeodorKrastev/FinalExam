package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void goToSearchField() {
        driver.findElement(By.id("search-bar")).click();
    }
    public void searchUser(String username) {
        driver.findElement(By.id("search-bar")).sendKeys(username);
    }

    public void clickFollow() {
        mediumWait.until(ExpectedConditions.visibilityOfAllElements());
        driver.findElement(By.xpath("//button[text()='Follow']"));
    }
    public void openUserProfile(String profileLink) {
        driver.get(profileLink);

    }

}
