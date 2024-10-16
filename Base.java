package com.tutorialsninja.qa.Base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsninja.qa.utilities.Utilities;

	public class Base {

		 public static WebDriver driver; // Made static for single instance

		//Made public so can be accessed by other class or child class also
public Properties prop;  
    
public Properties dataProp;
    
  public Base() ///this is Base consrtuctor same name as its class name Base. 
  {
  
	  prop = new Properties();
  File propFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");

        try {
            FileInputStream fis = new FileInputStream(propFile);
            prop.load(fis);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    

  dataProp = new Properties();

  File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\TutorialsNinjaTestData.xlsx");

  try {
      FileInputStream dataFis = new FileInputStream(dataPropFile);
      dataProp.load(dataFis);
  } catch (Throwable e) {
      e.printStackTrace();
  }

  }
public WebDriver intialiseBrowserAndOpenApplicationURL(String browserName)
{
	
    if (browserName.equalsIgnoreCase("chrome"))
 {
     driver = new ChromeDriver();
} 
else if (browserName.equalsIgnoreCase("firefox")) 
{
  driver=new FirefoxDriver(); 

} 
else if (browserName.equalsIgnoreCase("edge")) 
 {
	driver = new EdgeDriver();
}
else if (browserName.equalsIgnoreCase("safari"))
    {
        driver=new SafariDriver(); 

    }
    
    driver = new EdgeDriver();

	driver.manage().window().maximize();
   
	
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));

driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
    
    driver.get(prop.getProperty("url"));


	    return driver;		
		
	}


}
	