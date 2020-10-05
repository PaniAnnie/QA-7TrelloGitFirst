import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProfileTests extends TestBase {

    @BeforeMethod
    public void initTests() {
        WebElement loginIcon = driver.findElement(By.xpath("//a[@class='btn btn-sm btn-link text-white']"));
        loginIcon.click();
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
        waitUntilElementIsClickable((By.xpath("//button[@data-test-id='header-boards-menu-button']")), 45);
        //Open board
        WebElement qa7HaifaBoard = driver.findElement(By.xpath("//li[@class='boards-page-board-section-list-item'][.//div[@title='QA Haifa7']]"));
        qa7HaifaBoard.click();
        waitUntilElementIsClickable(By.xpath("//button[@data-test-id=\"header-member-menu-button\"]"), 15);
        //Open Profile
        waitUntilElementIsClickable(By.xpath("//button[@data-test-id='header-member-menu-button']"),10);
        driver.findElement(By.xpath("//button[@data-test-id='header-member-menu-button']")).click(); //data-test-id="header-member-menu-button"
        waitUntilElementIsClickable(By.xpath("//a[@data-test-id = 'header-member-menu-profile']"),10);
        driver.findElement(By.xpath("//a[@data-test-id = 'header-member-menu-profile']")).click();
        waitUntilElementIsClickable(By.xpath("//a[@data-tab='profile']"),10);
    }

    @Test
    public void isThisProfilePage() {
        WebElement profileTab = driver.findElement(By.xpath("//a[@data-tab='profile']"));
        //Assert.assertEquals(profileTab.getText(), "Профиль и доступ");
        Assert.assertEquals(profileTab.getText(), "Profile and Visibility");
    }

    @Test
    public void isUsernameDisplayedCorrectly() {
        WebElement memberMenuIcon = driver.findElement(By.xpath("//button[@data-test-id='header-member-menu-button']"));
        String titleMenu = memberMenuIcon.getAttribute("title");
        String userNameInTitle = titleMenu.substring(titleMenu.indexOf("(")+1,titleMenu.length()-1);
        WebElement userNameField = driver.findElement(By.xpath("//input[@name='username']"));
        System.out.println("Username: " + userNameField.getAttribute("value"));
        Assert.assertEquals(userNameInTitle, userNameField.getAttribute("value"));
    }
}
