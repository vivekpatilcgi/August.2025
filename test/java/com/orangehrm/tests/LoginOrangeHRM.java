package com.orangehrm.tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangehrm.Base.BaseTest;                 
import com.orangehrm.pages.Login_PageFactory;      
import com.orangehrm.utilities.ExcelUtilities;
import com.orangehrm.utilities.ScreenshotUtilities;

public class LoginOrangeHRM extends BaseTest {     // do NOT redeclare WebDriver here

    @Test(dataProvider = "logindata")
    public void verifylogin(String username, String password) throws IOException {
        test = extent.createTest("Login with the user: " + username);

        Login_PageFactory obj = PageFactory.initElements(driver, Login_PageFactory.class);
        obj.enterUsername(username);
        obj.enterPassword(password);
        obj.clickLogin();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.titleContains("OrangeHRM"));
            String actualTitle = driver.getTitle();
            test.pass("Login Successful and title matched: " + actualTitle);
        } catch (TimeoutException e) {
            String screenpath = ScreenshotUtilities.Capture(driver, "Login_Failed_" + username);
            test.fail("Login unsuccessful; title was: " + driver.getTitle())
                .addScreenCaptureFromPath(screenpath);
            Assert.fail("Login failed for user: " + username);
        }
    }

    @DataProvider(name = "logindata")
    public Object[][] logindata() throws IOException {
        String excelPath = "C:\\Users\\Dell\\OneDrive\\Desktop\\CGI\\Lab\\Lab5\\atomation_ecercise\\src\\test\\resources\\Testdata\\dynamicdata.xlsx";
        return ExcelUtilities.getdata(excelPath, "DynamicData");
    

    }
}
