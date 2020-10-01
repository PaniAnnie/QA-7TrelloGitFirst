import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void initTests() {
        WebElement loginIcon = driver.findElement(By.xpath("//a[@class='btn btn-sm btn-link text-white']"));
        loginIcon.click();
    }

    @Test
    public void loginNegativeLoginEmpty() {
        //empty login
        waitUntilElementIsClickable(By.id("password"), 15);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(PASSWORD);
        waitUntilElementIsClickable(By.id("login"), 10);
        driver.findElement(By.id("login")).click();
        waitUntilElementIsPresent(By.id("error"), 10);
        WebElement errorMessage = driver.findElement(By.id("error"));
        System.out.println("Test empty login: " + errorMessage.getText());
        Assert.assertEquals(errorMessage.getText(), "Missing email", "Message is not correct: ");
    }

    @Test
    public void loginNegativeWrongLogin() {
        //wrong login
        //тест перестал проходить, и я не могу разобраться, почему.
        waitUntilElementIsClickable(By.id("user"), 15);
        WebElement loginField = driver.findElement(By.id("user"));
        loginField.click();
        loginField.clear();
        loginField.sendKeys("neti");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(PASSWORD);
        waitUntilElementIsClickable(By.xpath("//input[@type='submit']"), 20);
        WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));
        loginButton.click();
        waitUntilElementIsClickable(By.id("error"), 20);
        System.out.println("Test wrong login: " + driver.findElement(By.id("error")).getText());
        Assert.assertEquals(driver.findElement(By.id("error")).getText(), "We can’t log you in to Trello right now. We’ve sent you an email with instructions.",
                "Message is not correct: ");
    }

    @Test
    public void loginNegativeWrongPassword() {
        //wrong password
        waitUntilElementIsClickable(By.id("user"), 15);
        WebElement loginField = driver.findElement(By.id("user"));
        loginField.click();
        loginField.clear();
        loginField.sendKeys(LOGIN);
        waitUntilElementIsClickable(By.xpath("//input[@value='Log in with Atlassian']"), 10);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        waitUntilElementIsClickable(By.id("password"), 15);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys("1111");
        waitUntilElementIsClickable(By.id("login-submit"), 10);
        driver.findElement(By.id("login-submit")).click();
        waitUntilElementIsClickable(By.id("login-error"), 20);
        System.out.println("Test wrong password: " + driver.findElement(By.id("login-error")).getText());
        Assert.assertEquals(driver.findElement(By.id("login-error")).getText(), "Incorrect email address and / or password.\n" +
                "Do you need help logging in?", "Message is not correct: ");
    }

    @Test
    public void loginPositiveAndBoardButton() {
        //positive
        waitUntilElementIsClickable(By.id("user"), 15);
        WebElement loginField = driver.findElement(By.id("user"));
        loginField.click();
        loginField.clear();
        loginField.sendKeys(LOGIN);
        waitUntilElementIsClickable(By.xpath("//input[@value='Log in with Atlassian']"), 10);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        waitUntilElementIsClickable(By.id("password"), 15);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(PASSWORD);
        waitUntilElementIsClickable(By.id("login-submit"), 10);
        driver.findElement(By.id("login-submit")).click();
        waitUntilElementIsPresent(By.xpath("//button[@data-test-id='header-boards-menu-button']"), 20);
        System.out.println("Text on the button: " + driver.findElement(By.xpath("//button[@data-test-id='header-boards-menu-button']")).getText());
        Assert.assertTrue(driver.findElement(By.xpath("//button[@data-test-id='header-boards-menu-button']"))
                .getText().equals("Boards"), "The text of the button is not 'Boards': ");
    }
}
