package pomImplementation;

import java.util.Map;

import org.openqa.selenium.WebDriver;

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

		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		OrganizationPage org = new OrganizationPage(driver);
		NewOrganizationInfoPage newOrgInfo = new NewOrganizationInfoPage(driver);
		CreateNewOrganizationPage createOrg = new CreateNewOrganizationPage(driver);
		
		
//		WebElement loginpage = driver.findElement(By.xpath("//a[text()='vtiger']"));
		
		if (driver.getTitle().contains("vtiger"))
			System.out.println("Login page displayed");
		else
			System.out.println("Login page not displayed");

		String username = property.fetchDataFromProperties("username");
		String password = property.fetchDataFromProperties("password");
		login.loginToApp(username, password);
		

//		WebElement homepage = driver.findElement(By.xpath("//a[@class='hdrLink']"));
		if (driver.getTitle().equals("Home"))
			System.out.println("Home page displayed");
		else
			System.out.println("Home page not displayed");

		home.clickRequiredTab(TabNames.ORGANIZATIONS, webUtil);
		
		
//		driver.findElement(By.xpath("//a[text()='Organizations' and contains(@href,'Accounts&action')]")).click();
//		WebElement organizepage = driver.findElement(By.xpath("//a[@class='hdrLink']"));
		if (driver.getTitle().contains("Organizations"))
			System.out.println("Organizations page displayed succesfully");
		else
			System.out.println("Organizations page not displayed");
		
		org.clickPlusButton();

//		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
//		WebElement neworganizepage = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		
		if (driver.getTitle().equals("Creating New Organization"))
			System.out.println("creating new organization page displayed");
		else
			System.out.println("creating new organization page not displayed");
		
		Map<String, String> map = excel.getDataFromExcel("OrganizationsTestData",
				"Create Organization With Industry And Type");
		String orgName = map.get("Organization Name") + jutil.generateRandomNumber(100);
		createOrg.setOrgName(orgName);
		createOrg.selectIndustry(webUtil, map.get("Industry"));
		createOrg.selectType(webUtil, map.get("Type"));
		
		createOrg.clickSaveButton();
		
//		driver.findElement(By.name("accountname")).sendKeys(map.get("Organization Name"));
//		WebElement Field = driver.findElement(By.name("industry"));
//		Field.click();

//		Select s = new Select(Field);
//		s.selectByValue("Construction");
//		webUtil.dropdown("Construction", Field);
//
//		WebElement Type = driver.findElement(By.name("accounttype"));
//		Type.click();
////		Select p = new Select(Type);
////		p.selectByValue("Partner");
//		webUtil.dropdown("Partner", Type);
//
//		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
//		WebElement OrgSavepage = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		
		if (newOrgInfo.getPageHeader().contains(orgName)) {
			System.out.println("New Organization created");
			excel.writeDataToExcel("OrganizationsTestData", "Create Organization With Industry And Type", "Pass", IConstantPath.EXCEL_PATH);	
		}
		else
		{
			System.out.println("Organization not created");
			excel.writeDataToExcel("OrganizationsTestData", "Create Organization With Industry And Type", "Fail", IConstantPath.EXCEL_PATH);
		}

//		WebElement adminicon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
////		Actions a = new Actions(driver);
////		a.moveToElement(adminicon).perform();
//		webUtil.mouseHover(adminicon);
//		Thread.sleep(2000);
//
//		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

	
		home.signOutOfApp(webUtil);
		webUtil.closeAllWindows();
		excel.closeExcel();
	}
}
	
