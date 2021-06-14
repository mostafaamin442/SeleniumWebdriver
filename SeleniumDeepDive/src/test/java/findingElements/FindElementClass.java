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

public class FindElementClass {

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

		try {

			WebElement	UserName = driver.findElement(By.name("username"));
			WebElement	Password = driver.findElement(By.name("password"));
			WebElement	LoginButton = driver.findElement(By.className("radius"));
			LoginButton.getText();


			UserName.sendKeys("Mostafa");
			Password.sendKeys("12121993");
			LoginButton.click();

		} catch (org.openqa.selenium.NoSuchElementException E) {
			System.out.println("The eleemnt is not found please try use another attribute");
		}

	}

	@AfterTest
	public void Close() throws InterruptedException {

		Thread.sleep(50);
		driver.close();
	}
}

