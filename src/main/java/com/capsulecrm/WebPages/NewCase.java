package com.capsulecrm.WebPages;

import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.Utils.ExplicitWait;

public class NewCase {
	
	@FindBy(xpath="//input[@id='partySearch']")
	private WebElement caseRelateTo;
	
	
	@FindBy(xpath="//div[@class='searchresult']//ul[1]")
	private WebElement caseRelateAutoSuggest;
	
	@FindBy(xpath="//input[@id='caseNameDecorate:name']")
	private WebElement name;
	
	@FindBy(xpath="//textarea[@id='caseDescriptionDecorate:description']")
	private WebElement description;
	
	@FindBy(xpath="//input[@id='tagsDecorate:tagComboBox']")
	private WebElement tags;
	
	@FindBy(xpath="//input[@id='tagsDecorate:j_id191']")
	private WebElement addTags;
	
	@FindBy(xpath="//input[@id='save']")
	private WebElement save;
	public Logger log=Logger.getLogger(NewCase.class);
	WebDriver driver;
	public NewCase(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//Adds new case on page
	public AddedCase addCase(Map<String, String> data)
	{
		log.debug("Adding new case");
		caseRelateTo.sendKeys(data.get("FIRST NAME")+" "+data.get("LAST NAME"));
		ExplicitWait.waitTillClickable(caseRelateAutoSuggest, driver);
		caseRelateAutoSuggest.click();
		name.sendKeys(data.get("CASE NAME"));
		description.sendKeys(data.get("CASE DESCRIPTION"));
		tags.sendKeys(data.get("CASE TAG"));
		addTags.click();
		ExplicitWait.waitTillClickable(save, driver);
		save.click();
		return new AddedCase(driver);
	}
}
