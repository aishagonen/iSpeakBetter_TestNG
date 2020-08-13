package com.ispeakbetter.pages;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.ispeakbetter.util.Constants;
import com.ispeakbetter.util.ElementUtil;
import com.ispeakbetter.util.JavaScriptUtil;


public class HomePage {
	
	WebDriver driver;
	WebElement element;
	Properties prop;
	ElementUtil elementUtil;
	JavaScriptUtil javaScriptUtil;
	
	By title = By.tagName("title");
	By header = By.className("rsp");
	By homeLoginBtn = By.id("cmdSiginLink");
	By email = By.id("_email");
	By password = By.id("_password");
	By loginBtn = By.id("cmdSignin");
	By errorMsg = By.id("crendentialsError");
	By chat = By.id("zsiq_agtpic");
	By chatHeader = By.xpath("//div[@id='attname']");
	By closeChat = By.xpath("//div[contains(@class,'win_close sqico-larrow')]");				
	By iframe = By.id("siqiframe");

	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		javaScriptUtil = new JavaScriptUtil(driver);	
	}
	
	public void getHomePageTitleHeader() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String title = driver.getTitle();
		System.out.println("Page title is: "+ title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE, "Title is not correct");
		
		String headerText = elementUtil.doGetText(header);
		System.out.println("Page header is: "+ headerText);
		Assert.assertEquals(headerText, Constants.HOME_PAGE_HEADER, "Header is not correct");
	}
	
	public void verifyInvalidUser() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		element = driver.findElement(homeLoginBtn);
		javaScriptUtil.clickElementByJS(element);
		
		elementUtil.doSendKeys(email, "kralshakir@gmail.com");
		elementUtil.doSendKeys(password, "lompen1");
		
		element = driver.findElement(loginBtn);
		javaScriptUtil.clickElementByJS(element);
		
		elementUtil.waitForElementVisible(errorMsg);
		String msg = elementUtil.doGetText(errorMsg);
		System.out.println(msg);
	}
	
	public void verifyChatHeader() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
				
		element = driver.findElement(By.id("zsiq_agtpic"));
		javaScriptUtil.clickElementByJS(element);
		Thread.sleep(3000);
		driver.switchTo().frame(driver.findElement(By.id("siqiframe")));
		System.out.println("I am in the frame");
		Thread.sleep(3000);
		String header = driver.findElement(By.id("attname")).getText();
		System.out.println("Chat box header is: "+ header);
		Thread.sleep(3000);
		element = driver.findElement(By.xpath("//div[contains(@class,'win_close sqico-larrow')]"));
		javaScriptUtil.clickElementByJS(element);

		Assert.assertEquals(header, Constants.CHAT_BOX_HEADER, "Chat box header is incorrect");	
		driver.switchTo().defaultContent();
	}
	
}





