package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GenericHelper {

    WebDriver driver;
    private Logger logger;
    JavascriptExecutor js;

    public GenericHelper(WebDriver driver) {
        this.driver=driver;
        this.logger=logger;
        js=(JavascriptExecutor)driver;
    }

    public void naigateToUrl(String url){
        TestBase.log("Navigate to url"+url);
        try {
                driver.navigate().to(url);
        }
        catch(Exception e){
            TestBase.log("Problem to reach Url"+e.getStackTrace());
        }
    }
    public void setText(WebElement element, String text){

        element.sendKeys(text);
        TestBase.log("Set Text is "+ text);
    }

    public void click(WebElement element){
        element.click();
        TestBase.log("Clicked");
    }


}
