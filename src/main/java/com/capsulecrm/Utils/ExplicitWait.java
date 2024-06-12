package com.capsulecrm.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWait {
	//Provides explict wait for WebElements
	public static void waitTillClickable(WebElement element, WebDriver driver)
	{
		WebDriverWait wait= new WebDriverWait(driver, 10000);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void waitTillPresent(By locator, WebDriver driver)
	{
		WebDriverWait wait= new WebDriverWait(driver, 10000);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public static void waitTillvisible(WebElement element, WebDriver driver)
	{
		WebDriverWait wait= new WebDriverWait(driver, 10000);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

}
