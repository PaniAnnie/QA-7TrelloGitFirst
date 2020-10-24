package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePageHelper extends PageBase{

    @FindBy(xpath = "//a[@data-tab='profile']")
    WebElement profileTab;

    @FindBy(xpath = "//input[@name='username']")
    WebElement memberUsername;

    @FindBy(xpath = "//a[@data-tab='activity']")
    WebElement activityTab;

    public ProfilePageHelper(WebDriver driver){
        super(driver);
    }

    public ProfilePageHelper waitUntilPageIsLoaded() {
        waitUntilElementIsVisible(memberUsername,10);
        waitUntilElementIsClickable(profileTab,10);
        return this;
    }

    public String getProfileTabName() {
        return profileTab.getText();
    }

    public String getUserNameFromProfile() {
        return memberUsername.getAttribute("value");
    }

    public ProfilePageHelper openActivityTab(){
        waitUntilElementIsClickable(activityTab, 20);
        activityTab.click();
        return this;
    }
}
