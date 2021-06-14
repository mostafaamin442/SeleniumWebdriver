package workingWithElements;

import java.util.List;
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

public class WebTables {
	

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
		String url = "https://the-internet.herokuapp.com/tables";
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test

	public void TestWebTable() throws InterruptedException {

		WebElement WebTable = driver.findElement(By.id("table2"));
		
		//get all  rows
		List<WebElement> rows  = WebTable.findElements(By.tagName("tr"));
		Assert.assertEquals(5, rows.size());
		
		//get all cell data
		// for each row inside rows
		for (WebElement row : rows) {
			
			List<WebElement> columns = row.findElements(By.tagName("td"));
			
			for (WebElement col : columns) {
				
				System.out.println(col.getText());
				
			}
			System.out.println();
		}


	}

	@AfterTest
	public void Close() {

		driver.close();
	}
}

