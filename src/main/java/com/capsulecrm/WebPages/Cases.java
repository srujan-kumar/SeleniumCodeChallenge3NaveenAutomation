package com.capsulecrm.WebPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.Utils.ExplicitWait;

public class Cases {

	@FindBy(xpath="//a[contains(text(),'Case')]")
	private WebElement addCase;
	public Logger log=Logger.getLogger(Cases.class);
	WebDriver driver;
	public Cases(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//Navigate to Add new case page
	public NewCase clickAddCase()
	{
		ExplicitWait.waitTillClickable(addCase, driver);
		addCase.click();
		return new NewCase(driver);
	}
}
