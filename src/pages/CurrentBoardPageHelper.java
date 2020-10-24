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

    public CurrentBoardPageHelper waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(boardButton, 15);
        waitUntilElementIsVisible(headerIcon, 10);
        return this;
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

    public CurrentBoardPageHelper addNewList() {
        clickAddingList();
        enterNewListName("Testlist");
        submitAddingList();
        stopAddingList();
        return this;
    }

    public CurrentBoardPageHelper stopAddingList() {
        stopAddingListButton.click();
        waitUntilElementIsInvisible(stopAddingListButton,5);
        return this;
    }

    public CurrentBoardPageHelper submitAddingList() {
        waitUntilElementIsClickable(submitListButton, 10);
        submitListButton.click();
        return this;
    }

    public CurrentBoardPageHelper enterNewListName(String listName) {
        editField(nameListField, listName);
        return this;
    }

    public CurrentBoardPageHelper clickAddingList() {
        initAddingList.click();
        waitUntilElementIsClickable(nameListField, 10);
        return this;
    }

    public CurrentBoardPageHelper archiveThisList() {
        putListToArchive.click();
        waitUntilElementIsInvisible(putListToArchive, 5);
        return this;
    }

    public CurrentBoardPageHelper openFirstListExtraMenu() {
        firstListExtraMenu.click();
        waitUntilElementIsClickable(putListToArchive, 10);
        return this;
    }

    public CurrentBoardPageHelper addNewCardInFirstList() {
        clickAddingCard();
        enterNewCardName("Testcard");
        submitAddingCard();
        stopAddingCard();
        return this;
    }

    public CurrentBoardPageHelper clickAddingCard() {
        initAddingCard.click();
        waitUntilElementIsClickable(nameCardField, 10);
        return this;
    }

    public CurrentBoardPageHelper enterNewCardName(String cardName) {
        editField(nameCardField, cardName);
        return this;
    }

    public CurrentBoardPageHelper stopAddingCard() {
        stopAddingCardButton.click();
        waitUntilElementIsInvisible(stopAddingCardButton,5);
        return this;
    }

    public CurrentBoardPageHelper submitAddingCard() {
        waitUntilElementIsClickable(submitCardButton, 10);
        submitCardButton.click();
        return this;
    }
}
