package chrome;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestChrome
{
	WebDriver driver;
	 @BeforeSuite
	  public void setUp() 
	 {
	 
//	          File file = new File("E:\\exefiles\\chromedriver.exe");
//	          System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
//	          driver = new ChromeDriver();
//	         
	         System.setProperty("webdriver.chrome.driver", "E:/exefiles/chromedriver.exe");
	         driver=new ChromeDriver();
	         
	         
	 }
	 
	 @Test
	 public void check()
	 {
		driver.get("http://www.google.com");
		
	 }
	 @AfterSuite
	 public void destroy()
	 {
		 driver.quit();
	 }

}
