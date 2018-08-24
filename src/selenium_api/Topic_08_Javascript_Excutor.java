package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_08_Javascript_Excutor {
	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_JE() {
		openAnyURLByIE("http://live.guru99.com/");
		String homePageDomain = (String) executeJSForWebBrowser("return document.domain;");
		Assert.assertEquals(homePageDomain, "live.guru99.com");
		String homePageURL = (String) executeJSForWebBrowser("return document.URL;");
		Assert.assertEquals(homePageURL, "http://live.guru99.com/");
		WebElement mobileLink = driver.findElement(By.xpath("//a[text()='Mobile']"));
		highlightElement(mobileLink);
		clickAnElementByJS(mobileLink);
		WebElement addToCart = driver.findElement(By.xpath("//h2[a[@title='Samsung Galaxy']]/following-sibling::div[@class='actions']/button"));
		highlightElement(addToCart);
		clickAnElementByJS(addToCart);
		// verify text bang JS
		String getTextAddToCart = (String) executeJSForWebBrowser("return document.documentElement.innerText;");
		Assert.assertTrue(getTextAddToCart.contains("Samsung Galaxy was added to your shopping cart."));
		WebElement privacyPolicyLink = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
		highlightElement(privacyPolicyLink);
		clickAnElementByJS(privacyPolicyLink);
		// verify title
		String titlePrivacy = (String) executeJSForWebBrowser("return document.title;");
		Assert.assertEquals(titlePrivacy, "Privacy Policy");
		ScrollToBottomPage();
		WebElement wishlistContent = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']"));
		Assert.assertTrue(wishlistContent.isDisplayed());
		// navigate to diffirent domain
		openAnyURLByIE(" http://demo.guru99.com/v4/");
		// verify domain
		String guruDomain = (String) executeJSForWebBrowser("return document.domain;");
		Assert.assertEquals(guruDomain, "demo.guru99.com");

	}

	@Test
	public void TC_02_Remove_Attribute() {
		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		String firstName ="Automation", lastName = "Testing"; 
		WebElement firstNameIframe = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
		driver.switchTo().frame(firstNameIframe);
		driver.findElement(By.xpath("//input[@name='fname']")).sendKeys(firstName);
		WebElement lastNameElement = driver.findElement(By.xpath("//input[@name='lname']"));
		RemoveAttribute(lastNameElement,"disabled");
		lastNameElement.sendKeys(lastName);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		String messageSucess = driver.findElement(By.xpath("//h2[text()='Your input was received as:']/following-sibling::div[@class='w3-container w3-large w3-border']")).getText();
		Assert.assertTrue(messageSucess.contains(firstName) && messageSucess.contains(lastName));
		
	}

	@AfterTest

	public void afterTest() {
//		driver.quit();
	}

	public Object openAnyURLByIE(String URL) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.location = '" + URL + "'");
	}

	public Object executeJSForWebBrowser(String javaSript) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(javaSript);
	}

	public Object clickAnElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].click();", element);
	}

	public Object ScrollToBottomPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

	}
	public Object highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].style.border='2px groove green'", element);
	}
	public Object RemoveAttribute(WebElement element, String attribute) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
	}
}
