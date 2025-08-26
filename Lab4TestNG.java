package pac1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class Lab4TestNG {

    WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        System.out.println("Lab4TestNG: Setting up " + browser + " browser.");
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
    public void searchFunctionalityTest() {
        // Enter 'Mobile' and Search
        driver.findElement(By.name("search")).sendKeys("Mobile");
        driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();

        // Verify Search page title
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains("Search"), "Search page title not found!");

        // Clear search box and search for "Monitors" in product descriptions
        By searchBox = By.name("search");
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys("Monitors");
        driver.findElement(By.name("description")).click(); // Corrected from sub_category
        driver.findElement(By.id("button-search")).click();

        // Verify results contain the search term
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(bodyText.contains("Monitors"), "Search results for 'Monitors' not correct!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
