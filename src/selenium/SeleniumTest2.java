package selenium;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;


import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SeleniumTest2 
{
	private WebDriver driver;
	private String google,irctc,gmail,macys;

	@BeforeSuite
	void initialize()
	{
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		//		google="https://www.google.co.in/";
		gmail="https://accounts.google.com/";
		macys="https://www.macys.com/account/profile";
	}

	@Test(enabled=false)
	void testgspann()
	{
		driver.get(google + "/?gws_rd=ssl");
		driver.findElement(By.id("gbqfq")).clear();
		driver.findElement(By.id("gbqfq")).sendKeys("gspann technologies");
		driver.findElement(By.cssSelector("em")).click();

	}
	@Test(enabled=false)
	public void alert() throws InterruptedException
	{

		google="https://www.google.co.in";
		driver.get(google);
		JavascriptExecutor js = (JavascriptExecutor)driver;

		js.executeScript("alert('Hey Girl')");
		Thread.sleep(3000);
		Alert alert=driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
	}
	@Test(enabled=true)
	public void irctc() throws InterruptedException, IOException
	{
		irctc="https://www.irctc.co.in/";
		driver.get(irctc);
		String parentWin=driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='Flights']")).click();

		Set<String> allWin=driver.getWindowHandles();
		for(String eachWin:allWin)
		{

			driver.switchTo().window(eachWin);
		}

		Thread.sleep(8000);
		WebElement source=driver.findElement(By.xpath("//input[@id='origin']"));
		source.sendKeys("delhi");
		driver.findElement(By.xpath("//a[text()='New Delhi, DEL']")).click();

		//			Actions builder=new Actions(driver);
		//			builder.click().keyDown(Keys.ENTER).keyUp(Keys.ENTER).build().perform();
		//			


		WebElement destination=driver.findElement(By.xpath("//input[@id='destination']"));
		destination.sendKeys("jaipur");
		driver.findElement(By.xpath("//a[text()='Jaipur, JAI']")).click();

		//			Actions builder1=new Actions(driver);
		//			builder1.keyDown(Keys.ENTER).keyUp(Keys.ENTER).build().perform();

		driver.findElement(By.xpath("//div[text()='Departure']/following::input[@id='departDateInt']/following::img[3]")).click();
		System.out.println("calender clicked");
		driver.findElement(By.xpath("//a[text()='15']")).click();

		//			Select dd=new Select(driver.findElement(By.name("classType")));
		//			
		//			dd.selectByIndex(1);
		Thread.sleep(4000);
		driver.findElement(By.xpath("//div[@class='srchbtn'][@onclick='submitSearch();']")).click();


		Thread.sleep(10000);

		for(String eachWin:allWin)
		{
			driver.switchTo().window(eachWin);
		}

		List <WebElement> flights=driver.findElements(By.xpath("//div[@id='flightListResult']/div"));
		int no_of_flights=flights.size();
		System.out.println("No.of Flights="+no_of_flights);

		System.out.println("Airline:"+driver.findElement(By.xpath("//div[@id='flightListResult']/div[1]//td[1]/div[1]")).getText());

		System.out.println("Price="+driver.findElement(By.xpath("//div[@id='flightListResult']/div[1]//td[9]//td/span")).getText());

		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"\\selenium\\screenshot\\Airline.png"));

		System.out.println("thank you for using IRCTC .. :P");
	}

	@Test(enabled=false)
	public void irctc2() throws InterruptedException
	{
		irctc="https://www.irctc.co.in/";
		driver.get(irctc);
		String parentWin=driver.getWindowHandle();
		////			driver.findElement(By.xpath("//a[text()='Flights']")).click();
		//			
		//			Set<String> allWin=driver.getWindowHandles();
		//			for(String eachWin:allWin)
		//			{
		//				
		//				driver.switchTo().window(eachWin);
		//			}
		//			System.out.println(driver.getTitle());
		//			driver.close();
		//			
		//			driver.switchTo().window(parentWin);

		driver.findElement(By.id("loginbutton")).click();
		driver.findElement(By.xpath("//div[@id='loginerrorpanel_header_controls']/span")).click();

		String homeWin=driver.getWindowHandle();

		System.out.println("going into the frame");
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='banner3']//iframe")));

		System.out.println("in the frame");
		driver.findElement(By.xpath("//img[@alt='Maharajas Express']")).click();
		System.out.println("maharaja clicked");
		Thread.sleep(10000);

		Set<String> allWin1=driver.getWindowHandles();
		for(String eachWin:allWin1)
		{

			driver.switchTo().window(eachWin);
		}
		System.out.println(driver.getTitle());

		System.out.println(" Goin to Journey ");
		WebElement journey=driver.findElement(By.xpath("//ul[@class='menuH']//a[text()='Journeys']"));
		WebElement gems=driver.findElement(By.xpath("//ul[@class='menuH']//a[text()='Journeys']/following::ul[1]/li[1]/a[text()='Gems of India']"));
		WebElement jaipur=driver.findElement(By.xpath("//ul[@class='menuH']//a[text()='Journeys']/following::ul[1]/li[1]/a[text()='Gems of India']/following::li[4]/a[text()='Jaipur']"));

		Actions builder=new Actions(driver);
		Thread.sleep(4000);

		builder.moveToElement(journey).moveToElement(gems).click(jaipur).build().perform();

	}
	@Test(enabled=false)
	public void maharaja() throws InterruptedException
	{
		driver.get("http://www.the-maharajas.com/");
		WebElement journey=driver.findElement(By.xpath("//ul[@class='menuH']//a[text()='Journeys']"));
		WebElement gems=driver.findElement(By.xpath("//ul[@class='menuH']//a[text()='Journeys']/following::ul[1]/li[1]/a[text()='Gems of India']"));
		WebElement jaipur=driver.findElement(By.xpath("//ul[@class='menuH']//a[text()='Journeys']/following::ul[1]/li[1]/a[text()='Gems of India']/following::li[4]/a[text()='Jaipur']"));

		Actions builder=new Actions(driver);
		Thread.sleep(4000);
		System.out.println(driver.getTitle());
		builder.moveToElement(journey).moveToElement(gems).click(jaipur).build().perform();
		Thread.sleep(4000);	
		System.out.println("jaipur clicked");

		driver.findElement(By.xpath("//ul[@class='tabs']/li[2]/a[text()='Places Of Interest']")).click();
		List<WebElement> places=driver.findElements(By.xpath("//h3"));
		Iterator<WebElement> itr=places.iterator();
		while(itr.hasNext())
		{
			System.out.println(itr.next().getText());
		}

	}

	@Test(enabled=false)
	public void data() throws IOException
	{
		Excel excel=new Excel();
		excel.setExcel("E:\\data\\testdata.xlsx", "Sheet1");
		String name=excel.getCellValue(1,0);
		System.out.println(name);
		//Assert.assertEquals("pawan", name);

	}
	@Test(enabled=false)
	public void gmail() throws IOException
	{
		Excel excel=new Excel();
		excel.setExcel("E:\\data\\testdata.xlsx", "Sheet1");

		for(int row=1;row<4;row++)
		{
			String username="";
			String password="";
			for(int col=1;col<3;col++)
			{
				if(col==1)
				{
					username=excel.getCellValue(row,col);
				}else{
					password=excel.getCellValue(row,col);
				}

			}
			driver.get(gmail);
			driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@id='Passwd']")).sendKeys(password);
			driver.findElement(By.xpath("//input[@id='signIn']")).click();

			System.out.println(driver.getTitle());

		}

		Assert.assertTrue((driver.getTitle().contains("Personal Info - Account Settings")));
	}

	@Test(enabled=false)
	public void createAccount() throws IOException, InterruptedException
	{
		ArrayList<String> a=new ArrayList<String>();
		a.add("//input[@id='firstName']");
		a.add("//input[@id='lastName']");
		a.add("//input[@id='addressLine1']");
		a.add("//input[@id='city']");
		a.add("//select[@id='state']");
		driver.get(macys);
		Excel excel=new Excel();
		excel.setExcel("E:\\data\\testdata.xlsx", "Sheet2");

		for(int row=1;row<3;row++)
			for(int col=0;col<17;col++)
			{
				driver.findElement(By.xpath(a.get(col))).sendKeys(excel.getCellValue(row,col));
			
				//driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(excel.getCellValue(row,col));
				//driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(excel.getCellValue(row,col));
				//driver.findElement(By.xpath("//input[@id='addressLine1']")).sendKeys(excel.getCellValue(row,col));
				//driver.findElement(By.xpath("//input[@id='city']")).sendKeys(excel.getCellValue(row,col));

				String state=excel.getCellValue(row,col);



				Select state1=new Select(driver.findElement(By.xpath("//select[@id='state']")));
				state1.selectByIndex(4);
				driver.findElement(By.xpath("//input[@id='zipcode']")).sendKeys(excel.getCellValue(row,col));

				Select month=new Select(driver.findElement(By.xpath("//select[@id='month']")));
				month.selectByVisibleText("November");

				Select date=new Select(driver.findElement(By.xpath("//select[@id='date']")));
				date.selectByVisibleText("27");

				Select year=new Select(driver.findElement(By.xpath("//select[@id='year']")));
				year.selectByVisibleText("1989");

				Select gender=new Select(driver.findElement(By.xpath("//select[@id='gender']")));
				gender.selectByVisibleText("Male");

				driver.findElement(By.xpath("//input[@id='email']")).sendKeys(excel.getCellValue(row,col));
				driver.findElement(By.xpath("//input[@id='createConfirmEmail']")).sendKeys(excel.getCellValue(row,col));
				driver.findElement(By.xpath("//input[@id='password']")).sendKeys(excel.getCellValue(row,col));
				driver.findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys(excel.getCellValue(row,col));

				Select secQues=new Select(driver.findElement(By.xpath("//select[@id='SecurityQna']")));
				secQues.selectByVisibleText("What was the name of your favorite cartoon series as a child?");

				driver.findElement(By.xpath("//input[@id='securityAns']")).sendKeys(excel.getCellValue(row,col));
				driver.findElement(By.xpath("//button[@id='createProfileBtn']")).click();
				Thread.sleep(2000);
				String screenshot=excel.getCellValue(row,col);
				if(screenshot.contains("yes"))
				{
					File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"\\selenium\\screenshot\\record.png"));

				}
				
			}
	}


	@Test(enabled=false)
	public void irctcExcel() throws InterruptedException, IOException{

		irctc="https://www.irctc.co.in/";

		driver.get(irctc);
		String parentWin=driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='Flights']")).click();

		Set<String> allWin=driver.getWindowHandles();
		for(String eachWin:allWin)
		{

			driver.switchTo().window(eachWin);
		}

		Thread.sleep(8000);
		Excel excel=new Excel();
		excel.setExcel("E:\\data\\testdata.xlsx", "Sheet3");

		for(int row=2;row<4;row++)

		{
			WebElement source=driver.findElement(By.xpath("//input[@id='origin']"));
			source.sendKeys(excel.getCellValue(row,2));
			driver.findElement(By.xpath("//a[text()='New Delhi, DEL']")).click();


			WebElement destination=driver.findElement(By.xpath("//input[@id='destination']"));
			destination.sendKeys(excel.getCellValue(row,1));
			driver.findElement(By.xpath("//a[text()='Mumbai, BOM']")).click();

			//				Actions builder1=new Actions(driver);
			//				builder1.keyDown(Keys.ENTER).keyUp(Keys.ENTER).build().perform();

			String date=excel.getCellValue(row,3);
			// String to be scanned to find the pattern.

									String day=date.substring(0,1);
									String month=date.substring(3,4);
			//						String year=date.substring(6,9);
									
									System.out.println(day+month);

//			Pattern p = Pattern.compile("-?\\d+");
//			Matcher m = p.matcher(date);
//			//						  List<String> list=new List();
//			String day=m.group(0);
//			String mm=m.group(1);
//			String yy=m.group(2);
//
//			System.out.println(day+mm+yy);

			//						  while (m.find()) {
			//						    System.out.println(m.group());
			//						    
			//						  
			//						  }
			//						  

			driver.findElement(By.xpath("//div[text()='Departure']/following::input[@id='departDateInt']/following::img[3]")).click();

			driver.findElement(By.xpath("//a[text()='15']")).click();

			String classTravel=excel.getCellValue(row,4);

			Select dd=new Select(driver.findElement(By.name("classType")));
			dd.selectByVisibleText(classTravel);




			driver.findElement(By.xpath("//div[@class='srchbtn'][@onclick='submitSearch();']")).click();

			Thread.sleep(4000);

			String screenshot=excel.getCellValue(row,5);
			if(screenshot.equalsIgnoreCase("yes"))
			{
				File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"\\selenium\\screenshot\\airlineexcel.png"));

			}

		}
	}

	public void fresh() throws InterruptedException, IOException
	{
		driver.get(irctc);
		driver.findElement(By.xpath("//a[text()='Flights']")).click();

		Set<String> allWin=driver.getWindowHandles();
		for(String eachWin:allWin)
		{

			driver.switchTo().window(eachWin);
		}

		Thread.sleep(8000);
		Excel excel=new Excel();
		excel.setExcel("E:\\data\\testdata.xlsx", "Sheet3");
		
			
		
		
	}


	@AfterSuite
	public void tearDown() throws Exception {
		driver.quit();

	}


}
