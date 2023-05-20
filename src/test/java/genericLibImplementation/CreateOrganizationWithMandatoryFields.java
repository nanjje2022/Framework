package genericLibImplementation;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericLibraries.ExcelUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertiesUtility;
import genericLibraries.TabNames;
import genericLibraries.WebDriverUtility;
import pompages.CreateNewOrganizationPage;
import pompages.HomePage;
import pompages.LoginPage;
import pompages.NewOrganizationInfoPage;
import pompages.OrganizationPage;

public class CreateOrganizationWithMandatoryFields {

	public static void main(String[] args) throws InterruptedException 
	{
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
		
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		OrganizationPage org = new OrganizationPage(driver);
		CreateNewOrganizationPage createOrg = new CreateNewOrganizationPage(driver);
		NewOrganizationInfoPage newOrgInfo = new NewOrganizationInfoPage(driver);
		
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
		
//		driver.findElement(By.name("user_name")).sendKeys("admin");
//		driver.findElement(By.name("user_password")).sendKeys("admin");
//		driver.findElement(By.id("submitButton")).click();

		String username = property.fetchDataFromProperties("username");
		String password = property.fetchDataFromProperties("password");
		login.loginToApp(username, password);
		
		WebElement homepage = driver.findElement(By.xpath("//a[@class='hdrLink']"));
		if(homepage.getText().equals("Home"))
			System.out.println("Home page displayed");
		else
			System.out.println("Home page not displayed");
		
//		driver.findElement(By.xpath("//a[text()='Organizations' and contains(@href,'Accounts&action')]")).click();
		
		home.clickRequiredTab(TabNames.ORGANIZATIONS, webUtil);
		
		WebElement organizepage = driver.findElement(By.xpath("//a[@class='hdrLink']"));
		if(organizepage.getText().contains("Organizations"))
			System.out.println("Organizations page displayed succesfully");
		else
			System.out.println("Organizations page not displayed");
		
//		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		org.clickPlusButton();
		
		WebElement neworganizepage = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if(neworganizepage.getText().equals("Creating New Organization"))
			System.out.println("creating new organization page displayed");
		else
			System.out.println("creating new organization page not displayed");
		
		Map<String, String> map = excel.getDataFromExcel("OrganizationsTestData", "Create Organization");
		String orgName = map.get("Organization Name")+jutil.generateRandomNumber(100);
		
//		driver.findElement(By.name("accountname")).sendKeys("orgName");
//		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		
		createOrg.setOrgName(orgName);
		createOrg.clickSaveButton();	
		
		WebElement orgpagecreated = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if(orgpagecreated.isDisplayed())
			System.out.println("Organization created succesfully"+orgpagecreated.getText());
		else
			System.out.println("Oraganization not created");
		
		WebElement adminicon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webUtil.mouseHover(adminicon);
//		Actions a = new Actions(driver);
//		a.moveToElement(adminicon).perform();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
//		driver.quit();		
		excel.closeExcel();
		webUtil.closeAllWindows();
	}
}
