package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

@Test
public class Topic_05_HandleButton {
	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

//	public void TC_01() {
//		driver.get("http://live.guru99.com/");
//		clickElementByJavascript("//div[@class='footer']//a[@title='My Account']");
//		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='account-login']")).isDisplayed());
//		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
//	}
//
//	public void TC_02() {
//		driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
//
//		// clickElementByJavascript("//label[text()='Dual-zone air conditioning']");
//		// driver.findElement(By.xpath("//label[text()='Dual-zone air
//		// conditioning']")).click();
//		// Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air
//		// conditioning']")).isSelected());
//
//		// clickElementByJavascript("//label[text()='Dual-zone air
//		// conditioning']/preceding-sibling::input");
//		// driver.findElement(By.xpath("//label[text()='Dual-zone air
//		// conditioning']/preceding-sibling::input")).click();
//		// Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air
//		// conditioning']/preceding-sibling::input")).isSelected());
//		String dualZoneCheckBox = "//label[text()='Dual-zone air conditioning']/preceding-sibling::input";
//		clickElementByJavascript(dualZoneCheckBox);
//		Assert.assertTrue(isElementSelected(dualZoneCheckBox));
//		unCheckTheCheckBox(dualZoneCheckBox);
//		Assert.assertFalse(isElementSelected(dualZoneCheckBox));
//	}
//
//	public void TC_03() {
//		driver.get("https://demos.telerik.com/kendo-ui/styling/radios");
//
//		String petrolRadioButton = "//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input";
//		clickElementByJavascript(petrolRadioButton);
//		Assert.assertTrue(isElementSelected(petrolRadioButton));
//		checkRadioButtonSelected(petrolRadioButton);
//	}
//
//	public void TC_04() {
//		driver.get("http://daominhdam.890m.com/#");
//		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
//		Alert alert = driver.switchTo().alert();
//		String alertJSMessage = alert.getText();
//		Assert.assertTrue(alertJSMessage.equals("I am a JS Alert"));
//
//		alert.accept();
//		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),
//				"You clicked an alert successfully");
//
//	}
//
//
//	public void TC_05() {
//		driver.get("http://daominhdam.890m.com/#");
//		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
//		Alert confirm = driver.switchTo().alert();
//		String confirmJS = confirm.getText();
//		Assert.assertTrue(confirmJS.equals("I am a JS Confirm"));
//		confirm.dismiss();
//		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
//
//	}
@Test
	public void TC_06() {
		driver.get("http://daominhdam.890m.com");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		Alert prompt = driver.switchTo().alert();
		String PromptJS = prompt.getText();
		Assert.assertTrue(PromptJS.equals("I am a JS prompt"));
		prompt.sendKeys("automationtest");
		prompt.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: automationtest");
		
		
	}
	@AfterTest
	public void afterTest() {
//		driver.quit();
	}

	public void clickElementByJavascript(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", element);
	}

	public boolean isElementSelected(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}

	public void unCheckTheCheckBox(String locator) {
		if (isElementSelected(locator)) {
			clickElementByJavascript(locator);
		}
	}

	public void checkRadioButtonSelected(String locator) {
		if (!isElementSelected(locator)) {
			clickElementByJavascript(locator);
		}
	}

}
