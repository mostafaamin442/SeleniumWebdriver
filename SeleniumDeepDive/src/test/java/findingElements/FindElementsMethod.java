package findingElements;

import java.util.ArrayList;
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

public class FindElementsMethod {

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
		String url = "https://the-internet.herokuapp.com";
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test

	public void GetLinks() throws InterruptedException {

	//Get all the links displayed on the page
	List<WebElement> links = driver.findElements(By.tagName("a"));
	
	//Verify there are 20 links displayed on the page
	System.out.println(links.size());
	Assert.assertEquals(46, links.size());
	
	//add the links to the array so that you can open each link individually
	ArrayList<String> targets = new ArrayList<String>();
	

	//print each link value  >> i called web element "link" which will search in links list
	for (WebElement link : links) {
		
		System.out.println(link.getAttribute("href"));
        targets.add(link.getAttribute("href")); 
     //   System.out.println("*************************************");
       
	    }
	
	 //Navigating to each link
    int i=0;
    for (String url : targets) {
        driver.navigate().to(url);
        System.out.println((++i)+": navigated to URL with href: "+url);
        Thread.sleep(3000); // To check if the navigation is happening properly.
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
    }
	}
		

	@AfterTest
	public void Close() {
		
		driver.close();
	}
}

