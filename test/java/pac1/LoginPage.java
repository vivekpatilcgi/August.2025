package pac1;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
 
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

class OrangeHRMLoginPage {
	WebDriver driver;
	
	public OrangeHRMLoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By usernameField = By.name("username");
	private By passwordField = By.name("password");
	private By loginButton = By.xpath("//button[@type='submit']");
	private By dashboardText = By.xpath("//h6[text()='Dashboard']");
	
	public boolean usernameIsDisplayed() {
		return driver.findElement(usernameField).isDisplayed();
	}
	
	public void enterUsername(String username) {
		driver.findElement(usernameField).sendKeys(username);
	}
	
	public void enterPassword(String password) {
		driver.findElement(passwordField).sendKeys(password);
	}
	
	public void clickOnLoginButton() {
		driver.findElement(loginButton).click();
	}
	
	public boolean dashboardIsDisplayed() {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			boolean isDisplayed = driver.findElement(dashboardText).isDisplayed();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			return isDisplayed;
		} catch (Exception e) {
			return false;
		}
	}
}
 
public class LoginPage {
	
	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
	String projectPath = System.getProperty("user.dir");

	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter spark = new ExtentSparkReporter(projectPath + "\\Testdata\\Aug28th.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		System.out.println("This is Before Suite");
	}

	@Test(dataProvider = "logindata")
	public void loginTest(String username, String password) {
		test = extent.createTest("Login Test with " + username);

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		OrangeHRMLoginPage objlogin = new OrangeHRMLoginPage(driver);
		
		try {
			if (objlogin.usernameIsDisplayed()) {
				objlogin.enterUsername(username);
				test.pass("Username field is displayed and entered.");
			} else {
				test.fail("Username field not displayed.");
				// If the element is not found, we should stop the test here
				Assert.fail("Username field not displayed.");
			}
			
			objlogin.enterPassword(password);
			objlogin.clickOnLoginButton();
			
			if (objlogin.dashboardIsDisplayed()) {
				test.pass("Login successful. Dashboard is displayed.");
			} else {
				test.fail("Login failed. Dashboard not displayed.");
				Assert.fail("Login failed.");
			}
		} catch (Exception e) {
			test.fail("Test failed due to an exception: " + e.getMessage());
			Assert.fail("Test failed due to an exception.", e);
		}
	}

	@BeforeMethod
	public void setupDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("This is Before Method");
	}

	@AfterMethod
	public void tearDownDriver() {
		if (driver != null) {
			driver.quit();
		}
		System.out.println("This is After Method");
	}

	@AfterSuite
	public void flushReport() {
		if (extent != null) {
			extent.flush();
		}
		System.out.println("This is After Suite");
	}

	@DataProvider
	public String[][] logindata() throws IOException {
		String[][] data = null;
		File file1 = new File(projectPath + "\\Testdata\\dynamicdata.xlsx");
		System.out.println("Project path: " + projectPath);
		
		try (FileInputStream fs = new FileInputStream(file1);
			 XSSFWorkbook workbook = new XSSFWorkbook(fs)) {
			
			XSSFSheet worksheet = workbook.getSheetAt(0);
			int rowcount = worksheet.getPhysicalNumberOfRows();
			int colcount = worksheet.getRow(0).getPhysicalNumberOfCells(); // Get column count from the first row
			
			data = new String[rowcount][colcount];
			
			System.out.println("Rows: " + rowcount);
			
			for (int i = 0; i < rowcount; i++) {
				for (int j = 0; j < colcount; j++) {
					data[i][j] = worksheet.getRow(i).getCell(j).getStringCellValue();
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found at: " + file1.getAbsolutePath());
			throw e;
		}
		
		return data;
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("This is Before Class");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("This is After Class");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("This is Before Test");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("This is After Test");
	}
}
