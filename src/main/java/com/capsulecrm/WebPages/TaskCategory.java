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

public class TaskCategory {

	@FindBy(xpath="//input[@id='editCategoryForm:taskCategoryNameDecorate:taskCategoryName']")
	private WebElement name;
	
	@FindBy(xpath="//input[@id='editCategoryForm:j_id175']")
	private WebElement save;
	
	public Logger log=Logger.getLogger(TaskCategory.class);
	private WebDriver driver;
	public TaskCategory(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	//Adds new task category and returns enterd data in map
	public List<Map<String, String>> add()
	{
		log.debug("Adding new Task Category");
		List<Map<String, String>> data=ReadXlsx.getData("TASK CATEGORY");
		for(int i=0;i<data.size();i++)
		{
			Map<String, String> user=data.get(i);
			new AccountSettings(driver).getAddNew().click();
			ExplicitWait.waitTillClickable(name, driver);
			ExplicitWait.waitTillvisible(name, driver);
			name.sendKeys(user.get("NAME"));
			save.click();
			
			
		}
		return data;
	}
}
