package pageObjects.logIn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.GenericHelper;

public class LoginPageElements {
    GenericHelper genericHelper;

    @FindBy(xpath = "//input[@placeholder='Username']")
    public
    WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    public
    WebElement inputPassword;

    @FindBy(xpath = "//input[@data-test='login-button']")
    public
    WebElement loginButton;


    public LoginPageElements(WebDriver driver){
        PageFactory.initElements(driver,this);
        genericHelper=new GenericHelper(driver);
    }

    public void setTextUsername(String username){
        System.out.println("Setting username as : " + username);
        genericHelper.setText(inputUserName,username);
        System.out.println("Username set");
    }

    public void setTextPassword(String password){
        System.out.println("Setting Password as : " + password);
        genericHelper.setText(inputPassword,password);
        System.out.println("Password set");
    }

    public void clickSubmitButton(){
        System.out.println("Clicking Submit Button");
        genericHelper.click(loginButton);
        System.out.println("Submit button clicked");
    }
}
