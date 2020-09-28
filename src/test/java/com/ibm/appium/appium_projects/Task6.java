package com.ibm.appium.appium_projects;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class Task6 {

	// Instantiate Appium Driver
	AppiumDriver<MobileElement> driver = null;

	@BeforeClass
	public void calc() throws InterruptedException, IOException {
		// Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "ONEPLUS A6000");
		caps.setCapability("platformName", "Android");
		caps.setCapability("appPackage", "com.android.chrome");
		caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
		caps.setCapability("noReset", true);

		try {
			// Initialize driver
			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			System.out.println("Google chrome is open");
			Thread.sleep(4000);
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void addTask() throws InterruptedException {
		
		driver.get("https://www.training-support.net/selenium");
		
		 WebDriverWait wait = new WebDriverWait(driver, 30);
		 Thread.sleep(8000);
	 
		//scrolling to the element
	  driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(textStartsWith(\"Popups\"))"));
	  wait.until(ExpectedConditions.visibilityOf( driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Popups']"))));
	  driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Popups']")).click();
	  Thread.sleep(4000);
	  //Entering valid credentials
	  wait.until(ExpectedConditions.visibilityOf( driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Sign In\")"))));
	  driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Sign In\")")).click();
	  wait.until(ExpectedConditions.visibilityOf( driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"username\")"))));
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"username\")")).sendKeys("admin");
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"password\")")).sendKeys("password");
	  driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Log in\")")).click();
	  Thread.sleep(4000);
	  //Assertion
	  wait.until(ExpectedConditions.visibilityOf(driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"action-confirmation\")"))));
	  String ValidloginMessage = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"action-confirmation\")")).getText();
	  Assert.assertTrue("Valid login credential message is not correct and is: "+ValidloginMessage, ValidloginMessage.equalsIgnoreCase("Welcome Back, admin"));
	  
	  Thread.sleep(4000);
	  //Entering Invalid credentials
	  driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Sign In\")")).click();
	  wait.until(ExpectedConditions.visibilityOf( driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"username\")"))));
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"username\")")).clear();
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"username\")")).sendKeys("admin wrong");
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"password\")")).clear();
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"password\")")).sendKeys("password wrong");
	  driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Log in\")")).click();
	  Thread.sleep(4000);
	  //Assertion
	  wait.until(ExpectedConditions.visibilityOf(driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"action-confirmation\")"))));
	  String InvalidloginMessage = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"action-confirmation\")")).getText();
	  Assert.assertTrue("InValid login credential message is not correct and is: "+InvalidloginMessage, InvalidloginMessage.equalsIgnoreCase("Invalid Credentials"));
	 	 
	}

	@AfterClass

	public void afterClass() {

		driver.quit();

	}

}
