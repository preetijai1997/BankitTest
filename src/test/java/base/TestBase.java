package base;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
	
	
	public static WebDriver driver;
	public static Properties config= new Properties();
	public static Properties OR= new Properties();
	public static FileInputStream fis;
	public static Logger log= Logger.getLogger("devpinoyLogger");
	
	
	@BeforeSuite
	public void setUp() 
	{
		if(driver==null)
		{
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				config.load(fis);
				log.debug("Config file is loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			
			try {
				
				OR.load(fis);
				log.debug("OR file is loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			if(config.getProperty("browser").equals("chrome"))
				
			{
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
				
				driver= new ChromeDriver();
				log.debug("ChromeDriver is initialize");
			}
			else if(config.getProperty("browser").equals("firefox"))
			{
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\geckodriver.exe");
				
				driver= new FirefoxDriver();
				log.debug("Firefox Driver is initialize");
			}
		
			
			driver.get(config.getProperty("url"));
			log.debug("Open the URL");
			driver.manage().window().maximize();
			
			
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit_wait")),TimeUnit.SECONDS);
			
			
		}
	}
	
	public static boolean elementPresent(WebDriver driver,String path) 
	{
		try
		{
			driver.findElement(By.xpath(path));
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
		
		
		return true;
	}
	@AfterSuite
	public void tearDown()
	{
		if(driver !=null)
		{
			//driver.quit();
		}
		log.debug("Quit the site");
	}
	
}
