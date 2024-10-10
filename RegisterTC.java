package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.Base.Base;
import com.tutorialsninja.qa.utilities.Utilities;

 public class RegisterTC extends Base{	 
		
	 public RegisterTC()
		{
			super();//Base class constructor method is called here
		}
		
	 WebDriver driver;
	
	 @BeforeMethod
	 public void setup()	 {
			 
	driver= intialiseBrowserAndOpenApplicationURL("browserName");
    driver.findElement(By.xpath("//span[text()='My Account']")).click();
    driver.findElement(By.linkText("Register")).click();	 
 
	 }
	
	@AfterMethod
	public void tearDown() {
	
	driver.quit();
	
	}
	
	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {

	    driver.findElement(By.id("input-firstname")).sendKeys("Archana");
	    driver.findElement(By.id("input-lastname")).sendKeys("Krishna1");
	    driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
	    driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
	    driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
	    driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
	    driver.findElement(By.name("agree")).click();
	    driver.findElement(By.xpath("//input[@value='Continue']")).click();

	    String actualSuccessHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
	    Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account creation failed");
	}

	@Test(priority = 2)
	public void verifyRegisteringAccountByProvidingAllFields() {

	driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
	driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
	driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
	driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
	driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
	driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
	driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).click();
	driver.findElement(By.name("agree")).click();
	driver.findElement(By.xpath("//input[@value='Continue']")).click();

String actualSuccessHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"), "Account creation failed");


	}

	@Test(priority = 3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {
	
	driver.findElement(By.id("input-firstname")).sendKeys("Archana");
	driver.findElement(By.id("input-lastname")).sendKeys("Krishna3");
	driver.findElement(By.id("input-email")).sendKeys("aln.sap.st@gmail.com");
	driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
	driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
	driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
	driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).click();
	driver.findElement(By.name("agree")).click();
	driver.findElement(By.xpath("//input[@value='Continue']")).click();

String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-danger')]")).getText();
Assert.assertTrue(actualWarningMessage.contains(dataProp.getProperty("duplicateEmailWarning")), "Expected warning message not found");

	}
	
@Test(priority = 4)
public void verifyRegisteringAccountWithoutFillingAnyDetails() 
{
    driver.findElement(By.xpath("//input[@value='Continue']")).click();
String actualPrivacyPolicyWarning = driver.findElement(By.xpath("//div[contains(@class, 'alert-danger')]")).getText();
Assert.assertTrue(actualPrivacyPolicyWarning.contains(dataProp.getProperty("privacyPolicyWarning")), "Privacy Policy warning not found");

String actualFirstNameWarning = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div[@class='text-danger']")).getText();
Assert.assertEquals(actualFirstNameWarning, dataProp.getProperty("firstNameWarning"), "First Name warning not found");

String actualLastNameWarning = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div[@class='text-danger']")).getText();
Assert.assertEquals(actualLastNameWarning, dataProp.getProperty("lastNameWarning"), "Last Name warning not found");

String actualEmailWarning = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div[@class='text-danger']")).getText();
Assert.assertEquals(actualEmailWarning, dataProp.getProperty("emailWarning"), "Email warning not found");

String actualTelephoneWarning = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div[@class='text-danger']")).getText();
Assert.assertEquals(actualTelephoneWarning, dataProp.getProperty("telephoneWarning"), "Telephone warning not found");

String actualPasswordWarning = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div[@class='text-danger']")).getText();
Assert.assertEquals(actualPasswordWarning, dataProp.getProperty("passwordWarning"), "Password warning not found");

driver.findElement(By.xpath("//input[@value='Continue']")).click();

}
}
