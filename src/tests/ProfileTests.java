package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class ProfileTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    HorizontalMenuPageHelper horizontalMenu;
    DropMenuPageHelper dropMenu;
    ProfilePageHelper profileAndVisibilityPage;

    @BeforeMethod
    public void initTests() {
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        dropMenu = PageFactory.initElements(driver,DropMenuPageHelper.class);
        horizontalMenu = PageFactory.initElements(driver, HorizontalMenuPageHelper.class);
        profileAndVisibilityPage = PageFactory.initElements(driver, ProfilePageHelper.class);

        homePage.waitUntilPageIsLoaded()
                .openLoginPage();
        loginPage.waitUntilPageIsLoaded()
                .loginAtlassianUser(LOGIN, PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        horizontalMenu.waitUntilPageIsLoaded()
                      .openDropMenuPage();
        dropMenu.waitUntilPageIsLoaded()
                .openProfilePage();
        profileAndVisibilityPage.waitUntilPageIsLoaded();

    }

    @Test
    public void isThisProfilePage() {
        Assert.assertEquals(profileAndVisibilityPage.getProfileTabName(), "Profile and Visibility");
    }

    @Test
    public void isUsernameDisplayedCorrectly() {
        String titleMenuButton = horizontalMenu.getTitleOfMenuIcon();
        String userNameInTitleOfButton = titleMenuButton.substring(titleMenuButton.indexOf("(")+1,titleMenuButton.length()-1);
        String userNameFromProfile = profileAndVisibilityPage.getUserNameFromProfile();
        Assert.assertEquals(userNameInTitleOfButton, userNameFromProfile, "Something goes wrong: ");
    }
}
