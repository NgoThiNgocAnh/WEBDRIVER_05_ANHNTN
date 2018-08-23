package selenium_api;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class Topic_07_Frame_Iframe {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_HandleIframe() {
		driver.get("https://www.hdfcbank.com/");
		// step 01-02 handle notification iframe
		List<WebElement> notificationIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
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

		// step 05
		WebElement flipBanner = driver.findElement(By.xpath("//div[@class='flipBanner']"));
		Assert.assertTrue(flipBanner.isDisplayed());
		List<WebElement> flipBannerImage = driver
				.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		int flipBannerImageNumber = flipBannerImage.size();
		Assert.assertEquals(flipBannerImageNumber, 8);
		int i = 0;
		for (WebElement image : flipBannerImage) {
			Assert.assertTrue(image.isDisplayed());
			i++;
			System.out.println("Image [" + i + "] displayed!");
		}
	}


	public void TC_02_HandleWindown() {
		driver.get("http://daominhdam.890m.com/");
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		// switch qua window
		// case 1 chỉ mở thêm 1tab hoặc 1 window
		// get ra GUID của page hiện tại
		String parentGUID = driver.getWindowHandle();
		System.out.println("Title before: " + driver.getTitle());
		// case2 mowr >= 1 tab hoac 1 window

		// switchToChildWindowByGUID(parentGUID);
		switchToWindowByTitle("Google");
		// verify title
		String googleTitle = driver.getTitle();
		Assert.assertEquals(googleTitle, "Google");
		System.out.println("Title after:" + googleTitle);
		closeAllWithoutParentWindows(parentGUID);

	}
	@Test
	public void TC_03_Bank_HandleWindow() {
		driver.get("https://www.hdfcbank.com/");
		//get GUID cua parent window
		String parentGUID = driver.getWindowHandle();
		//step 1: tat popup 
		List<WebElement> notificationIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		System.out.println("number element: " + notificationIframe.size());
		if(notificationIframe.size() > 0) {
			//switch qua iframe
			driver.switchTo().frame(notificationIframe.get(0));
			driver.findElement(By.xpath("//div[@id='div-close']")).click();
			System.out.println("Closed popup ");
			//switch về dom
			driver.switchTo().defaultContent();
		} 
		//step 2: switch qua tab moi mo
		driver.findElement(By.xpath("//a[text()='Agri']")).click();
		//verify tab moi
		switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
		String titleAgri = driver.getTitle();
		Assert.assertEquals(titleAgri, "HDFC Bank Kisan Dhan Vikas e-Kendra");
		//click account details 
		driver.findElement(By.xpath("//p[text()='Account Details']")).click();
		switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
		String titleAccount = driver.getTitle();
		Assert.assertEquals(titleAccount, "Welcome to HDFC Bank NetBanking");
		//switch qua frame
		WebElement privacyFrame = driver.findElement(By.xpath("//frame[@name='footer']"));
		driver.switchTo().frame(privacyFrame);
		driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
		//switch window privacy
		switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		String titlePrivacy = driver.getTitle();
		Assert.assertEquals(titlePrivacy, "HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		driver.findElement(By.xpath("//a[text()='CSR']")).click();
		//verify title CSR
		String titleCSR = driver.getTitle();
		Assert.assertEquals(titleCSR, "HDFC BANK - CSR - Homepage");
		//tắt tất cả các window chỉ để lại parent window
		
		closeAllWithoutParentWindows(parentGUID);
	}

	@AfterTest

	public void afterTest() {
//		driver.quit();
	}

	public void switchToChildWindowByGUID(String parent) {
		// get tất cả các GUID của tất cả các window hoặc các tab
		Set<String> allWindows = driver.getWindowHandles();
		// duyệt qua tất cả các window hoặc các tab
		for (String runWindow : allWindows) {
			// nếu window//tabID nào mà khác vs parentID thì switch qua
			if (!runWindow.equals(parent)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(String title) {
		// get tất cả các title của tất cả các window hoặc tab
		Set<String> allWindows = driver.getWindowHandles();
		// duyệt qua từng window
		for (String runWindows : allWindows) {
			// switch qua lần lượt các window hoặc tab
			driver.switchTo().window(runWindows);
			// get lần lượt title của các window hoặc tab
			String currentWin = driver.getTitle();
			// kiểm tra title truyền vào với title vừa get
			if (currentWin.equals(title)) {
				break;
			}
		}
	}
    public boolean closeAllWithoutParentWindows(String parentWindow) {
    	//get tất cả các GUID của tất cả các window hoặc tab
        Set<String> allWindows = driver.getWindowHandles();
        //duyệt qua tất cả các window hoặc tab
        for (String runWindows : allWindows) {
        	//kiểm tra GUID nếu khác với GUID của parent window thì closed window đó
                    if (!runWindows.equals(parentWindow)) {
                                driver.switchTo().window(runWindows);
                                driver.close();
                    }
        }
        //switch về parent window
        driver.switchTo().window(parentWindow);
        //kiểm tra xem có switch về đúng parent window không
        if (driver.getWindowHandles().size() == 1)
                   return true;
        else
                   return false;
}
}
