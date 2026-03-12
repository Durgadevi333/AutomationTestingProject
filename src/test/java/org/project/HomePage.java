package org.project;

import java.time.Duration;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BaseClass{
	    public HomePage() {
	       
            PageFactory.initElements(driver, this);
	}
//	    //That menu btn
//	    @FindBy(xpath="//button[@aria-controls='nav-drawer']")
//		private WebElement menuBtn;
//		public WebElement getMenuBtn() {
//	        return menuBtn;
//		}
	    @FindBy(xpath="//span[contains(text(),'Site home')]")
		private WebElement siteHome;
		public WebElement getSite() {
		      return siteHome;
		}
		@FindBy(xpath="(//a[text()='Access'])[14]")
		private WebElement course;
		public WebElement accessCourse() {
			
			return course;
		}
		@FindBy(xpath="//span[text()='Day 9 - Facilitation Guide (Polymorphism)']")
		private WebElement labContent;
		public WebElement getLabContent() {
			return labContent;
		}
		@FindBy(xpath="//img[contains(@alt,'Day 9 - Interview Questions')]")
		private WebElement checkStatus;
		public WebElement getCheckStatus() {
			return checkStatus;
		}
		
		@FindBy(xpath="//img[contains(@title,'Day 9 - Interview Questions')]")
		private WebElement autoComplete;
		public WebElement getAutoComplete() {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(autoComplete));
			JavascriptExecutor js = (JavascriptExecutor) driver;   
			js.executeScript("arguments[0].click();", autoComplete);
			return autoComplete;
		}
		@FindBy(xpath="//span[text()='Lab Source Code']")
		private WebElement sourceLab;
		public WebElement getSourceLab() {
			return sourceLab;
		}
		
}
