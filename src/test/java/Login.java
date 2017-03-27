import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class Login {
    private WebDriver driver;
    private ProfilesIni firProfiles;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeMethod
    public void setup() {
        //Access firefox browser profile "certificateIssue" to use It In test.
        firProfiles = new ProfilesIni();
        FirefoxProfile wbdrverprofile = firProfiles.getProfile("default");
        wbdrverprofile.setAcceptUntrustedCertificates(true);
        wbdrverprofile.setAssumeUntrustedCertificateIssuer(false);

        driver = new FirefoxDriver(wbdrverprofile);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://192.168.100.26/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void loginWorks() {
        WebElement loginField = driver.findElement(By.id("Username"));
        loginField.clear();
        loginField.sendKeys("EugenBorisik");

        WebElement passField = driver.findElement(By.id("Password"));
        passField.clear();
        passField.sendKeys("qwerty12345");

        //Thread.sleep(1000); //This is not a good way to use it because that doesn't make our tests run as quickly as possible
        WebElement loginButton = driver.findElement(By.id("SubmitButton"));
        loginButton.click();

        WebElement logoutLink = (new WebDriverWait(driver, 10)) // Explicit waiter task 40 p.3
                .until(ExpectedConditions.presenceOfElementLocated(By.className("sign-out-span")));
        Assert.assertTrue(logoutLink.isDisplayed());
    }

    @Test
    public void searchFieldDisplay() {
        WebElement loginField = driver.findElement(By.id("Username"));
        loginField.clear();
        loginField.sendKeys("EugenBorisik");

        WebElement passField = driver.findElement(By.id("Password"));
        passField.clear();
        passField.sendKeys("qwerty12345");

        WebElement loginButton = driver.findElement(By.id("SubmitButton"));
        loginButton.click();

        WebElement officeTab = driver.findElement(By.cssSelector("#officeMenu"));
        officeTab.click();

        WebElement searchOfficeField = (new WebDriverWait(driver, 15))
                .until(new ExpectedCondition<WebElement>() {
                    public WebElement apply(WebDriver d) {
                        return d.findElement(By.cssSelector("#input-search"));
                    }
                });
        Assert.assertTrue(searchOfficeField.isDisplayed());
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                new Object[]{"EugenBorisik", "qwerty12345", "//*[contains(text(),'Eugen')]", "Eugen My Profile"},
                new Object[]{"EugenBorisik", "", "//*[@id='password-box-validation']", "Password is required"},
                new Object[]{"", "qwerty12345", "//*[@id='user-box-validation']", "Username is required"},
        };
    }

    @Test(dataProvider = "testData")
    public void loginCorrect(String login, String password, String element, String result) {
        WebElement loginCorrect = driver.findElement(By.id("Username"));
        loginCorrect.clear();
        loginCorrect.sendKeys(login);

        WebElement passField = driver.findElement(By.id("Password"));
        passField.clear();
        passField.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id("SubmitButton"));
        loginButton.click();

        WebElement nameDisplays = driver.findElement(By.xpath(element));
        String elementText = nameDisplays.getText();
        assertEquals(elementText, result);
    }
}