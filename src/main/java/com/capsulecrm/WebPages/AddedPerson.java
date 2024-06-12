package com.capsulecrm.WebPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.Utils.ExplicitWait;

public class AddedPerson {
	@FindBy(xpath="//a[@aria-label='People & Organisations']")
	private WebElement peopleAndOrganisation;
	public Logger log=Logger.getLogger(AddedPerson.class);
	WebDriver driver;
	public AddedPerson(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//Navigate to People and organisation page after adding new person
	public PeopleAndOrg navigatePeopleAndOrg()
	{
		log.debug("Navigating to People and Organisation page");
		ExplicitWait.waitTillClickable(peopleAndOrganisation, driver);
		peopleAndOrganisation.click();
		return new PeopleAndOrg(driver);
	}
}
