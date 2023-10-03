package End2EndTTesting.com.practo;

import java.io.File;
import java.io.IOException;
import java.time.*;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass 
{
	public static WebDriver driver;
	String url= "https://www.practo.com/search/doctors?results_type=doctor&q=%5B%7B%22word%22%3A%22physician%22%2C%22autocompleted%22%3Atrue%2C%22category%22%3A%22common_name%22%7D%5D&city=Bangalore";

	
	@BeforeMethod()
	public void openBrowser() {
		
		WebDriverManager.edgedriver().setup();
	    driver= new EdgeDriver();
		driver.manage().window().maximize();
		driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Test()
	public void print() throws IOException, InterruptedException {
		
	    System.out.println("Web Page Is Opened In Chrome Browser");
	
	    Thread.sleep(5000);
	    String Doctor_name="Dr. KVS Mahesh";
	    WebElement button = driver.findElement(By.xpath("//h2[text()='"+Doctor_name+"']/following::button[text()='Book Appointment'][1]"));
	
	    Thread.sleep(6000);
	    button.click();
	
	    WebElement Next_Slot = driver.findElement(By.xpath("//button[@data-qa-id='next_slots']"));
	    WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(50));
	    wait.until(ExpectedConditions.elementToBeClickable(Next_Slot));
	    Next_Slot.click();
	
	    String User_Time = "10:30 AM";
	    WebElement Time = driver.findElement(By.xpath("//button[@data-qa-id='slot_time']/span[text()='"+User_Time+"']"));
	    Time.click();
	
	    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	    WebElement mobile=driver.findElement(By.xpath("//input[@name='phone']"));
	
	    mobile.sendKeys("123456789");
	    
	    TakesScreenshot t =((TakesScreenshot) driver);
		File source=t.getScreenshotAs(OutputType.FILE);
		File destination= new File(".//target//abc.png");
		Files.copy(source, destination);
	}
//

	@AfterMethod()
	public void closeBrowser() {
		driver.quit();
	}
}
