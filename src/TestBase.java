import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    WebDriver driver;
    protected static final String PASSWORD = "Johndeacon1947!";
    protected static final String LOGIN = "annaollap@gmail.com";

    @BeforeMethod
    public void startAppl() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://trello.com");
        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown() {
       driver.quit();
    }
}
