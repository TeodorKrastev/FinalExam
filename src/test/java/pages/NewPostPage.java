package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class NewPostPage extends BasePage {
    private final String URL = "http://training.skillo-bg.com:4200/posts/create";

    @FindBy(css = "input[type='file']")
    WebElement fileUploadInput;

    @FindBy(id = "create-post")
    WebElement submitBtn;

    @FindBy(name = "caption")
    WebElement captionInput;

    @FindBy(css = "input.input-lg")
    WebElement fileNameField;

    @FindBy(css = ".image-preview")
    WebElement imagePreview;

    public NewPostPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyUrl() {
        verifyUrl(URL);
    }

    public void uploadImage(File file) {
        fileUploadInput.sendKeys(file.getAbsolutePath());
    }

    public void waitForImageToShow() {
        waitForVisibility(imagePreview);
    }

    public String getImageFileName() {
        waitForVisibility(fileNameField);
        return fileNameField.getAttribute("placeholder");
    }

    public void populateCaption(String text) {
        enterText(captionInput, text);
    }

    public void clickSubmitBtn() {
        clickElement(submitBtn);
    }

    public void uploadProfilePicture(File file) {
        fileUploadInput.sendKeys(file.getAbsolutePath());
    }

}
