package pac1;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
 
public class TC002_GetURL {
    public static void main(String[] args) {
        // No need for WebDriverManager.chromedriver().setup(); with modern Selenium
        WebDriver driver = new ChromeDriver();
        
        driver.get("https://www.google.com");
        System.out.println("Title is: " + driver.getTitle());
        System.out.println("URL is: " + driver.getCurrentUrl());
        // System.out.println("Page source is: " + driver.getPageSource()); // Commented out for cleaner output

        driver.navigate().to("https://www.yahoo.com");
        System.out.println("Title is: " + driver.getTitle());
        System.out.println("URL is: " + driver.getCurrentUrl());
        // System.out.println("Page source is: " + driver.getPageSource());

        driver.navigate().back();
        System.out.println("After back, title is: " + driver.getTitle());
        System.out.println("URL is: " + driver.getCurrentUrl());
        // System.out.println("Page source is: " + driver.getPageSource());
        
        driver.navigate().refresh();
        System.out.println("After refresh, title is: " + driver.getTitle());
        System.out.println("URL is: " + driver.getCurrentUrl());
        // System.out.println("Page source is: " + driver.getPageSource());
        
        driver.quit();
    }
}