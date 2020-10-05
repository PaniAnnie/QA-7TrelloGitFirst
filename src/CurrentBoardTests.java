import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

    public class CurrentBoardTests extends TestBase {

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
            waitUntilElementIsClickable(By.id("workspaces-preamble-board-header-button"), 15);
            waitUntilElementIsPresent(By.tagName("h1"), 10);
            //waitUntilElementIsClickable(By.xpath("//a[@class='open-add-list js-open-add-list']"), 10);
        }

        @Test
        public void isCorrectCurrentBoard() {
            System.out.println("Header of the current board: " + driver.findElement(By.tagName("h1")).getText());
            Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(), "QA Haifa7", "Header of the current board is wrong: ");
        }

        @Test
        public void loginPositiveAndAddList() {
            System.out.println("Number of lists: " + driver.findElements(By.xpath("//div[@class='list js-list-content']")).size());
            int quantityOfListsInTheBeginning = driver.findElements(By.xpath("//div[@class='list js-list-content']")).size();
            driver.findElement(By.xpath("//a[@class='open-add-list js-open-add-list']")).click();
            waitUntilElementIsClickable(By.xpath("//input[@class='list-name-input']"), 10);
            WebElement nameListField = driver.findElement(By.xpath("//input[@class='list-name-input']"));
            nameListField.click();
            nameListField.clear();
            nameListField.sendKeys("Testlist");
            waitUntilElementIsClickable(By.xpath("//input[@class='primary mod-list-add-button js-save-edit']"), 10);
            driver.findElement(By.xpath("//input[@class='primary mod-list-add-button js-save-edit']")).click();
            waitUntilElementIsInvisible(By.xpath("//input[@class='primary mod-list-add-button js-save-edit']"), 10);
            System.out.println("Number of lists after adding: " + driver.findElements(By.xpath("//div[@class='list js-list-content']")).size());
            int quantityOfListsInTheEnd = driver.findElements(By.xpath("//div[@class='list js-list-content']")).size();
            Assert.assertEquals(quantityOfListsInTheEnd, quantityOfListsInTheBeginning+1, "Something goes wrong: ");
        }

        @Test
        public void loginPositiveAndDeleteList() {
            if (driver.findElements(By.xpath("//div[@class='list js-list-content']")).size() == 0) {
                driver.findElement(By.xpath("//a[@class='open-add-list js-open-add-list']")).click();
                waitUntilElementIsClickable(By.xpath("//input[@class='list-name-input']"), 10);
                WebElement nameListField = driver.findElement(By.xpath("//input[@class='list-name-input']"));
                nameListField.click();
                nameListField.clear();
                nameListField.sendKeys("Testlist");
                waitUntilElementIsClickable(By.xpath("//input[@type='submit']"), 5);
                driver.findElement(By.xpath("//input[@type='submit']")).click();
                waitUntilElementIsClickable(By.cssSelector("a.icon-close.dark-hover"), 5);
                WebElement xButton = driver.findElement(By.cssSelector("a.icon-close.dark-hover"));
                xButton.click();
                waitUntilElementIsInvisible(By.cssSelector("a.icon-close.dark-hover"), 5);
            }
                waitUntilElementsAreVisible(By.xpath("//div[@class='list js-list-content']"), 10);
                System.out.println("Number of lists: " + driver.findElements(By.xpath("//div[@class='list js-list-content']")).size());
                int quantityOfListsInTheBeginning = driver.findElements(By.xpath("//div[@class='list js-list-content']")).size();
                driver.findElement(By.cssSelector("a.list-header-extras-menu")).click();
                waitUntilElementIsClickable(By.xpath("//a[@class='js-close-list']"), 10);
                driver.findElement(By.xpath("//a[@class='js-close-list']")).click();
                waitUntilElementIsInvisible(By.xpath("//a[@class='js-close-list']"), 10);
                System.out.println("Number of lists after deleting: " + driver.findElements(By.xpath("//div[@class='list js-list-content']")).size());
                int quantityOfListsInTheEnd = driver.findElements(By.xpath("//div[@class='list js-list-content']")).size();
                Assert.assertEquals(quantityOfListsInTheEnd, quantityOfListsInTheBeginning-1, "Something goes wrong: ");
        }



        /*
        @Test
        public void loginPositiveAndAddBoard() throws InterruptedException {
            System.out.println("Number of boards: " +
                    driver.findElements(By.xpath("//span[@class=\"board-tile-fade\"]")).size());
            driver.findElement(By.xpath("//div[@class=\"board-tile mod-add\"]")).click();
            WebElement nameBoardField = driver.findElement(By.xpath("//input[@data-test-id=\"create-board-title-input\"]"));
            nameBoardField.click();
            nameBoardField.clear();
            nameBoardField.sendKeys("TestBoard");
            Thread.sleep(15000);
            driver.findElement(By.xpath("//button[@data-test-id=\"create-board-submit-button\"]")).click();
            Thread.sleep(15000);
            driver.findElement(By.xpath("//a[@data-test-id=\"header-home-button\"]")).click();
            Thread.sleep(5000);
            System.out.println("NEW number of boards: " +
                    driver.findElements(By.xpath("//span[@class=\"icon-lg icon-member\"]/following::span[@class=\"board-tile-fade\"]")).size());
        }
        */
    }