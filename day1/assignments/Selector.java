package week4.day1.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Selector {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.nykaa.com/");	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebElement brand = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions action = new Actions(driver);
		action.moveToElement(brand).perform();
		
		driver.findElement(By.xpath("//input[@id='brandSearchBox']")).sendKeys("L'Oreal Paris");
		
		WebElement element = driver.findElement(By.xpath("//div[@id='scroller-container']//a[contains(text(),'Oreal Paris')][1]"));
		action = new Actions(driver);
		action.moveToElement(element).click().perform();
		String title = driver.getTitle();
		if (title.contains("L'Oreal Paris")) {
			System.out.println("Title is L'Oreal Paris");
		} else {
			System.out.println("Title is not L'Oreal Paris");
		}
		
		
		driver.findElement(By.xpath("//button[@class=' css-n0ptfk']/span[contains(text(), 'Sort By')]")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//label[@for='checkbox_Shampoo_316']//div[@class='control-indicator checkbox ']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		Thread.sleep(1000);
		if (driver.findElement(By.xpath("//span[@class='filter-value']")).getText().equals("Shampoo")) {
			System.out.println("Shampoo filter is applied");
		} else {
			System.out.println("Shampoo filter is not applied");
		}
		
		driver.findElement(By.xpath("//div[contains(text(), 'Oreal Paris Colour Protect')]")).click();
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowList.get(1));
		
		WebElement size = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select s = new Select(size);
		s.selectByVisibleText("175ml");
		
		System.out.println("MRP: " + driver.findElement(By.xpath("(//span[contains(text(),'MRP:')]/following-sibling::span)[1]")).getText());
		
		driver.findElement(By.xpath("//span[text()='ADD TO BAG'][1]")).click();
		driver.findElement(By.xpath("//button[@class='css-g4vs13']")).click();
		Thread.sleep(1000);
		
		WebElement frameElement = driver.findElement(By.xpath("//div[@id='portal-root']//iframe"));
		driver.switchTo().frame(frameElement);
		String grandTotal = driver.findElement(By.xpath("//span[text()='Grand Total']/following-sibling::div")).getText().substring(1);
		System.out.println("Grand Total: " + grandTotal);
		
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(), 'CONTINUE AS GUEST')]")).click();
		
		if (grandTotal.equals(driver.findElement(By.xpath("//div[text()= 'Grand Total']/following-sibling::div/span")).getText())) {
			System.out.println("Grand total doesn't change");
		} else {
			System.out.println("Grand total is changed");
		}
		
		driver.quit();
	}

}
