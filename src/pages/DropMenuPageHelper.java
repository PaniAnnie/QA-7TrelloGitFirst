package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DropMenuPageHelper extends PageBase{
    public DropMenuPageHelper(WebDriver driver) {
    super(driver);
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(By.xpath("//a[@data-test-id = 'header-member-menu-profile']"),10);
    }

    public void openProfilePage() {
        driver.findElement(By.xpath("//a[@data-test-id = 'header-member-menu-profile']")).click();
    }

}
