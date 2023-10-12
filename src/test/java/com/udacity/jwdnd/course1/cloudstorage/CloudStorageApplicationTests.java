package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

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
    public void getLoginPage() {
        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    /**
     * PLEASE DO NOT DELETE THIS method.
     * Helper method for Udacity-supplied sanity checks.
     **/
    private void doMockSignUp(String firstName, String lastName, String userName, String password) {
        // Create a dummy account for logging in later.
        var signupPage = new SignupPage(this.driver, this.port);
        signupPage.doMockSignUp(firstName, lastName, userName, password);
        Assertions.assertTrue(
                driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
    }


    /**
     * PLEASE DO NOT DELETE THIS method.
     * Helper method for Udacity-supplied sanity checks.
     **/
    private void doLogIn(String userName, String password) {
        // Log in to our dummy account.
        var loginPage = new LoginPage(driver, port);
        loginPage.doLogin(userName, password);
    }

    /**
     * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
     * rest of your code.
     * This test is provided by Udacity to perform some basic sanity testing of
     * your code to ensure that it meets certain rubric criteria.
     * <p>
     * If this test is failing, please ensure that you are handling redirecting users
     * back to the login page after a successful sign-up.
     * Read more about the requirement in the rubric:
     * <a href="https://review.udacity.com/#!/rubrics/2724/view">...</a>
     */
    @Test
    public void testRedirection() {
        // Create a test account
        doMockSignUp("Redirection", "RT", "RTUser", "RTPassword");

        // Check if we have been redirected to the login page.
        Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
    }

    /**
     * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
     * rest of your code.
     * This test is provided by Udacity to perform some basic sanity testing of
     * your code to ensure that it meets certain rubric criteria.
     * <p>
     * If this test is failing, please ensure that you are handling bad URLs
     * gracefully, for example with a custom error page.
     * <p>
     * Read more about custom error pages at:
     * <a href="https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page">...</a>
     */
    @Test
    public void testBadUrl() {
        // Create a test account
        doMockSignUp("URL", "UT", "RedirectTest", "RedirectPassword");
        doLogIn("RedirectTest", "RedirectPassword");

        // Try to access a random made-up URL.
        driver.get("http://localhost:" + this.port + "/some-random-page");
        Assertions.assertTrue(driver.getPageSource().contains("Whitelabel Error Page"));
    }


    /**
     * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
     * rest of your code.
     * This test is provided by Udacity to perform some basic sanity testing of
     * your code to ensure that it meets certain rubric criteria.
     * <p>
     * If this test is failing, please ensure that you are handling uploading large files (>1MB),
     * gracefully in your code.
     * <p>
     * Read more about file size limits here:
     * <a href="https://spring.io/guides/gs/uploading-files/">...</a> under the "Tuning File Upload Limits" section.
     */
    @Test
    public void testLargeUpload() {
        // Create a test account
        doMockSignUp("Large File", "LFT", "LFTUser", "LFTPassword");
        doLogIn("LFTUser", "LFTPassword");

        // Try to upload an arbitrary large file
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
        String fileName = "upload5m.zip";

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
        WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
        fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

        WebElement uploadButton = driver.findElement(By.id("uploadButton"));
        uploadButton.click();
        try {
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Large File upload failed");
        }

        Assertions.assertTrue(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));
    }

    /**
     * Test that verifies that an unauthorized user can only access the login and signup pages.
     */
    @Test
    public void testUnauthorizedAccess() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
        driver.get("http://localhost:" + this.port + "/home");

        // redirect to login page
        Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());

        driver.get("http://localhost:" + this.port + "/signup");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("display-5")));
        Assertions.assertTrue(driver.getPageSource().contains("Sign Up"));
    }

    /**
     * Test that signs up a new user,
     * - logs in,
     * - verifies that the home page is accessible,
     * - logs out,
     * - and verifies that the home page is no longer accessible.
     */
    @Test
    public void testHomePage() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

        doMockSignUp("test", "test","Test", "test");
        doLogIn("Test", "test");
        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logoutDiv")));
        WebElement logoutBtn = driver.findElement(By.id("logout-btn"));
        logoutBtn.click();
        Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
        Assertions.assertFalse(driver.getPageSource().contains("Home"));
    }
}