package hardcodedTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ClickAndDeleteLead {

	public static void main(String[] args) 
	{
WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement loginpage = driver.findElement(By.xpath("//a[text()='vtiger']"));
		if(loginpage.getText().equals("vtiger"))
			System.out.println("Login page displayed");
		else
		System.out.println("Login page not displayed");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		WebElement homepage = driver.findElement(By.xpath("//a[@class='hdrLink']"));
		if(homepage.getText().equals("Home"))
			System.out.println("Home page displayed");
		else
			System.out.println("Home page not displayed");
		
		driver.findElement(By.xpath("//a[text()='Leads' and contains(@href,'Leads&action')]")).click();
		WebElement leadpage = driver.findElement(By.xpath("//a[@class='hdrLink']"));
		if(leadpage.getText().equals("Leads"))
			System.out.println("Lead page displayed");
		else
			System.out.println("Lead page not displayed");
		
		WebElement checkbox = driver.findElement(By.xpath("//tr[2]/following-sibling::tr[1]"
				+ "/descendant::input[@type='checkbox']"));
		checkbox.click();
		if(checkbox.isSelected())
			System.out.println("Checkbox clicked");
		else
			System.out.println("checkbox not selected");
		
		driver.findElement(By.xpath("//input[@value='Delete']")).click();
		Alert al= driver.switchTo().alert();
		System.out.println(al.getText());
		al.accept();
		
		WebElement AdminIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a = new Actions(driver);
		a.moveToElement(AdminIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']"));
		driver.close();
		
	}

}
