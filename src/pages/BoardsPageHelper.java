package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class BoardsPageHelper extends PageBase {

    @FindBy(xpath = "//li[@class = 'boards-page-board-section-list-item']")
    List<WebElement> listAllBoardsList;

    public BoardsPageHelper(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public BoardsPageHelper waitUntilPageIsLoaded() {
        waitUntilElementsAreVisible(listAllBoardsList,20);
        return this;
    }

    public BoardsPageHelper openCurrentBoardPage(String boardName) {
        WebElement board = driver.findElement(By.xpath("//li[@class='boards-page-board-section-list-item'][.//div[@title='"+boardName+"']]"));
        board.click();
        return this;
    }
}
