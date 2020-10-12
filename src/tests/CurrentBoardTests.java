package tests;

import org.openqa.selenium.By;
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
            homePage = new HomePageHelper(driver);
            loginPage = new LoginPageHelper(driver);
            boardsPage = new BoardsPageHelper(driver);
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
            //считаем количество листов
            int quantityOfListsInTheBeginning = qaHaifa7currentBoard.getQuantityOfListsInThisBoard();
            System.out.println("Number of lists: " + qaHaifa7currentBoard.getQuantityOfListsInThisBoard());
            //жмем добавить лист
            qaHaifa7currentBoard.addNewList();
            //считаем количество листов
            int quantityOfListsInTheEnd = qaHaifa7currentBoard.getQuantityOfListsInThisBoard();
            System.out.println("Number of lists after adding: " + qaHaifa7currentBoard.getQuantityOfListsInThisBoard());
            //сравниваем
            Assert.assertEquals(quantityOfListsInTheEnd, quantityOfListsInTheBeginning+1, "Something goes wrong: ");
        }

    @Test
        public void loginPositiveAndDeleteList() {
            if (qaHaifa7currentBoard.getQuantityOfListsInThisBoard() == 0) {
                qaHaifa7currentBoard.addNewList();
            }
                //считаем количество листов
                int quantityOfListsInTheBeginning = qaHaifa7currentBoard.getQuantityOfListsInThisBoard();
                System.out.println("Number of lists: " + qaHaifa7currentBoard.getQuantityOfListsInThisBoard());
                //удаляем лист
                qaHaifa7currentBoard.openFirstListExtraMenu();
                qaHaifa7currentBoard.archiveThisList();
                //считаем снова
                int quantityOfListsInTheEnd = qaHaifa7currentBoard.getQuantityOfListsInThisBoard();
                System.out.println("Number of lists: " + qaHaifa7currentBoard.getQuantityOfListsInThisBoard());
                //сравниваем
                Assert.assertEquals(quantityOfListsInTheEnd, quantityOfListsInTheBeginning-1, "Something goes wrong: ");
        }
}