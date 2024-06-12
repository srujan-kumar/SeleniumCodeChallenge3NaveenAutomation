package com.capsulecrm.Browser;

import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class DriverConfigs {
	
	//Configures chrome to run in headless mode
		public void headless(ChromeOptions options)
		{
			options.addArguments("window-size=1400,800");
			options.addArguments("headless");
		}
		//Configures FireFox to run in headless mode
		public void headless(FirefoxOptions options)
		{
			FirefoxBinary firefoxBinary=new FirefoxBinary();
			firefoxBinary.addCommandLineOptions("--headless");
			options.setBinary(firefoxBinary);
		}
		
		//disables images in chrome browser
		public void disableImg(ChromeOptions options)
		{
			HashMap<String, Object> images=new HashMap<String, Object>();
			images.put("images", 2);
			HashMap<String, Object> pref=new HashMap<String, Object>();
			pref.put("profile.default_content_setting_values", images);
			options.setExperimentalOption("prefs", pref);
		}
		//disables images in Firefox browser
		public void disableImg(FirefoxOptions options)
		{
			FirefoxProfile profile=new FirefoxProfile();
			profile.setPreference("permissions.default.image", 2);
			options.setProfile(profile);
			options.setCapability(FirefoxDriver.PROFILE, profile);
		}

}
