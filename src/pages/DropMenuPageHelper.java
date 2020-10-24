package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DropMenuPageHelper extends PageBase{
    @FindBy(xpath = "//a[@data-test-id = 'header-member-menu-profile']")
    WebElement linkToProfile;

    public DropMenuPageHelper(WebDriver driver) {
    super(driver);
    }

    public DropMenuPageHelper waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(linkToProfile,10);
        return this;
    }

    public DropMenuPageHelper openProfilePage() {
        linkToProfile.click();
        return this;
    }

}
