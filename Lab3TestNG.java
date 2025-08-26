package pac1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class Lab3TestNG {

    WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        System.out.println("Lab3TestNG: Setting up " + browser + " browser.");
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");
    }

    @Test
    public void lab3() {
        // Verify Title
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains("Your Store"), "Title verification failed!");

        // Click on Desktop then Mac
        driver.findElement(By.linkText("Desktops")).click();
        driver.findElement(By.partialLinkText("Mac")).click();

        // Verify Mac heading
        WebElement macHeading = driver.findElement(By.xpath("//h2[contains(text(),'Mac')]"));
        Assert.assertTrue(macHeading.isDisplayed(), "Mac heading not displayed!");

        // Sort A-Z
        Select sortDropdown = new Select(driver.findElement(By.id("input-sort")));
        sortDropdown.selectByVisibleText("Name (A - Z)");

        // Add to Cart
        driver.findElement(By.xpath("//div[@class='product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12']//button[contains(@onclick, 'cart.add')]")).click();

        // Success Message
        WebElement successMsg = driver.findElement(By.cssSelector(".alert-success"));
        Assert.assertTrue(successMsg.getText().contains("Success"), "Add to Cart failed!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
