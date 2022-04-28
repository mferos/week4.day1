package week4.day1.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FunWithFrame {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://leafground.com/pages/frame.html");	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.switchTo().frame(0);
		WebElement clickButton = driver.findElement(By.xpath("//button[@id='Click']"));
		clickButton.click();
		
		File src = clickButton.getScreenshotAs(OutputType.FILE);
		File dst = new File("./screenshots/clickMe.png");
		FileUtils.copyFile(src, dst);
		
		driver.switchTo().defaultContent();
		
		List<WebElement> frameList = driver.findElements(By.tagName("iframe"));
		System.out.println("No. of frames : " + frameList.size());
	}
 
}
