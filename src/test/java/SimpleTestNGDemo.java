import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SimpleTestNGDemo {

    @BeforeMethod
    public void setUp() {
        System.out.println("Setup: Launching browser or initializing test data.");
    }

    @Test
    public void testLogin() {
        System.out.println("Running: Login test");
    }

    @Test
    public void testLogout() {
        System.out.println("Running: Logout test");
    }
}
