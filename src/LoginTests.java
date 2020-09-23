import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void initTests() throws InterruptedException {
        WebElement loginIcon = driver.findElement(By.xpath("//a[@class='btn btn-sm btn-link text-white']"));
        loginIcon.click();
        Thread.sleep(3000);
    }

    @Test
    public void loginNegativeLoginEmpty() throws InterruptedException {
        //empty login
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(PASSWORD);
        driver.findElement(By.id("login")).click();
        Thread.sleep(1000);
        WebElement errorMessage = driver.findElement(By.xpath("//p[@class='error-message']"));
        System.out.println("Test empty login: " + errorMessage.getText());
    }

    @Test
    public void loginNegativeWrongLogin() throws InterruptedException {
        //wrong login
        WebElement loginField = driver.findElement(By.id("user"));
        loginField.click();
        loginField.clear();
        loginField.sendKeys("annao");
        Thread.sleep(3000);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(PASSWORD);
        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        Thread.sleep(5000);
        System.out.println("Test wrong login: " + driver.findElement(By.id("error")).getText());
    }


    @Test
    public void loginNegativeWrongPassword() throws InterruptedException {
        //wrong password
        WebElement loginField = driver.findElement(By.id("user"));
        loginField.click();
        loginField.clear();
        loginField.sendKeys(LOGIN);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(10000);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys("1111");
        Thread.sleep(1000);
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(20000);
        WebElement errorMessage = driver.findElement(By.id("login-error"));
        System.out.println("Test wrong password: " + errorMessage.getText());

    }
    @Test
    public void loginPositiveAndBoardButton() throws InterruptedException {
        //positive
        WebElement loginField = driver.findElement(By.id("user"));
        loginField.click();
        loginField.clear();
        loginField.sendKeys(LOGIN);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(10000);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(PASSWORD);
        Thread.sleep(1000);
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(20000);
        System.out.println(driver.findElement(By.xpath("//button[@data-test-id='header-boards-menu-button']")).getText());

    }
}
