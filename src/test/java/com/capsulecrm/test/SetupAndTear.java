package com.capsulecrm.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.capsulecrm.Browser.WebDrivers;
import com.capsulecrm.Report.ExtentReport;
import com.capsulecrm.WebPages.LoginPage;



public class SetupAndTear {
	
	@BeforeSuite
	public void setup()
	{
		 ExtentReport.initialize();
		 ExtentReport.logger= ExtentReport.report.startTest("Selenium coding challenge 3");
			
	}
	
	@AfterSuite
	public void clean()
	{
		ExtentReport.report.flush();
	}

}
