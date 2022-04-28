package week4.day1;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingFrameAndAlert {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_prompt");	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.switchTo().frame("iframeResult");
		driver.findElement(By.xpath("//button[text() = 'Try it']")).click();
		Alert alert = driver.switchTo().alert();
		alert.sendKeys("Feros");
		alert.accept();
		String str = driver.findElement(By.id("demo")).getText();
		if (str.contains("Feros")) {
			System.out.println("Successfully validated");
		}
	}

}
