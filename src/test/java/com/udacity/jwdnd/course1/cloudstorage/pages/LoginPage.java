package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    @FindBy(id = "inputUsername")
    private WebElement _loginUserName;

    @FindBy(id = "inputPassword")
    private WebElement _loginPassword;

    @FindBy(id = "login-button")
    private WebElement _loginButton;

    private WebDriver _driver;
    private final WebDriverWait _waiter;
    private int _port;

    public LoginPage(WebDriver driver, int port) {
        PageFactory.initElements(driver, this);
        this._driver = driver;
        this._port = port;
        this._waiter = new WebDriverWait(driver, 5);
    }

    public void doLogin(String userName, String password){
        _waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
        _loginUserName.click();
        _loginUserName.sendKeys(userName);

        _waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
        _loginPassword.click();
        _loginPassword.sendKeys(password);

        _waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        _loginButton.click();

        _waiter.until(ExpectedConditions.titleContains("Home"));
    }
}
