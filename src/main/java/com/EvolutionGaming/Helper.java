package com.EvolutionGaming;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.EvolutionGaming.PageObjects.*;

public class Helper {
    public static ChromeOptions opt;
    public static ChromeDriver driver;
    private static Random rand = new Random();
    private static JavascriptExecutor executor;

    public static void initializeDriver() throws IOException {
        opt = new ChromeOptions();
        capabilities();
        driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        executor = driver;
        getUrl();
    }

    private static void capabilities() throws IOException {
        WebDriverManager.chromedriver().version(data("chromeVersion")).setup();
        opt.addArguments("--disable-notifications");
        opt.addArguments("disable-infobars");
        opt.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        opt.setExperimentalOption("useAutomationExtension", false);
        opt.addArguments("--disable-web-security");
        opt.addArguments("--no-proxy-server");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        opt.setExperimentalOption("prefs", prefs);
    }


    public static String data(String data) throws IOException { // Extracts values from property file
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("Properties.properties");
        prop.load(fis);
        return prop.getProperty(data);
    }

    public static void clickBy(By by) throws InterruptedException {
        WebElement element = findElement(by);
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(800);
        executor.executeScript("arguments[0].click();", element);
        System.out.println("Clicked " + by);
    }

    public static int clickByRandom(By by) throws InterruptedException {
        Thread.sleep(800);
        WebElement element;
        int randomIndex = rand.nextInt(findElements(by).size());
        element = findElements(by).get(randomIndex);
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
        executor.executeScript("arguments[0].click();", element);
        System.out.println("Clicked " + by + " indexed: " + randomIndex);
        return randomIndex;
    }

    public static boolean isEmpty(By by) {
        return !driver.findElements(by).isEmpty();
    }

    public static WebElement findElements(By by, int index) {
        return driver.findElements(by).get(index);
    }

    public static List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    public static WebElement findElement(By by) {
        return driver.findElement(by);
    }

    public static void closeSession() {
        driver.quit();
        System.out.println("ChromeDriver has closed");
    }
}
