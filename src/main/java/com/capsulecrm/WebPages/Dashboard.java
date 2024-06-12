package com.capsulecrm.WebPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.Utils.ExplicitWait;

public class Dashboard {

	WebDriver driver;
	
	@FindBy(xpath="//a[@aria-label='People & Organisations']")
	private WebElement peopleNOrg;
	
	@FindBy(xpath="//div[@class='nav-bar-account-details']")
	private WebElement profile;
	
	@FindBy(xpath="//a[@href='/settings']")
	private WebElement accountSetting;
	
	public Dashboard(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	//Navigate to people and organisation page
	public PeopleAndOrg navigateToPeopleNorg()
	{
		ExplicitWait.waitTillClickable(peopleNOrg, driver);
		peopleNOrg.click();
		return new PeopleAndOrg(driver);
	}
	//Navigate to account settings
	public AccountSettings AccountSetting()
	{
		ExplicitWait.waitTillClickable(profile, driver);
		profile.click();
		ExplicitWait.waitTillClickable(accountSetting, driver);
		accountSetting.click();
		return new AccountSettings(driver);
	}
}
