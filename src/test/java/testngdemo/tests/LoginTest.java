package testngdemo.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testngdemo.pages.LoginPage;
import org.testng.Assert;
import testngdemo.utils.BrowserUtils;
import testngdemo.utils.WaitUtils;
import testngdemo.utils.ConfigReader;

public class LoginTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // ✅ Use reusable browser function
        driver = BrowserUtils.initBrowser();
        driver.get(ConfigReader.getProperty("baseUrl"));
    }

    @Test
    public void testLoginandLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToApp(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        // ✅ Use reusable wait function
        boolean isLoginSuccessful = WaitUtils.waitForUrlContains(driver, "/secure", 10);
        Assert.assertTrue(isLoginSuccessful, "Login failed - secure page not reached");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/secure"), "Login failed - not on secure page");

        loginPage.clickLogout();
        String logoutUrl = driver.getCurrentUrl();
        Assert.assertTrue(logoutUrl.contains("/login"), "Logout failed - not redirected to login page");

    }

    }

