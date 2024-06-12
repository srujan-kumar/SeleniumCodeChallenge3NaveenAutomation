package com.capsulecrm.WebPages;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.capsulecrm.Utils.ReadXlsx;

public class Tracks {
	
	@FindBy(xpath="//input[@id='j_id123:trackDescriptionDecorate:trackDescription']")
	private WebElement name;
	
	@FindBy(xpath="//input[@id='j_id123:trackTagDecorate:trackTag']")
	private WebElement tag;
	
	@FindBy(xpath="//input[@id='j_id123:taskLines:0:taskDescriptionDecorate:taskDescription']")
	private WebElement description;
	
	@FindBy(xpath="//span[@class='ui-icon ui-icon-triangle-1-s']")
	private WebElement category;
	
	@FindBy(xpath="//input[@id='j_id123:taskLines:0:taskDaysAfterDecorate:taskDaysAfter']")
	private WebElement due;
	
	@FindBy(xpath="//select[@name='j_id123:taskLines:0:taskAssignmentDecorate:j_id218']")
	private WebElement Assignee;
	
	@FindBy(xpath="//a[@class='btn-primary btn-clear singlesubmit']")
	private WebElement save;
	
	private WebDriver driver;
	public Logger log=Logger.getLogger(Tracks.class);
	public Tracks(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	//Adds new task and returns entered data in form of map
	public List<Map<String, String>> add()
	{
		log.debug("Adding new racks");
		List<Map<String, String>> data=ReadXlsx.getData("TRACKS");
		for(int i=0;i<data.size();i++)
		{
			Map<String, String> user=data.get(i);
			new AccountSettings(driver).getAddNew().click();
			name.sendKeys(user.get("NAME"));
			tag.sendKeys(user.get("TAG"));
			description.sendKeys(user.get("DESCRIPTION"));
			
			Actions actions=new Actions(driver);
			actions.click(category).build().perform();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			actions.click(driver.findElement(By.xpath("//li[contains(text(),'"+user.get("CATEGORY")+"')]"))).build().perform();
			due.sendKeys(user.get("DUE"));
			Select select=new Select(Assignee);
			
			select.selectByVisibleText(user.get("ASSIGNEE"));
			save.click();
		
		}
		return data;
	}
}
