package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_02_Xpath_Css_Locator {
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

  @Test
  public void f() {
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("ngoc@gmail.com");
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("ngoc@gmail.com");
	  
	  
  }


  @AfterClass
  public void afterClass() {
  }

}
