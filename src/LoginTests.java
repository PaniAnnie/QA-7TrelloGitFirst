import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    }

    @Test
    public void loginNegativeWrongLogin() {
        //wrong login
        waitUntilElementIsClickable(By.id("user"), 15);
        WebElement loginField = driver.findElement(By.id("user"));
        loginField.click();
        loginField.clear();
        loginField.sendKeys("annao");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(PASSWORD);
        waitUntilElementIsClickable(By.id("login"), 20);
        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        //waitUntilElementIsPresent(By.id("error"), 20);
        //waitUntilElementIsPresent(By.xpath("//p[@class='error-message']"), 20);
        // ===== с локатором по id или простому xpath тест проходит, но сообщение не выводится.
        // Через Thread.sleep выводилось. В чем проблема, непонятно. =====================

        //waitUntilElementIsPresent(By.xpath("//p[contains(text(),'Trello')]"), 20);
        // ===== c waitUntilElementIsPresent выводит сообщение только по такому локатору!!! ======

        // ==== c waitUntilElementIsClickable тест проходит, сообщение выводится, лишних задержек нет.
        // Но ведь сообщение - не кликабельный элемент?
        waitUntilElementIsClickable(By.id("error"), 20);
        System.out.println("Test wrong login: " + driver.findElement(By.id("error")).getText());
    }

    @Test
    public void loginNegativeWrongPassword() {
        //wrong password
        // те же вопросы, что и в предыдущем тесте!
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
        //waitUntilElementIsPresent(By.id("login-error"), 20);
        // ===== с локатором по id тест проходит, но сообщение не выводится.
        // Через Thread.sleep выводилось. В чем проблема, непонятно. =====================

        //waitUntilElementIsPresent(By.xpath("//div[@id='login-error']//span//a"), 20);
        // ===== c waitUntilElementIsPresent выводит сообщение только по такому локатору!!! ======

        // ==== c waitUntilElementIsClickable тест проходит, сообщение выводится, лишних задержек нет.
        // Но ведь сообщение - не кликабельный элемент?
        waitUntilElementIsClickable(By.id("login-error"), 20);
        System.out.println("Test wrong password: " + driver.findElement(By.id("login-error")).getText());
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
    }
}
