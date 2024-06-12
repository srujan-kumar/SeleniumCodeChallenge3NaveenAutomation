package com.capsulecrm.WebPages;

import java.util.HashMap;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.Utils.DynamicXpath;
import com.capsulecrm.Utils.ExplicitWait;


public class PeopleAndOrg {
	
	@FindBy(xpath="//a[@href='/party/person/new']")
	private WebElement addPerson;
	
	@FindBy(xpath="//a[@aria-label='Projects']")
	private WebElement cases;
	
	String personsxpath="//div[@class='list-results-cell-summary']/following::a[contains(text(),'%replacable%')]/ancestor::tr";
	
	WebDriver driver;
	public PeopleAndOrg(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public NewPerson clickAddPerson()
	{
		ExplicitWait.waitTillClickable(addPerson, driver);
		addPerson.click();
		return new NewPerson(driver);
	}
	//Fetches added person data from the table and return in form of string
	public Map<String, String> AddedPersonData(Map<String, String> personData)
	{
		Map<String, String> fetchData=new HashMap<>();
		By xpath=DynamicXpath.get(personsxpath, personData.get("FIRST NAME")+" "+personData.get("LAST NAME"));
		ExplicitWait.waitTillPresent(xpath, driver);
		String[] rowData=driver.findElement(xpath).getAttribute("innerText").trim().split(" ");
		/*for(int i=0;i<rowData.length;i++)
		{
			System.out.println(i+"="+rowData[i]);
		}*/
		fetchData.put("FIRST NAME", rowData[0]);
		fetchData.put("LAST NAME", rowData[1]);
		fetchData.put("JOB TITLE", rowData[1]);
//		fetchData.put("ORGANISATION", rowData[3]+" "+rowData[4]);
		fetchData.put("EMAIL ADDRESS", rowData[4]);
		fetchData.put("PHONE NUMBER", rowData[5]);
		//fetchData.put("TAGS", rowData[6]);
		return fetchData;
	}
	
	public Cases ClickCase()
	{
		ExplicitWait.waitTillClickable(cases, driver);
		cases.click();
		return new Cases(driver);
	}

}
