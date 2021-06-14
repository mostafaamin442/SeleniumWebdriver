package workingWithElements;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;


import io.github.bonigarcia.wdm.WebDriverManager;

public class DropDownList {
	
	WebElement Searchbtn;

	public static WebDriver driver;

	@BeforeTest
	public void StartDriver(@Optional ("chrome") String browsername) {
		if (browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();


		} else if (browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		String url = "https://the-internet.herokuapp.com/dropdown";
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	/*@Test(priority = 1)

	public void Login() {

		WebElement Mail = driver.findElement(By.id("email"));
		WebElement Pass = driver.findElement(By.id("password"));
		WebElement btn = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/div[1]/form/div[4]/button"));
		
		Mail.sendKeys("mostafa.amin@qurba.io");
		Pass.sendKeys("HelloMostafa#1234");
		btn.click();
	}*/
	
	@Test
	public void testDropDownList() throws InterruptedException {
		
		// catch drop down list
		WebElement OptionsList = driver.findElement(By.id("dropdown"));
		
		// select is class from selenium support UI , We create an object from it
		Select SelectOptions = new Select(OptionsList);
		
		// check if the list support multiple selection or not
		Assert.assertFalse(SelectOptions.isMultiple());
		
		//check the number of options inside the list
		Assert.assertEquals(3, SelectOptions.getOptions().size());
		
		SelectOptions.selectByIndex(2);
		Thread.sleep(2000);
		SelectOptions.selectByVisibleText("Option 1");
		Thread.sleep(2000);
		SelectOptions.selectByValue("2");
		Assert.assertEquals("Option 2", SelectOptions.getFirstSelectedOption().getText());
		Thread.sleep(2000);
		
	}
	
	@AfterTest
	public void CloseDriver() {
	
		driver.close();
	}
	
}

