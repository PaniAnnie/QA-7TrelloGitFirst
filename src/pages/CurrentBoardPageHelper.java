package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CurrentBoardPageHelper extends PageBase {
    @FindBy(id = "workspaces-preamble-board-header-button")
    WebElement boardButton;

    @FindBy(tagName = "h1")
    WebElement headerIcon;

    @FindBy(xpath = "//div[@class='list js-list-content']")
    List<WebElement> listAllListsInTheBoard;

    @FindBy(xpath = "//a[@class='icon-lg icon-close dark-hover js-cancel-edit']")
    WebElement stopAddingListButton;

    @FindBy(xpath = "//input[@class='primary mod-list-add-button js-save-edit']")
    WebElement submitListButton;

    @FindBy(xpath = "//input[@class='list-name-input']")
    WebElement nameListField;

    @FindBy(xpath = "//a[@class='open-add-list js-open-add-list']")
    WebElement initAddingList;

    @FindBy(xpath = "//a[@class='js-close-list']")
    WebElement putListToArchive;

    @FindBy(css = "a.list-header-extras-menu")
    WebElement firstListExtraMenu;

    @FindBy(className = "js-add-card")
    WebElement initAddingCard;

    @FindBy(css = ".list-card-composer-textarea")
    WebElement nameCardField;

    @FindBy(xpath = "//input[@class='primary confirm mod-compact js-add-card']")
    WebElement submitCardButton;

    @FindBy(xpath = "//a[@class='icon-lg icon-close dark-hover js-cancel']")
    WebElement stopAddingCardButton;

    String boardName;

    public CurrentBoardPageHelper(WebDriver driver, String boardName) {
        super(driver);
        this.boardName = boardName;
        PageFactory.initElements(driver, this);
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(boardButton, 15);
        waitUntilElementIsVisible(headerIcon, 10);
    }

    public String getCurrentBoardHeader(){
        return headerIcon.getText();
    }

    public boolean isCorrectCurrentBoard() {
        return headerIcon.getText().equals(this.boardName);
    }

    public int getQuantityOfListsInThisBoard(){
        waitUntilElementsAreVisible(listAllListsInTheBoard, 15);
        return listAllListsInTheBoard.size();
    }

    public void addNewList() {
        clickAddingList();
        enterNewListName("Testlist");
        submitAddingList();
        stopAddingList();
    }

    public void stopAddingList() {
        stopAddingListButton.click();
        waitUntilElementIsInvisible(stopAddingListButton,5);
    }

    public void submitAddingList() {
        waitUntilElementIsClickable(submitListButton, 10);
        submitListButton.click();
    }

    public void enterNewListName(String listName) {
        editField(nameListField, listName);
    }

    public void clickAddingList() {
        initAddingList.click();
        waitUntilElementIsClickable(nameListField, 10);
    }

    public void archiveThisList() {
        putListToArchive.click();
        waitUntilElementIsInvisible(putListToArchive, 5);
    }

    public void openFirstListExtraMenu() {
        firstListExtraMenu.click();
        waitUntilElementIsClickable(putListToArchive, 10);
    }

    public void addNewCardInFirstList() {
        clickAddingCard();
        enterNewCardName("Testcard");
        submitAddingCard();
        stopAddingCard();
    }

    public void clickAddingCard() {
        initAddingCard.click();
        waitUntilElementIsClickable(nameCardField, 10);
    }

    public void enterNewCardName(String cardName) {
        editField(nameCardField, cardName);
    }

    public void stopAddingCard() {
        stopAddingCardButton.click();
        waitUntilElementIsInvisible(stopAddingCardButton,5);
    }

    public void submitAddingCard() {
        waitUntilElementIsClickable(submitCardButton, 10);
        submitCardButton.click();
    }
}
