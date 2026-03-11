package org.project;

import java.io.File;


import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNgProject extends BaseClass {
	@DataProvider(name="loginData")
	
	public Object[][] data(ITestContext context){
	    String browser = context.getCurrentXmlTest().getParameter("browser");

	    return new Object[][] {
	    	{browser,"AF05088924","dur123","Durga","Devi","durgadevirajendran33@gmail.com","ANP_D4432"},
	    	
	    	 {browser,"3333","dur123","","","",""},
	         
	         
	    };
	 //{browser,"AF05088924","dur123","Durga","Devi","durgadevirajendran33@gmail.com"," "},
   	 //{browser,"AF05088924","dur123"," "," "," "," "},
	}
	
	@Test(dataProvider="loginData")
    public void test1(String browser, String user, String pass,String fName,String lName,String emailId,String bCode) throws InterruptedException, IOException {

        browserLaunch(browser);
        urlLaunch();
        maximize();
        
        //login functionality
        LoginPage lp = new LoginPage(driver);
        lp.getUserName().sendKeys(user);
        lp.getPassword().sendKeys(pass);
        lp.getremember().click();
        lp.getClick().click();
        
        //Validation 
        SoftAssert s = new SoftAssert();
        
        String currentUrl = driver.getCurrentUrl();
        if(user.equals("AF05088924") && pass.equals("dur123") && lp.getValidCheck().isDisplayed()) {
            s.assertTrue(true, "Login Successful");
            
  //DashBoard Functionality
            DashBoardPage dp = new DashBoardPage(driver);
            waitAndClick(dp.getProfileDetails()); 
            waitAndClick(dp.getUserDetails());
            waitAndClick(dp.getEditProfile());
            Thread.sleep(3000);
            waitAndClick(dp.getAddPic());
            
           System.out.println("Clikc");
           
           //Webdriver Wait-1
           WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(15));
           Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Upload a file']")));
           
           //saying to take this img to upload(default)
            String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\IMG_20260117_182734.jpg";
            dp.getAddChoosePic().sendKeys(filePath);
            
            waitAndClick(dp.getUploadPic());
            waitAndClick(dp.getOverallUpdate());
         
            waitAndClick(dp.getUserDetails());
            waitAndClick(dp.getEditProfile());
            
            dp.getEditFirstName().clear();
            dp.getEditFirstName().sendKeys(fName);
            
            dp.getEditSureName().clear();
            dp.getEditSureName().sendKeys(lName);
            
            
            dp.getEditEmail().clear();
            Thread.sleep(3000);
            dp.getEditEmail().sendKeys(emailId);
           
            //doing the batchcode
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", dp.getBatchCode());
            
            dp.getBatchCode().clear();
            dp.getBatchCode().sendKeys(bCode);
            
            Select centerSelect = new Select(dp.getBatchCenterCode());
            centerSelect.selectByVisibleText("TNDBS");
            
            Wait.until(ExpectedConditions.elementToBeClickable(dp.getOverallUpdate()));
            jsClick(dp.getOverallUpdate());
            
            //VALIDATE FOR REQUIRED DATA
         // SCENARIO A: Required Fields (Firstname/Surname/Email) are effectively empty
            if(fName.isBlank() || lName.isBlank() || emailId.isBlank() ) {
            	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                		By.xpath("//span[contains(text(),'Missing')]  | //div[contains(text(),'Invalid')]" )));
            	s.assertTrue(error.isDisplayed(), "System Blocked the blank space");
            	takeScreenshot("Validation_Working");
            }
         // SCENARIO B: Batch Code is blank (This is where the BUG is caught)
            else if(bCode.isBlank()) {
            	String url = driver.getCurrentUrl();
                s.assertTrue(url.contains("profile.php"), "BUG FOUND: System allowed blank Batch Code!");
                waitAndClick(dp.getUserDetails());
        		Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//dt[text()='Batch Code']")));
                takeScreenshot("BatchCode_BUG");
                
            }
            System.out.println("batch code updated");

            //MSG Functionalities
            waitAndClick(dp.getCornerProfile());
            waitAndClick(dp.getDropDashboard());
                        
            waitAndClick(dp.getMsg());
            
            Wait.until(ExpectedConditions.elementToBeClickable(dp.getwriteMsg()));
            dp.getwriteMsg().sendKeys("https://www.geeksforgeeks.org/software-testing/how-to-handle-action-class-in-selenium/");
            
            Wait.until(ExpectedConditions.elementToBeClickable(dp.getSendIcon()));
            waitAndClick(dp.getSendIcon());
            takeScreenshot("UI BUG");
            //for closing the msg tab, clicking again 
            Wait.until(ExpectedConditions.elementToBeClickable(dp.getMsg()));
            waitAndClick(dp.getMsg());

          //CUSTOMIZE FUNCTIONALITIES
          waitAndClick(dp.getcustomize());
                      
          WebElement arrow = driver.findElement(By.id("sidepreopen-control"));
          String state = arrow.getAttribute("aria-expanded");
          if ("false".equals(state)) {
              js.executeScript("arguments[0].click();", arrow);
           }
          Thread.sleep(5000);

          //start customizing...
          dp.getStart();
          dp.moveBlock(3);
                    
          Wait.until(ExpectedConditions.elementToBeClickable(dp.getReset()));
          dp.getReset().click();
          
          Wait.until(ExpectedConditions.elementToBeClickable(dp.getcustomize()));
          dp.getcustomize().click();

          dp.moveBlock(1);          
          Wait.until(ExpectedConditions.elementToBeClickable(dp.getStop()));

          dp.getStop().click();
          Thread.sleep(3000);
          //Again clicking the profile icon
          waitAndClick(dp.getProfile());
          dp.getGrade();
          
   //MOVING TO HOMEPAGE...
          HomePage hp = new HomePage(driver);
          waitAndClick(hp.getSite());
          
         //----------------------------ERROR OCCURING IN THIS LINE --------------------------------------------(changed tne homepage)
          waitAndClick(hp.accessCourse());
//          //doing window handling	
//          
//          String parent = driver.getWindowHandle();
          
          js.executeScript("arguments[0].scrollIntoView({block: 'center'});", hp.getLabContent());
          waitAndClick(hp.getLabContent());
//          
//          Set<String> windows = driver.getWindowHandles();
//          for (String win : windows) {
//              if (!win.equals(parent)) {
//                  driver.switchTo().window(win);
//                  //it will open a pdf
//                  String src=""
//                  driver.close();
//              }
//          }
//          driver.switchTo().window(parent);
          Thread.sleep(3000);
          
          Wait.until(ExpectedConditions.elementToBeClickable(hp.getAutoComplete()));
          hp.getAutoComplete();
          hp.getSourceLab().click();
          Thread.sleep(5000);

          String src = "D:\\downloading\\SourceCodes-20251118T053954Z-1-001.zip";
          String dest = "C:\\Users\\ganes\\eclipse-workspace\\MainProject\\Captures\\SourceCodes-20251118T053954Z-1-001.zip";

          try {
        	  downloadFile(src,dest);
          }catch(Exception e) {
        	  System.out.println("Error");          }
          
        } else {
            s.assertTrue(currentUrl.contains("login"), "Invalid login");

        }
        
        
        s.assertAll();
     //  driverQuit();
}
}
