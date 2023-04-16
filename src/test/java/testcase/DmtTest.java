package testcase;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;

public class DmtTest extends TestBase {
	
	
	
	
	@Test(priority=2)
	public void AremitSenderRegistration()
	{
		driver.findElement(By.xpath(OR.getProperty("DMTLink"))).click();
		List<WebElement> dmtDropDown=driver.findElements(By.xpath(OR.getProperty("DMTDropdowns")));
		System.out.println("----------------------------");
		for(int i=0; i<dmtDropDown.size();i++)
		{
			System.out.println("0000000000000000000");
			//System.out.println(dmtDropDown.get(i).getText());
			if(dmtDropDown.get(i).getText().equals("AremitLink"))
			{
				System.out.println("1111111111111111111111111111111");
				String option=dmtDropDown.get(i).getText();
				System.out.println("Option is : "+ option);
				dmtDropDown.get(i).click();
			}
		}
		
		
	}
	/*
	@DataProvider
	public Object[][] getData()
	{
		String sheetName="DmtTest";
		int rows=excel.getRowCount(sheetName);
		int cols= excel.getColumnCount(sheetName);
		
		Object[][] data= new Object[rows-1][cols];
		
		for(int rowNum=2; rowNum<rows;rowNum++)
		{
			for(int colNum=0;colNum<cols;colNum++)
			{
			data [rowNum-2][colNum]= excel.getCellData(sheetName,colNum,rowNum);
		}
		}
		return data;
		}
	*/	
		
	}


