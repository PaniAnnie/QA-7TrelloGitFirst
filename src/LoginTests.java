import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void initTests() {
        waitUntilHomepageIsLoaded();
        openLoginPage();
        waitUntilLoginPageIsLoaded();
    }

    @Test
    public void loginNegativeLoginEmpty() {
        enterTruePassword();
        pressLoginButton();
        Assert.assertEquals(getErrorMessage(), "Missing email", "Message is not correct: ");
        //System.out.println("Test empty login: " + getErrorMessage());
    }

    @Test
    public void loginNegativeWrongLogin() {
        notAtlassianWrongUsername("neti");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys("PASSWORD");
        waitUntilElementIsClickable(By.id("login"), 10);
        pressLoginButton();
        waitUntilElementIsClickable(By.xpath("//div[@id='password-error']//p[@class='error-message']"), 20);
        System.out.println("Test wrong login: " + driver.findElement(By.xpath("//div[@id='password-error']//p[@class='error-message']")).getText());
        //Assert.assertTrue();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='password-error']//p[@class='error-message']")).getText(), "Incorrect email address and / or password. Do you need help logging in?",
                "Message is not correct: ");
    }

    @Test
    public void loginNegativeWrongPassword() {
        //wrong password
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
        passwordField.sendKeys("PASSWORD");
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
        WebElement loginField = driver.findElement(By.id("user"));
        loginField.click();
        loginField.clear();
        loginField.sendKeys(LOGIN);
        waitUntilElementIsClickable(By.xpath("//input[@value='Log in with Atlassian']"), 10);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        waitUntilElementIsClickable(By.id("password"), 15);
        enterTruePassword();
        waitUntilElementIsClickable(By.id("login-submit"), 10);
        driver.findElement(By.id("login-submit")).click();
        waitUntilElementIsPresent(By.xpath("//button[@data-test-id='header-boards-menu-button']"), 20);
        System.out.println("Text on the button: " + driver.findElement(By.xpath("//button[@data-test-id='header-boards-menu-button']")).getText());
        Assert.assertTrue(driver.findElement(By.xpath("//button[@data-test-id='header-boards-menu-button']"))
                .getText().equals("Boards"), "The text of the button is not 'Boards': ");
    }


    public void waitUntilLoginPageIsLoaded() {
        waitUntilElementIsClickable(By.id("login"), 10);
        waitUntilElementIsClickable(By.id("user"), 15);
        waitUntilElementIsClickable(By.id("password"), 15);
    }

    public void openLoginPage() {
        WebElement loginIcon = driver.findElement(By
                .xpath("//a[@class='btn btn-sm btn-link text-white']"));
        loginIcon.click();
    }

    public void waitUntilHomepageIsLoaded() {
        waitUntilElementIsClickable(By.xpath("//a[@class='btn btn-sm btn-link text-white']"), 30);
    }


    public String getErrorMessage(){
        waitUntilElementIsPresent(By.id("error"), 10);
        return driver.findElement(By.id("error")).getText();
    }

    public void pressLoginButton() {
        driver.findElement(By.id("login")).click();
    }

    public void enterTruePassword() {
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(PASSWORD);
    }

    public void notAtlassianWrongUsername(String login) {
        WebElement loginField = driver.findElement(By.id("user"));
        loginField.click();
        loginField.clear();
        loginField.sendKeys(login);
    }
}
