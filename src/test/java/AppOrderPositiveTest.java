import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppOrderPositiveTest {
    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }


    @AfterEach
    public void afterEach() {
        driver.quit();
        driver = null;
    }

    @Test
    public void testOrderCardForm() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Тестов Игнат Борисович");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79991230000");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector(".button")).click();
        WebElement actualElement = driver.findElement(By.cssSelector("[data-test-id=order-success]"));
        String actualText = actualElement.getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText);
        assertTrue(actualElement.isDisplayed());
    }
}
