package workingWithElements;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;


import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleSearch {
	
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
		String url = "https://www.google.com/";
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test

	public void SearchBtn() throws InterruptedException {


		WebElement Searchbtn = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input"));
		WebElement enter = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[3]/center/input[1]"));
		WebElement space = driver.findElement(By.xpath("//*[@id=\"gb\"]"));
		
		Searchbtn.sendKeys("Selenium Webdriver");
		Thread.sleep(3000);
		space.click();
		Thread.sleep(2000);
		enter.click();
		Searchbtn = driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[1]/div[1]/div[2]/div/div[2]/input"));
		Searchbtn.clear();
		driver.navigate().back();
			
		

	}

	@AfterTest
	public void Close() {

		driver.close();
	}
}

