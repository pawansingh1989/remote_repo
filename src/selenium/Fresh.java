package selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Fresh
{
	private FileInputStream file;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFCell cell;
	private DataFormatter formatter;
	WebDriver driver;
	String irctc,macys;
	
	@BeforeSuite
	void initialize()
	{
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		irctc="https://www.irctc.co.in/";
		macys="https://www.macys.com/account/profile";
		//google="https://www.google.co.in/";
	}
	
	public void setExcel(String path,String sheetname) throws IOException
	{
		file=new FileInputStream(path);
		workbook=new XSSFWorkbook(file);
		sheet=workbook.getSheet(sheetname);
	
	}
	
	public String getCellValue(int row,int col)
	{
		cell=sheet.getRow(row).getCell(col);
		formatter=new DataFormatter();
		return formatter.formatCellValue(cell);
				
	}
	
	@Test(enabled=true)
	public void irctc() throws InterruptedException, IOException 
	{
//		driver.get(irctc);
//		driver.findElement(By.xpath("//a[text()='Flights']")).click();
//		
//		Set<String> allWin=driver.getWindowHandles();
//		for(String eachWin:allWin){
//			driver.switchTo().window(eachWin);
//	}
		try {
			setExcel("E:\\data\\testdata.xlsx", "Sheet3");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int row = sheet.getLastRowNum()+1;
        int col = sheet.getRow(0).getLastCellNum();  
        System.out.println(row);
        System.out.println(col);
       
       
        for(int i=2;i<row;i++)
        {
        	driver.get(irctc);
    		driver.findElement(By.xpath("//a[text()='Flights']")).click();
    		
    		Set<String> allWin=driver.getWindowHandles();
    		for(String eachWin:allWin){
    			driver.switchTo().window(eachWin);
    		}
        	System.out.println("start of the record");
        	 String a[]=new String[col-1];        	 
  
        	for(int j=1;j<col;j++){  		
        		
        		a[j-1]=getCellValue(i,j);
        		
//        		System.out.println(getCellValue(i,j));
        	}
        	
        	System.out.println("before origin");
        	driver.findElement(By.xpath("//input[@id='origin']")).sendKeys(a[0]);
        	Thread.sleep(2000);
        	driver.findElement(By.xpath("//ul[@class='ui-autocomplete ui-menu ui-widget ui-widget-content ui-corner-all'][1]/li[1]/a")).click();
        	
        	System.out.println("after origin");
        	
        	Thread.sleep(2000);

          	driver.findElement(By.xpath("//input[@id='destination']")).sendKeys(a[1]);
          	Thread.sleep(5000);
          	System.out.println("between destination");
          	driver.findElement(By.xpath("//ul[@class='ui-autocomplete ui-menu ui-widget ui-widget-content ui-corner-all'][2]/li[1]/a")).click();
          	
          	String date=a[2]; //date from excel
          	String month=date.substring(0,2);
          	int mm = Integer.parseInt(month);
			String dd=date.substring(3,5);
			String yy=date.substring(6,8);
			System.out.println(dd);
			System.out.println(mm);
			System.out.println(yy);
			
//			if(i==3)
//			{
//				driver.findElement(By.xpath("//div[text()='Departure']/following::input[@id='departDateInt']/following::img[3]")).click();
//				driver.findElement(By.xpath("//a[@class='ui-datepicker-next ui-corner-all']//span[text()='Next']")).click();
//				driver.findElement(By.xpath("//td[@data-year = '20"+yy+"' and @data-month = '"+(mm-1)+"']/a[text() = '"+dd+"']")).click();
//				
//			}
//			
			driver.findElement(By.xpath("//div[text()='Departure']/following::input[@id='departDateInt']/following::img[3]")).click();
			if(driver.findElement(By.xpath("//td[@data-year = '20"+yy+"' and @data-month = '"+(mm-1)+"']/a[text() = '"+dd+"']")).isDisplayed()){
				
				driver.findElement(By.xpath("//td[@data-year = '20"+yy+"' and @data-month = '"+(mm-1)+"']/a[text() = '"+dd+"']")).click();
			   }
			   else
			   {
				
			    driver.findElement(By.xpath("//span[text()='Next']")).click();
			    driver.findElement(By.xpath("//td[@data-year = '20"+yy+"' and @data-month = '"+(mm-1)+"']/a[text() = '"+dd+"']")).click();
			   }
			
			
			
//			driver.findElement(By.xpath("//div[text()='Departure']/following::input[@id='departDateInt']/following::img[3]")).click();
//			driver.findElement(By.xpath("//td[@data-year = '20"+yy+"' and @data-month = '"+(mm-1)+"']/a[text() = '"+dd+"']")).click();
//			
			
			String travel=a[3]; //travel class from excel

			Select option=new Select(driver.findElement(By.name("classType")));
			option.selectByVisibleText(travel);

			driver.findElement(By.xpath("//div[@class='srchbtn'][@onclick='submitSearch();']")).click();
			
			
			String screenshot=a[4];
			if(screenshot.equalsIgnoreCase("yes"))
			{
				
				File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"\\selenium\\screenshot\\airlineexcel"+i+".png"));

			}

				
			Thread.sleep(2000);
        	System.out.println("end record");
        	
        	
        }
                	
        	}

	@Test(enabled=false)
	public void macys() throws IOException, InterruptedException
	{
		
		try {
			setExcel("E:\\data\\testdata.xlsx", "Sheet2");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int row = sheet.getLastRowNum()+1;
        int col = sheet.getRow(0).getLastCellNum();  
        System.out.println(row);
        System.out.println(col);
        
        for(int i=1;i<row;i++)
        {
        	driver.get(macys);
           	String a[]=new String[col];
      	
        	for(int j=0;j<col;j++)
        	{	
        		a[j]=getCellValue(i,j);
           		//System.out.println(getCellValue(i,j));
        	}
        	
        	driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(a[0]);
  			driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(a[1]);
			driver.findElement(By.xpath("//input[@id='addressLine1']")).sendKeys(a[2]);
			driver.findElement(By.xpath("//input[@id='addressLine2']")).sendKeys(a[3]);
			driver.findElement(By.xpath("//input[@id='city']")).sendKeys(a[4]);
			
			if(i==1)
			{
			String state=a[5];//date from excel
			if(!(state.isEmpty()))
			{
          	String desiredState=state.substring(0,1);
          	System.out.println("desiredstate="+desiredState);
          	int stateintversion = Integer.parseInt(desiredState);
          	System.out.println("Stateindex="+stateintversion);
			Select state1=new Select(driver.findElement(By.xpath("//select[@id='state']")));
			state1.selectByIndex(stateintversion);
			}
			}
			else
			{
				String state=a[5];
				if(!(state.isEmpty()))
				{
					driver.findElement(By.xpath("//select[@id='state']")).click();
					driver.findElement(By.xpath("//select[@id='state']//option[text()='"+state+"']")).click();
//					Select state1=new Select(driver.findElement(By.xpath("//select[@id='state']")));
//					state1.selectByValue(state);
					
				}
			}
			
			
			
			driver.findElement(By.xpath("//input[@id='zipcode']")).sendKeys(a[6]);

			String date=a[7]; //date from excel
			if(!(date.isEmpty()))
			{
          	String mm=date.substring(0,2);
          	System.out.println(mm);
          	int month=Integer.parseInt(mm);
          	String dd=date.substring(3,5);
          	System.out.println(dd);
          	int day=Integer.parseInt(dd);
			String yy=date.substring(6,8);
			System.out.println(yy);
			int year=Integer.parseInt(yy);
			
			String Month=driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-last']//div[@class='ui-datepicker-title']//span[1]")).getText();
			String Day=driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-last']//div[@class='ui-datepicker-title']//span[2]")).getText();
			
			
			String convertedval = "";
			switch (month) {
			case 1:
				convertedval= "January";
				break;
			case 2:
				convertedval= "February";
				break;
			case 3:
				convertedval= "March";
				break;
			case 4:
				convertedval= "April";
				break;
			case 5:
				convertedval= "May";
				break;
			case 6:
				convertedval= "June";
				break;
			case 7:
				convertedval= "July";
				break;
			case 8:
				convertedval= "August";
				break;
			case 9:
				convertedval= "September";
				break;
			case 10:
				convertedval= "October";
				break;
			case 11:
				convertedval= "November";
				break;
			case 12:
				convertedval= "December";
				break;
				
				
				
				
				

			default:
				break;
			}
			System.out.println("===================="+convertedval);
			Select ddmonth=new Select(driver.findElement(By.xpath("//select[@id='month']")));
			ddmonth.selectByIndex(month);
			Select date1=new Select(driver.findElement(By.xpath("//select[@id='date']")));
			date1.selectByIndex(day);
			driver.findElement(By.xpath("//select[@id='year']")).click();
			driver.findElement(By.xpath("//select[@id='year']//option[text()='19"+year+"']")).click();
			}

			String sex=a[8];
			if(!(sex.isEmpty()))
			{
			Select gender=new Select(driver.findElement(By.xpath("//select[@id='gender']")));
			gender.selectByVisibleText(sex);
			}

			driver.findElement(By.xpath("//input[@id='email']")).sendKeys(a[9]);
			driver.findElement(By.xpath("//input[@id='createConfirmEmail']")).sendKeys(a[10]);
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys(a[11]);
			driver.findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys(a[12]);
			
			String ques=a[13];
			
			if(!(ques.isEmpty()))
			{
			Select selQues=new Select(driver.findElement(By.xpath("//select[@id='SecurityQna']")));
			int quesno=Integer.parseInt(ques);
			selQues.selectByIndex(quesno);
			driver.findElement(By.xpath("//input[@id='securityAns']")).sendKeys(a[14]);
			}
			
			driver.findElement(By.xpath("//button[@id='createProfileBtn']")).click();
			Thread.sleep(2000);
			String screenshot=a[15];
			if(screenshot.contains("yes"))
			{
				File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"\\selenium\\screenshot\\record"+i+".png"));

			}
			Thread.sleep(2000);
        	System.out.println("end record");
        }
	}
	
	

	@AfterSuite
	public void destory()
	{
		driver.quit();
	}
}

        
	





	
	
        
	


