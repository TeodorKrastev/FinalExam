package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PostModal extends BasePage{

    @FindBy(tagName = "app-post-modal")
    WebElement modalDialog;

    @FindBy(css = ".like")
    WebElement likeButton;

    @FindBy(css = ".ml-4")
    WebElement dislikeButton;

    @FindBy(xpath = "//input[@placeholder='Comment here']")
    WebElement commentField;

    public PostModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void waitForDialogAppear(){
        smallWait.until(ExpectedConditions.visibilityOf(modalDialog));
    }

    public void likePost(){
        smallWait.until(ExpectedConditions.elementToBeClickable(likeButton));
        clickElement(likeButton);
    }
    public void dislikePost() {
        smallWait.until(ExpectedConditions.elementToBeClickable(likeButton));
        clickElement(dislikeButton);
    }
    public void waitForDialogAppear2() {
        smallWait.until(ExpectedConditions.visibilityOf(commentField));
    }

    public void writeComment(String comment) {
        commentField.sendKeys(comment);
        commentField.sendKeys(Keys.RETURN);
    }

    public boolean isCommentDisplayed(String comment) {
        WebElement newComment = driver.findElement(By.xpath(".//*[contains(text(), '" + comment + "')]"));
        return newComment.isDisplayed();
    }
}