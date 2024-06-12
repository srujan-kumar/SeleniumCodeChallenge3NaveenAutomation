package com.capsulecrm.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TableExtract {
	//Extracts data from table, and returns in a list
	public static List<String> getData(By xpath,WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<WebElement> tds=driver.findElements(xpath);
		List<String> fetchedData=new ArrayList<>();
		for(int i=0;i<tds.size();i++)
		{
			fetchedData.add(tds.get(i).getAttribute("innerText").trim());
			//System.out.println(i+"+"+Arrays.toString((tds.get(i).getAttribute("innerText").trim().split("\n"))));
		}
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		return fetchedData;
		
	}
}
