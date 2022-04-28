package week4.day1.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://dev121343.service-now.com");	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.switchTo().frame(0);
		
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("India@123");
		driver.findElement(By.id("sysverb_login")).click();
		Thread.sleep(1000);
		
		driver.switchTo().defaultContent();
		
		driver.findElement(By.id("filter")).sendKeys("incident");
		//WebElement incident = driver.findElement(By.xpath("//span[text()='Incident'][1]"));
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("//ul[@aria-label='Modules for Application: Incident']//div[text()='All']"));
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
		
		Thread.sleep(3000);
		
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//div[@class='navbar-header']//button[text()='New']")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowList.get(1));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tbody[@class='list2_body']/tr//td//a[@role='button']")).click();
		Thread.sleep(3000);
		driver.switchTo().window(windowList.get(0));
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//div[@class='col-xs-10 col-md-9 col-lg-8 form-field input_controls']//input[@id='incident.short_description']")).sendKeys("Feros from Test Leaf");
		
		String incidentNumber = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		System.out.println("Incident number: " + incidentNumber);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@id='sysverb_insert' and text()='Submit']")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='input-group']//input[@placeholder='Search']")).sendKeys(incidentNumber);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='input-group']//input[@placeholder='Search']")).sendKeys(Keys.ENTER);
		
		String incident = driver.findElement(By.xpath("//tbody[@class='list2_body']/tr[1]/td/a[@class='linked formlink']")).getText();
		if (incident.equals(incidentNumber)) {
			System.out.println("Incident: " + incidentNumber + " is successfully Created");
		} else {
			System.out.println("Incident: " + incidentNumber + " is not Created");
		}
	}
}
