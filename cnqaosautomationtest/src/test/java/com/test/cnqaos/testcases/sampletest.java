package com.test.cnqaos.testcases;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;

import com.cnqaos.testbase.TestBase;
import com.sun.org.apache.bcel.internal.generic.NEW;

import freemarker.template.utility.NormalizeNewlines;


public class sampletest extends TestBase
{
	String file = "";
	String sorcedirectory = System.getProperty("user.dir");
	String sheetname = "Sheet1";
	public sampletest() throws IOException 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public void testrequ() throws IOException 
	{
	
		initializebrowser();
		
		
		List<WebElement> elements = driver.findElements(By.tagName("a"));
		System.out.println(elements.size());
		List<String> elements2 = new ArrayList<String>();
		for(WebElement ess : elements) 
		{
			try {
				elements2.add(ess.getText());
				elements.get(115).click();
							
			} catch (Exception e) {
				// TODO: handle exception
			}  
		}	
		for(String st : elements2)
		{
			System.out.println("element text = " + st);
		
		}
	
		}
	
	
	
	
	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		
		
        sampletest sample = new sampletest();
        sample.testrequ();
	}
	
	
	@DataProvider
	public Object[][] dataprovider() throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		BufferedInputStream bStream = new BufferedInputStream(new FileInputStream(sorcedirectory+ file));
		
		Workbook workbook = WorkbookFactory.create(bStream);
		
		Sheet sheet = workbook.getSheet(sheetname);
		
		int getlastrow = sheet.getLastRowNum();
		int getlastcoumn = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[getlastrow][getlastcoumn];
		
		for(int i=0;i<getlastrow;i++)
		{
			Row row = sheet.getRow(i+1);
			
			for(int j=0;j<getlastcoumn;j++)
			{
				Cell cell = row.getCell(j);
				
				String valkues;
				
				
				
				try 
				{
					valkues = cell.getRichStringCellValue().toString();
				} catch (Exception e) 
				{
					valkues = ((XSSFCell)cell).getRawValue();
				}
				data[i][j]=valkues;
			}
		}
		
		
		return data;
	}

	
	public void takescreensot() throws IOException 
	{
		File fileutis = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String srocefile = System.getProperty("user.dir");
		String filename = new SimpleDateFormat("dd_mm_yy").format(new  Date());
		FileUtils.copyFile(fileutis, new File(srocefile + "/screenshot/" + filename + ".png"));
		
		
	}
	
	public void failscreeshot(WebDriver driver, ITestResult result) 
	{

		if(ITestResult.FAILURE==result.getStatus())
		{
			try {
				
				
				File filename = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				String srcfile = System.getProperty("user.dir");
				
				String filepath = srcfile + "/screenshot" + ".png" ;
				
				FileUtils.copyFile(filename, new File(filepath));
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
	
	
}
