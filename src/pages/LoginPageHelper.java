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

    public LoginPageHelper loginNotAtlassianUser(String login, String password) {
        loginNotAtlassianUsername(login);
        enterPassword(password);
        pressLoginButton();
        return this;
    }

    public LoginPageHelper loginAtlassianUser(String login, String password) {
        loginAtlassianUsername(login);
        loginUserAsAtlassian();
        enterPassword(password);
        pressAtlassianLoginButton();
        return this;
    }

    public LoginPageHelper waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(loginField, 15);
        waitUntilElementIsClickable(passwordField, 15);
        waitUntilElementIsClickable(loginButton, 10);
        return this;
    }

    public LoginPageHelper loginNotAtlassianUsername(String login) {
        editField(loginField, login);
        return this;
    }

    public LoginPageHelper loginAtlassianUsername(String login) {
        editField(loginField, login);
        return this;
    }

    public LoginPageHelper enterPassword(String password) {
        waitUntilElementIsClickable(passwordField, 15);
        editField(passwordField, password);
        return this;
    }

    public LoginPageHelper pressLoginButton() {
        waitUntilElementIsClickable(loginButton, 15);
        loginButton.click();
        return this;
    }

    public LoginPageHelper loginUserAsAtlassian() {
        waitUntilElementIsClickable(loginUserAtl, 10);
        loginUserAtl.click();
        return this;
    }

    public LoginPageHelper pressAtlassianLoginButton() {
        waitUntilElementIsClickable(loginButtonAtl, 10);
        loginButtonAtl.click();
        return this;
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
