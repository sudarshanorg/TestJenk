package org.EZCLM;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Ezclmlogin

{
	static WebDriver driver;

	WebElement element;
	static String quote[] = { 
			"6913132",
			"6913133",
			"6913134",
			"6913135"};

	public static void main(String args[]) throws InterruptedException

	{
		Ezclmlogin dec = new Ezclmlogin();
		String quote1 = null;
		dec.login(driver, quote1);

	}

	public WebDriver login(WebDriver driver, String quote1) throws InterruptedException {

		for (String quote11 : quote) 
		{

			System.setProperty("webdriver.ie.driver",
					"\\\\hiscox.nonprod\\profiles\\Citrix\\XDRedirect\\kannans\\Desktop\\Softwares\\Selenium\\drivers\\IEDriverServer.exe");
			DesiredCapabilities caps;

			caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			driver = new InternetExplorerDriver(caps);
			driver.manage().window().maximize();

			driver.get("http://ezclmhiscoxuat.sutherlandglobal.com:8080/");
			System.out.println(driver.getTitle());
			driver.findElement(By.id("ctrltxtvchusername")).sendKeys("CZUser1");
			driver.findElement(By.id("ctrltxtvchpassword")).sendKeys("Usdc-123");
			driver.findElement(By.id("imglogin")).click();
			Thread.sleep(10000);
			for (String w_handle : driver.getWindowHandles()) {
	            driver.switchTo().window(w_handle);
	            Thread.sleep(12000);
	            WebElement Search = driver.findElement(By.xpath(".//*[@class='dhx_tabbar_row']/div/div[3]/div[contains(text(),'Search')]"));
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            for (int iCnt = 0; iCnt < 2; iCnt++) {
	                //Execute javascript
	                js.executeScript("arguments[0].style.border='4px groove green'", Search);
	                Thread.sleep(1000);
	                js.executeScript("arguments[0].style.border=''", Search);
	            }
	            Thread.sleep(3000);
	            Actions action = new Actions(driver);
	            action.moveToElement(Search).click().perform();
	            break;
	            
	        }
			driver.switchTo().frame(1);
//	        driver.findElement(radio_policy).click();
	        Thread.sleep(3000);
	        driver.findElement(By.id("txtPolicyNo")).sendKeys(quote11);
	        driver.findElement(By.id("btnFind")).click();
	        Thread.sleep(10000);
	        element = driver.findElement(By.xpath(".//*[@class='objbox']/div/table/tbody/tr[2]/td[14][contains(text(),'Referred')]"));
	        //element = driver.findElement(By.xpath(".//*[@class='objbox']/div/table/tbody/tr[2]/td[14][contains(text(),'Declined')]"));
	        Actions action2 = new Actions(driver);
	        action2.doubleClick(element).perform();
	        Thread.sleep(3000);
	        driver.switchTo().defaultContent();
	        Thread.sleep(5000);
	        if (driver.findElement( By.xpath("//img[@src='../../../images/OrderClose.gif']")).isDisplayed()) {
	            driver.findElement( By.xpath("//img[@src='../../../images/OrderClose.gif']")).click();
	        }
	        driver.switchTo().defaultContent();
	        Thread.sleep(3000);
	        if (driver.findElement(By.xpath("//center/div/img[@src='../../../images/OrderClose.gif']")).isDisplayed()) {
	            driver.findElement(By.xpath("//center/div/img[@src='../../../images/OrderClose.gif']")).click();
	        }
	        driver.switchTo().defaultContent();
	        driver.switchTo().frame(3);
	        Thread.sleep(3000);
	        WebElement element1 = driver.findElement(By.xpath(".//*[@class='dhx_tabcontent_sub_zone']//following-sibling::td[contains(text(),'Test ProducerAgentID')]"));
	        action2.doubleClick(element1).perform();
	        Thread.sleep(6000);
	        File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	        try {
	            
	            //String targetPath = "C:\\Users\\kannans\\IdeaProjects\\git\\api-automation-testing\\nsl-automation\\target\\results\\referral";
	        	String targetPath = "C:\\Users\\kannans\\IdeaProjects\\git\\api-automation-testing\\nsl-automation\\target\\results\\referral_";
	        	//String targetPath = "C:\\Users\\kannans\\IdeaProjects\\git\\api-automation-testing\\nsl-automation\\target\\results\\Decline_";
	            FileUtils.copyFile(scr, new File(targetPath + quote11 + "_SS.jpg"));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			driver.quit();
					}
		return driver;
	}
}