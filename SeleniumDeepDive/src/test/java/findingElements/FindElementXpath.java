package findingElements;

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

public class FindElementXpath {

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
		String url = "https://the-internet.herokuapp.com/login";
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test

	public void login() {

		//normal xpath
		WebElement UserName = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		WebElement Password = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		WebElement btn = driver.findElement(By.xpath("//*[@id=\"login\"]/button"));
		
		
		//using relative xpath 
		/*WebElement UserName = driver.findElement(By.xpath("//input[1]"));
		WebElement Password = driver.findElement(By.xpath("//input"));
		WebElement btn = driver.findElement(By.xpath("//button"));*/
		
		
		UserName.sendKeys("mostafa");
		Password.sendKeys("12345678");
		btn.click();
		
	}

	@AfterTest
	public void Close() throws InterruptedException {

		Thread.sleep(50);
		driver.close();
	}
}

