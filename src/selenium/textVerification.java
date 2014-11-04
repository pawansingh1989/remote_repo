package selenium;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;



public class textVerification 
{
	private WebDriver driver;
	String website,macys_Q1,macys_Q3,google;

	@BeforeMethod
	@Parameters("browser")   
	public void setup(String explorer)
	{
		website="https://www.macys.com/";
		if(explorer.equalsIgnoreCase("chrome"))
		{
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\exefiles\\chromedriver.exe");
        driver=new ChromeDriver();
		}
		else
		{
		if(explorer.equalsIgnoreCase("IE"))
		{
			  System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\exefiles\\IEDriverServer.exe" );
			  System.out.println( System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\exefiles\\IEDriverServer.exe" ));
			  driver = new InternetExplorerDriver();
		}
		else
		{
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//		website="https://www.google.com";
//		macys_Q3="https://www.macys.com/account/profile";
//		macys_Q1="http://www1.macys.com/";
		}
		}
	}
	
		
	@Test(enabled=false)
	public void verify1()
	{
		driver.get(website);
		String text=driver.findElement(By.xpath("//span[text()='Google Search']")).getText();
		Assert.assertEquals("Google Search", text);
		
		
	}
	@Test(enabled=false)
	public void verify2()
	{
		driver.get(website);
		String id=driver.findElement(By.xpath("//span[text()='Google Search']")).getAttribute("id");
		Assert.assertEquals(id, "gbqfba");
	}
	@Test(enabled=false)
	void verify3()
	{
		driver.get(website);
		String h=driver.findElement(By.xpath("//div[@id='hplogo']")).getCssValue("height");
		Assert.assertEquals(h,"95px");
		
		
	}
	@Test(enabled=false)
	void assignment_Day4_Q1()
	{
		driver.get(website);
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("abc");
		driver.findElement(By.xpath("//input[@id='Passwd']")).sendKeys("xyz");
		driver.findElement(By.xpath("//input[@id='signIn']")).click();
		
		String err_msg=driver.findElement(By.xpath("//span[@id='errormsg_0_Passwd']")).getText();
		//System.out.println(err_msg);
		Assert.assertEquals(err_msg,"The email or password you entered is incorrect. ?");
		
		
	}
	@Test(enabled=false)
	public void assignment_Day4_Q2() throws InterruptedException
	{
		driver.get(website);
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("pawanpop@gmail.com");
		driver.findElement(By.xpath("//input[@id='Passwd']")).sendKeys("pawan_037");
		driver.findElement(By.xpath("//input[@id='signIn']")).click();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//colgroup/following::tbody//tr[2]//td[2]")).click();
		Thread.sleep(8000);
		System.out.println("B4");
		driver.findElement(By.xpath("//div[@aria-Label='Delete']/div/div")).click();
		System.out.println("CLICKED");
		String msg=driver.findElement(By.xpath("//div[@class='vh']")).getText();
		System.out.println(msg);
		Assert.assertEquals(msg, "The conversation has been moved to the Trash.  Learn more  Undo");
		driver.findElement(By.xpath("//div[@class='vh']//span[@id='link_undo']")).click();
		
		
	}
	@Test(enabled=false)
	public void keyboardevents1() throws InterruptedException
	{
		driver.get(website);
		Actions builder=new Actions(driver);
//		Thread.sleep(5000);
		builder.click(driver.findElement(By.xpath("//a[text()='हिन्दी']"))).keyDown(Keys.CONTROL).click(driver.findElement(By.xpath("//a[text()='ਪੰਜਾਬੀ']"))).keyUp(Keys.CONTROL).build().perform();
		
		
		
	}
	@Test(enabled=false)
	public void links() throws InterruptedException
	{
		driver.get(website);
		java.util.List<WebElement> mylist=driver.findElements(By.xpath("//div[@id='als']/a"));
		
		WebElement element3=mylist.get(3);
		
		driver.findElement(By.xpath("//input[@id='gbqfq']")).sendKeys(element3.getText());
		Thread.sleep(4000);
		driver.findElement(By.xpath("//button[@id='gbqfb']")).click();
				
	}
	
	@Test(enabled=false)
	
	public void js() throws InterruptedException
	{
		driver.get(website);
		JavascriptExecutor js = (JavascriptExecutor)driver;
	
		js.executeScript("alert(document.getElementsByTagName('a').length)");
		Thread.sleep(10000);
	}
	
	@Test(enabled=false)
	public void screenshots() throws IOException
	{
		driver.get(website);
		driver.findElement(By.xpath("//input[@id='gbqfq']")).sendKeys("Gspann Tech");
		driver.findElement(By.xpath("//button[@id='gbqfb']")).click();
		
	
//		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(srcFile,new File("D:/seleniumscreenshot/pic1.png"));
//		
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"\\selenium\\screenshot\\pic1.png"));
//		System.out.println(System.getProperty("user.dir")+"\\selenium\\screenshot\\pic1.png");
		
	}
	@Test(enabled=false)
	public void assignmentDay6Q1() throws InterruptedException
	{
		driver.get(macys_Q1);
		driver.findElement(By.xpath("//a[@id='closeButton']")).click();
		Actions action = new Actions(driver);
		
		Thread.sleep(4000);
		while(true) {
		List<WebElement> categories=driver.findElements(By.xpath("//div[@id='globalMastheadCategoryMenu']//ul//li"));
		int number_of_categories=categories.size();
		
		
		 Random number = new Random();
		 int randomNum= number.nextInt(number_of_categories-1)+1;
		 
		 System.out.println(randomNum);
		
		 WebElement randomly_selected_category=driver.findElement(By.xpath("//div[@id='globalMastheadCategoryMenu']//ul//li"+"["+randomNum+"]"));
		 String selected_category_text=randomly_selected_category.getText();
		 System.out.println(selected_category_text);
		
		 WebElement category=driver.findElement(By.xpath("//div[@id='globalMastheadCategoryMenu']//li//a[text()='"+selected_category_text+"']"));
		 action.moveToElement(category).build().perform();
		  
		 List <WebElement> sub_categories=driver.findElements(By.xpath("//div[@id='globalMastheadFlyout']/div[@class='subnav flyout-on']//ul/li/a"));
		
		 int no_of_subcategories=sub_categories.size();
		 System.out.println("no.of subcategories="+no_of_subcategories);
		 Random num = new Random();
		 int randomNum1=num.nextInt(no_of_subcategories-1)+1;
		 System.out.println(randomNum1);
		
		 String text=sub_categories.get(randomNum1).getText();
		 System.out.println(text);
//		
		driver.findElement(By.xpath("//div[@id='globalMastheadFlyout']/div[@class='subnav flyout-on']/div/div/ul/li/a[text()=\""+text+"\"]")).click();
		
		Thread.sleep(2000);
		try {
			if(!(driver.findElement(By.xpath("//h2[text()='Brand']/following::div[@class='brand-options']//div[@id='featuredBrandsHdr']")).isDisplayed()))
				driver.findElement(By.xpath("//h2[text()='Brand']")).click();
		}catch(Exception e) {
			continue;
		}
			
		//select brand and get brand
		String brandName=driver.findElement(By.xpath("//h2[text()='Brand']/following::div[@class='brand-options']//ul[@id='featuredBrands']//li[1]//span[1]")).getText();
		System.out.println(brandName);
		driver.findElement(By.xpath("//h2[text()='Brand']/following::div[@class='brand-options']//ul[@id='featuredBrands']//li[1]")).click();
		Thread.sleep(4000);
		String no_of_products=driver.findElement(By.xpath("//h2[text()='Brand']/following::div[@class='brand-options']//ul[@id='featuredBrands']//li[1]//span[2]")).getText();
		 System.out.println(no_of_products);
		 
		 String product_count=driver.findElement(By.xpath("//span[@id='productCount']")).getText();
		 Thread.sleep(4000);
		 System.out.println(product_count);
		 
		 Thread.sleep(4000);	
		 break;
		}
	}
	
	@Test(enabled=false)
	public void assignmentDay6Q2() throws InterruptedException, IOException
	{
		
	driver.get(macys_Q1);
	driver.findElement(By.xpath("//a[@id='closeButton']")).click();
	Actions action = new Actions(driver);
	
	Thread.sleep(4000);
	while(true) {
	List<WebElement> categories=driver.findElements(By.xpath("//div[@id='globalMastheadCategoryMenu']//ul//li"));
	int number_of_categories=categories.size();
	
	
	 Random number = new Random();
	 int randomNum= number.nextInt(number_of_categories-1)+1;
	 
	 System.out.println(randomNum);
	
	 WebElement randomly_selected_category=driver.findElement(By.xpath("//div[@id='globalMastheadCategoryMenu']//ul//li"+"["+randomNum+"]"));
	 String selected_category_text=randomly_selected_category.getText();
	 System.out.println(selected_category_text);
	
	 WebElement category=driver.findElement(By.xpath("//div[@id='globalMastheadCategoryMenu']//li//a[text()='"+selected_category_text+"']"));
	 action.moveToElement(category).build().perform();
	  
	 List <WebElement> sub_categories=driver.findElements(By.xpath("//div[@id='globalMastheadFlyout']/div[@class='subnav flyout-on']//ul/li/a"));
	
	 int no_of_subcategories=sub_categories.size();
	 System.out.println("no.of subcategories="+no_of_subcategories);
	 Random num = new Random();
	 int randomNum1=num.nextInt(no_of_subcategories-1)+1;
	 System.out.println(randomNum1);
	
	 String text=sub_categories.get(randomNum1).getText();
	 System.out.println(text);
//	
	driver.findElement(By.xpath("//div[@id='globalMastheadFlyout']/div[@class='subnav flyout-on']/div/div/ul/li/a[text()=\""+text+"\"]")).click();
	
	Thread.sleep(2000);
	try {
		if(!(driver.findElement(By.xpath("//h2[text()='Brand']/following::div[@class='brand-options']//div[@id='featuredBrandsHdr']")).isDisplayed()))
			driver.findElement(By.xpath("//h2[text()='Brand']")).click();
	}catch(Exception e) {
		continue;
	}
	
	//select brand and get brand
	String brandName=driver.findElement(By.xpath("//h2[text()='Brand']/following::div[@class='brand-options']//ul[@id='featuredBrands']//li[1]//span[1]")).getText();
	System.out.println(brandName);
	driver.findElement(By.xpath("//h2[text()='Brand']/following::div[@class='brand-options']//ul[@id='featuredBrands']//li[1]")).click();
	Thread.sleep(4000);
	String no_of_products=driver.findElement(By.xpath("//h2[text()='Brand']/following::div[@class='brand-options']//ul[@id='featuredBrands']//li[1]//span[2]")).getText();
	System.out.println(no_of_products);
	 
	 
	File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"\\selenium\\screenshot\\product_count.png"));
	 
	 String product_count=driver.findElement(By.xpath("//span[@id='productCount']")).getText();
	 Thread.sleep(4000);
	 System.out.println(product_count);
	 
	 Thread.sleep(4000);	
	 break;
	}
	 Thread.sleep(4000);
	 if(driver.findElement(By.xpath("//div[@id='paginateTop']//a[@class='currentPage paginationSpacer']")).isDisplayed())
	 {
	 String pageno=driver.findElement(By.xpath("//div[@id='paginateTop']//a[@class='currentPage paginationSpacer']")).getText();
	 System.out.println(pageno);
	 Assert.assertEquals(pageno,1); //to verify first page is opened
	 	
	 WebElement page2=driver.findElement(By.xpath("//div[@id='paginateTop']//a[text()='2']"));
	 page2.click();
	 String classname=page2.getAttribute("class");
	 Assert.assertEquals(classname, "currentPage paginationSpacer");
	 }
	 Thread.sleep(4000);
	}
	
	@Test(enabled=false)
	public void assignmentDay6Q3() throws InterruptedException, IOException
	{
		driver.get(macys_Q3);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='create profile']")).click();
		System.out.println("******");
		String errmsg=driver.findElement(By.xpath("//div[@class='row error-class-row formMsgs']//li")).getText();
		//System.out.println(errmsg);
		Assert.assertEquals(errmsg, "We're sorry. The fields highlighted below must be completed before we can process your request.");
		
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"\\selenium\\screenshot\\pic1.png"));
		
		System.out.println(System.getProperty("user.dir")+"\\selenium\\screenshot\\pic1.png");
		
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Pawan");
		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Singh");
		driver.findElement(By.xpath("//input[@id='addressLine1']")).sendKeys("rajouri-garden");
		driver.findElement(By.xpath("//input[@id='city']")).sendKeys("new-delhi");
		
		Select state=new Select(driver.findElement(By.xpath("//select[@id='state']")));
		state.selectByValue("NY");
		driver.findElement(By.xpath("//input[@id='zipcode']")).sendKeys("110027");
		
		Select month=new Select(driver.findElement(By.xpath("//select[@id='month']")));
		month.selectByVisibleText("November");
		
		Select date=new Select(driver.findElement(By.xpath("//select[@id='date']")));
		date.selectByVisibleText("27");
		
		Select year=new Select(driver.findElement(By.xpath("//select[@id='year']")));
		year.selectByVisibleText("1989");
		
		Select gender=new Select(driver.findElement(By.xpath("//select[@id='gender']")));
		gender.selectByVisibleText("Male");
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("pawan.singh@gspann.com");
		driver.findElement(By.xpath("//input[@id='createConfirmEmail']")).sendKeys("pawan.singh@gspann.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("pawan");
		driver.findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys("Pawan");
		
		Select secQues=new Select(driver.findElement(By.xpath("//select[@id='SecurityQna']")));
		secQues.selectByVisibleText("What was the name of your favorite cartoon series as a child?");
		
		driver.findElement(By.xpath("//input[@id='securityAns']")).sendKeys("popeye");
		
		Thread.sleep(4000);
	}
	
		
		@Test(enabled=false)
		public void revise() throws InterruptedException, IOException
		{
			driver.get(macys_Q1);
			String text=driver.findElement(By.xpath("//a[text()='HOME']")).getText();
			Assert.assertEquals(text, "HOME");
			boolean result=driver.findElement(By.xpath("//a[text()='HOME']")).isDisplayed();
			
			Assert.assertTrue(result);
			
			String id=driver.findElement(By.xpath("//a[text()='HOME']/ancestor::li")).getAttribute("id");
			System.out.println(id);
			Assert.assertEquals(id,"flexLabel_22672");
			
			
			String css=driver.findElement(By.xpath("//a[text()='HOME']/ancestor::li")).getCssValue("margin-right");
			System.out.println(css);
			
			String css1=driver.findElement(By.xpath("//div[@id='featureNav']")).getCssValue("margin-right");
			System.out.println(css1);
			
			
			Actions builder=new Actions(driver);
			builder.keyDown(Keys.CONTROL).click(driver.findElement(By.xpath("//a[text()='BED & BATH']"))).click(driver.findElement(By.xpath("//a[text()='WOMEN']"))).keyUp(Keys.CONTROL).build().perform();
//			builder.doubleClick(null);
			//builder.dragAndDrop(source, target);
	
			JavascriptExecutor js=(JavascriptExecutor)driver;
			//js.executeScript("alert('Hi');");
			js.executeScript("alert(document.getElementsByTagName('a').length)");
			Thread.sleep(3000);
			
			File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src,new File("D:\\logs\\issue.png"));
			FileUtils.copyFile(src,new File(System.getProperty("user.dir")+"\\selinium\\screenshot\\pic1.png"));
					
			
		}
		@Test(enabled=false)
		void revise_sel() throws InterruptedException
		{
			driver.get("https://www.macys.com/account/profile");
			Select dd=new Select(driver.findElement(By.xpath("//select[@id='month']")));
			List<WebElement> options=dd.getOptions();
			//System.out.println(options);
			dd.selectByIndex(2);
			Thread.sleep(2000);
			Assert.assertFalse(dd.isMultiple());
			
			WebElement w=driver.findElement(By.xpath("//input[@id='emailSalesEventsYes']"));
	
			Assert.assertTrue(w.isSelected());
		
		
			if(w.isSelected())
			{
				driver.findElement(By.xpath("//input[@id='emailSalesEventsLater']")).click();
				System.out.println("clicked");
			}
			
			WebDriverWait wait=new WebDriverWait(driver,30);
//			wait.until(isTrue			
					  
			
		}
		
		boolean isElementPresent(By locator)
		{
			
			try
			{
				driver.findElement(locator);
				return true;
			}
			catch(NoSuchElementException e)
			{
				return false;
			}
		
		}
		@Test(enabled=false)
		public void checkElement() throws InterruptedException
		{
			Actions builder=new Actions(driver);
			driver.get("http://www.google.com");
			Thread.sleep(4000);
			WebElement element=driver.findElement(By.xpath("//input[@id='gbqfq']"));
			
//			element.click();
			
//			Actions builder=new Actions(driver);
//			element.sendKeys("");
			
//			builder.click(element).keyDown(Keys.SHIFT).build().perform();
//			element.sendKeys("selenium");
//			builder.keyUp(Keys.SHIFT).build().perform();
			
			builder.clickAndHold(element);
			
			builder.click(element).keyDown(Keys.SHIFT).sendKeys(driver.findElement(By.id("gbqfq")),"selenium").keyUp(Keys.SHIFT).build().perform();
			builder.release(element);
			
			//driver.findElement(By.xpath("//span[text()='Google Search'][@id='gbqfsa']")).click();
			Thread.sleep(6000);
			
			Assert.assertTrue(isElementPresent(By.xpath("//a[text()='Selenium - Web Browser Automation']")));
			//Assert.assertTrue(isElementPresent(By.xpath("//input[@id='gbqfq']")));
			
		
		}
		
		
		@Test(enabled=false)
		public void assignment1Day7()
		{
			driver.get(website);
			Assert.assertTrue(isElementPresent(By.xpath("//div[@id='macysHomePageLogo']")));
			
		}
		@Parameters("browser")   
		@Test(enabled=false)
		public void assignment2Day7(String explorer) throws InterruptedException, IOException
		{
			driver.get("https://www.macys.com/account/profile");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[text()='create profile']")).click();
			System.out.println("******");
			
			File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			if(explorer.equalsIgnoreCase("chrome"))
			{
			FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"\\selenium\\screenshot\\chrome.png"));
			}
			else
				if(explorer.equalsIgnoreCase("IE"))
				{	
					System.out.println("InternetExplorer");
					FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"\\selenium\\screenshot\\IE.png"));
				}
				else
					{
						FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"\\selenium\\screenshot\\firefox.png"));
					}
		}
		
		@Test(enabled=false)
		public void question2Day7()
		{
			driver.get(website);
			String url=driver.getCurrentUrl();
			System.out.println(url);
			Assert.assertTrue(url.contains("macys"));	
			
			
		}
		@Test(enabled=true)
		public void alert() throws InterruptedException
		{
			google="https://www.google.co.in";
			driver.get(google);
			JavascriptExecutor js = (JavascriptExecutor)driver;
			
			js.executeScript("alert('Welcome')");
			Thread.sleep(3000);
			Alert alert=driver.switchTo().alert();
			alert.accept();
		}
		
		
		
	
		@AfterMethod
		@Parameters("browser")   
	public void destory(String explorer)
	{
			if(explorer.equalsIgnoreCase("chrome"))
			{
				driver.quit();
			}
			else
				if(explorer.equalsIgnoreCase("IE"))
				{
					driver.quit();
				}
				else
					driver.quit();
			
	}

}
