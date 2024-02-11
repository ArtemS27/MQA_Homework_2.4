// This sample code supports Appium Java client >=9
// https://github.com/appium/java-client
import io.appium.java_client.AppiumBy;
import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.*;

public class ChangeTextTest {

    private AndroidDriver driver;
    String emptyText = "     ";
    String newText = "New text";

    @BeforeEach
    public void setUp() throws MalformedURLException {
        var options = new BaseOptions()
                .amend("platformName", "Android")
                .amend("appium:deviceName", "Some name")
                .amend("appium:appPackage", "ru.netology.testing.uiautomator")
                .amend("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity")
                .amend("appium:automationName", "uiautomator2")
                .amend("appium:ensureWebviewsHavePages", true)
                .amend("appium:nativeWebScreenshot", true)
                .amend("appium:newCommandTimeout", 3600)
                .amend("appium:connectHardwareKeyboard", true);

        URL getURL = new URL("http://127.0.0.1:4723");

        driver = new AndroidDriver(getURL, options);
    }

    @Test
    public void changeTextToSpaces() {
        var el1 = driver.findElement(AppiumBy.id("ru.netology.testing.uiautomator:id/textToBeChanged"));
        el1.isDisplayed();
        var expectedText = el1.getText();
        var el2 = driver.findElement(AppiumBy.id("ru.netology.testing.uiautomator:id/userInput"));
        el2.isDisplayed();
        el2.click();
        el2.sendKeys(emptyText);
        var el3 = driver.findElement(AppiumBy.id("ru.netology.testing.uiautomator:id/buttonChange"));
        el3.isDisplayed();
        el3.click();
        var actualText = el1.getText();
        Assertions.assertEquals(actualText, expectedText);
    }

    @Test
    public void changeTextAndOpenInNewActivity() {
        var el1 = driver.findElement(AppiumBy.id("ru.netology.testing.uiautomator:id/userInput"));
        el1.isDisplayed();
        el1.click();
        el1.sendKeys(newText);
        var el2 = driver.findElement(AppiumBy.id("ru.netology.testing.uiautomator:id/buttonActivity"));
        el2.isDisplayed();
        el2.click();
        var el3 = driver.findElement(AppiumBy.id("ru.netology.testing.uiautomator:id/text"));
        el3.isDisplayed();
        Assertions.assertEquals(el3.getText(), newText);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
