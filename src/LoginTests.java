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
    public void loginNegativeWrongInput() throws InterruptedException {

        WebElement loginField = driver.findElement(By.id("user"));
        loginField.click();
        loginField.clear();
        loginField.sendKeys("trrrrr@mail.com");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys("11111111");
        driver.findElement(By.id("login")).click();
        Thread.sleep(1000);
        WebElement errorMessage = driver.findElement(By.xpath("//p[@class='error-message']"));
        System.out.println(errorMessage.getText());
    }

    @Test
    public void loginNegativeWrongPassword() throws InterruptedException {
        WebElement loginField = driver.findElement(By.xpath("//input[@name='user']"));
        loginField.click();
        loginField.clear();
        loginField.sendKeys("netimeny@inbox.ru");
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys("11111111");
        driver.findElement(By.cssSelector("#login")).click();
        Thread.sleep(1000);
        WebElement errorMessage = driver.findElement(By.xpath("//p[contains(text(),'.')]"));
        System.out.println(errorMessage.getText());
        driver.quit();
    }
    @Test
    public void loginPositiveAndBoardButton() throws InterruptedException {
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
        System.out.println(driver.findElement(By.xpath("//button[@data-test-id=\"header-boards-menu-button\"]")).getText());

    }
}
