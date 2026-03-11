package org.project;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BaseClass{
	    public HomePage(WebDriver driver) {
            PageFactory.initElements(driver, this);
	}
	    @FindBy(xpath="//span[contains(text(),'Site home')]")
		private WebElement SiteHome;
		public WebElement getSite() {
			JavascriptExecutor js = (JavascriptExecutor) driver;   
	        js.executeScript("arguments[0].click();", SiteHome);	
	        return SiteHome;
		}
		@FindBy(xpath="(//a[text()='Access'])[14]")
		private WebElement course;
		public WebElement accessCourse() {
			JavascriptExecutor js = (JavascriptExecutor) driver;   
			js.executeScript("arguments[0].scrollIntoView({block: 'center'});", course);
			js.executeScript("arguments[0].click();", course);
			return course;
		}
		@FindBy(xpath="//span[contains(text(),\"Day 3 \")]")
		private WebElement labContent;
		public WebElement getLabContent() {
			return labContent;
		}
		
		@FindBy(xpath="//img[contains(@title,'Day 9 - Interview Questions')]")
		private WebElement autoComplete;
		public WebElement getAutoComplete() {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			 wait.until(ExpectedConditions.elementToBeClickable(autoComplete));
			JavascriptExecutor js = (JavascriptExecutor) driver;   
			js.executeScript("arguments[0].scrollIntoView({block: 'center'});", autoComplete);
			js.executeScript("arguments[0].click();", autoComplete);
			return autoComplete;
		}
		@FindBy(xpath="//span[text()='Lab Source Code']")
		private WebElement sourceLab;
		public WebElement getSourceLab() {
			return sourceLab;
		}
		
}
