package com.capsulecrm.WebPages;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.Utils.ExplicitWait;

public class AddedCase {

	@FindBy(xpath="//div[@class='entity-details-title']")
	private WebElement title;
	
	@FindBy(xpath="//div[@class='entity-details-party']//child::a")
	private WebElement name;
	
	@FindBy(xpath="//span[@class='kase-summary-status-open']")
	private WebElement status;
	public Logger log=Logger.getLogger(AddedCase.class);
	WebDriver driver;
	public AddedCase(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//fetch the data of added case
	public Map<String, String> validateCaseDetails()
	{
		log.info("Adding new Case");
		Map<String, String> caseData=new HashMap<>();
		ExplicitWait.waitTillClickable(title, driver);
		caseData.put("CASE NAME", title.getText());
		caseData.put("NAME", name.getText());
		caseData.put("STATUS", status.getText());
		return caseData;
	}
	
	
	
}
