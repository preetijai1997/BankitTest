package testcase;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;

public class LoginTest  extends TestBase{
	
	
	@Test(priority=1)
	public void login() throws InterruptedException
	{
         
		driver.switchTo().frame(config.getProperty("frame"));
		 log.debug("Frame is initialize");
		
		driver.findElement(By.name(OR.getProperty("username"))).sendKeys(config.getProperty("name"));
		log.debug("Username is entered");
		
		driver.findElement(By.id(OR.getProperty("pass"))).sendKeys(config.getProperty("pass"));
		log.debug("Password is entered");
		
		driver.findElement(By.id(OR.getProperty("loginBtn"))).click();
		log.debug("Login button is clicked");
		
		
		Assert.assertTrue(elementPresent(driver,OR.getProperty("crossBtn")),"Cross button not visible");
		log.debug("Button visibility");
		
		Thread.sleep(3000);
		driver.findElement(By.xpath(OR.getProperty("crossBtn"))).click();
	}

}
