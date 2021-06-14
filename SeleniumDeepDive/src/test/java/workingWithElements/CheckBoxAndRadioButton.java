package workingWithElements;

import java.util.NoSuchElementException;
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

public class CheckBoxAndRadioButton {

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
		String url = "https://the-internet.herokuapp.com/checkboxes";
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test(enabled = false)

	public void SearchBtn() throws InterruptedException {

		WebElement check1 = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]"));
		check1.click();

		Thread.sleep(3000);
		WebElement check2 = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]"));
		check2.click();

		// check if the box is selected to un-select it 
		if (check1.isSelected()) {
			check1.click();
		}

	}



	@Test
	public void testIsElementPrsernt() {

		// check if the  element is exist , will return true or false
		if (isElementPersent(By.xpath("//*[@id=\"checkboxes\"]/input[1]"))) {

			// if the element is present find the element again to can do our actions on it
			WebElement check1 = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]"));
 
			if (!check1.isSelected()) {

				check1.click();
			}

			// if the element is not found print error that the element isn't exist
		} else {

			Assert.fail("check box 1 isn't doesn't exist");

		}
	}

	// check if the  element is exist , will return true or false
	private boolean isElementPersent(By	by) {

		try {
			driver.findElement(by);
			return true ;

		} catch (NoSuchElementException e) {

			return false ;

		}

	}

	@AfterTest
	public void Close() {

		driver.close();
	}
}

