package hardcodedTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationWithMandatoryFields {

	public static void main(String[] args) throws InterruptedException 
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
		
		driver.findElement(By.xpath("//a[text()='Organizations' and contains(@href,'Accounts&action')]")).click();
		WebElement organizepage = driver.findElement(By.xpath("//a[@class='hdrLink']"));
		if(organizepage.getText().contains("Organizations"))
			System.out.println("Organizations page displayed succesfully");
		else
			System.out.println("Organizations page not displayed");
		
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		WebElement neworganizepage = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if(neworganizepage.getText().equals("Creating New Organization"))
			System.out.println("creating new organization page displayed");
		else
			System.out.println("creating new organization page not displayed");
		
		driver.findElement(By.name("accountname")).sendKeys("NGO");
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		WebElement orgpagecreated = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if(orgpagecreated.isDisplayed())
			System.out.println("Organization created succesfully"+orgpagecreated.getText());
		else
			System.out.println("Oraganization not created");
		
		WebElement adminicon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a = new Actions(driver);
		a.moveToElement(adminicon).perform();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();		
	}
}
