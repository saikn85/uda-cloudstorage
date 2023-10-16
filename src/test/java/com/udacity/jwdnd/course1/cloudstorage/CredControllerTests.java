package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredControllerTests {
    private final String credTabXPath = "//*[@id=\"nav-credentials-tab\"]";
    private final String credAddBtnXPath = "//*[@id=\"nav-credentials\"]/button";
    private final String credUrlXPath = "//*[@id=\"credentialModal\"]/div/div/div[2]/form/div[1]/label";
    private final String credUrlInpXPath = "//*[@id=\"credential-url\"]";
    private final String credUsernameInpXPath = "//*[@id=\"credential-username\"]";
    private final String credPasswordInpXPath = "//*[@id=\"credential-password\"]";
    private final String credSaveCredBtnXPath = "//*[@id=\"credentialModal\"]/div/div/div[3]/button[2]";

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.edgedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new EdgeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyThatUponCredSubmissionTheCredIsSecurelyStored() {
        this.doMockSignUp("Random", "Random", "Random", "RandomRandom");
        this.doLogIn("Random", "RandomRandom");
        this.createCredential("jdbc:h2:mem:cloud-storage", "randomizer", "randomizerPassword");

        var wait = new WebDriverWait(this.driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(this.credTabXPath)));
        this.driver.findElement(By.xpath(this.credTabXPath)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialTable")));
        var table = this.driver.findElement(By.id("credentialTable"));
        var rows = table.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));

        Assertions.assertFalse(rows.isEmpty());
    }

    @Test
    public void verifyThatUserIsAbleToUpdateAnySelectedCred() {
        this.doMockSignUp("Random", "Random", "random1", "noteDescription");
        this.doLogIn("random1", "noteDescription");
        this.createCredential("Random.com", "RandomRandom", "Rambo3");

        var wait = new WebDriverWait(this.driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(this.credTabXPath)));
        this.driver.findElement(By.xpath(this.credTabXPath)).click();
        var passwordBefore = this.driver.findElement(
                        By.xpath("//*[@id=\"credentialTable\"]/tbody/tr/td[3]"))
                .getText();

        this.editCredential("NakatomiTowers1");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(this.credTabXPath)));
        this.driver.findElement(By.xpath(this.credTabXPath)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialTable")));
        var passwordAfter = this.driver.findElement(
                        By.xpath("//*[@id=\"credentialTable\"]/tbody/tr/td[3]"))
                .getText();

        Assertions.assertNotEquals(passwordBefore, passwordAfter);
    }

    @Test
    public void verifyThatUserIsAbleToDeleteAnySelectedCred() {
        this.doMockSignUp("Random", "Random", "random", "randomizer");
        this.doLogIn("random", "randomizer");
        this.createCredential("Random.com", "RandomRandom", "Rambo3");

        var wait = new WebDriverWait(this.driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(this.credTabXPath)));
        this.driver.findElement(By.xpath(this.credTabXPath)).click();

        this.deleteCredential();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(this.credTabXPath)));
        this.driver.findElement(By.xpath(this.credTabXPath)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialTable")));
        var table = this.driver.findElement(By.id("credentialTable"));
        var rows = table.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));

        Assertions.assertTrue(rows.isEmpty());
    }

    private void doMockSignUp(String firstName, String lastName, String userName, String password) {
        // Create a dummy account for logging in later.
        var signupPage = new SignupPage(this.driver, this.port);
        signupPage.doMockSignUp(firstName, lastName, userName, password);
        Assertions.assertTrue(
                driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
    }

    private void doLogIn(String userName, String password) {
        // Log in to our dummy account.
        var loginPage = new LoginPage(driver, port);
        loginPage.doLogin(userName, password);
    }

    private void createCredential(String url, String username, String password) {
        this.driver.findElement(By.xpath(this.credTabXPath)).click();
        var wait = new WebDriverWait(this.driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(this.credAddBtnXPath)));
        this.driver.findElement(By.xpath(this.credAddBtnXPath)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(this.credUrlXPath)));
        this.driver.findElement(By.xpath(this.credUrlInpXPath)).sendKeys(url);
        this.driver.findElement(By.xpath(this.credUsernameInpXPath)).sendKeys(username);
        this.driver.findElement(By.xpath(this.credPasswordInpXPath)).sendKeys(password);
        this.driver.findElement(By.xpath(this.credSaveCredBtnXPath)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-success")));
        this.driver.findElement(By.xpath("/html/body/div/div/span[2]/a")).click();
    }

    private void editCredential(String password) {
        var wait = new WebDriverWait(this.driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"credentialTable\"]/tbody/tr/td[1]/button")));
        this.driver.findElement(By.xpath("//*[@id=\"credentialTable\"]/tbody/tr/td[1]/button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(this.credUrlXPath)));
        this.driver.findElement(By.xpath(this.credPasswordInpXPath)).clear();
        this.driver.findElement(By.xpath(this.credPasswordInpXPath)).sendKeys(password);
        this.driver.findElement(By.xpath(this.credSaveCredBtnXPath)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-success")));
        this.driver.findElement(By.xpath("/html/body/div/div/span[2]/a")).click();
    }

    private void deleteCredential() {
        var wait = new WebDriverWait(this.driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialTable")));
        this.driver.findElement(By.xpath("//*[@id=\"credentialTable\"]/tbody/tr/td[1]/a")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-success")));
        this.driver.findElement(By.xpath("/html/body/div/div/span[2]/a")).click();
    }
}
