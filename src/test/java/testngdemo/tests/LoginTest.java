package testngdemo.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import org.testng.Assert;

import testngdemo.pages.LoginPage;
import testngdemo.utils.BrowserUtils;
import testngdemo.utils.WaitUtils;
import testngdemo.utils.ConfigReader;
import testngdemo.utils.ExtentReportManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.qameta.allure.*;

public class LoginTest {

    WebDriver driver;
    ExtentReports extent = ExtentReportManager.getReportObject();
    ExtentTest test;

    @BeforeMethod
    @Step("Open browser and navigate to login page")
    public void setUp() {
        driver = BrowserUtils.initBrowser();
        driver.get(ConfigReader.getProperty("baseUrl"));
    }

    @Test(retryAnalyzer = testngdemo.utils.RetryAnalyzer.class)
    @Epic("Login Module")
    @Feature("Login & Logout Flow")
    @Story("Valid login and logout with correct credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Performs login with valid credentials and logs out, then verifies URLs")
    public void testLoginandLogout() {
        test = extent.createTest("Login and Logout Test");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToApp(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        test.log(Status.INFO, "Login attempted");

        boolean isLoginSuccessful = WaitUtils.waitForUrlContains(driver, "/secure", 10);
        Assert.assertTrue(isLoginSuccessful, "Login failed - secure page not reached");
        test.pass("Reached secure page after login");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/secure"), "Login failed - not on secure page");

        loginPage.clickLogout();
        String logoutUrl = driver.getCurrentUrl();
        Assert.assertTrue(logoutUrl.contains("/login"), "Logout failed - not redirected to login page");
        test.pass("Successfully logged out and redirected to login page");
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush(); // Save ExtentReports
    }
}
