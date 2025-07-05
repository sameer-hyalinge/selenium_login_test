package testngdemo.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testngdemo.pages.LoginPage;
import org.testng.Assert;

public class LoginTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @Test
    public void testLoginandLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToApp("tomsmith", "SuperSecretPassword!");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/secure"), "Login failed - not on secure page");

        loginPage.clickLogout();
        String logoutUrl = driver.getCurrentUrl();
        Assert.assertTrue(logoutUrl.contains("/login"), "Logout failed - not redirected to login page");

    }

    }

