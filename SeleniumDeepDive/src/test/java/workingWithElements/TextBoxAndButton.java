package workingWithElements;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;


import io.github.bonigarcia.wdm.WebDriverManager;

public class TextBoxAndButton {
	

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

	public void login() throws InterruptedException {

		WebElement UserName = driver.findElement(By.id("username"));
		WebElement Password = driver.findElement(By.id("password"));
		WebElement btn = driver.findElement(By.className("radius"));

		UserName.clear();
		UserName.sendKeys("tomsmith");
		Password.sendKeys("SuperSecretPassword!");
		btn.click();
		Thread.sleep(3000);

		//Checking Elemnet's text
		WebElement SucessNotification = driver.findElement(By.id("flash"));
		System.out.println(SucessNotification.getText());
		Assert.assertTrue(SucessNotification.getText().contains("You logged into a secure area!") );



	}

	@AfterTest
	public void Close() {

		driver.close();
	}
}

