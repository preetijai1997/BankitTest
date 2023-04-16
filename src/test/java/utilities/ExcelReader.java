package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import base.TestBase;

public class ExcelReader  extends TestBase{
	
	
	@Test(priority=3)
	public void ExcelReadeing() throws IOException, InterruptedException
	
	{
		
		FileInputStream file= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\Test.xlsx");
		
		XSSFWorkbook workbook= new XSSFWorkbook(file);
		
		XSSFSheet firstSheet= workbook.getSheet("Sheet1");
		XSSFSheet secondSheet= workbook.getSheet("Sheet2");
		int rowCount=firstSheet.getLastRowNum()-firstSheet.getFirstRowNum();
		
		int cellCount=firstSheet.getRow(0).getLastCellNum();
		
		int rowCountSecondPage=secondSheet.getLastRowNum();
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		
		
		for(int i=1; i<rowCount+1;i++)
		{
			XSSFRow currentRow=firstSheet.getRow(i);
			String mobile_number=currentRow.getCell(0).getStringCellValue();
			System.out.println("Mobile num is : "+ mobile_number);
			performNeccessaryAction(driver,"//input[@id=\"mobile_no\"]","xpath","sendkeys",mobile_number,false);
			//driver.findElement(By.id(OR.getProperty("senderNum"))).sendKeys(mobile_number);
			Thread.sleep(3000);
			performNeccessaryAction(driver,OR.getProperty("subBtn"),"xpath","click","",false);
			
			if(elementPresent(driver,"//td[text()='Kyc Status :']"))
			{
				performNeccessaryAction(driver,OR.getProperty("cancelBtn"),"xpath","click","",false);
				
			} 
			else
			{
			
				XSSFRow currentRow1=secondSheet.getRow(i);
			
			
				String Sender_Name=currentRow1.getCell(0).getStringCellValue();
				System.out.println("name is  is : "+ Sender_Name);
				String Address=currentRow1.getCell(1).getStringCellValue();
				System.out.println("Address is : "+ Address);

			
				performNeccessaryAction(driver,"//input[@name='c_name']","xpath","sendkeys",Sender_Name,true);
				
				//driver.findElement(By.id(OR.getProperty("name"))).sendKeys(Sender_Name);
				performNeccessaryAction(driver,"//input[@name=\"c_address\"]","xpath","sendkeys",Address,true);
				
				//driver.findElement(By.id(OR.getProperty("address"))).sendKeys(Address);
				performNeccessaryAction(driver,"//input[@name='c_dob']","xpath","click","",true);
				//driver.findElement(By.id(OR.getProperty("dob"))).click();
				
				Select yearDropdown=new Select(driver.findElement(By.xpath(OR.getProperty("year"))));
				String year= currentRow1.getCell(2).getStringCellValue();
				System.out.println("year is : "+ year);
				yearDropdown.selectByValue(year);
				
				
				Select monthDropdown=new Select(driver.findElement(By.xpath(OR.getProperty("month"))));
				String month= currentRow1.getCell(3).getStringCellValue();
				System.out.println("month is "+ month);
				monthDropdown.selectByValue(month);
				
				performNeccessaryAction(driver,OR.getProperty("date"),"xpath","click","",true);
				//driver.findElement(By.xpath(OR.getProperty("date"))).click();		
				performNeccessaryAction(driver,OR.getProperty("subBtn"),"xpath","click","",true);
				//driver.findElement(By.xpath(OR.getProperty("subBtn"))).click();
				System.out.println("OTP is -------------");
				performNeccessaryAction(driver,OR.getProperty("OTP"),"id","click","",true);
				//driver.findElement(By.id(OR.getProperty("OTP"))).click();
				Thread.sleep(35000);
				System.out.println("OTP------------");
				performNeccessaryAction(driver,OR.getProperty("subBtn"),"xpath","click","",true);
				//driver.findElement(By.xpath(OR.getProperty("subBtn"))).click();
				}
			
			
			
			
			
				}
				
		
		}

		//driver.findElement(By.xpath(OR.getProperty("subBtn"))).click();

		
		
	
	
	
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
	
	public static void performNeccessaryAction(WebDriver driver, String path,String type, String action, String val,boolean isContinue)
	{
		boolean isPresent= elementPresent(driver,path);
		if(isPresent)
		{
		if(action.equals("click") )
		{
			if(type.equals("xpath"))
			{
			driver.findElement(By.xpath(path)).click();
			}
			else if(type.equals("id"))
			{
			driver.findElement(By.id(path)).click();
			}
			else if(type.equals("name"))
			{
			driver.findElement(By.name(path)).click();
		}
		}
		else if(action.equals("sendkeys"))
		{
			if(type.equals("xpath"))
			{
			driver.findElement(By.xpath(path)).sendKeys(val);
			}
			
			else if(type.equals("id"))
			{
			driver.findElement(By.id(path)).sendKeys(val);
			}
			else if(type.equals("name"))
			{
				driver.findElement(By.name(path)).sendKeys(val);
			}
			
		}
		
		
		
		else
		{
			System.out.println("Action can not be perform");
		}
		}
		else
		{
			System.out.println("element not present: " + path);
			if(!isContinue)
			{
				return;
			}
			
		}
		
	}
	
	}
	

