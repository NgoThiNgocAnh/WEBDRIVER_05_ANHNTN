package selenium_api;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;

public class Topic_09_UploadFile {
	WebDriver driver;
	String PropertyDirectory = System.getProperty("user.dir");
	String fileName = "10.jpg";
	String uploadFile = PropertyDirectory + "\\images\\" + fileName;
	String chromeUpload = PropertyDirectory + "\\upload\\chrome.exe";
	String fireFoxUpload = PropertyDirectory + "\\upload\\firefox.exe";
	String IeUpload = PropertyDirectory + "\\upload\\ie.exe";
	String subFolder = "abc" + random();
	String email = "abc" + random() + "@gmail.com";
	String fristName = "abc" + random();

	@BeforeTest
	public void beforeTest() {
		 System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		 driver = new ChromeDriver();

//		 driver = new FirefoxDriver();
//		System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
//		driver = new InternetExplorerDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_SendKeyAPI() throws Exception {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		WebElement uploadFileXpath = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFileXpath.sendKeys(uploadFile);
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName + "']")).isDisplayed());
		Thread.sleep(3000);
		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[text()='" + fileName + "']")).isDisplayed());
	}

	public void TC_02_AutoIT() throws Exception {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");

		// Chrome, firefox
		// WebElement uploadFileCSS =
		// driver.findElement(By.cssSelector(".fileinput-button"));
		// uploadFileCSS.click();

		// IE
		WebElement uploadFileIE = driver.findElement(By.xpath("//span[contains(text(),'Add files...')]"));
		uploadFileIE.click();

		Runtime.getRuntime().exec(new String[] { IeUpload, uploadFile });
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName + "']")).isDisplayed());
		Thread.sleep(3000);
		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[text()='" + fileName + "']")).isDisplayed());

	}


	public void TC_03_RobotClass() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		// Chrome, firefox

		// Specify the file location with extension
		StringSelection select = new StringSelection(uploadFile);

		// Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		WebElement uploadFileCSS = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadFileCSS.click();

		Robot robot = new Robot();
		Thread.sleep(1000);
		// focus to textbox
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		// giả lập nhấn xuống phím Ctr - V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		// Giả lập thả phím Ctr - V ra
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);
		// Giả lập nhấn phím enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

	@Test
	public void TC_04_Upload_File() {
		driver.get("https://encodable.com/uploaddemo/");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(uploadFile);
		WebElement selectUpload = driver.findElement(By.xpath("//select[@name='subdir1']"));
		Select select = new Select(selectUpload);
		select.selectByVisibleText("/uploaddemo/files/");
		
		driver.findElement(By.xpath("//input[@id='newsubdir1']")).sendKeys(subFolder);
		driver.findElement(By.xpath("//input[@id='formfield-email_address']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='formfield-first_name']")).sendKeys(fristName);
		driver.findElement(By.xpath("//input[@id='uploadbutton']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']/dd[text()='Email Address: " + email +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']/dd[text()='First Name: " + fristName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']/dt[contains(text(),'File 1 of 1:')]/a[text()='" + fileName + "']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='View Uploaded Files']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + subFolder + "']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='" + subFolder + "']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName + "']")).isDisplayed());
	}
	@AfterTest
 	public void afterTest() {
		driver.quit();
		
	}
	public int random() {
		Random random = new Random();
		return random.nextInt(999999);
	}

}
