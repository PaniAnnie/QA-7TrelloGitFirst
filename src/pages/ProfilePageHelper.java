package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePageHelper extends PageBase{
    public ProfilePageHelper(WebDriver driver){
        super(driver);
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(By.xpath("//a[@data-tab='profile']"),10);
    }

    public String getProfileTabName() {
        WebElement profileTab = driver.findElement(By.xpath("//a[@data-tab='profile']"));
        return profileTab.getText();
    }

    public String getTitleOfMenuButton() {
        WebElement memberMenuIcon = driver.findElement(By.xpath("//button[@data-test-id='header-member-menu-button']"));
        String titleMenuButton = memberMenuIcon.getAttribute("title");
        return titleMenuButton;
    }

    public String getUserNameFromProfile() {
        WebElement userNameField = driver.findElement(By.xpath("//input[@name='username']"));
        return userNameField.getAttribute("value");
    }
}
