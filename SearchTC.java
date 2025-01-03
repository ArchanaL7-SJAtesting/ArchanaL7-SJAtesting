package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.Base.Base;
public class SearchTC extends Base {
	
	public SearchTC()
	{
		super();//Base class constructor method is called here
	}
    WebDriver driver;

    @BeforeMethod
    public void setup() 
    {
      
    driver = intialiseBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
    }
    
    @AfterMethod
	public void tearDown() {
	
	driver.quit();
	
	}
	

    @Test(priority = 1)
    public void verifySearchWithValidProduct() {
       
driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));
driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
 
    }
@Test(priority=2)
public void verifySearchWithInvalidProduct() {
	    
driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidProduct"));
driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
   
    String actualSearchMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
Assert.assertEquals(actualSearchMessage, "There is no product that matches the search criteria.");
    }
	
	@Test(priority=3)
	public void verifySearchWithoutAnyProduct()
	{
driver.findElement(By.name("search")).sendKeys("");
driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
   
String actualSearchMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
Assert.assertEquals(actualSearchMessage, dataProp.get("noProductTextInSearchResults"));
	
	}
	}