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

public class CreateNewEvent {

	public static void main(String[] args) throws InterruptedException {
		
		PropertiesUtility property = new PropertiesUtility();
		ExcelUtility excel = new ExcelUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility webUtil = new WebDriverUtility();
		property.propertiesInit(IConstantPath.PROPERTIES_PATH);
		excel.excelInit(IConstantPath.EXCEL_PATH);
		
		String browser = property.fetchDataFromProperties("browser");
		String url = property.fetchDataFromProperties("url");
		long time = Long.parseLong(property.fetchDataFromProperties("timeouts"));
		
		WebDriver driver = webUtil.openApplication(browser, url, time);
//		WebDriverManager.chromedriver().setup();
//
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("http://localhost:8888/");
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement loginpage = driver.findElement(By.xpath("//a[text()='vtiger']"));

		if (loginpage.getText().equals("vtiger"))
			System.out.println("Login page displayed");
		else
			System.out.println("Login page not displayed");

		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();

		WebElement homepage = driver.findElement(By.xpath("//a[@class='hdrLink']"));

		if (homepage.getText().equals("Home"))
			System.out.println("Home page displayed");
		else
			System.out.println("Home page not displayed");

		WebElement dropdown = driver.findElement(By.id("qccombo"));
		
		webUtil.dropdown("Events", dropdown);
//		Select s = new Select(dropdown);
//		dropdown.click();
//		s.selectByValue("Events");
		
		WebElement quickCreatePage = driver.findElement(By.xpath("//b[text()='Create To Do']"));

		if (quickCreatePage.getText().equals("Create To Do"))
			System.out.println("New Event create page displayed");
		else
			System.out.println("New Event create page not displayed");

		Map<String,String> map = excel.getDataFromExcel("EventsTestData", "Create New Event");
//		driver.findElement(By.name("subject")).sendKeys("Inspection1");
		driver.findElement(By.name("subject")).sendKeys(map.get("Subject"));

		WebElement newEventPage = driver.findElement(By.xpath("//table/descendant::input[@name='button']"));
		newEventPage.click();

		WebElement eventInfoPage = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));

		if (eventInfoPage.getText().contains("Event Information"))
			System.out.println("New event created succesfully" + eventInfoPage.getText());
		else
			System.out.println("New event not created");

		WebElement AdminIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		
		webUtil.mouseHover(AdminIcon);
//		Actions a = new Actions(driver);
//		a.moveToElement(AdminIcon).perform();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		webUtil.closeAllWindows();
		excel.closeExcel();
	}

}
