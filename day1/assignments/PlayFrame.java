package week4.day1.assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PlayFrame {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.switchTo().frame("frame1");
		driver.findElement(By.xpath("//body/b[@id='topic']/following-sibling::input")).sendKeys("Test Leaf");
		
		driver.switchTo().frame("frame3");
		driver.findElement(By.xpath("//body/b[contains(text(), 'Frame Check box')]/following-sibling::input")).click();
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame("frame2");
		WebElement findElement = driver.findElement(By.id("animals"));
		Select s = new Select(findElement);
		s.selectByVisibleText("Avatar");
		
	}

}
