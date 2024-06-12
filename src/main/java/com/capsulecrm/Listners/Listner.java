package com.capsulecrm.Listners;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.capsulecrm.Report.ExtentReport;
import com.relevantcodes.extentreports.LogStatus;



public class Listner implements ITestListener
{
	//ItestListner to generate TestNG logs
	@Override
	public void onFinish(ITestContext arg0) 
	{
		
		ExtentReport.report.endTest(ExtentReport.logger);
		
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		ExtentReport.logger= ExtentReport.report.startTest(arg0.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		ExtentReport.logger= ExtentReport.report.startTest(arg0.getName());
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		com.capsulecrm.Report.LogStatus.fail("Test case failed");
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		com.capsulecrm.Report.LogStatus.fail("Test case failed");
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		
		com.capsulecrm.Report.LogStatus.pass("Test Case method "+arg0.getMethod().getMethodName()+" started");
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		com.capsulecrm.Report.LogStatus.pass("Test Case successfully Passed");
	}

}
