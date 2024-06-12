package com.capsulecrm.test;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.capsulecrm.Browser.WebDrivers;
import com.capsulecrm.Utils.ReadXlsx;
import com.capsulecrm.WebPages.AddedCase;
import com.capsulecrm.WebPages.AddedPerson;
import com.capsulecrm.WebPages.Cases;
import com.capsulecrm.WebPages.Dashboard;
import com.capsulecrm.WebPages.LoginPage;
import com.capsulecrm.WebPages.NewCase;
import com.capsulecrm.WebPages.NewPerson;
import com.capsulecrm.WebPages.PeopleAndOrg;

public class Test1 
{
	WebDriver driver;
	Dashboard dashboard;
	String Testcase="TESTCASE1";
	public Logger log=Logger.getLogger(Test1.class);
	@BeforeClass
	public void initialize()
	{
		driver=new WebDrivers().InitializeWebDrivers();
	}
	@Test(dataProvider="Persondata")
	public void addPersonAndCase(Object personData)
	{
		dashboard=new LoginPage(driver).login();
		PeopleAndOrg peopleAndOrg=dashboard.navigateToPeopleNorg();
		NewPerson Newperson= peopleAndOrg.clickAddPerson();
		Map<String, String> data=(Map<String, String>)personData;
		AddedPerson addedPerson=Newperson.fillAndSubmitPerson(data);
		peopleAndOrg=addedPerson.navigatePeopleAndOrg();
		Map<String, String> addedPersonData=peopleAndOrg.AddedPersonData(data);
		SoftAssert softAssert=new SoftAssert();
		log.debug("********Validating new person data********");
		softAssert.assertEquals(addedPersonData.get("FIRST NAME").contains(data.get("FIRST NAME")), true);
		softAssert.assertEquals(addedPersonData.get("LAST NAME").contains(data.get("LAST NAME")), true);
		softAssert.assertEquals(addedPersonData.get("JOB TITLE").contains(data.get("JOB TITLE")), true);
//		softAssert.assertEquals(addedPersonData.get("ORGANISATION").contains(data.get("ORGANISATION")), true);
//		softAssert.assertEquals(addedPersonData.get("TAGS").contains(data.get("TAGS")), true);
		softAssert.assertEquals(addedPersonData.get("EMAIL ADDRESS").contains(data.get("EMAIL ADDRESS")), true);
		softAssert.assertEquals(addedPersonData.get("PHONE NUMBER").contains(data.get("PHONE NUMBER")), true);
		softAssert.assertAll();
		log.debug("********new person data Validated********");
		Cases casePage=peopleAndOrg.ClickCase();
		NewCase newCase=casePage.clickAddCase();
		
		AddedCase addedCase=newCase.addCase(data);
		Map<String, String> fetchedCase=addedCase.validateCaseDetails();
		log.debug("********Validating new Case data********");
		softAssert.assertEquals(fetchedCase.get("CASE NAME"), data.get("CASE NAME"));
		softAssert.assertEquals(fetchedCase.get("NAME"), data.get("FIRST NAME")+" "+data.get("LAST NAME"));
		softAssert.assertEquals(fetchedCase.get("STATUS"), "Open");
		softAssert.assertAll();
		log.debug("********new case data Validated********");
	}
	
	@DataProvider
	public Object[] Persondata()
	{
		List<Map<String, String>> persons=ReadXlsx.getData(Testcase);
		Object[] data=new Object[persons.size()];
		for(int i=0;i<persons.size();i++ )
		{
			data[i]=persons.get(i);
		}
		return data;
	}
	@AfterClass
	public void clean()
	{
		driver.close();
		driver.quit();
	}
	
}
