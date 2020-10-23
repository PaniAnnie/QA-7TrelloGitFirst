package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageHelper extends PageBase {
    @FindBy(id = "user")
    WebElement loginField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(id = "error")
    WebElement errorMessage;

    @FindBy(xpath = "//div[@id='password-error']//p[@class='error-message']")
    WebElement errorLoginMessage;

    @FindBy (xpath = "//div[@role='alert']")
    WebElement errorMessageAtl;

    @FindBy (id = "login-submit")
    WebElement loginButtonAtl;

    @FindBy (xpath = "//input[@value='Log in with Atlassian']")
    WebElement loginUserAtl;

    public LoginPageHelper(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void loginNotAtlassianUser(String login, String password) {
        loginNotAtlassianUsername(login);
        enterPassword(password);
        pressLoginButton();
    }

    public void loginAtlassianUser(String login, String password) {
        loginAtlassianUsername(login);
        loginUserAsAtlassian();
        enterPassword(password);
        pressAtlassianLoginButton();
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(loginField, 15);
        waitUntilElementIsClickable(passwordField, 15);
        waitUntilElementIsClickable(loginButton, 10);
    }

    public void loginNotAtlassianUsername(String login) {
        editField(loginField, login);
    }

    public void loginAtlassianUsername(String login) {
        editField(loginField, login);
    }

    public void enterPassword(String password) {
        waitUntilElementIsClickable(passwordField, 15);
        editField(passwordField, password);
    }

    public void pressLoginButton() {
        waitUntilElementIsClickable(loginButton, 15);
        loginButton.click();
    }

    public void loginUserAsAtlassian() {
        waitUntilElementIsClickable(loginUserAtl, 10);
        loginUserAtl.click();
    }

    public void pressAtlassianLoginButton() {
        waitUntilElementIsClickable(loginButtonAtl, 10);
        loginButtonAtl.click();
    }

    public String getErrorMessage(){
        waitUntilElementIsClickable(errorMessage, 10);
        return errorMessage.getText();
    }

    public String getWrongLoginErrorMessage(){
        waitUntilElementIsClickable(errorLoginMessage, 20);
        return errorLoginMessage.getText();
    }

    public String getAtlassianErrorMessage(){
        waitUntilElementIsClickable(errorMessageAtl, 20);
        return errorMessageAtl.getText();
    }
}
