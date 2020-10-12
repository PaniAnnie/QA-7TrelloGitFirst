package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageHelper extends PageBase {

    public LoginPageHelper(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void loginNotAtlassianUser(String login, String password) {
        loginNotAtlassianUsername(login);
        enterPassword(password);
        pressLoginButton();
    }

    public void loginAtlassianUser(String login, String password) {
        loginAtlassianUsername(login);
        loginUserAsAtlassian();
        enterPassword(password);
        pressAtlassianLoginButton();
    }


    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(By.id("user"), 15);
        waitUntilElementIsClickable(By.id("password"), 15);
        waitUntilElementIsClickable(By.id("login"), 10);
    }

    public void loginNotAtlassianUsername(String login) {
        WebElement loginField = driver.findElement(By.id("user"));
        loginField.click();
        loginField.clear();
        loginField.sendKeys(login);
    }

    public void loginAtlassianUsername(String login) {
        WebElement loginField = driver.findElement(By.id("user"));
        loginField.click();
        loginField.clear();
        loginField.sendKeys(login);
    }

    public void enterPassword(String password) {
        waitUntilElementIsClickable(By.id("password"), 15);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void pressLoginButton() {
        waitUntilElementIsClickable(By.id("login"), 15);
        driver.findElement(By.id("login")).click();
    }

    public void loginUserAsAtlassian() {
        waitUntilElementIsClickable(By.xpath("//input[@value='Log in with Atlassian']"), 10);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    public void pressAtlassianLoginButton() {
        waitUntilElementIsClickable(By.id("login-submit"), 10);
        driver.findElement(By.id("login-submit")).click();
    }

    public String getErrorMessage(){
        waitUntilElementIsPresent(By.id("error"), 10);
        return driver.findElement(By.id("error")).getText();
    }

    public String getWrongLoginErrorMessage(){
        waitUntilElementIsClickable(By.xpath("//div[@id='password-error']//p[@class='error-message']"), 20);
        return driver.findElement(By.xpath("//div[@id='password-error']//p[@class='error-message']")).getText();
    }

    public String getAtlassianErrorMessage(){
        waitUntilElementIsClickable(By.id("login-error"), 20);
        return driver.findElement(By.id("login-error")).getText();
    }
}
