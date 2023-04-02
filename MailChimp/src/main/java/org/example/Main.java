package org.example;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name of browser: ");
        String browser = scanner.nextLine();
        WebDriver driver;
        if(browser.equals("chrome")) {
            System.setProperty("web driver.chrome.driver", ".\\src\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            // Create a new instance of the ChromeDriver
            driver = new ChromeDriver(options);
        } else if (browser.equals("edge")) {
            System.setProperty("web driver.edge.driver", ".\\src\\ms edge driver.exe");
            // Create a new instance of the ChromeDriver
            driver = new EdgeDriver();
        }
        else {
            driver = null;
        }
        // Navigate to the website registration page
        driver.get("https://login.mailchimp.com/signup/");

        // Fill in the registration form fields
        String email = generateEmail();
        WebElement usernameField = driver.findElement(By.id("new_username"));
        usernameField.sendKeys(email);

        String username = email;
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys(username);

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

        // Get the current URL before clicking the button
        String currentUrl = driver.getCurrentUrl();

        // Get the current URL after clicking the button
        String newUrl = driver.getCurrentUrl();

        // Check if the URLs are the same
        while (currentUrl.equals(newUrl)) {
            // Click the button again
            button.click();

            // Wait for 1 second before checking the URL again
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Get the current URL again
            newUrl = driver.getCurrentUrl();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the browser
        driver.quit();
    }

    public static String generatePassword() {
        String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
        String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String DIGITS = "0123456789";
        String SPECIAL_CHARS = "!@#$%^&*()_-+=[]{}|;:,.<>/?";
        String ALL_CHARS = LOWERCASE_CHARS + UPPERCASE_CHARS + DIGITS + SPECIAL_CHARS;

        Random random = new Random();
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

    private static String generateEmail() {
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder username = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            username.append(characters.charAt(random.nextInt(characters.length())));
        }
        String name = username.toString();
        return name+"@gmail.com";
    }

}
