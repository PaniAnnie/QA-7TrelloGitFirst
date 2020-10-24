package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class ActivityTests extends TestBase{
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qaHaifa7currentBoard;
    HorizontalMenuPageHelper horizontalMenu;
    DropMenuPageHelper dropMenu;
    ProfilePageHelper profileAndVisibilityPage;
    ActivityPageHelper activityPage;

    @BeforeMethod
    public void initTests() {
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        qaHaifa7currentBoard = new CurrentBoardPageHelper(driver, "QA Haifa7");
        dropMenu = PageFactory.initElements(driver, DropMenuPageHelper.class);
        horizontalMenu = PageFactory.initElements(driver, HorizontalMenuPageHelper.class);
        profileAndVisibilityPage = PageFactory.initElements(driver, ProfilePageHelper.class);
        activityPage = PageFactory.initElements(driver, ActivityPageHelper.class);

        homePage.waitUntilPageIsLoaded()
                .openLoginPage();
        loginPage.waitUntilPageIsLoaded()
                .loginAtlassianUser(LOGIN, PASSWORD);
        boardsPage.waitUntilPageIsLoaded();

    }

    @Test
    public void isAddingCardPresentInActivity(){
        boardsPage.openCurrentBoardPage("QA Haifa7");
        qaHaifa7currentBoard.waitUntilPageIsLoaded();
        if (qaHaifa7currentBoard.getQuantityOfListsInThisBoard() == 0) {
            qaHaifa7currentBoard.addNewList();
        }
        qaHaifa7currentBoard.openFirstListExtraMenu()
                            .addNewCardInFirstList();
        horizontalMenu.openDropMenuPage();
        dropMenu.waitUntilPageIsLoaded()
                .openProfilePage();
        profileAndVisibilityPage.openActivityTab();
        Assert.assertTrue(activityPage.getLastActionText().contains("added Testcard"));
    }
}
