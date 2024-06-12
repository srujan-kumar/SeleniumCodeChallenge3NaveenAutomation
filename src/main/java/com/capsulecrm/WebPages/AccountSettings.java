package com.capsulecrm.WebPages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.ReadProperty.ReadPropertyFile;
import com.capsulecrm.Utils.DynamicXpath;
import com.capsulecrm.Utils.ExplicitWait;
import com.capsulecrm.Utils.TableExtract;

public class AccountSettings {
	public Logger log=Logger.getLogger(AccountSettings.class);
	//Page Elements
	@FindBy(xpath="//*[contains(text(),'Account Settings')]//parent::div//child::ul//child::li")
	private List<WebElement> menu;
	
	@FindBy(xpath="//span[@class='settings-content-menu-title']")
	private WebElement title;
	
	@FindBy(xpath="//*[contains(@class,'page') and contains(@class,'header') and not(contains(@class,'title'))]")
	private WebElement pageTitle;
	
	@FindBy(xpath="//input[@id='appearance:uploadDecorate:logoImage']")
	private List<WebElement> AppearencChoosefile;
	
	@FindBy(xpath="//*[contains(text(),'Add new')]")
	private List<WebElement> addNew;
	
	@FindBy(xpath="//a[contains(text(),'Configure')]")
	private List<WebElement> configureButton;
	
	@FindBy(xpath="//a[contains(text(),'Save')]")
	private WebElement save;
	
	
	//Dynamic Xpaths
	String DXpathUser="//*[contains(text(),'%replacable%')]/ancestor::tr/child::td";
	String opportunityXpath="//*[contains(.,'%replacable%')]/ancestor::tr/child::td";
	
