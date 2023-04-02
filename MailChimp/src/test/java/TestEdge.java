import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;//tests
import org.openqa.selenium.WebDriver;//webdriver object
import org.openqa.selenium.edge.EdgeDriver;//webdriver Edge
import org.openqa.selenium.JavascriptExecutor;//to scroll page
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.Random;//to generate random password

import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.time.Duration;

public class TestEdge {

    private static WebDriver driver;

    @Test
    public void testUsernameBusy() {


        driver = new EdgeDriver();
        driver.get("https://login.mailchimp.com/signup/");
        WebElement emailField = driver.findElement(By.id("email"));//find email element
        emailField.sendKeys("username@gmail.com");//fill in email

        WebElement usernameField = driver.findElement(By.id("new_username"));//find username element
        usernameField.sendKeys("");//fill nothing as will be autofill with the email

        String password = generatePassword();//generate strong password
        WebElement passwordField = driver.findElement(By.id("new_password"));//find password element
        passwordField.sendKeys(password);//fill in password

        //create Javascript Executor to click sign up button
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //scroll down by 500 pixels enough to see sign up button
        js.executeScript("window.scrollBy(0,500)");

        WebElement button = driver.findElement(By.xpath("//*[@id=\"create-account-enabled\"]"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(button).pause(Duration.ofSeconds(1)).release().build().perform();

        //wait for 1 second before checking the URL again
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Locate the error message element and get its text content
        WebElement errorMessage = driver.findElement(By.cssSelector("span.invalid-error"));
        String errorMessageText = errorMessage.getText();

        // Assert that the error message text matches the expected value
        String expectedErrorMessage = "Great minds think alike - someone already has this username.";
        assertTrue(errorMessageText.startsWith(expectedErrorMessage));
        driver.quit();
    }

    @Test
    public void testUsernameLong() {
        driver = new EdgeDriver();
        driver.get("https://login.mailchimp.com/signup/");
        String longUsername = "";

        for (int i = 1; i <= 101; i++) {
            longUsername += "a";
        }
        WebElement usernameField = driver.findElement(By.id("new_username"));
        usernameField.sendKeys(longUsername);

        // Fill in the registration form fields
        String email = generateEmail();
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys(email);

        //generate random password applying conditions
        String password = generatePassword();
        WebElement passwordField = driver.findElement(By.id("new_password"));
        passwordField.sendKeys(password);

        //create Javascript Executor to click sign up button
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll down by 500 pixels
        js.executeScript("window.scrollBy(0,500)");

        //select button element by xpath
        WebElement button = driver.findElement(By.xpath("//*[@id=\"create-account-enabled\"]"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(button).pause(Duration.ofSeconds(1)).release().build().perform();

        //wait for 1 second before checking the URL again
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Locate the error message element and get its text content
        WebElement errorMessage = driver.findElement(By.cssSelector("span.invalid-error"));
        String errorMessageText = errorMessage.getText();

        // Assert that the error message text matches the expected value
        String expectedErrorMessage = "Enter a value less than 100 characters long";
        assertEquals(expectedErrorMessage, errorMessageText);
        driver.quit();

    }

    @Test
    public void emailMissing() {
        driver = new EdgeDriver();
        driver.get("https://login.mailchimp.com/signup/");
        // Fill in the registration form fields
        String email = generateEmail();
        WebElement usernameField = driver.findElement(By.id("new_username"));
        usernameField.sendKeys(email);

        //generate random password applying conditions
        String password = generatePassword();
        WebElement passwordField = driver.findElement(By.id("new_password"));
        passwordField.sendKeys(password);

        //create Javascript Executor to click sign up button
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll down by 500 pixels
        js.executeScript("window.scrollBy(0,500)");

        WebElement button = driver.findElement(By.xpath("//*[@id=\"create-account-enabled\"]"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(button).pause(Duration.ofSeconds(1)).release().build().perform();

        // Locate the error message element and get its text content
        WebElement errorMessage = driver.findElement(By.cssSelector("span.invalid-error"));
        String errorMessageText = errorMessage.getText();

        // Assert that the error message text matches the expected value
        String expectedErrorMessage = "An email address must contain a single @.";
        assertEquals(expectedErrorMessage, errorMessageText);
        driver.quit();
    }

    @Test
    public void testEverythingGood() {
        driver = new EdgeDriver();
        driver.get("https://login.mailchimp.com/signup/");
        // Fill in the registration form fields
        String email = generateEmail();
        WebElement usernameField = driver.findElement(By.id("new_username"));
        usernameField.sendKeys(email);

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys(email);

        //generate random password applying conditions
        String password = generatePassword();
        WebElement passwordField = driver.findElement(By.id("new_password"));
        passwordField.sendKeys(password);

        //create Javascript Executor to click sign up button
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll down by 500 pixels
        js.executeScript("window.scrollBy(0,500)");

        //select button element by xpath
        WebElement button = driver.findElement(By.xpath("//*[@id=\"create-account-enabled\"]"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(button).pause(Duration.ofSeconds(1)).release().build().perform();

        // Wait for 2 seconds before closing the browser
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Get the current URL before clicking the button
        String currentUrl = driver.getCurrentUrl();

        //check if page changed to success registration page
        assertTrue(currentUrl.startsWith("https://login.mailchimp.com/signup/success/"));
        driver.quit();
    }

    // Helper method to generate a random email
    private static String generateEmail() {
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder username = new StringBuilder();//create user part of email of 5 chars
        for (int i = 0; i < 5; i++) {
            username.append(characters.charAt(random.nextInt(characters.length())));
        }
        String name = username.toString();//convert username to string
        return name+"@gmail.com";
    }

    // Helper method to generate a random password
    public static String generatePassword() {
        String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";//all lower letters
        String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//all upper letters
        String DIGITS = "0123456789";//all digits
        String SPECIAL_CHARS = "!@#$%^&*()_-+=[]{}|;:,.<>/?";//all special characters
        String ALL_CHARS = LOWERCASE_CHARS + UPPERCASE_CHARS + DIGITS + SPECIAL_CHARS;

        Random random = new Random();//random object
        StringBuilder password = new StringBuilder();

        // Add at least one lowercase letter
        password.append(LOWERCASE_CHARS.charAt(random.nextInt(LOWERCASE_CHARS.length())));

        // Add at least one uppercase letter
        password.append(UPPERCASE_CHARS.charAt(random.nextInt(UPPERCASE_CHARS.length())));

        // Add at least one digit
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));

        // Add at least one special character
        password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        // Add remaining characters randomly from all character sets
        for (int i = 4; i < 8; i++) {
            password.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }

        return password.toString();
    }
}