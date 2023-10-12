package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage {
    @FindBy(id = "inputFirstName")
    private WebElement _inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement _inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement _inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement _inputPassword;

    @FindBy(id = "buttonSignUp")
    private WebElement _buttonSignUp;

    private WebDriver _driver;
    private final WebDriverWait _waiter;
    private int _port;

    public SignupPage(WebDriver driver, int port) {
        PageFactory.initElements(driver, this);
        this._driver = driver;
        this._waiter = new WebDriverWait(_driver, 5);
        this._port = port;
    }

    public void doMockSignUp(String firstName, String lastName, String userName, String password){
        // Visit the sign-up page.
        _driver.get("http://localhost:" + this._port + "/signup");
        _waiter.until(ExpectedConditions.titleContains("Sign Up"));

        // Fill out credentials
        _waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
        _inputFirstName.click();
        _inputFirstName.sendKeys(firstName);

        _waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
        _inputLastName.click();
        _inputLastName.sendKeys(lastName);

        _waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
        _inputUsername.click();
        _inputUsername.sendKeys(userName);

        _waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
        _inputPassword.click();
        _inputPassword.sendKeys(password);

        // Attempt to sign up.
        _waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
        _buttonSignUp.click();
    }
}
