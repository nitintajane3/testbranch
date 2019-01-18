package com.test.cnqaos.testcases;

import java.io.IOException;


import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cnqaos.pages.HomePage;
import com.cnqaos.pages.LoginPage;
import com.cnqaos.testbase.TestBase;
import com.cnqaos.utils.TestUtils;

public class LoginTest extends TestBase
{
	String sheetname = "Sheet1";
	String filepath = "/src/main/java/com/cnqaos/testdata/logindata.xlsx";
	LoginPage loginPage;
	HomePage homePage ;
	String expectedhomepagetitle = "Home Page";

	public LoginTest() throws IOException 
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	@BeforeMethod
	public void  loginSetUp() throws IOException 
	{
		// TODO Auto-generated method stub
		initializebrowser();
	}
	
	@Test(priority = 1, dataProvider = "LoginData")
	public void validLoginTestCaseOne(String username, String password) throws IOException, InterruptedException
	{
		loginPage = new LoginPage();
		homePage = new HomePage();
		
		loginPage.clickOnLoginButton(username,password);
		
		try 
		{
			String actualhomepagetitle = homePage.verifyHomePageTitle();
			
			Assert.assertEquals(actualhomepagetitle, expectedhomepagetitle, "home page not found");
				
		} catch (Exception e) 
		{
			
			Alert alert = driver.switchTo().alert();
			String alerttext = alert.getText();
			System.out.println(alerttext);
			
			alert.accept();
			
		}
				
	}
	
	
	@AfterMethod
	public void closedBrowser() 
	{
		
		driver.quit();
		// TODO Auto-generated method stub

	}
	
	
	@DataProvider
	public  Object[][] LoginData() throws IOException
	{
		 Object objects[][] = TestUtils.getTestData(sheetname,filepath);
		 return objects;
	}

}
