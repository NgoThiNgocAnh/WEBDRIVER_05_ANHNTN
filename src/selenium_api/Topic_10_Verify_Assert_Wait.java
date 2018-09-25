package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class Topic_10_Verify_Assert_Wait {
	WebDriver driver;
	WebDriverWait waitExplicit;

	@BeforeMethod
	public void beforeTest() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_Implicit_Wait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		// wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='finish']/h4")));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}

	
	public void TC_02_Explicit_Wait() {
		driver.get(
				"http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		// Step 02 - Wait cho "Date Time Picker" được hiển thị (sử dụng: presence
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='contentWrapper']")));
		// Step 02 - Wait cho "Date Time Picker" được hiển thị (sử dụng: visibility)
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='contentWrapper']")));

		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='raDiv']")));
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(),
				"No Selected Dates to display.");

		// Step 04 - Chọn ngày hiện tại
		driver.findElement(By.xpath("//a[text()='25']/parent::td")).click();

		// Step 05 - Wait cho đến khi "loader ajax" không còn visible (sử dụng:
		// invisibility)
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));

		// Step 06 - Wait cho selected date = 23 được visible ((sử dụng: visibility)
		waitExplicit.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(@class,'rcSelected')]//a[text()='25']")));

		// Step 07 - Verify ngày đã chọn
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(),
				"Tuesday, September 25, 2018");

	}


	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
