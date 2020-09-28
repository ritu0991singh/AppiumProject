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
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;



public class Task3 {

	  // Instantiate Appium Driver
    AppiumDriver<MobileElement> driver = null;
    
    @BeforeClass
    public void calc() throws InterruptedException, IOException {
        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "ONEPLUS A6000");
        caps.setCapability("platformName", "Android");
        caps.setCapability("appPackage", "com.google.android.keep");
        caps.setCapability("appActivity", ".activities.BrowseActivity");
        caps.setCapability("noReset", true);

      
        try {
            // Initialize driver
            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
            System.out.println("Google keep is open");
        	Thread.sleep(4000);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
    }

@Test
public void addTask() throws InterruptedException {

	driver.findElementById("com.google.android.keep:id/new_note_button").click();
	 WebDriverWait wait = new WebDriverWait(driver, 30);
	 
	 // Adding notes
	 wait.until(ExpectedConditions.visibilityOf(driver.findElementById("com.google.android.keep:id/editable_title")));
	 driver.findElementById("com.google.android.keep:id/editable_title").sendKeys("This is title for reminder");
	 wait.until(ExpectedConditions.visibilityOf(driver.findElementById("com.google.android.keep:id/edit_note_text")));
	 driver.findElementById("com.google.android.keep:id/edit_note_text").sendKeys("this is description for reminder");
	 wait.until(ExpectedConditions.visibilityOf(driver.findElementById("com.google.android.keep:id/menu_switch_to_list_view")));
	 driver.findElementById("com.google.android.keep:id/menu_switch_to_list_view").click();
	 Thread.sleep(4000);
	 driver.findElementById("com.google.android.keep:id/save").click();
	 Thread.sleep(4000);
	 
	 //adding reminder
	String text = driver.findElementById("com.google.android.keep:id/reminder_chip").getText();
	driver.findElementByClassName("android.widget.ImageButton").click();
	Thread.sleep(4000);
	
	String title = driver.findElementById("com.google.android.keep:id/index_note_title").getText();
	String desctext = driver.findElementById("com.google.android.keep:id/index_note_text_description").getText();
	
	
	Assert.assertTrue("reminder text displayed is not correct andi is:" +text, text.equalsIgnoreCase("Today, 17:00"));
	Assert.assertTrue("The tile is not correct and is" +title, title.equalsIgnoreCase("This is title for reminder"));
	Assert.assertTrue("Description is not correct and is" +desctext, desctext.equalsIgnoreCase("this is description for reminder"));
	
	  


}

@AfterClass

public void afterClass() {

    driver.quit();

}


}
