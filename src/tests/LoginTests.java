package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BoardsPageHelper;
import pages.HomePageHelper;
import pages.LoginPageHelper;

public class LoginTests extends TestBase {
LoginPageHelper loginPage;
BoardsPageHelper boardsPage;
HomePageHelper homePage;

    @BeforeMethod
    public void initTests() {
        homePage = new HomePageHelper(driver);
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);

        homePage.waitUntilPageIsLoaded();
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
    }

    @Test
    public void loginNegativeLoginEmpty() {
        loginPage.loginNotAtlassianUser("", PASSWORD);
        Assert.assertEquals(loginPage.getErrorMessage(), "Missing email", "Message is not correct: ");
    }

    @Test
    public void loginNegativeWrongLogin() {
        loginPage.loginNotAtlassianUser("neti", PASSWORD);
        Assert.assertEquals(loginPage.getWrongLoginErrorMessage(), "Incorrect email address and / or password. Do you need help logging in?",
                "Message is not correct: ");
    }

    @Test
    public void loginNegativeWrongPassword() {
        loginPage.loginAtlassianUser(LOGIN, "1234");
        Assert.assertEquals(loginPage.getAtlassianErrorMessage(), "Incorrect email address and / or password.\n" +
                "Do you need help logging in?", "Message is not correct: ");
    }

    @Test
    public void loginPositiveAndBoardButton() {
        loginPage.loginAtlassianUser(LOGIN, PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        Assert.assertTrue(boardsPage.getBoardsIconName().equals("Boards"), "The text of the button is not 'Boards': ");
    }
}
