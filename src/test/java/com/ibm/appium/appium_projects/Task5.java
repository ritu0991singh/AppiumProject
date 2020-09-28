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

public class Task5 {

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
		Thread.sleep(5000);


		 WebDriverWait wait = new WebDriverWait(driver, 30);
		

		  driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(text(\"Login Form\"))")).click();
		  wait.until(ExpectedConditions.visibilityOf(driver.findElementByClassName("android.widget.EditText")));
			
		  //with valid credentials
		  Thread.sleep(5000);
		  driver.findElementsByClassName("android.widget.EditText").get(0).sendKeys("admin");
		  driver.findElementsByClassName("android.widget.EditText").get(1).sendKeys("password");
		  wait.until(ExpectedConditions.visibilityOf(driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Log in\")"))));
		  driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Log in\")")).click();
		  wait.until(ExpectedConditions.visibilityOf(driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"action-confirmation\")"))));
			
		  //Assertion for valid credentials
		  
		 String correctCredentailMsgActual= driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"action-confirmation\")")).getText();
		 System.out.println("successful msg is: " +correctCredentailMsgActual);
		 String correctCredentailMsgExpected = "Welcome Back, admin";
		 Assert.assertTrue("The Message for correct credentials is not as expected and is: "+correctCredentailMsgActual, correctCredentailMsgActual.equalsIgnoreCase(correctCredentailMsgExpected));
	
		 //Entering invalid credentials
		 
		 driver.findElementByClassName("android.widget.Button").click();
		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Login Form\")"))));
		 Thread.sleep(3000);
		 driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Login Form']")).click();
		 wait.until(ExpectedConditions.visibilityOf(driver.findElementByClassName("android.widget.EditText")));
		 Thread.sleep(4000);
		driver.findElementsByClassName("android.widget.EditText").get(0).sendKeys("admin");
		driver.findElementsByClassName("android.widget.EditText").get(1).sendKeys("passwordwrong");
		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Log in\")"))));
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Log in\")")).click();
		Thread.sleep(4000);
		
		//Assertion for invalid credentials
		
		String IncorrectCredentailMsgActual= driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"action-confirmation\")")).getText();
		System.out.println("Incorrect credentials msg is: " +IncorrectCredentailMsgActual);
		String IncorrectCredentailMsgExpected="Invalid Credentials";
		Assert.assertTrue("The Message for Incorrect credentials is not as expected and is: "+IncorrectCredentailMsgActual, IncorrectCredentailMsgActual.equalsIgnoreCase(IncorrectCredentailMsgExpected));
				
			
		 
	}

	@AfterClass

	public void afterClass() {

		driver.quit();

	}

}
