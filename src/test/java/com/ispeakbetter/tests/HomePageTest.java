package com.ispeakbetter.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ispeakbetter.base.BasePage;
import com.ispeakbetter.pages.HomePage;



public class HomePageTest {

	WebDriver driver;
	Properties prop;
	BasePage basePage;
	HomePage homePage;
	
	
	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		driver.get(prop.getProperty("url"));
		homePage = new HomePage(driver);								
	}
	
	@Test(priority=1, enabled=true, description="This method verifies chat box header")
	public void verifyChatHeaderTest() throws InterruptedException {
		homePage.verifyChatHeader();
	}
	
	@Test(priority=2, description="This method verifies home page header")
	public void verifyHomePageTitleHeader() {
		homePage.getHomePageTitleHeader();
	}
	
	@Test(priority=3, enabled=true, description="Login test is using invalid username and invalid password.")
	public void verifyInvalidUserTest() throws InterruptedException {
		homePage.verifyInvalidUser();
	}
	
	@AfterTest
	public void tearDown() {
		basePage.quitBrowser();
	}
	
	
}
