package org.project;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GuestPage extends BaseClass{
	public  GuestPage(WebDriver driver) {
		this.driver = driver;
        PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//button[text()='Log in as a guest']")
	private WebElement guest;
	public WebElement getGuestButton() {
		return guest;
	}
	@FindBy(xpath="//span[text()='Home']")
	private WebElement home;
	public WebElement getHomeButton() {
		return home;
	}
	@FindBy(xpath="(//a[text()='Access'])[2]")
	private WebElement course;
	public WebElement getCourse() {
		return course;
	}
//	// Add this to your GuestPage class
//	@FindBy(xpath = "//button[@aria-controls='nav-drawer']")
//	private WebElement menuBtn;

//	@FindBy(xpath="//span[text()='Calendar']")
//	private WebElement calendar;
//	public WebElement getCalendar() {
//		return calendar;
//	}
	
	@FindBy(xpath="//a[text()='www.anudip.org']")
	private WebElement web;
	public WebElement getWeb() {
		return web;
	}
	
	@FindBy(xpath="//img[@title='Facebook']")
	private WebElement facebook;
	public WebElement getFacebook() {
		return facebook;
	}
	
	@FindBy(xpath="//img[@title='Twitter']")
	private WebElement twitter;
	public WebElement getTwitter() {
		return twitter;
	}
	
	@FindBy(xpath="//img[@title='Linkedin']")
	private WebElement linkedIn;
	public WebElement getLinkedIn() {
		return linkedIn;
	}
	@FindBy(xpath="//img[@title='Youtube']")
	private WebElement youtube;
	public WebElement getYoutube() {
		return youtube;
	}
	
	@FindBy(xpath="//button[text()='Continue']")
	private WebElement login;
	public WebElement guestLogin() {
		return login;
	}
	@FindBy(xpath="//a[text()='Forgotten your username or password?']")
	private WebElement forgetPass;
	public WebElement getForgetPass() {
		return forgetPass;
	}
	@FindBy(xpath="//i[@class='slicon-menu']")
	private WebElement menuClose;
	public void getMenuClose() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", menuClose);
	}
	@FindBy(xpath="//input[@name='username']")
	private WebElement forgetUsername;
	public WebElement getForgetUserName() {
		return forgetUsername;
	}
	@FindBy(xpath="(//input[@type='submit'])[2]")
	private WebElement forgetSearch;
	public WebElement getForgetSearch() {
		return forgetSearch;
	}
}
