package com.capsulecrm.Browser;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.capsulecrm.Listners.EventHandler;
import com.capsulecrm.ReadProperty.ReadPropertyFile;
import com.capsulecrm.Report.LogStatus;


import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDrivers 
{
	private WebDriver driver;
	public static Logger log=Logger.getLogger(WebDriver.class);
	//Launches browser based on input provided in Readproperties.propery file
	public WebDriver InitializeWebDrivers()
	{
		String browser=ReadPropertyFile.get("Browser");
		String headless=ReadPropertyFile.get("HeadlessMode");
		String imageDisable=ReadPropertyFile.get("DisableImage");
		if(browser.toLowerCase().contains("chrome")||browser.toLowerCase().contains("google"))
		{
			WebDriverManager.chromedriver().setup();
			
			ChromeOptions options=new ChromeOptions();
			if(imageDisable.equalsIgnoreCase("yes"))
			{
				new DriverConfigs().disableImg(options);
			}
			if(headless.equalsIgnoreCase("yes"))
			{
				new DriverConfigs().headless(options);
			}
			//options.addArguments("--incognito");
			DesiredCapabilities capabilites=DesiredCapabilities.chrome();
			capabilites.setCapability(ChromeOptions.CAPABILITY, options);
			driver=new ChromeDriver(options);
			
			//LogStatus.pass("Chrome drive launched with headless mode = "+headless.toUpperCase()+", Image Disable mode = "+imageDisable.toUpperCase());
		}
		else if(browser.toLowerCase().contains("fire")||browser.toLowerCase().contains("ff"))
		{
			WebDriverManager.firefoxdriver().setup();
			
			FirefoxOptions FFoptions=new FirefoxOptions();
			if(imageDisable.equalsIgnoreCase("yes"))
			{
				new DriverConfigs().disableImg(FFoptions);
			}
			if(headless.equalsIgnoreCase("yes"))
			{
				new DriverConfigs().headless(FFoptions);
			}
			
			driver=new FirefoxDriver(FFoptions);
			
			
			LogStatus.pass("FF drive launched with headless mode = "+headless.toUpperCase()+", Image Disable mode = "+imageDisable.toUpperCase());
			
			
		}
		else if(browser.toLowerCase().contains("edge")||browser.toLowerCase().contains("microsoft"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else if(browser.toLowerCase().contains("ie")||browser.toLowerCase().contains("explore"))
		{
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		}
		else if(browser.toLowerCase().contains("opera"))
		{
			WebDriverManager.operadriver().setup();
			driver=new OperaDriver();
		}
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		EventHandlerInit();
		driver.manage().deleteAllCookies();
		driver.get(ReadPropertyFile.get("url"));
		log.debug("driver launched "+ browser+ "url: "+ReadPropertyFile.get("url"));
		return driver;
	}
	//Opens url
	public void openUrl(String url)
	{
		driver.get(url);
	}
	//Quits browser
	public void quit()
	{
		driver.quit();
	}
	
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	//initializes WebDriver EventListner
	public void EventHandlerInit()
	{
		EventFiringWebDriver eventHandle=new EventFiringWebDriver(driver);
		EventHandler handler=new EventHandler();
		eventHandle.register(handler);
		driver=eventHandle;
	}
	
	
}
