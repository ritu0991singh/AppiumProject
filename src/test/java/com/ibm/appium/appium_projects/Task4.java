package com.ibm.appium.appium_projects;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
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

public class Task4 {

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
		 Thread.sleep(7000);
		 
		 //scrolling to To do list
		 driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(textStartsWith(\"To-Do List\"))"));
		 Thread.sleep(5000);
		 driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='To-Do List']")).click();
		  Thread.sleep(4000); 
		  wait.until(ExpectedConditions.visibilityOf(  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"taskInput\")"))));
		  Thread.sleep(4000);
		
		 //Adding tasks
		  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"taskInput\")")).sendKeys("Add tasks to list");
		  driver.findElement(MobileBy.AndroidUIAutomator("text(\"Add Task\")")).click();
		  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"taskInput\")")).sendKeys("Add tasks to list - 2");
		  driver.findElement(MobileBy.AndroidUIAutomator("text(\"Add Task\")")).click();
		  Thread.sleep(3000); 
		  
		  //clicking on each task
		  List<MobileElement> created_Tasks = driver.findElementsByClassName("android.view.View");
		  for (MobileElement task : created_Tasks) {
			  task.click();
			 }

		  Thread.sleep(5000); 
		  // clicking on clear list
		  driver.findElement(By.xpath("//android.webkit.WebView/android.view.View/android.view.View/android.view.View")).click();

		  Thread.sleep(3000); 
		  //Assertion
		  List<MobileElement> tasklist = driver.findElements(MobileBy.AndroidUIAutomator("resourceId(\"tasksList\")"));
		  System.out.println("List size is" +tasklist.size());
		  Assert.assertEquals(tasklist.size(), 0);
	
	}

	@AfterClass

	public void afterClass() {

		driver.quit();

	}

}
