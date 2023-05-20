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

public class CreateOrganizationWithIndustryType {

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
//		
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

		driver.findElement(By.xpath("//a[text()='Organizations' and contains(@href,'Accounts&action')]")).click();
		WebElement organizepage = driver.findElement(By.xpath("//a[@class='hdrLink']"));
		if (organizepage.getText().contains("Organizations"))
			System.out.println("Organizations page displayed succesfully");
		else
			System.out.println("Organizations page not displayed");

		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		WebElement neworganizepage = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if (neworganizepage.getText().equals("Creating New Organization"))
			System.out.println("creating new organization page displayed");
		else
			System.out.println("creating new organization page not displayed");
		Map<String, String> map = excel.getDataFromExcel("OrganizationsTestData",
				"Create Organization With Industry And Type");
		driver.findElement(By.name("accountname")).sendKeys(map.get("Organization Name"));
		WebElement Field = driver.findElement(By.name("industry"));
		Field.click();

//		Select s = new Select(Field);
//		s.selectByValue("Construction");
		webUtil.dropdown("Construction", Field);

		WebElement Type = driver.findElement(By.name("accounttype"));
		Type.click();
//		Select p = new Select(Type);
//		p.selectByValue("Partner");
		webUtil.dropdown("Partner", Type);

		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		WebElement OrgSavepage = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if (OrgSavepage.isDisplayed())
			System.out.println("Organization created succesfully with Industry & Type" + OrgSavepage.getText());
		else
			System.out.println("Organization not created");

		WebElement adminicon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//		Actions a = new Actions(driver);
//		a.moveToElement(adminicon).perform();
		webUtil.mouseHover(adminicon);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		Thread.sleep(3000);
		webUtil.closeAllWindows();
		excel.closeExcel();

	}

}
