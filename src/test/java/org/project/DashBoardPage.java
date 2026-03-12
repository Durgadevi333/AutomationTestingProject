package org.project;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashBoardPage extends BaseClass {
	
	public DashBoardPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//img[contains(@class,'userpicture')])[2]")
    private WebElement profilepic;
    public WebElement getProfileDetails() {
        return profilepic;
    }
    @FindBy(xpath = "//a[text()='Details']")
    private WebElement userDetails;
    public WebElement getUserDetails() {
        return userDetails;
    }
    
    @FindBy(xpath = "//a[text()='Edit profile']")
    private WebElement editProfile;
    public WebElement getEditProfile() {
        return editProfile;
    }
    
    @FindBy(xpath = "//a[@title='Add...']")
    private WebElement addProfilepic;
    public WebElement getAddPic() {
        return addProfilepic;
    }
    
    @FindBy(xpath="//input[@type='file']")
    private WebElement addChoosePic;
    public WebElement getAddChoosePic() {        
    	return addChoosePic;
    }
    
    @FindBy(xpath = "//button[text()='Upload this file']")
    private WebElement uploadProfilepic;
    public WebElement getUploadPic() {
        return uploadProfilepic;
    }
    @FindBy(xpath = "//input[@value='Update profile']")
    private WebElement OverallUpdate;
    public WebElement getOverallUpdate() {
        return OverallUpdate;
    }
    @FindBy(id="id_firstname")
    private WebElement editFirstName;
    public WebElement getEditFirstName() {
    	return editFirstName;
    }
    @FindBy(id="id_lastname")
    private WebElement editSureName;
    public WebElement getEditSureName() {
    	return editSureName;
    }
    @FindBy(id="id_email")
    private WebElement editEmail;
    public WebElement getEditEmail() {
    	return editEmail;
    }
    @FindBy(id="id_profile_field_batchcode")
    private WebElement editBatchCode;
    public WebElement getBatchCode() {
    	return editBatchCode;
    }
    @FindBy(id="id_profile_field_centercode")
    private WebElement editBatchCentreCode;
    public WebElement getBatchCenterCode() {
    	return editBatchCentreCode;
    }
    @FindBy(xpath="//img[@class='userpicture']")
    private WebElement cornerProfileIcon;
    public WebElement getCornerProfile() {
    	return cornerProfileIcon;
    }
    @FindBy(xpath="(//span[text()='Dashboard'])[1]")
    private WebElement dropDashboard;
    public WebElement getDropDashboard() {
    	return dropDashboard;
    }
     
    @FindBy(xpath = "//span[text()='Message']")
    private WebElement msg;
    public WebElement getMsg() {
        return msg;
    }

    @FindBy(xpath = "//textarea[@placeholder='Write a message...']")
    private WebElement writeMsg;
    public WebElement getwriteMsg() {
        return writeMsg;
    }

    @FindBy(xpath = "//span[@data-region='send-icon-container']")
    private WebElement sendIcon;
    public WebElement getSendIcon() {
        return sendIcon;
    }

    @FindBy(xpath = "//button[text()='Customise this page']")
    private WebElement customize;
    public WebElement getcustomize() {
        return customize;
    }
    
    @FindBy(id="sidepreopen-control")
    private WebElement arrow;
    public WebElement getArrow() {
    	return arrow;
    }
    
    @FindBy(xpath = "//button[contains(text(),'Reset page')]")
    private WebElement reset;
    public WebElement getReset() {
        return reset;
    }

    @FindBy(xpath = "//button[text()='Stop customising this page']")
    private WebElement stop;
    public WebElement getStop() {
        return stop;
    }

    @FindBy(xpath = "(//span[contains(@title,'Move')])[1]")
    private WebElement start;
    public WebElement getStart() {
        return start;
    }
    
    public void moveBlock(int times) {
        waitAndClick(getStart()); 
        
        Actions act = new Actions(driver);
        for (int i = 0; i < times; i++) {
            act.sendKeys(Keys.DOWN).perform();
        
        }
        act.sendKeys(Keys.ENTER).perform();
    }

    @FindBy(xpath = "(//img[@class='userpicture'])[1]")
    private WebElement profile;
    public WebElement getProfile() {
        return profile;
    }

    @FindBy(xpath = "//span[text()='Grades']")
    private WebElement grade;
    public void getGrade() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", grade);
        
    }
}