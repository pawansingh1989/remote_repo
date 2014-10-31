package InternetExplorer;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestIE 
{
	WebDriver driver;
	 @BeforeSuite
	  public void setUp() 
	 {
	 
	          File file = new File("E:\\exefiles\\IEDriverServer.exe");
	          System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
	          driver = new InternetExplorerDriver();
	         
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
