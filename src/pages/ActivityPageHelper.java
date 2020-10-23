package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ActivityPageHelper extends PageBase{

    @FindBy(css = "a.show-more.js-more-actions")
    WebElement loadMoreActivity;

    @FindBy (xpath = "//a[@class='action-card']")
    WebElement lastCard;

    @FindBy (xpath = "//div[@class = 'phenom-desc']")
    WebElement lastAction;


    public ActivityPageHelper(WebDriver driver){
        super(driver);
    }

    public void waitUntilPageIsLoaded(){
        waitUntilElementIsVisible(loadMoreActivity, 15);
    }

    public String getLastCardText() {
        waitUntilElementIsVisible(lastCard, 10);
        return lastCard.getText();
    }

    public String getLastActionText() {
        waitUntilElementIsVisible(lastAction, 10);
        return lastAction.getText();
    }

}
