package pac1;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class TC009_TestNG2 {
    private WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void beforeMethod(String brow) {
  	  System.out.println("This is Before Method");
  	  if(brow.equalsIgnoreCase("chrome"))
  	  {
  	  WebDriverManager.chromedriver().setup();
  		 driver=new ChromeDriver();
  	  }
  	  if(brow.equalsIgnoreCase("edge"))
  	  {
  	  WebDriverManager.edgedriver().setup();
  		 driver=new EdgeDriver();
  	  }
  	  if(brow.equalsIgnoreCase("firefox"))
  	  {
  	  WebDriverManager.firefoxdriver().setup();
  		 driver=new FirefoxDriver();
  	  }
  	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      driver.get("https://tutorialsninja.com/demo/");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("This is After Method: Tearing down WebDriver.");
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void productNavigationAndAddToCartTest() {
        // Verify Title
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains("Your Store"), "Title verification failed!");
        System.out.println("Title verification successful.");

        // Click on Desktop then Mac
        driver.findElement(By.linkText("Desktops")).click();
        driver.findElement(By.partialLinkText("Mac")).click();
        System.out.println("Navigated to Mac page.");

        // Verify Mac heading
        WebElement macHeading = driver.findElement(By.xpath("//h2[contains(text(),'Mac')]"));
        Assert.assertTrue(macHeading.isDisplayed(), "Mac heading not displayed!");
        System.out.println("Mac heading is displayed.");

        // Sort A-Z
        Select sortDropdown = new Select(driver.findElement(By.id("input-sort")));
        sortDropdown.selectByVisibleText("Name (A - Z)");
        System.out.println("Sorted by Name (A-Z).");

        // Add to Cart
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/div/div[2]/div[2]/button[1]")).click();
        System.out.println("Clicked 'Add to Cart'.");

        // Success Message
        WebElement successMsg = driver.findElement(By.cssSelector(".alert-success"));
        Assert.assertTrue(successMsg.getText().contains("Success"), "Add to Cart failed!");
        System.out.println("Verified 'Add to Cart' success message.");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("This is Before Class");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("This is After class");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("This is Before test");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("This is After test");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("This is Before suite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("This is After suite");
    }
}
