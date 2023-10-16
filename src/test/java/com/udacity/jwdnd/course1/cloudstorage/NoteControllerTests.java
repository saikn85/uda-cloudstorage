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
public class NoteControllerTests {
    @LocalServerPort
    private int port;
    private WebDriver driver;

    private final String notesTabXPath = "//*[@id=\"nav-notes-tab\"]";
    private final String notesAddBtnXPath = "//*[@id=\"nav-notes\"]/button";
    private final String notesTitleXPath = "//*[@id=\"noteModal\"]/div/div/div[2]/form/div[1]/label";
    private final String notesTitleInpXPath = "//*[@id=\"note-title\"]";
    private final String notesDescInpXPath = "//*[@id=\"note-description\"]";
    private final String notesSaveNotesBtnXPath = "//*[@id=\"noteModal\"]/div/div/div[3]/button[2]";

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
    public void verifyThatUponNotesSubmissionANewNoteIsCreated() {
        this.doMockSignUp("Random", "Random", "Random", "RandomRandom");
        this.doLogIn("Random", "RandomRandom");
        this.createNote("Note Test", "Test note.");

        var wait = new WebDriverWait(this.driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-success")));
        this.driver.findElement(By.xpath("/html/body/div/div/span[2]/a")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(this.notesTabXPath)));
        this.driver.findElement(By.xpath(this.notesTabXPath)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userTable")));
        var table = this.driver.findElement(By.id("userTable"));
        var rows = table.findElements(By.tagName("tr"));

        Assertions.assertFalse(rows.isEmpty());
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

    private void createNote(String noteTitle, String noteDescription) {
        this.driver.findElement(By.xpath(this.notesTabXPath)).click();
        var wait = new WebDriverWait(this.driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(this.notesAddBtnXPath)));
        this.driver.findElement(By.xpath(this.notesAddBtnXPath)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(this.notesTitleXPath)));
        this.driver.findElement(By.xpath(this.notesTitleInpXPath)).sendKeys("Test note");
        this.driver.findElement(By.xpath(this.notesDescInpXPath)).sendKeys("This is a test note.");
        this.driver.findElement(By.xpath(this.notesSaveNotesBtnXPath)).click();
    }
}
