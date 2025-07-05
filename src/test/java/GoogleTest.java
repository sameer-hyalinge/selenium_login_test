import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class GoogleTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.out.println("Launching Chrome...");
        driver = new ChromeDriver();  // WebDriverManager handles driver download if configured
        driver.manage().window().maximize();
    }

    @Test
    public void openGoogle() {
        System.out.println("Opening Google...");
        driver.get("https://www.google.com");
        String title = driver.getTitle();
        System.out.println("Page Title is: " + title);
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Closing browser...");
        driver.quit();
    }
}
