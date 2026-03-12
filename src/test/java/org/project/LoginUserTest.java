package org.project;

import java.io.File;



import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginUserTest extends BaseClass {
    @Parameters("browser")
    @BeforeMethod
    public void setup(String browser) {

        browserLaunch(browser);
        urlLaunch();
        maximize();

    }

   
    @DataProvider(name = "loginData")
    public Object[][] data() {
        return new Object[][]{
                {"AF05088924", "dur123", "Durga", "Devi", "durgadevirajendran33@gmail.com", " "},
                {"3333", "dur123", "", "", "", ""},
                {"AF05088924", "982q", "", "", "", ""},
                {"=$de", "3dw", "", "", "", ""},
                {" ", " ", "", "", "", ""}                
        };        
    }
    
    
	@Test(dataProvider="loginData",priority = 2)
    public void test1(String username, String password,String fName,String lName,String emailId,String bCode) throws InterruptedException, IOException {

        
        //login functionality
        LoginPage lp = new LoginPage();
        lp.getUserName().sendKeys(username);
        lp.getPassword().sendKeys(password);
        lp.getremember().click();
        lp.getClick().click();
        
        //Validation 
        SoftAssert s = new SoftAssert();
        
        String currentUrl = driver.getCurrentUrl();
        if(username.equals("AF05088924") && password.equals("dur123") && lp.getValidCheck().isDisplayed()) {
            s.assertTrue(true, "Logged In Successful");
            
  //DashBoard Functionality
            DashBoardPage dp = new DashBoardPage();
            waitAndClick(dp.getProfileDetails()); 
            waitAndClick(dp.getUserDetails());
            waitAndClick(dp.getEditProfile());
            Thread.sleep(3000);
            waitAndClick(dp.getAddPic());
            
           System.out.println("Clicked");
           
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
           wait.until(ExpectedConditions.elementToBeClickable(dp.getUploadPic()));
           
           Thread.sleep(2000);
          
            String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\gallery.jpg";
            dp.getAddChoosePic().sendKeys(filePath);
            Thread.sleep(2000);
            waitAndClick(dp.getUploadPic());
            
            wait.until(ExpectedConditions.elementToBeClickable(dp.getOverallUpdate()));            
            
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
            
            wait.until(ExpectedConditions.elementToBeClickable(dp.getOverallUpdate()));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Update profile']")));
            jsClick(dp.getOverallUpdate());
            
            //VALIDATE FOR BACTH CODE
            
          if(bCode.isBlank()) {
            	String url = driver.getCurrentUrl();
                s.assertTrue(url.contains("profile.php"), "BUG FOUND: System allowed blank Batch Code!");
                waitAndClick(dp.getUserDetails());
        		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//dt[text()='Batch Code']")));
                takeScreenshot("BatchCode_BUG");
                
            }
            System.out.println("batch code updated");

            //MSG Functionalities
            waitAndClick(dp.getCornerProfile());
            waitAndClick(dp.getDropDashboard());
                        
            waitAndClick(dp.getMsg());
            
            wait.until(ExpectedConditions.elementToBeClickable(dp.getwriteMsg()));
            dp.getwriteMsg().sendKeys("https://www.geeksforgeeks.org/software-testing/how-to-handle-action-class-in-selenium/");
            
            wait.until(ExpectedConditions.elementToBeClickable(dp.getSendIcon()));
            waitAndClick(dp.getSendIcon());
            Thread.sleep(3000);
            takeScreenshot("UI BUG");
            
            //for closing the msg tab, clicking again 
            wait.until(ExpectedConditions.elementToBeClickable(dp.getMsg()));
            waitAndClick(dp.getMsg());

          //CUSTOMIZE FUNCTIONALITIES
          waitAndClick(dp.getcustomize());
                      
          String state = dp.getArrow().getAttribute("aria-expanded");
          if ("false".equals(state)) {
        	  jsClick(dp.getArrow());
          }
          Thread.sleep(5000);

          //start customizing...
          dp.getStart();
          dp.moveBlock(3);
                    
          wait.until(ExpectedConditions.elementToBeClickable(dp.getReset()));
          dp.getReset().click();
          
          wait.until(ExpectedConditions.elementToBeClickable(dp.getcustomize()));
          dp.getcustomize().click();

          dp.moveBlock(1);          
          wait.until(ExpectedConditions.elementToBeClickable(dp.getStop()));

          dp.getStop().click();
          Thread.sleep(3000);
          //Again clicking the profile icon
          waitAndClick(dp.getProfile());
          dp.getGrade();
          
   //MOVING TO HOMEPAGE...
          HomePage hp = new HomePage();
          
          jsClick(hp.getSite());
          
          
          jsClick(hp.accessCourse());

          
          js.executeScript("arguments[0].scrollIntoView({block: 'center'});", hp.getLabContent());
          waitAndClick(hp.getLabContent());        

          Thread.sleep(2000);
          
          driver.navigate().back();
          
          String currentTitle = hp.getCheckStatus().getAttribute("title"); 
          if(currentTitle.contains("Not completed")) {
        	  jsClick(hp.getAutoComplete());
              }
          
         
          waitAndClick(hp.getSourceLab());
          Thread.sleep(5000);
            
          
        } else {
            s.assertTrue(currentUrl.contains("login"), "Invalid login");

        }
        
        
        s.assertAll();
      
}

    
    @Parameters({"username", "password"})
    @Test(priority = 1)
    public void test2(String username,String password) throws InterruptedException, IOException {


        GuestPage gp = new GuestPage();
        gp.getGuestButton().click();
        gp.getHomeButton().click();
        gp.getCourse().click();

        //Validation
        SoftAssert s = new SoftAssert();
        
        String currentId = driver.getWindowHandle();
        

        WebElement[] allLinks = {gp.getWeb(), gp.getFacebook(), gp.getTwitter(), gp.getLinkedIn(), gp.getYoutube()};
        String[] keyWords = {"anudip", "facebook", "x.com", "linkedin", "youtube"};

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        for (int i = 0; i < allLinks.length; i++) {
            allLinks[i].click();
            Set<String> allIds = driver.getWindowHandles();
            for (String id : allIds) {
                if (!id.equals(currentId)) {
                    driver.switchTo().window(id);
                }
            }

            try {
                wait.until(ExpectedConditions.urlContains(keyWords[i]));
                String actualUrl = driver.getCurrentUrl();
                s.assertTrue(actualUrl.contains(keyWords[i]), "URL mismatch for " + keyWords[i]);
            } catch (Exception e) {
                takeScreenshot("Link_Failure_" + keyWords[i]);
                s.fail("URL Mismatch for" + keyWords[i]);
            }

            driver.close();
            driver.switchTo().window(currentId);
            Thread.sleep(2000);
        }

        gp.guestLogin().click();
        gp.getForgetPass().click();
        gp.getMenuClose();
        gp.getForgetUserName().sendKeys(username);
        gp.getForgetSearch().click();
        takeScreenshot("ForgetPassword Bug");

        gp.getGuestLogin().click();
   
        
        LoginPage lp = new LoginPage();
        lp.getUserName().sendKeys(username);
        lp.getPassword().sendKeys(password);
        lp.getClick().click();

       
        s.assertAll();
      
    }


}

