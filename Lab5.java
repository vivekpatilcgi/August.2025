package pac1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Lab5 {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 1. Launch the URL
        driver.get("https://tutorialsninja.com/demo/");

        // 2. Verify 'Title' of the page
        if (driver.getTitle().contains("Your Store")) {
            System.out.println("Title verified");
        } else {
            System.out.println("Title mismatch");
        }

        // 3. Click on 'My Account' dropdown
        // FIX: Use a correct locator for 'My Account'
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        
        // PAUSE: Give the dropdown menu 1 second to appear
        Thread.sleep(1000); 

        // 4. Select 'Register' from dropdown
        driver.findElement(By.linkText("Register")).click();

        // 5. Verify heading 'Register Account'
        WebElement heading = driver.findElement(By.xpath("//*[@id=\"content\"]/h1"));
        if (heading.isDisplayed()) {
            System.out.println("Heading 'Register Account' is verified");
        }

        // 6. Click on 'Continue' without filling anything
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        
        // PAUSE: Give the warning message 1 second to appear
        Thread.sleep(1000); 

        // 7. Verify warning message
        WebElement warn = driver.findElement(By.xpath("//*[@id=\"account-register\"]/div[1]"));
        if (warn.getText().contains("Warning: You must agree to the Privacy Policy!")) {
            System.out.println("Warning Appeared: " + warn.getText());
        }

        // Part 2: Personal Details

        // 1. First Name validation
        driver.findElement(By.id("input-firstname")).sendKeys("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFG");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        Thread.sleep(1000); // PAUSE: For error message
        WebElement firstNameError = driver.findElement(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]"));
        System.out.println("First Name validation error displayed: " + firstNameError.getText());
        driver.findElement(By.id("input-firstname")).clear();
        driver.findElement(By.id("input-firstname")).sendKeys("Vivek");

        // 2. Last Name validation
        driver.findElement(By.id("input-lastname")).sendKeys("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFG");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        Thread.sleep(1000); // PAUSE: For error message
        WebElement lastNameError = driver.findElement(By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]"));
        System.out.println("Last Name validation error displayed: " + lastNameError.getText());
        driver.findElement(By.id("input-lastname")).clear();
        driver.findElement(By.id("input-lastname")).sendKeys("Patil");

        // 3. Email
        // FIX: Generate a truly unique email for each run using a timestamp
        String uniqueEmail = "vivekpatil280803" + System.currentTimeMillis() + "@gmail.com";
        driver.findElement(By.id("input-email")).sendKeys(uniqueEmail);
        System.out.println("Using unique email: " + uniqueEmail);

        // 4. Telephone & Password
        driver.findElement(By.id("input-telephone")).sendKeys("9874563210");
        driver.findElement(By.id("input-password")).sendKeys("Test1234");
        driver.findElement(By.id("input-confirm")).sendKeys("Test1234");

        // Newsletter & Agree Policy
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        
        // PAUSE: Give the success page time to load
        Thread.sleep(2000); 

        // Success message
        WebElement successMsg = driver.findElement(By.xpath("//h1[text()='Your Account Has Been Created!']"));
        if (successMsg.isDisplayed()) {
            System.out.println("Registration successful: " + successMsg.getText());
        }
        
        // Click Continue button on success page
        driver.findElement(By.linkText("Continue")).click();
        
        // PAUSE: Give the account page time to load
        Thread.sleep(1000);

        // Navigate to 'View your order history'
        driver.findElement(By.linkText("View your order history")).click();

        Thread.sleep(1000); // PAUSE: Give the order history page time to load

        // Verify Order History page
        WebElement orderHistoryHeading = driver.findElement(By.xpath("//h1[text()='Order History']"));
        if (orderHistoryHeading.isDisplayed()) {
            System.out.println("Navigated to Order History page.");
        }
        
        driver.quit();
    }
}