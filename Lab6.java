package pac1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Lab6 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        //1
        driver.get("https://tutorialsninja.com/demo/index.php?");
        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.linkText("Login")).click();    
        driver.findElement(By.xpath("//*[@id=\"input-email\"]")).sendKeys("vivekpatil2800@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"input-password\"]")).sendKeys("Vivek@1234");       
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();
        
        //2
        driver.findElement(By.linkText("Components")).click();
        
        //3
        driver.findElement(By.linkText("Monitors (2)")).click();
        
        //4
        driver.findElement(By.xpath("//*[@id=\"input-limit\"]/option[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[2]/div/div[1]/a/img")).click();
        
        //5
        driver.findElement(By.xpath("//*[@id=\"button-cart\"]")).click();
        
        //6, 7
        //No specification for second product
        
        //8,
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div[1]/button[1]")).click();
        
        //10, 11
        driver.findElement(By.xpath("//*[@id=\"search\"]/input")).sendKeys("Mobile");
        driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
        
        //12,
        driver.findElement(By.xpath("//*[@id=\"description\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"button-search\"]")).click();
        
        //13,
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[1]/div/div[1]/a/img")).click();
        
        //14
        WebElement quantityInput = driver.findElement(By.xpath("//*[@id='input-quantity']"));
        quantityInput.clear();
        quantityInput.sendKeys("3");
        
        //15
        driver.findElement(By.xpath("//*[@id=\"button-cart\"]")).click();
        
        //17
        driver.findElement(By.xpath("//*[@id=\"cart\"]/button")).click();
               
        //19
        driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[2]/div/p/a[2]/strong")).click();
        
        //20
        driver.findElement(By.linkText("My Account")).click();
        
        //21
        driver.findElement(By.linkText("Logout")).click(); 
        
        //22
        WebElement logoutHeading = driver.findElement(By.xpath("//*[@id='content']/h1"));
        String actualHeadingText = logoutHeading.getText();
        String expectedHeadingText = "Account Logout";
        if (actualHeadingText.equals(expectedHeadingText)) {
            System.out.println("Verification Successful: The page heading (Account Logout) is correct.");
        } else {
            System.out.println("Verification Failed: The page heading is not correct.");
        }
        
        //23
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/a")).click();
        
        // Close browser after execution
        driver.quit();
    }
}