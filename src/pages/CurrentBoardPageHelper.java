package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CurrentBoardPageHelper extends PageBase {
    String boardName;

    public CurrentBoardPageHelper(WebDriver driver, String boardName) {
        super(driver);
        this.boardName = boardName;
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(By.id("workspaces-preamble-board-header-button"), 15);
        waitUntilElementIsPresent(By.tagName("h1"), 10);
    }

    public String getCurrentBoardHeader(){
        return driver.findElement(By.tagName("h1")).getText();
    }

    public boolean isCorrectCurrentBoard() {
        return driver.findElement(By.tagName("h1")).getText().equals(this.boardName);
    }

    public int getQuantityOfListsInThisBoard(){
        waitUntilElementsAreVisible(By.xpath("//div[@class='list js-list-content']"), 15);
        return driver.findElements(By.xpath("//div[@class='list js-list-content']")).size();
    }

    public void addNewList() {
        //кликаем на добавление
        clickAddingList();
        //вводим имя
        enterNewListName("Testlist");
        //подтверждаем
        submitAddingList();
        //останавливаем добавление следующего листа
        stopAddingList();
    }

    public void stopAddingList() {
        driver.findElement(By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel-edit']")).click();
        waitUntilElementIsInvisible(By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel-edit']"),5);
    }


    public void submitAddingList() {
        waitUntilElementIsClickable(By.xpath("//input[@class='primary mod-list-add-button js-save-edit']"), 10);
        driver.findElement(By.xpath("//input[@class='primary mod-list-add-button js-save-edit']")).click();
    }

    public void enterNewListName(String listName) {
        WebElement nameListField = driver.findElement(By.xpath("//input[@class='list-name-input']"));
        nameListField.click();
        nameListField.clear();
        nameListField.sendKeys(listName);
    }

    public void clickAddingList() {
        driver.findElement(By.xpath("//a[@class='open-add-list js-open-add-list']")).click();
        waitUntilElementIsClickable(By.xpath("//input[@class='list-name-input']"), 10);
    }

    public void archiveThisList() {
        driver.findElement(By.xpath("//a[@class='js-close-list']")).click();
        waitUntilElementIsInvisible(By.xpath("//a[@class='js-close-list']"), 10);
    }

    public void openFirstListExtraMenu() {
        driver.findElement(By.cssSelector("a.list-header-extras-menu")).click();
        waitUntilElementIsClickable(By.xpath("//a[@class='js-close-list']"), 10);
    }

    public void openDropMenuPage() {
        waitUntilElementIsClickable(By.xpath("//button[@data-test-id='header-member-menu-button']"),10);
        driver.findElement(By.xpath("//button[@data-test-id='header-member-menu-button']")).click(); //data-test-id="header-member-menu-button"
    }


}
