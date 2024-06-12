package com.capsulecrm.WebPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.ReadProperty.ReadPropertyFile;

public class LoginPage {

	@FindBy(xpath="//input[@id='login:usernameDecorate:username']")
	private WebElement userName;
	
	@FindBy(xpath="//input[@id='login:passwordDecorate:password']")
	private WebElement password;
	
	@FindBy(xpath="//input[@id='login:login']")
	private WebElement loginButton;
	public Logger log=Logger.getLogger(LoginPage.class);
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//Login to website using data provided in TestRunDetails.properties
	public Dashboard login()
	{
		userName.sendKeys(ReadPropertyFile.get("username"));
		password.sendKeys(ReadPropertyFile.get("password"));
		loginButton.click();
		log.debug("Login success");
		return new Dashboard(driver);
	}
	
}
