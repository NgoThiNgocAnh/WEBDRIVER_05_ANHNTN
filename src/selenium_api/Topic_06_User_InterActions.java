package selenium_api;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;

public class Topic_06_User_InterActions {

	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_ToolTips() {
		driver.get("http://daominhdam.890m.com/");
		WebElement hoverText = driver.findElement(By.xpath("//a[text()='Hover over me']"));
		Actions action = new Actions(driver);
		action.moveToElement(hoverText).perform();
		// verify hover text

		Assert.assertEquals("Hooray!", driver.findElement(By.xpath("//div[@class='tooltip-inner']")).getText());

	}

	public void TC_02_HoverLogin() {
		driver.get("https://www.myntra.com/");
		WebElement hoverLogin = driver.findElement(By.xpath("//div[@class='desktop-userIconsContainer']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(hoverLogin).perform();
		driver.findElement(By.xpath("//a[@data-track='login']")).click();
		// verify login
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());

	}

	public void TC_03_ClickAndHold() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> Selectable = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
		
		// click and hold mouse 
		Actions action = new Actions(driver);
//		action.clickAndHold(Selectable.get(0)).moveToElement(Selectable.get(3)).release().perform();
		
		//Click Ctrl
		//nhan phim ctrl xuong 
		action.keyDown(Keys.CONTROL).build().perform();
		Selectable.get(0).click();
		Selectable.get(2).click();
		Selectable.get(5).click();
		Selectable.get(8).click();
		
		//nha phim control ra 
		action.keyUp(Keys.CONTROL).build().perform();
				
		//verify 
		List<WebElement> SeclectedAble = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(SeclectedAble.size(), 4);
		

		
	}
	
	public void TC_04_DoubleClick() {
		driver.get("http://www.seleniumlearn.com/double-click");
		
		Actions action = new Actions(driver);
		//gia lap double click
		
		WebElement doubleclick = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
		action.doubleClick(doubleclick).perform();
		//verify 
		Alert alert = driver.switchTo().alert();
		String alertDoubleClick = alert.getText();
		Assert.assertTrue(alertDoubleClick.equals("The Button was double-clicked."));
		
	}
	
	public void TC_05_RightClick() {
		
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement rightClick = driver.findElement(By.xpath("//span[text()='right click me']"));
		Actions action = new Actions(driver);
		WebElement quitClick = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"));
		action.contextClick(rightClick);
		action.moveToElement(quitClick).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]")).isDisplayed());
		quitClick.click();
		Alert alertQuit = driver.switchTo().alert();
		Assert.assertEquals(alertQuit.getText(), "clicked: quit");
		alertQuit.accept();
	}
	

	public void TC_06_DrapAndDrop() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		WebElement sourceElement = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetElement = driver.findElement(By.xpath("//div[@id='droptarget']"));
		Assert.assertEquals(targetElement.getText(), "Drag the small circle here.");
		
		Actions action = new Actions(driver);
		action.dragAndDrop(sourceElement, targetElement).perform();;
		Assert.assertEquals(targetElement.getText(),"You did great!" );
	}
	
	@Test
	public void TC_07_DrapAndDropHtml5() {
		driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
		
		WebElement targetElement = driver.findElement(By.xpath("//div[@id='droppable']"));
		WebElement sourceElement = driver.findElement(By.xpath("//div[@id='draggable']"));
		Assert.assertEquals(targetElement.getText(), "Drop here");
		Actions action = new Actions(driver);
		action.dragAndDrop(sourceElement, targetElement).perform();
		Assert.assertEquals(targetElement.getText(), "Dropped!");
		
	}
	@AfterTest
	public void afterTest() {
	}

}
