package genericLibImplementation;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import genericLibraries.ExcelUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertiesUtility;
import genericLibraries.WebDriverUtility;

public class CreateContactWithMandatoryField {

	public static void main(String[] args) throws InterruptedException 
	{
		PropertiesUtility property = new PropertiesUtility();
		ExcelUtility excel = new ExcelUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility web = new WebDriverUtility();
		property.propertiesInit(IConstantPath.PROPERTIES_PATH);
		excel.excelInit(IConstantPath.EXCEL_PATH);
		
		String browser = property.fetchDataFromProperties("browser");
		String url = property.fetchDataFromProperties("url");
		long time = Long.parseLong(property.fetchDataFromProperties("timeouts"));
		
		WebDriver driver = web.openApplication(browser, url, time);
//		WebDriverManager.chromedriver().setup();
//		
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("http://localhost:8888/");
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		
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
		
		
//		Random random = new Random();
//		String lastname = "kumar" + random.nextInt(100);
		Map<String,String> map = excel.getDataFromExcel("ContactsTestData", "Create Contact");
		String lastname = map.get("Last Name")+jutil.generateRandomNumber(100);
		
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		driver.findElement(By.xpath(" //input[@class='crmButton small save']")).click();
		
		WebElement contactcreatepage = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		
		if(contactcreatepage.getText().contains("Contact Information"))
			System.out.println("Contact created succesfully");
		else
			System.out.println("Contact not created");
		
		WebElement AdminIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		web.mouseHover(AdminIcon);
//		Actions a = new Actions(driver);
//		a.moveToElement(AdminIcon).perform();
//		
		Thread.sleep(3000);	
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		web.closeAllWindows();
		excel.closeExcel();
//		driver.quit();
	}
}
