package pac1;
 
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
 
public class TC006_Alerts {
    public static void main(String[] args) throws InterruptedException {
        
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximizing window is a good practice
        driver.get("https://letcode.in/alert");
 
        // 1. Simple Alert
        driver.findElement(By.id("accept")).click();
        Alert simpleAlert = driver.switchTo().alert();
        System.out.println("Simple Alert Text: " + simpleAlert.getText());
        simpleAlert.accept();
 
        // 2. Confirm Alert
        driver.findElement(By.id("confirm")).click();
        Alert confirmAlert = driver.switchTo().alert();
        System.out.println("Confirm Alert Text: " + confirmAlert.getText());
        confirmAlert.dismiss(); // Let's dismiss this one for a change
 
        // 3. Prompt Alert
        driver.findElement(By.id("prompt")).click();
        Alert promptAlert = driver.switchTo().alert();
        System.out.println("Prompt Alert Text: " + promptAlert.getText());
        promptAlert.sendKeys("Vivek Patil");
        promptAlert.accept();
 
        // 4. Modern Alert (Sweet Alert)
        driver.findElement(By.id("modern")).click();
        driver.findElement(By.xpath("/html/body/app-root/app-alert/section/div/div/div[1]/div/div/div[5]/button")).click();
        System.out.println("Modern Alert Closed");
        
        driver.quit();
    }
}