package org.project;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass{
	public LoginPage() {	
		PageFactory.initElements(driver,this);;
	}
	@FindBy(id="username")
	private WebElement username;
	public WebElement getUserName() {
		return username;
	}
	@FindBy(id="password")
	private WebElement password;
	public WebElement getPassword() {
	    return password;
	}
	@FindBy(xpath="//input[@name='rememberusername']")
	private WebElement remember;
	
	public WebElement getremember() {
		return remember;
	}
	@FindBy(id="loginbtn")
	private WebElement click;
	public WebElement getClick() {
		return click;
	}
	@FindBy(xpath="//img[contains(@alt,'diya')]")
	private WebElement validCheck;
	public WebElement getValidCheck() {
		return validCheck;
	}

	
}

