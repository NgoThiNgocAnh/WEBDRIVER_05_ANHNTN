package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.lang.Thread;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_02_Xpath_Css {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_LoginWithPassEmpty()
	{
		driver.get("http://live.guru99.com/index.php");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("send2")).click();
		String usernameEmptyMessage = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(usernameEmptyMessage, "This is a required field.");
		String passwordEmptyMessage = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(passwordEmptyMessage, "This is a required field.");
	}

	//Test Script 03: Login with Email invalid
	//Step 01 - Truy cập vào trang: http://live.guru99.com/
	//Step 02 - Click vào link "My Account" để tới trang đăng nhập
	//Step 03 - Nhập email invalid: 123434234@12312.123123
	//Step 04 - Click Login button
	//Step 05 - Verify error message xuất hiện:  Please enter a valid email address. For example johndoe@domain.com.
	
	@Test
	public void TC_02_LoginWithEmailInvalid()
	{
		driver.get("http://live.guru99.com/index.php");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.cssSelector("#email")).sendKeys("123434234@12312.123123");
		driver.findElement(By.id("send2")).click();
		String usernameInvalidMessage = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(usernameInvalidMessage, "Please enter a valid email address. For example johndoe@domain.com.");		
		

	}
	
	//	Test Script 04: Login with Password incorrect
	//	Step 01 - Truy cập vào trang: http://live.guru99.com/
	//	Step 02 - Click vào link "My Account" để tới trang đăng nhập
	//	Step 03 - Nhập email correct and password incorrect: automation@gmail.com/ 123
	//	Step 04 - Click Login button
	//	Step 05 - Verify error message xuất hiện: Please enter 6 or more characters without leading or trailing spaces.
	
	@Test
	public void TC_03_LoginWithPassLessThan6Character()
	{
		driver.get("http://live.guru99.com/index.php");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.cssSelector("#pass")).sendKeys("123");
		driver.findElement(By.id("send2")).click();
		String passwordincorrectMessage = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(passwordincorrectMessage, "Please enter 6 or more characters without leading or trailing spaces.");				

	}
//	Step 01 - Truy cập vào trang: http://live.guru99.com/
//		Step 02 - Click vào link "My Account" để tới trang đăng nhập
//		Step 03 - Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản
//		Step 04 - Nhập thông tin hợp lệ vào tất cả các field: First Name/ Last Name/ Email Address/ Password/ Confirm Password
//		(Lưu ý: Tạo random cho dữ liệu tại field Email Address)
//		Step 05 - Click REGISTER button
//		Step 05 - Verify message xuất hiện khi đăng kí thành công: Thank you for registering with Main Website Store.
//		Step 06 - Logout khỏi hệ thống
//		Step 07 - Kiểm tra hệ thống navigate về Home page sau khi logout thành công
	@Test
	public void TC_04_CreatAnAccount() throws InterruptedException
	{
		driver.get("http://live.guru99.com/index.php");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		driver.findElement(By.cssSelector("#firstname")).sendKeys("abc");
		driver.findElement(By.cssSelector("#middlename")).sendKeys("def");
		driver.findElement(By.cssSelector("#lastname")).sendKeys("hap");
		driver.findElement(By.cssSelector("#email_address")).sendKeys("automation" + randomEmail() + "@gmail.com");
		driver.findElement(By.cssSelector("#password")).sendKeys("123456");
		driver.findElement(By.cssSelector("#confirmation")).sendKeys("123456");
		driver.findElement(By.xpath("//label[@for='is_subscribed']")).click();
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		String registerSuccessMessage = driver.findElement(By.xpath("//li[@class='success-msg']")).getText();
		Assert.assertEquals(registerSuccessMessage, "Thank you for registering with Main Website Store.");
		driver.findElement(By.xpath("//a/span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		Thread.sleep(10000);
		String titleHomePage = driver.getTitle();
		Assert.assertEquals(titleHomePage, "Home page");
		
		
		
		
		
	}
	@AfterClass
	public void afterClass() {}
	public int randomEmail() {
		Random random = new Random();
		int number = random.nextInt(999999);
		return number; 
	}
	

}
