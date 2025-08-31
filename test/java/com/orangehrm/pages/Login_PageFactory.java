package com.orangehrm.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_PageFactory {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(css = "button[type='submit']")
    private WebElement loginBtn;

    public Login_PageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterUsername(String user) {
        wait.until(ExpectedConditions.visibilityOf(username)).clear();
        username.sendKeys(user);
    }

    public void enterPassword(String pass) {
        wait.until(ExpectedConditions.visibilityOf(password)).clear();
        password.sendKeys(pass);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }

    public void loginAs(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }
}
