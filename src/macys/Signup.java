package macys;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Signup 
{
	private WebDriver driver;
	private String website;
	@BeforeSuite
	public void setup()
	{
		driver=new FirefoxDriver();
		website="http://www.macys.com/";
	}

	@Test
	public void testmacys() throws InterruptedException
	{
		driver.get(website);
		driver.findElement(By.xpath("//a[@id='closeButton']")).click();
		driver.findElement(By.xpath("//area[@alt='sign up for emails']")).click();
		
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys("Pawan");
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("Singh");
		driver.findElement(By.xpath("//input[@name='EmailAddr']")).sendKeys("pawan.singh@gspann.com");
		driver.findElement(By.xpath("//select[@name='country']")).click();
		driver.findElement(By.xpath("//option[@value='IN']")).click();
		Thread.sleep(4000);
	}
	@AfterSuite
	public void demolish()
	{
		driver.quit();
	}
	
	
}
