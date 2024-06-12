package com.capsulecrm.WebPages;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.Utils.ReadXlsx;

public class Users {

	@FindBy(xpath="//input[@id='register:firstnameDecorate:firstName']")
	private WebElement firstName;
	
	@FindBy(xpath="//input[@id='register:lastNameDecorate:lastName']")
	private WebElement lastName;
	
	@FindBy(xpath="//input[@id='register:emailDecorate:email']")
	private WebElement email;
	
	@FindBy(xpath="//input[@id='register:save']")
	private WebElement inviteUser;
	
	@FindBy(xpath="//input[@id='register:usernameDecorate:username']")
	private WebElement userName;
	public Logger log=Logger.getLogger(Users.class);
	private WebDriver driver;
	public Users(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	//Adds new user and returns entered data in form of map 
	public List<Map<String, String>> add()
	{
		List<Map<String, String>> data=ReadXlsx.getData("Users");
		for(int i=0;i<data.size();i++)
		{
			log.debug("Adding new User");;
			Map<String, String> user=data.get(i);
			new AccountSettings(driver).getAddNew().click();
			firstName.sendKeys(user.get("FIRST NAME"));
			lastName.sendKeys(user.get("LAST NAME"));
			email.sendKeys(user.get("EMAIL ADDRESS"));
			userName.sendKeys(user.get("USER NAME"));
			inviteUser.click();
			
		}
		return data;
	}
	
}
