package hardcodedTests;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateAndDuplicateLeadTest {

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
		
		driver.findElement(By.xpath("//img[@alt='Create Lead...']")).click();
		WebElement Creatingleadpage = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if(Creatingleadpage.getText().contains("Creating"))
			System.out.println("Creating lead page displayed");
		else
			System.out.println("Creating Lead page not displayed");
		
		Random random = new Random();
		String lastname = "john" + random.nextInt(100);
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		
		String company = "Global Systems" + random.nextInt(100);
		driver.findElement(By.name("company")).sendKeys(company);
		driver.findElement(By.xpath("//input[normalize-space(@value)='Save']")).click();
		
		WebElement LeadInformation = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if(LeadInformation.isDisplayed())
			System.out.println("Lead created succesfully showing"+LeadInformation.getText());
		else
			System.out.println("Lead not created");
		
		driver.findElement(By.name("Duplicate")).click();
		WebElement duplicatepage = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if(duplicatepage.getText().contains("Duplicating"))
			System.out.println("Duplicate lead page displayed");
		else
			System.out.println("Duplicating lead page not displayed");
		
		String newlastname = "johnar" + random.nextInt(100);
		WebElement lastnameTF = driver.findElement(By.name("lastname"));
		lastnameTF.clear();
		lastnameTF.sendKeys(newlastname);
		driver.findElement(By.xpath("//input[normalize-space(@value)='Save']")).click();
		
		WebElement DuplicatepageInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if(DuplicatepageInfo.isDisplayed())
			System.out.println("New duplicateLead succesfully created");
		else
			System.out.println("Duplicare of lead not created");
		
		WebElement AdminIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a = new Actions(driver);
		a.moveToElement(AdminIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']"));
		driver.close();

	}

}