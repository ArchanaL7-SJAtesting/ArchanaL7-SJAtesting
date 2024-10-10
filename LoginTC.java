 package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.Base.Base;
import com.tutorialsninja.qa.utilities.Utilities;
	
	public class LoginTC extends Base {
		
		public LoginTC()
		{
			super();  //Base class constructor method is called here
		}
		
		 public WebDriver driver;
		
		 @BeforeMethod
		
		 public void setup()	 {
			 
	driver = intialiseBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
	driver.findElement(By.xpath("//span[text()='My Account']")).click();
    driver.findElement(By.linkText("Login")).click();	 
 
		 } 
		
		@AfterMethod
		
		public void tearDown() {
			
		driver.quit();
		
		}

@Test(priority=1,dataProvider="validCredentialsSupplier")
public void verifyLoginWithValidCredentials(String email,String password) 

{
    
	driver.findElement(By.id("input-email")).sendKeys(email);
	driver.findElement(By.id("input-password")).sendKeys(password);
	driver.findElement(By.xpath("//input[@value='Login']")).click();

 Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());

	    }
		
   @DataProvider(name="validCredentialsSupplier")		

   public Object[][] supplyTestData() {
	   
	Object[][] data = Utilities.getTestDataFromExcel("Login");
    
	return data;
				    }
  		
	@Test (priority=2)
	public void verifyLoginWithInValidCredentials() 
	{
	    driver.findElement(By.id("input-email")).sendKeys("invalidhsaafh@gmail.com");
	    driver.findElement(By.id("input-password")).sendKeys("iv874562456");
	    driver.findElement(By.xpath("//input[@value='Login']")).click();

String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message not found!");
    
	   	       }
	
	@Test (priority=3)
	public void verifyLoginWithInValidEmailAndValidPassword() {
		
	driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
	driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
	driver.findElement(By.xpath("//input[@value='Login']")).click();

String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message not found!");

		
		}
	
		@Test (priority=4)
		public void verifyLoginWithValidAndInvalidPassword() {
		
driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
driver.findElement(By.xpath("//input[@value='Login']")).click();

    String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]")).getText();
    String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
    Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected Warning");
}
		
	@Test (priority=5)
		public void verifyLoginWithoutProvidingCredentials() {
		    
		    driver.findElement(By.id("input-email")).sendKeys("");
		    driver.findElement(By.id("input-password")).sendKeys("");
		    driver.findElement(By.xpath("//input[@value='Login']")).click();

		    String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-danger')]")).getText();
		    String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		    
		    Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected Warning Message not found");
				}

		
		  } 
		
    
