package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BoardsPageHelper;
import pages.CurrentBoardPageHelper;
import pages.HomePageHelper;
import pages.LoginPageHelper;
import pages.DropMenuPageHelper;
import pages.ProfilePageHelper;

public class ProfileTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qaHaifa7currentBoard;
    DropMenuPageHelper dropMenu;
    ProfilePageHelper profileAndVisibilityPage;

    @BeforeMethod
    public void initTests() {
        homePage = new HomePageHelper(driver);
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
        qaHaifa7currentBoard = new CurrentBoardPageHelper(driver, "QA Haifa7");
        dropMenu = new DropMenuPageHelper(driver);
        profileAndVisibilityPage = new ProfilePageHelper(driver);

        homePage.waitUntilPageIsLoaded();
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginAtlassianUser(LOGIN, PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        boardsPage.openCurrentBoardPage("QA Haifa7");
        qaHaifa7currentBoard.waitUntilPageIsLoaded();
        qaHaifa7currentBoard.openDropMenuPage();
        dropMenu.waitUntilPageIsLoaded();
        dropMenu.openProfilePage();
        profileAndVisibilityPage.waitUntilPageIsLoaded();
    }

    @Test
    public void isThisProfilePage() {
        Assert.assertEquals(profileAndVisibilityPage.getProfileTabName(), "Profile and Visibility");
    }

    @Test
    public void isUsernameDisplayedCorrectly() {
        String titleMenuButton = profileAndVisibilityPage.getTitleOfMenuButton();
        String userNameInTitleOfButton = titleMenuButton.substring(titleMenuButton.indexOf("(")+1,titleMenuButton.length()-1);
        String userNameFromProfile = profileAndVisibilityPage.getUserNameFromProfile();
        Assert.assertEquals(userNameInTitleOfButton, userNameFromProfile, "Something goes wrong: ");
    }
}
