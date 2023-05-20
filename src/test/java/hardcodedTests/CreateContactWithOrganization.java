package hardcodedTests;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrganization {

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
		
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		WebElement Contacthomepage = driver.findElement(By.xpath("//a[@class='hdrLink']"));
		if(Contacthomepage.isDisplayed())
			System.out.println("Contact home page displayed succesfully");
		else
			System.out.println("Contact home page not displayed");
		
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		WebElement ContactInfopage = driver.findElement(By.xpath("//td[text()=' Contact Information']"));
		if(ContactInfopage.isDisplayed());
		System.out.println("ContactInfopage displayed succesfully");
		
			
		
		Random random = new Random();
		String lastname = "kumar" + random.nextInt(100);
		driver.findElement(By.name("lastname")).sendKeys(lastname);
	
		
		driver.findElement(By.xpath("//tr/child::td[2]/img[@src='themes/softed/images/select.gif']")).click();
		String parentID = driver.getWindowHandle();
		Set<String> windowIDs = driver.getWindowHandles();
		for(String window : windowIDs)
		{
			driver.switchTo().window(window);
		}
		
		driver.findElement(By.xpath("//a[text()='Edureka']")).click();
		driver.switchTo().window(parentID);
		
		driver.findElement(By.xpath(" //input[@class='crmButton small save']")).click();
		
		WebElement contactcreatepage = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		
		if(contactcreatepage.getText().contains("Contact Information"))
			System.out.println("Contact created succesfully with existing organization");
		else
			System.out.println("Contact not created");
		
		WebElement AdminIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a = new Actions(driver);
		a.moveToElement(AdminIcon).perform();
			
		Thread.sleep(3000);	
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		Thread.sleep(3000);
		
		driver.quit();
	}
}
