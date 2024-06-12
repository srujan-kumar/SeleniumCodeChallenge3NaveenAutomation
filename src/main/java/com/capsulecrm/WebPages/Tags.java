package com.capsulecrm.WebPages;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.Utils.ExplicitWait;
import com.capsulecrm.Utils.ReadXlsx;

public class Tags {
	@FindBy(xpath="//input[@id='j_id177:tagNameDecorate:tagName']")
	private WebElement name;
	
	@FindBy(xpath="//input[@class='btn-primary singlesubmit']")
	private WebElement save;
	public Logger log=Logger.getLogger(Tags.class);
	private WebDriver driver;
	public Tags(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	//Adds new tag and returns map with entered data
	public List<Map<String, String>> add()
	{
		log.debug("Adding new tags");
		List<Map<String, String>> data=ReadXlsx.getData("TASK CATEGORY");
		for(int i=0;i<data.size();i++)
		{
			Map<String, String> user=data.get(i);
			new AccountSettings(driver).getAddNew().click();
			ExplicitWait.waitTillClickable(name, driver);
			name.sendKeys(user.get("NAME"));
			save.click();
			
			
		}
		return data;
	}
}
