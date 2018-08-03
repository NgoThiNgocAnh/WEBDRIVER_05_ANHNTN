package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;

public class Topic_04_TextBox_DropDownList {

	WebDriver driver;
	String CustomerID, name, dob, address, city, state, pin, mobile, email, pass, newAddress, newCity ;
	By nameTextBox = By.xpath("//input[@name='name']");
	By genderTextBox = By.xpath("//input[@name='gender']");
	By dobTextBox = By.xpath("//input[@name='dob']");
	By addressTextArea = By.xpath("//textarea[@name='addr']");
	By stateTextBox = By.xpath("//input[@name='state']");
	By cityTextBox = By.xpath("//input[@name='city']");
	By pinTextBox = By.xpath("//input[@name='pinno']");
	By mobileTextBox = By.xpath("//input[@name='telephoneno']");
	By emailTextBox = By.xpath("//input[@name='emailid']");
	By passTextBox = By.xpath("//input[@name='password']");
	
	
	  @BeforeTest
	  public void beforeTest() {
		  driver = new FirefoxDriver();
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  name = "abcde";
		  dob = "1995-12-12";
		  address = "1231 minh khai";
		  city = "hai ba trung a";
		  state = "ha noic";
		  pin = "123456";
		  mobile = "1234567";
		  email = "auto" + randomEmail() + "@gmail.com" ;
		  pass = "123456";
		  newAddress = "234 dai la";
		  newCity = "ho chi minh";
	  }


  public void TC_01_DropDownList() {
	//	  Test Script 01: Xử lí HTML Dropdown/ list
	//	  Step 01 - Truy cập vào trang: http://daominhdam.890m.com/
	//	  Step 02 - Kiểm tra dropdown Job Role 01 không hỗ trợ thuộc tính multi-select
	//	  Step 03 - Chọn giá trị Automation Tester trong dropdown bằng phương thức selectVisible
	//	  Step 04 - Kiểm tra giá trị đã được chọn thành công
	//	  Step 05 - Chọn giá trị Manual Tester trong dropdown bằng phương thức selectValue
	//	  Step 06 - Kiểm tra giá trị đã được chọn thành công
	//	  Step 07 - Chọn giá trị Mobile Tester trong dropdown bằng phương thức selectIndex
	//	  Step 08 - Kiểm tra giá trị đã được chọn thành công
	//	  Step 09 - Kiểm tra dropdown có đủ 5 giá trị
	  driver.get("http://daominhdam.890m.com/");
	  Select JobRole = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
	  Assert.assertTrue(!JobRole.isMultiple());
	  
	  JobRole.selectByVisibleText("Automation Tester");
	  Assert.assertEquals(JobRole.getFirstSelectedOption().getText(), "Automation Tester");
	  
	  JobRole.selectByValue("manual");
	  Assert.assertEquals(JobRole.getFirstSelectedOption().getText(), "Manual Tester");
	  
	  JobRole.selectByIndex(3);
	  Assert.assertEquals(JobRole.getFirstSelectedOption().getText(), "Mobile Tester");
	  
	  int JobItems = JobRole.getOptions().size();
	  Assert.assertEquals(JobItems, 5);
  }

  @Test
  public void TC_02_CustomDropDown() {
	  driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
	  driver.findElement(By.xpath("//span[@id='number-button']")).click();
	  
  }



  public void TC_03_TextBox_TextArea() {
		//Test Script 03: Xử lí textbox/ textarea
		//Step 01 - Access vào trang: http://demo.guru99.com/v4
		//Step 02 - Đăng nhập với thông tin: User = mngr94214 | Pass = Uqazedu
		//Note: Manual test để lấy thông tin User/Pass nếu hết hạn - User chỉ tồn tại trong 20 ngày - http://demo.guru99.com/
		//Verify HomePage được hiển thị thành công
		//Step 03 - Chọn menu New Customer
		//Step 04 - Nhập toàn bộ dữ liệu đúng > Click Submit
		//  Step 05 - Sau khi hệ thống tạo mới Customer thành công > Get ra thông tin của Customer ID
		//  Sử dụng xpath với following-sibling::td
		//  Step 06 - Chọn menu Edit Customer > Nhập Customer ID > Submit
		//  Step 07 - Tại màn hình Edit Customer:
		//  Verify giá trị tại 2 field: Customer Name và Address đúng với dữ liệu khi tạo mới New Customer tại Step 04
		//  Nhập giá trị mới tại 2 field Customer Address và City > Submit
		//  Verify giá trị tại 2 field: Customer Address và City đúng với dữ liệu sau khi đã Edit thành công
	  //Step 01
	  driver.get("http://demo.guru99.com/v4");
	  //Step 02
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr145519");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("jynujUb");
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  String marquee = driver.findElement(By.xpath("//marquee")).getText();
	  Assert.assertEquals(marquee, "Welcome To Manager's Page of Guru99 Bank");
	  //Step 03
	  driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	 //Step 04
	  driver.findElement(nameTextBox).sendKeys(name);
	  driver.findElement(dobTextBox).sendKeys(dob);
	  driver.findElement(addressTextArea).sendKeys(address);
	  driver.findElement(cityTextBox).sendKeys(city);
	  driver.findElement(stateTextBox).sendKeys(state);
	  driver.findElement(pinTextBox).sendKeys(pin);
	  driver.findElement(mobileTextBox).sendKeys(mobile);
	  driver.findElement(emailTextBox).sendKeys(email);
	  driver.findElement(passTextBox).sendKeys(pass);
	  driver.findElement(By.xpath("//input[@name='sub']")).click();
	  //Step 05
	  CustomerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	  
	  // verify create new customer
	  
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), mobile);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
	  
	  //Step 06 
	  driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
	  driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(CustomerID);
	  driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
	  //Step 07 
	  Assert.assertTrue(driver.findElement(nameTextBox).isDisplayed());
	  Assert.assertTrue(driver.findElement(genderTextBox).isDisplayed());
	  Assert.assertTrue(driver.findElement(dobTextBox).isDisplayed());

	  Assert.assertTrue(driver.findElement(addressTextArea).isEnabled());
	  
	  //verify customer name / address 
	  Assert.assertEquals(driver.findElement(nameTextBox).getAttribute("value"), name);
	  Assert.assertEquals(driver.findElement(addressTextArea).getText(), address);
	  
	  //edit address/ city
	  
	  driver.findElement(addressTextArea).clear();;
	  driver.findElement(addressTextArea).sendKeys(newAddress);
	  driver.findElement(cityTextBox).clear();
	  driver.findElement(cityTextBox).sendKeys(newCity);
	  driver.findElement(By.xpath("//input[@name='sub']")).click();
	  
	  //verify edit address/ city
	  
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), newAddress);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), newCity);
	    }
   
  
    
  @AfterTest
  public void afterTest() {
//	  driver.quit();
  }

  public int randomEmail() {
	  Random rand = new Random();
	  int number = rand.nextInt(999999) + 1;
	  return number;
  }
}
