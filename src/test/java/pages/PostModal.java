package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class PostModal extends BasePage {

    @FindBy(tagName = "app-post-modal")
    WebElement modalDialog;

    @FindBy(css = ".modal-content .post-info-container .like")
    WebElement likeButton;

    @FindBy(css = ".modal-content .post-info-container .ml-4")
    WebElement dislikeButton;

    @FindBy(css = "input[placeholder='Comment here']")
    WebElement commentField;

    @FindBy(css = ".modal-content .post-info-container .liked")
    WebElement likeIcon;

    @FindBy(css = ".modal-content .post-info-container .liked")
    WebElement dislikeIcon;

    public PostModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitForDialogAppear() {
        waitForVisibility(modalDialog);
    }

    public void likePost() {
        smallWait.until(ExpectedConditions.elementToBeClickable(likeButton));
        clickElement(likeButton);
    }

    public void dislikePost() {
        smallWait.until(ExpectedConditions.elementToBeClickable(dislikeButton));
        clickElement(dislikeButton);
    }

    public void waitForDialogAppear2() {
        waitForVisibility(commentField);
    }

    public void writeComment(String comment) {
        enterText(commentField, comment);
        commentField.sendKeys(Keys.RETURN);
    }

    public String getCommentText() {
        WebElement newComment = driver.findElement(By.xpath(".//*[contains(text(), 'Test Test.')]"));
        return newComment.getText();
    }

    public boolean verifyLikeIconChanged() {
        String iconClass = likeIcon.getAttribute("class");
        Assert.assertTrue(iconClass.contains("liked"), "Dislike icon is not changed.");
        return false;
    }

    public void verifyDislikeIconChanged() {
        String iconClass = dislikeIcon.getAttribute("class");
        Assert.assertTrue(iconClass.contains("liked"), "Dislike icon is not changed.");
    }
}