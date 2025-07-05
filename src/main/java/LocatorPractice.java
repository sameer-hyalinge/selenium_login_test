import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LocatorPractice {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/login");

        // Wait for username field and send keys
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        username.sendKeys("tomsmith");

        // Wait for password field and send keys
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        password.sendKeys("SuperSecretPassword!");

        // Wait for and click login button
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.radius")));
        loginButton.click();

        // Wait for success message and assert it is displayed
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".flash.success")));

        String messageText = successMessage.getText();
        if (messageText.contains("You logged into a secure area!")) {
            System.out.println("✅ Login successful and success message validated.");
        } else {
            System.out.println("❌ Login success message not found.");
        }
        // ➕ LOGOUT BUTTON
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.button.secondary.radius")));
        logoutButton.click();

        // ✅ VALIDATE LOGOUT MESSAGE
        WebElement logoutMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".flash.success")));
        String logoutText = logoutMessage.getText();

        if (logoutText.contains("You logged out of the secure area!")) {
            System.out.println("✅ Logout successful and message validated.");
        } else {
            System.out.println("❌ Logout message not found.");
        }

        driver.quit();
    }
}
