package com.ibm.appium.appium_projects;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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



public class Task1 {

	  // Instantiate Appium Driver
    AppiumDriver<MobileElement> driver = null;
    
    @BeforeClass
    public void calc() throws InterruptedException, IOException {
        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "ONEPLUS A6000");
        caps.setCapability("platformName", "Android");
        caps.setCapability("appPackage", "com.google.android.apps.tasks");
        caps.setCapability("appActivity", ".ui.TaskListsActivity");
        caps.setCapability("noReset", true);

      
        try {
            // Initialize driver
            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
            System.out.println("Google task is open");
            Thread.sleep(4000);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
    }

@Test
public void addTask() throws InterruptedException {
	
	driver.findElementById("com.google.android.apps.tasks:id/tasks_fab").click();
	 Thread.sleep(4000);
	WebDriverWait wait = new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.visibilityOf(driver.findElementById("add_task_title")));
	driver.findElementById("add_task_title").sendKeys("Complete Activity with Google Tasks");
	//Thread.sleep(2000);
	driver.findElement(MobileBy.AndroidUIAutomator("text(\"Save\")")).click();
	Thread.sleep(4000);
	
	wait.until(ExpectedConditions.visibilityOf(driver.findElementById("com.google.android.apps.tasks:id/tasks_fab")));
	driver.findElementById("com.google.android.apps.tasks:id/tasks_fab").click();
	Thread.sleep(4000);
	wait.until(ExpectedConditions.visibilityOf(driver.findElementById("add_task_title")));
	driver.findElementById("add_task_title").sendKeys("Complete Activity with Google Keep");
	Thread.sleep(4000);
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(MobileBy.AndroidUIAutomator("text(\"Save\")"))));
	driver.findElement(MobileBy.AndroidUIAutomator("text(\"Save\")")).click();
	
	Thread.sleep(4000);
	wait.until(ExpectedConditions.visibilityOf(driver.findElementById("com.google.android.apps.tasks:id/tasks_fab")));
	driver.findElementById("com.google.android.apps.tasks:id/tasks_fab").click();
	Thread.sleep(4000);
	wait.until(ExpectedConditions.visibilityOf(driver.findElementById("add_task_title")));
	driver.findElementById("add_task_title").sendKeys("Complete the second Activity with Google Keep");

	//Thread.sleep(4000);
	driver.findElement(MobileBy.AndroidUIAutomator("text(\"Save\")")).click();
	Thread.sleep(4000);
	String first="Complete Activity with Google Tasks";
	String second="Complete Activity with Google Keep";
	String third="Complete the second Activity with Google Keep";
	
	 List<MobileElement> textItems = driver.findElementsById("com.google.android.apps.tasks:id/task_name");

     for(MobileElement textItem : textItems) {

         System.out.println(textItem.getText());

     }
	
	
	
	Assert.assertTrue("assertion not completed for 1st task", first.equalsIgnoreCase(driver.findElement(MobileBy.AndroidUIAutomator("text(\"Complete Activity with Google Tasks\")")).getText()));
	Assert.assertTrue("assertion not completed with google keep", second.equalsIgnoreCase(driver.findElement(MobileBy.AndroidUIAutomator("text(\"Complete Activity with Google Keep\")")).getText()));
	Assert.assertTrue("asserion not completed for third task ", third.equalsIgnoreCase(driver.findElement(MobileBy.AndroidUIAutomator("text(\"Complete the second Activity with Google Keep\")")).getText()));


}

@AfterClass

public void afterClass() {

    driver.quit();

}


}
