package selenium_api;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

@Test
public class Topic_07_Frame_Iframe {

	WebDriver driver;
	WebDriverWait wait;
	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_HandleIframe() {
		driver.get("https://www.hdfcbank.com/");
		// step 01-02 handle notification iframe
		List<WebElement> notificationIframe = driver
				.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		System.out.println("number element: " + notificationIframe.size());
		if (notificationIframe.size() > 0) {
			driver.switchTo().frame(notificationIframe.get(0));
			WebElement close = driver.findElement(By.xpath("//div[@id='div-close']"));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", close);
			System.out.println("Closed popup!");
			// switch to top windowns
			driver.switchTo().defaultContent();
		}
		// step 03
		// verify text
		WebElement textIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
		driver.switchTo().frame(textIframe);
		String getTextIframe = driver.findElement(By.xpath("//span[@id='messageText']")).getText();
		Assert.assertEquals("What are you looking for?", getTextIframe);
		System.out.println(getTextIframe);
		driver.switchTo().defaultContent();
		// step 04
		
		WebElement imageIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
		driver.switchTo().frame(imageIframe);
		//
		By bannerImageXpath = By.xpath("//div[@id='productcontainer']//img");
		List<WebElement> bannerImages = driver.findElements(bannerImageXpath);
		
		int bannerImageNumber = bannerImages.size();
		Assert.assertEquals(bannerImageNumber, 6);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(bannerImageXpath));
		driver.switchTo().defaultContent();
		
		//step 05 
		WebElement flipBanner = driver.findElement(By.xpath("//div[@class='flipBanner']"));
		Assert.assertTrue(flipBanner.isDisplayed());
		List<WebElement> flipBannerImage = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		int flipBannerImageNumber = flipBannerImage.size();
		Assert.assertEquals(flipBannerImageNumber, 8);
		int i = 0;
		for(WebElement image : flipBannerImage) {
			Assert.assertTrue(image.isDisplayed());
			i++;
			System.out.println("Image [" + i + "] displayed!");
		}
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
