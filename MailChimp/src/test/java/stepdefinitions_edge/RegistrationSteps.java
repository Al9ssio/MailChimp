package stepdefinitions_edge;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.AfterClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;//web driver object
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationSteps {

    private static WebDriver driver;

    @AfterClass
    public static void close() {
        driver.quit();
    }

    //Username busy
    @Given("^I am on the registration page 1$")
    public void i_am_on_the_registration_page_1() {

        System.setProperty("webdriver.edge.driver", "src/msedgedriver.exe");
        driver = new EdgeDriver();
        driver.get("https://login.mailchimp.com/signup/");
    }

    @When("^I enter an email address 1$")
    public void i_enter_an_email_address() {
        WebElement emailField = driver.findElement(By.id("email"));//find email element
        emailField.sendKeys("username@gmail.com");//fill in email
    }

    @When("^I enter a used username$")

    public void i_enter_a_used_username() {
        WebElement usernameField = driver.findElement(By.id("new_username"));//find username element
        usernameField.sendKeys("");//fill nothing as will be autofill with the email
    }

    @When("^I enter a password 1$")
    public void i_enter_a_password_1() {
        String password = generatePassword();//generate strong password
        WebElement passwordField = driver.findElement(By.id("new_password"));//find password element
        passwordField.sendKeys(password);//fill in password
    }

    @When("^I click the sign-up button 1$")
    public void i_click_the_sign_up_button() {
        //create Javascript Executor to click sign up button
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //scroll down by 500 pixels enough to see sugn up button
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
    }

    @Then("^I see an error message saying that the username is already taken$")
    public void i_see_an_error_message_saying_that_the_username_is_already_taken() {
        // Locate the error message element and get its text content
        WebElement errorMessage = driver.findElement(By.cssSelector("span.invalid-error"));
        String errorMessageText = errorMessage.getText();

        // Assert that the error message text matches the expected value
        String expectedErrorMessage = "Great minds think alike - someone already has this username.";
        assertTrue(errorMessageText.startsWith(expectedErrorMessage));
        driver.quit();
    }

    //Username too long
    @Given("^I am on the registration page 2$")
    public void i_am_on_the_registration_page_2() {

        System.setProperty("webdriver.edge.driver", "src/msedgedriver.exe");
        driver = new EdgeDriver();
        driver.get("https://login.mailchimp.com/signup/");
    }

    @When("^I enter a long username$")
    public void i_enter_a_long_username() {
        String longUsername = "";

        for (int i = 1; i <= 101; i++) {
            longUsername += "a";
        }
        WebElement usernameField = driver.findElement(By.id("new_username"));
        usernameField.sendKeys(longUsername);
    }

    @When("^I enter an email address 2$")
    public void i_enter_an_email_2() {
        String email = generateEmail();
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys(email);
    }

    @When("^I enter a password 2$")
    public void i_enter_a_password2() {
        String password = generatePassword();//generate strong password
        WebElement passwordField = driver.findElement(By.id("new_password"));//find password element
        passwordField.sendKeys(password);//fill in password
    }

    @When("^I click the sign-up button 2$")
    public void i_click_the_sign_up_button2() {
        //create Javascript Executor to click sign up button
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //scroll down by 500 pixels enough to see sugn up button
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
    }

    @Then("^I see an error message saying that the username is too long$")
    public void i_see_an_error_message_saying_that_the_usernaame_is_too_long() {
        // Locate the error message element and get its text content
        WebElement errorMessage = driver.findElement(By.cssSelector("span.invalid-error"));
        String errorMessageText = errorMessage.getText();

        // Assert that the error message text matches the expected value
        String expectedErrorMessage = "Enter a value less than 100 characters long";
        assertEquals(expectedErrorMessage, errorMessageText);
        driver.quit();
    }

    //Email missing
    @Given("^I am on the registration page 3$")
    public void i_am_on_the_registration_page_3() {

        System.setProperty("webdriver.edge.driver", "src/msedgedriver.exe");
        driver = new EdgeDriver();
        driver.get("https://login.mailchimp.com/signup/");
    }

    @When("^I do not enter an email address$")
    public void i_dont_enter_a_email() {
    }

    @When("^I enter a username 3$")
    public void i_enter_a_username_3() {
        // Fill in the registration form fields
        String email = generateEmail();
        WebElement usernameField = driver.findElement(By.id("new_username"));
        usernameField.sendKeys(email);
    }

    @When("^I enter a password 3$")
    public void i_enter_a_password_3() {
        String password = generatePassword();//generate strong password
        WebElement passwordField = driver.findElement(By.id("new_password"));//find password element
        passwordField.sendKeys(password);//fill in password
    }

    @When("^I click the sign-up button 3$")
    public void i_click_the_sign_up_button3() {
        //create Javascript Executor to click sign up button
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //scroll down by 500 pixels enough to see sugn up button
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
    }

    @Then("^I see an error message saying that the email address is missing$")
    public void i_see_an_error_message_saying_that_the_email_is_missing() {
        // Locate the error message element and get its text content
        WebElement errorMessage = driver.findElement(By.cssSelector("span.invalid-error"));
        String errorMessageText = errorMessage.getText();

        // Assert that the error message text matches the expected value
        String expectedErrorMessage = "An email address must contain a single @.";
        assertEquals(expectedErrorMessage, errorMessageText);
        driver.quit();
    }

    //Registration successfully
    @Given("^I am on the registration page 4$")
    public void i_am_on_the_registration_page_4() {

        System.setProperty("webdriver.edge.driver", "src/msedgedriver.exe");
        driver = new EdgeDriver();
        driver.get("https://login.mailchimp.com/signup/");
    }

    @When("^I enter an email address 4$")
    public void i_enter_a_username_4() {
        String email = generateEmail();
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys(email);
    }

    @When("^I enter a username 4$")
    public void i_enter_an_username4() {
        // Fill in the registration form fields
        String email = generateEmail();
        WebElement usernameField = driver.findElement(By.id("new_username"));
        usernameField.sendKeys("");
    }

    @When("^I enter a password 4$")
    public void i_enter_a_password_4() {
        String password = generatePassword();//generate strong password
        WebElement passwordField = driver.findElement(By.id("new_password"));//find password element
        passwordField.sendKeys(password);//fill in password
    }

    @When("^I click the sign-up button 4$")
    public void i_click_the_sign_up_button4() {
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
    }

    @Then("^I am taken to the success registration page$")
    public void i_register_with_success() {

        // Get the current URL before clicking the button
        String currentUrl = driver.getCurrentUrl();

        //check if page changed to success registration page
        assertTrue(currentUrl.startsWith("https://login.mailchimp.com/signup/success/"));
        driver.quit();
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

}

