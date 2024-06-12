package com.capsulecrm.test;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.capsulecrm.Browser.WebDrivers;
import com.capsulecrm.WebPages.AccountSettings;
import com.capsulecrm.WebPages.Dashboard;
import com.capsulecrm.WebPages.LoginPage;

public class Test2 {

	WebDriver driver;
	Dashboard dashboard;
	SoftAssert sAssert;
	public Logger log=Logger.getLogger(Test2.class);
	@BeforeClass
	public void initialize()
	{
		driver=new WebDrivers().InitializeWebDrivers();
		sAssert=new SoftAssert();
	}
	@Test
	public void accountTest()
	{
		
		dashboard=new LoginPage(driver).login();
		AccountSettings accSettingObj=dashboard.AccountSetting();
		sAssert.assertEquals(accSettingObj.getTitle(), "Account Settings");
		List<WebElement> menu=accSettingObj.getMenu();
		for(int i=0;i<menu.size();i++)
		{
			log.debug("navigating to "+menu.get(i).getText()+" page");
			accSettingObj.clickMenu(menu.get(i));
			
			String menuString=menu.get(i).getText();
			log.debug("***************Validating titles*****************");
			Assert.assertEquals(accSettingObj.getPageTitle().contains(menuString),true);
			log.debug("Expected "+menuString+" Actual: "+accSettingObj.getPageTitle());
			List<Map<String, String>> data=accSettingObj.validateTask();
			/*for(Map.Entry<String, String> ent: data.get(0).entrySet())
			{
				System.out.println(ent.getKey()+" "+ent.getValue());
			}*/
			if(data.size()>0)
			{
				log.debug("***************Validating newly added data of page "+menuString+"*****************");
				Assert.assertEquals(data.get(0).equals(data.get(1)), true);
				log.debug("Validation success");
			}
			
			
		}
	}
	@AfterClass
	public void clean()
	{
		driver.close();
		driver.quit();
	}
	
}
