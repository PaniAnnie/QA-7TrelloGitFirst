import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    WebDriver driver;
    ChromeOptions options = new ChromeOptions();

    protected static final String PASSWORD = "Testing1947!";
    protected static final String LOGIN = "annaollap@gmail.com";

    @BeforeMethod
    public void startAppl() {
        options.addArguments("--lang=" + "en");
        driver = new ChromeDriver(options);
        driver.get("https://trello.com");
        waitUntilElementIsClickable(By.xpath("//a[@class='btn btn-sm btn-link text-white']"), 10);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public void waitUntilElementIsClickable(By locator, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsPresent(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
