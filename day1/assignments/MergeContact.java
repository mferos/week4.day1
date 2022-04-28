package week4.day1.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("DemoCSR");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("crmsfa");
		driver.findElement(By.xpath("//input[@class='decorativeSubmit']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'CRM/SFA')]")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		driver.findElement(By.xpath("//table[@id='widget_ComboBox_partyIdFrom']/following::a/img[@alt='Lookup']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowList = new ArrayList<String>(windowHandles);
		
		driver.switchTo().window(windowList.get(1));
		driver.findElement(By.xpath("(//td[contains(@class,'td-partyId')]//a)[2]")).click();
		driver.switchTo().window(windowList.get(0));
		
		driver.findElement(By.xpath("//table[@id='widget_ComboBox_partyIdTo']/following::a/img[@alt='Lookup']")).click();
		
		windowHandles = driver.getWindowHandles();
		windowList = new ArrayList<String>(windowHandles);
		
		driver.switchTo().window(windowList.get(1));
		driver.findElement(By.xpath("(//td[contains(@class,'td-partyId')]//a)[3]")).click();
		driver.switchTo().window(windowList.get(0));
		
		driver.findElement(By.xpath("//a[text()= 'Merge']")).click();
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		if (driver.getTitle().contains("View Contact")) {
			System.out.println("Validation success");
		} else {
			System.out.println("Validation failed");
		}
		
	}

}
