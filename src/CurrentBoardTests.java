import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

    public class CurrentBoardTests extends TestBase {

        @BeforeMethod
        public void initTests() throws InterruptedException {
            WebElement loginIcon = driver.findElement(By.xpath("//a[@class='btn btn-sm btn-link text-white']"));
            loginIcon.click();
            Thread.sleep(3000);
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
        }

        @Test
        public void loginPositiveAndAddBoard() throws InterruptedException {
            System.out.println("Number of boards: " +
                    driver.findElements(By.xpath("//span[@class=\"board-tile-fade\"]")).size());
            driver.findElement(By.xpath("//div[@class=\"board-tile mod-add\"]")).click();
            WebElement nameBoardField = driver.findElement(By.xpath("//input[@data-test-id=\"create-board-title-input\"]"));
            nameBoardField.click();
            nameBoardField.clear();
            nameBoardField.sendKeys("TestBoard");
            driver.findElement(By.xpath("//button[@data-test-id=\"create-board-submit-button\"]")).click();
            Thread.sleep(5000);
            driver.findElement(By.xpath("//a[@data-test-id=\"header-home-button\"]")).click();
            Thread.sleep(5000);
            System.out.println("NEW number of boards: " +
                    driver.findElements(By.xpath("//span[@class=\"icon-lg icon-member\"]/following::span[@class=\"board-tile-fade\"]")).size());
        }

        @Test
        public void loginPositiveAndDeleteList() throws InterruptedException {

            driver.findElement(By.xpath("//div[@title=\"QA Haifa7\"]")).click();
            Thread.sleep(10000);

            if (driver.findElements(By.xpath("//div[@class=\"list js-list-content\"]")).size() == 0) {
                driver.findElement(By.xpath("//a[@class='open-add-list js-open-add-list']")).click();
                WebElement nameListField = driver.findElement(By.xpath("//input[@class=\"list-name-input\"]"));
                nameListField.click();
                nameListField.clear();
                Thread.sleep(5000);
                nameListField.sendKeys("Testlist");
                Thread.sleep(5000);
                driver.findElement(By.xpath("//input[@class='primary mod-list-add-button js-save-edit']")).click();
                Thread.sleep(10000);
                System.out.println(driver.findElements(By.xpath("//div[@class=\"list js-list-content\"]")).size());
                driver.findElement(By.xpath("//a[@class='list-header-extras-menu dark-hover js-open-list-menu icon-sm icon-overflow-menu-horizontal']")).click();
                driver.findElement(By.xpath("//a[@class='js-close-list']")).click();
                System.out.println(driver.findElements(By.xpath("//div[@class=\"list js-list-content\"]")).size());
            } else {
                System.out.println(driver.findElements(By.xpath("//div[@class=\"list js-list-content\"]")).size());
                driver.findElement(By.xpath("//a[@class='list-header-extras-menu dark-hover js-open-list-menu icon-sm icon-overflow-menu-horizontal']")).click();
                driver.findElement(By.xpath("//a[@class='js-close-list']")).click();
                System.out.println(driver.findElements(By.xpath("//div[@class=\"list js-list-content\"]")).size());
            }



        }
    }