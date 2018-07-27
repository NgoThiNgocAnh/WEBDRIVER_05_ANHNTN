package selenium_api;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_03_WebDriver_Browser {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
//		driver = new FirefoxDriver();
//		driver.manage().window().maximize();
//		driver.get("http://daominhdam.890m.com/");
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String exePath = "D:\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	//		Step 01 - Truy cập vào trang: http://daominhdam.890m.com/
	//		Step 02 - Kiểm tra các phần tử sau hiển thị trên trang: Email/ Age (Under 18)/ Education
	//		Step 03 - Nếu có nhập giá trị: Automation Testing vào 2 field Email/ Education và chọn Age = Under 18
	@Test
	public void TC_01_IsDisplayed() throws InterruptedException {
		  WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));
		  WebElement ageRadio = driver.findElement(By.xpath("//input[@id='under_18']"));
		  WebElement educationTextArea = driver.findElement(By.xpath("//textarea[@id='edu']"));
		  
		  Assert.assertTrue(isControlDisplayed(emailTextbox));
		  Assert.assertTrue(isControlDisplayed(ageRadio));
		  Assert.assertTrue(isControlDisplayed(educationTextArea));
		  
		  if(isControlDisplayed(emailTextbox)&&isControlDisplayed(educationTextArea))
		  {
			  emailTextbox.sendKeys("Automation Testing");
			  ageRadio.click();
			  educationTextArea.sendKeys("Automation Testing");
		  }
		  Thread.sleep(5000);
	  }
	public boolean isControlDisplayed(WebElement element) {
		  return element.isDisplayed();
	  }
  
	//  Test Script 02: Kiểm tra phần tử enable/ disable trên trang
	//  Step 01 - Truy cập vào trang: http://daominhdam.890m.com/
	//  Step 02 - Kiểm tra các phần tử sau enable trên trang: Email/ Age (Under 18)/ Education/ Job Role 01/ Interests (Development)/ Slider 01/ Button is enabled
	//  Step 03 - Kiểm tra các phần tử sau disable trên trang: Password / Age (Radiobutton is disabled)/ Biography/ Job Role 02/ Interests (Checkbox is disabled)/ Slider 02/ Button is disabled
	//  Step 04 - Nếu có in ra Element is enabled/ ngược lại Element is disabled
  
	@Test
	public void TC_02_IsEnable() {
		WebElement emailTextBox = driver.findElement(By.xpath("//input[@id='mail']"));
		WebElement ageRadio = driver.findElement(By.xpath("//input[@id='under_18']"));
		WebElement educationTextArea = driver.findElement(By.xpath("//textarea[@id='edu']"));
		WebElement JobRole01Select = driver.findElement(By.xpath("//select[@id='job1']"));
		WebElement Interests = driver.findElement(By.xpath("//input[@id='development']"));
		WebElement Slider01 = driver.findElement(By.xpath("//input[@id='slider-1']"));
		WebElement ButtonIsEnable = driver.findElement(By.xpath("//button[@id='button-enabled']"));
		
		WebElement Password = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement AgeRadioButton = driver.findElement(By.xpath("//input[@id='radio-disabled']"));
		WebElement JobRole02 = driver.findElement(By.xpath("//select[@id='job2']"));
		WebElement InterestsCheckBox = driver.findElement(By.xpath("//select[@id='job2']"));
		WebElement Slider02 = driver.findElement(By.xpath("//input[@id='slider-2']"));
		WebElement ButtonIsDisable = driver.findElement(By.xpath("//button[@id='button-enabled']"));
		WebElement BiographyTextArea = driver.findElement(By.xpath("//textarea[@id='bio']"));
		 
		isControlEnable(emailTextBox);
		isControlEnable(ageRadio);
		isControlEnable(educationTextArea);
		isControlEnable(JobRole01Select);
		isControlEnable(Interests);
		isControlEnable(Slider01);
		isControlEnable(ButtonIsEnable);
		isControlEnable(Password);
		isControlEnable(AgeRadioButton);
		isControlEnable(JobRole02);
		isControlEnable(InterestsCheckBox);
		isControlEnable(Slider02);
		isControlEnable(ButtonIsDisable);
		isControlEnable(BiographyTextArea);
	
	}

	public void isControlEnable(WebElement element) {
		if(element.isEnabled()) {
			System.out.println("Element is enabled");
		}else {
			System.out.println("Element is disabled");
		}
	}

	//Test Script 03: Kiểm tra phần tử được chọn trên trang
	//Step 01 - Truy cập vào trang: http://daominhdam.890m.com/
	//Step 02 - Click chọn Age (Under 18)/ Interests (Development)
	//Step 03 - Kiểm tra các phần tử tại Step 02 đã được chọn
	//Step 04 - Nếu chưa được chọn thì cho phép chọn lại 1 lần nữa
	@Test
	public void TC_03_CheckRadio() throws InterruptedException {
		 String element = "//*[@id='development']";
		 String element1 = "//*[@id='under_18']";
		 driver.findElement(By.xpath(element)).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath(element1)).click();
		 Thread.sleep(2000);
		 if(isElementSelected(driver, element)){
		 System.out.println("'Development' checkbox is selected");
		 } else{
		 System.out.println("'Development' checkbox isn't selected");
		 driver.findElement(By.xpath(element)).click();
		 }
		 if(isElementSelected(driver, element1)){
		 System.out.println("'Under 18' radio button is selected");
		 } else{
		 System.out.println("'Under 18' radio button isn't selected");
		 driver.findElement(By.xpath(element1)).click();
		 }
		 Thread.sleep(5000);
	}
		
	public boolean isElementSelected(WebDriver driver, String yourLocator) {
		 try {
		 WebElement locator;
		 locator = driver.findElement(By.xpath(yourLocator));
		 return locator.isSelected();
		 } catch (NoSuchElementException e) {
		 return false;
		 }
		 }
	@AfterClass
	public void afterClass() {
//	driver.quit();	
	}
	
	
	
}
