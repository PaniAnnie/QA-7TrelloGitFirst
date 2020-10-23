package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BoardsPageHelper;
import pages.CurrentBoardPageHelper;
import pages.HomePageHelper;
import pages.LoginPageHelper;

public class CurrentBoardTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qaHaifa7currentBoard;

        @BeforeMethod
        public void initTests() {
            homePage = PageFactory.initElements(driver, HomePageHelper.class);
            loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
            boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
            qaHaifa7currentBoard = new CurrentBoardPageHelper(driver, "QA Haifa7");

            homePage.waitUntilPageIsLoaded();
            homePage.openLoginPage();
            loginPage.waitUntilPageIsLoaded();
            loginPage.loginAtlassianUser(LOGIN, PASSWORD);
            boardsPage.waitUntilPageIsLoaded();
            boardsPage.openCurrentBoardPage("QA Haifa7");
            qaHaifa7currentBoard.waitUntilPageIsLoaded();
        }

        @Test
        public void isCorrectCurrentBoard() {
            System.out.println("Header of the current board: " + qaHaifa7currentBoard.getCurrentBoardHeader());
            Assert.assertEquals(qaHaifa7currentBoard.getCurrentBoardHeader(), "QA Haifa7", "Header of the current board is wrong: ");
        }

        @Test
        public void isCorrectCurrentBoard2() {
            Assert.assertTrue(qaHaifa7currentBoard.isCorrectCurrentBoard(), "Header of the current board is wrong: ");
        }

        @Test
        public void loginPositiveAndAddList() {
            int quantityOfListsInTheBeginning = qaHaifa7currentBoard.getQuantityOfListsInThisBoard();
            qaHaifa7currentBoard.addNewList();
            int quantityOfListsInTheEnd = qaHaifa7currentBoard.getQuantityOfListsInThisBoard();
            Assert.assertEquals(quantityOfListsInTheEnd, quantityOfListsInTheBeginning+1, "Something goes wrong: ");
        }

        @Test
        public void loginPositiveAndDeleteList() {
            if (qaHaifa7currentBoard.getQuantityOfListsInThisBoard() == 0) {
                qaHaifa7currentBoard.addNewList();
            }
                int quantityOfListsInTheBeginning = qaHaifa7currentBoard.getQuantityOfListsInThisBoard();
                qaHaifa7currentBoard.openFirstListExtraMenu();
                qaHaifa7currentBoard.archiveThisList();
                int quantityOfListsInTheEnd = qaHaifa7currentBoard.getQuantityOfListsInThisBoard();
                Assert.assertEquals(quantityOfListsInTheEnd, quantityOfListsInTheBeginning-1, "Something goes wrong: ");
        }
}