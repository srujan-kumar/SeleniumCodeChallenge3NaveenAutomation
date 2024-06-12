package com.capsulecrm.WebPages;

import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.capsulecrm.Utils.ExplicitWait;

public class NewPerson {

	@FindBy(xpath="//div[@class='select-box select title-select']")
	private WebElement titleDropdown;

	@FindBy(xpath="//div[@id='ember45']")
	private WebElement titleSelection;
	
	@FindBy(css=".party-form-first-name")
	private WebElement FirstName;
	
	@FindBy(css=".party-form-last-name")
	private WebElement LastName;
	
	@FindBy(css=".party-form-job-title")
	private WebElement jobTitle;
	
	@FindBy(css="input[placeholder='Find an organisation']")
	private WebElement organisation;

	@FindBy(css="#ember101")
	private WebElement organisationSelect;

	
	@FindBy(css="input[name='3fbe0eee-fedb-4ba3-8936-6e02e7c2f0ea']")
	private WebElement tags;
	
	@FindBy(id="party:tagsDecorate:j_id187")
	private WebElement addTags;
	
	@FindBy(xpath="//li[@id='tagItem']")
	private WebElement tagItem;
	
	@FindBy(css=".party-form-phone-number")
	private WebElement phoneNo;
	

	@FindBy(css=".party-form-email-address")
	private WebElement email;
	
	@FindBy(css=".party-form-website")
	private WebElement website;
	
	@FindBy(css="button[type='submit']")
	private WebElement save;
	
	public Logger log=Logger.getLogger(NewPerson.class);
	private WebDriver driver;
	public NewPerson(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//Adds a new person, input is taken from Excel sheet.
	public AddedPerson fillAndSubmitPerson(Map<String, String> person)
	{
		log.debug("Adding new person");
		//Select select=new Select(title);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		titleDropdown.click();
		ExplicitWait.waitTillClickable(titleSelection, this.driver);
		titleSelection.click();

		//select.selectByValue(person.get("TITLE"));

		FirstName.sendKeys(person.get("FIRST NAME"));
		LastName.sendKeys(person.get("LAST NAME"));
		jobTitle.sendKeys(person.get("JOB TITLE"));
		//organisation.sendKeys(person.get("ORGANISATION"));
//		organisation.sendKeys("ORGANISATION1234");
//
//		organisationSelect.click();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			throw new RuntimeException(e);
//		}
//
		//ExplicitWait.waitTillClickable(tags, this.driver);

		//tags.sendKeys(person.get("TAGS"));
		//addTags.click();
		ExplicitWait.waitTillClickable(phoneNo, this.driver);
		phoneNo.click();
		phoneNo.sendKeys(person.get("PHONE NUMBER"));
		
		email.sendKeys(person.get("EMAIL ADDRESS"));
		website.sendKeys(person.get("WEBSITES"));
		save.click();
		return new AddedPerson(driver);
	}
}