	private WebDriver driver;
	public AccountSettings(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	public WebElement getAddNew()
	{
		return this.addNew.get(0);
	}
	public String getTitle()
	{
		return title.getText();
	}
	
	public String getPageTitle()
	{
		return pageTitle.getText();
	}
	public List<WebElement> getMenu()
	{
		return this.menu;
	}
	//Clicks on Menu based on Menu element provided
	public String[] clickMenu(WebElement menu)
	{
		
		String a=menu.getText();
		log.debug("Clicked on Menu "+a);
		ExplicitWait.waitTillClickable(menu, driver);
		menu.click();
		new AccountSettings(driver);
		ExplicitWait.waitTillvisible(pageTitle, driver);
		String[] menuData= {a,pageTitle.getText()};
		return menuData;
	}
	//This method checks for choose file button and Add New button, If such button is available it performs the task f addition 
	//new data , data is fetched from Excel sheet, it returns List of Map, one map contains original data and another contans updated data
	
	public List<Map<String, String>> validateTask()
	{
		List<Map<String, String>> objs=new ArrayList<>();
		if(AppearencChoosefile.size()>0)
		{
			log.debug("Choose button available to upload file, FIle will be uploaded");
			AppearencChoosefile.get(0).sendKeys(System.getProperty("user.dir")+ReadPropertyFile.get("logo"));
			save.click();
		}
		if(addNew.size()>0)
		{
			
			String callClass=pageTitle.getText();
			log.debug("Add new button is available in menu "+callClass);
			List<Map<String, String>> data;
			if(callClass.equalsIgnoreCase("Users"))
			{
				data=new Users(driver).add();
				for(int i=0;i<data.size();i++)
				{
					Map<String, String> user=data.get(i);
					By xpath=DynamicXpath.get(DXpathUser, user.get("FIRST NAME")+" "+user.get("LAST NAME"));
					List<String> fetchedData=TableExtract.getData(xpath, driver);
					Map<String, String> userData=new HashMap<>();
					userData.put("FIRST NAME", fetchedData.get(0).split(" ")[0]);
					userData.put("LAST NAME", fetchedData.get(0).split(" ")[1]);
					userData.put("EMAIL ADDRESS", fetchedData.get(5));
					userData.put("USER NAME", fetchedData.get(1));
					/*for(int j=0;i<fetchedData.size();j++)
					{
						System.out.println(j+" * "+fetchedData.get(j));
					}*/
					objs.add(userData);
					objs.add(user);
				}
				return objs;
				
			}
			else if(callClass.equalsIgnoreCase("Opportunities"))
			{
				data=new Opportunities(driver).add();
				for(int i=0;i<data.size();i++)
				{
					Map<String, String> user=data.get(i);
					By xpath=DynamicXpath.get(opportunityXpath, user.get("NAME"));//user.get("NAME")
					List<String> fetchedData=TableExtract.getData(xpath, driver);
					Map<String, String> userData=new HashMap<>();
					
					userData.put("NAME", fetchedData.get(0).split("\n")[0]);
					userData.put("DESCRIPTION", fetchedData.get(0).split("\n")[1]);
					userData.put("PROBABILITY", fetchedData.get(1).replaceAll("%", ""));
					userData.put("DAYS", fetchedData.get(2));
					/*for(int j=0;i<fetchedData.size();j++)
					{
						System.out.println(j+" * "+fetchedData.get(j));
					}*/
					objs.add(userData);
					objs.add(user);
				}
				return objs;
				
			}
			else if(callClass.equalsIgnoreCase("Tracks"))
			{
				data=new Tracks(driver).add();
				for(int i=0;i<data.size();i++)
				{
					Map<String, String> user=data.get(i);
					By xpath=DynamicXpath.get(DXpathUser, user.get("NAME"));//user.get("NAME")
					List<String> fetchedData=TableExtract.getData(xpath, driver);
					Map<String, String> userData=new HashMap<>();
					
					userData.put("NAME", fetchedData.get(0));
					userData.put("TAG", fetchedData.get(1));
					userData.put("DESCRIPTION", user.get("DESCRIPTION"));
					userData.put("CATEGORY", user.get("CATEGORY"));
					userData.put("DUE", user.get("DUE"));
					userData.put("ASSIGNEE", user.get("ASSIGNEE"));
					/*for(int j=0;i<fetchedData.size();j++)
					{
						System.out.println(j+" * "+fetchedData.get(j));
					}*/
					objs.add(userData);
					objs.add(user);
				}
				return objs;
				
			}
			else if(callClass.equalsIgnoreCase("Task Categories"))
			{
				data=new TaskCategory(driver).add();
				for(int i=0;i<data.size();i++)
				{
					Map<String, String> user=data.get(i);
					By xpath=DynamicXpath.get(DXpathUser, user.get("NAME"));//user.get("NAME")
					List<String> fetchedData=TableExtract.getData(xpath, driver);
					Map<String, String> userData=new HashMap<>();
					
					userData.put("NAME", fetchedData.get(1));
					
					/*for(int j=0;i<fetchedData.size();j++)
					{
						System.out.println(j+" * "+fetchedData.get(j));
					}*/
					objs.add(userData);
					objs.add(user);
				}
				return objs;
			}
			else if(callClass.equalsIgnoreCase("Tags and DataTags"))
			{
				data=new Tags(driver).add();
				for(int i=0;i<data.size();i++)
				{
					Map<String, String> user=data.get(i);
					By xpath=DynamicXpath.get(DXpathUser, user.get("NAME"));//user.get("NAME")
					List<String> fetchedData=TableExtract.getData(xpath, driver);
					Map<String, String> userData=new HashMap<>();
					
					userData.put("NAME", fetchedData.get(0));
									
					objs.add(userData);
					objs.add(user);
				}
				return objs;
			}
			
			
		}
		else if (configureButton.size()>1) {
			Map<String, String> expected=new HashMap<>();
			expected.put("NUMBER", "10");
			Map<String, String> actual=new HashMap<>();
			actual.put("NUMBER", configureButton.size()+"");
			log.debug("****************TOTAL CONFIGURE BUTTON AVAILABLE: "+configureButton.size()+"************");
			objs.add(expected);
			objs.add(actual);
		}
		return objs;
	}

}
