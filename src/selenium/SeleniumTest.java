package selenium;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class SeleniumTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

 /* @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.google.co.in/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  */
@BeforeSuite
public void beforesuite()
{
	System.out.println("Before Suite");
	
}
@AfterSuite
public void aftersuite()
{
	System.out.println("After Suite");
}
@BeforeClass
public void beforeclass()
{
	System.out.println("Before Class");
}
@AfterClass
public void afterclass()
{
	System.out.println("After Class");
}
@BeforeMethod
public void beforemethod()
{
	System.out.println("Before method");
}
@AfterMethod
public void aftermethod()
{
	System.out.println("After Method");
}
  @BeforeTest
public void beforetest()
{
	System.out.println("Before test");
}


 /* @Test
  public void testGspann() throws Exception {
    driver.get(baseUrl + "/?gws_rd=ssl");
    driver.findElement(By.id("gbqfq")).clear();
    driver.findElement(By.id("gbqfq")).sendKeys("gspann technologies");
    driver.findElement(By.cssSelector("em")).click();
    
  }
 */
@Test(dependsOnMethods={"test2"})
public void test()
{
	System.out.println("test");
}
@Test(priority=1)
public void test2()
{
	System.out.println("test2");
}
@Parameters({"username","password"})
@Test
public void param(String name, String passcode)
{
	System.out.println(name);
	System.out.println(passcode);
	
	
}
  @AfterTest
  public void aftertest()
  {
	  System.out.println("After Test");
	  
  }
  /*@After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }*/
  
}

  


  
