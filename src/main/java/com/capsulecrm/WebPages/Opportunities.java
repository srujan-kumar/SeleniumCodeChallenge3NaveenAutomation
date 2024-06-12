package com.capsulecrm.WebPages;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.Utils.ReadXlsx;

public class Opportunities {

	@FindBy(xpath="//input[@class='form-input-text milestone-modal-name']")
	private WebElement name;
	
	@FindBy(xpath="//textarea[@class='form-input-text milestone-modal-description']")
	private WebElement description;
	
	@FindBy(xpath="//input[@class='form-input-text milestone-modal-probability']")
	private WebElement probability;
	
	@FindBy(xpath="//input[@class='form-input-text milestone-modal-days-until-stale']")
	private WebElement days;
	
	@FindBy(xpath="//button[@class='async-button ember-view btn-primary']")
	private WebElement save;
	
	private WebDriver driver;
	public Logger log=Logger.getLogger(Opportunities.class);
	public Opportunities(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	//Adds a new opportunity and returns enterd data
	public List<Map<String, String>> add()
	{
		log.debug("Adding new opprotunity");
		List<Map<String, String>> data=ReadXlsx.getData("OPPORTUNITIES");
		for(int i=0;i<data.size();i++)
		{
			Map<String, String> user=data.get(i);
			new AccountSettings(driver).getAddNew().click();
			name.sendKeys(user.get("NAME"));
			description.sendKeys(user.get("DESCRIPTION"));
			probability.sendKeys(user.get("PROBABILITY"));
			days.sendKeys(user.get("DAYS"));
			save.click();
			
			
		}
		return data;
	}
}
