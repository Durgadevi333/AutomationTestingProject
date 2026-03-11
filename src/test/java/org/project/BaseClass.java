package org.project;

import java.io.File;

import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver; 

    public void browserLaunch(String browser) {
        if(browser.equalsIgnoreCase("chrome")) {
          WebDriverManager.chromedriver().setup(); 
          driver = new ChromeDriver();
        } 
        else if(browser.equalsIgnoreCase("edge")) {
            WebDriverManager.chromedriver().setup(); 
            driver = new EdgeDriver();
        } 
        else if(browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup(); 
            driver = new FirefoxDriver();
        }
    }
	
    public void urlLaunch() {
        driver.get("https://learn.anudip.org/login/index.php");
    }

    public void maximize() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    
    public void jsClick(WebElement element) {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    	js.executeScript("arguments[0].click();", element);
    	
    }
    public void waitAndClick(WebElement element) {

        // 1. We use WebDriverWait to handle the TIMING
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        jsClick(element);
    }
 
    
    public void takeScreenshot(String name) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcScrenshot = ts.getScreenshotAs(OutputType.FILE);
        File destScrenshot = new File("C:\\Users\\ganes\\eclipse-workspace\\MainProject\\Captures\\"+ name + ".png");
        FileUtils.copyFile(srcScrenshot, destScrenshot);
    			
    }
    
    public void downloadFile(String sourcePath, String destPath) throws IOException {
        File srcFile = new File(sourcePath);
        File destFile = new File(destPath);
        FileUtils.copyFile(srcFile, destFile);
        System.out.println("Transfer successful: " + destFile.getName());
  
    }
    
//    @AfterMethod
//    public void driverQuit() {
//    	driver.quit();
//    }
}
