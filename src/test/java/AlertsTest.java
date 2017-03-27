import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class AlertsTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void testJSAlert() {
        WebElement jsAlertButton = driver.findElement(By.xpath("//*[contains(text(),'Click for JS Alert')]"));
        jsAlertButton.click();
        Alert alert = driver.switchTo().alert();
        String textOnAlert = alert.getText();
        alert.accept();
        assertEquals("I am a JS Alert", textOnAlert);
    }

    @Test
    public void ConfirmAccess() {
        WebElement ConfirmButton = driver.findElement(By.xpath("//*[contains(text(),'Click for JS Confirm')]"));
        ConfirmButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        assertEquals("I am a JS Confirm", alert.getText());
        alert.accept();
    }

    @Test
    public void testPrompt() {
        WebElement button = driver.findElement(By.xpath("//*[contains(text(),'Click for JS Prompt')]"));
        button.click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("I am a super hero");
        alert.accept();

        WebElement message = driver.findElement(By.cssSelector("#result"));
        assertEquals("You entered: I am a super hero", message.getText());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}