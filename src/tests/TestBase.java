package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    WebDriver driver;
    ChromeOptions options = new ChromeOptions();

    public static final String PASSWORD = "Testing1947!";
    public static final String LOGIN = "annaollap@gmail.com";

    @BeforeMethod
    public void startAppl() {
        options.addArguments("--lang=" + "en");
        driver = new ChromeDriver(options);
        driver.get("https://trello.com");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
