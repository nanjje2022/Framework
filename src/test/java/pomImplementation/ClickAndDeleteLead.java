package pomImplementation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import genericLibraries.ExcelUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertiesUtility;
import genericLibraries.TabNames;
import genericLibraries.WebDriverUtility;
import pompages.HomePage;
import pompages.LeadDuplicatePage;
import pompages.LeadInfoPage;
import pompages.LeadsPage;
import pompages.LoginPage;

public class ClickAndDeleteLead {

	public static void main(String[] args) throws InterruptedException {

		PropertiesUtility property = new PropertiesUtility();
		ExcelUtility excel = new ExcelUtility();
		WebDriverUtility webUtil = new WebDriverUtility();
		JavaUtility jutil = new JavaUtility(); 
		
		property.propertiesInit(IConstantPath.PROPERTIES_PATH);
		excel.excelInit(IConstantPath.EXCEL_PATH);
		
		String browser = property.fetchDataFromProperties("browser");
		String url = property.fetchDataFromProperties("url");
		long time = Long.parseLong(property.fetchDataFromProperties("timeouts"));
		
		WebDriver driver = webUtil.openApplication(browser, url, time);
		
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		LeadsPage lead = new LeadsPage(driver);
		LeadInfoPage leadInfo = new LeadInfoPage(driver);
		LeadDuplicatePage leadDuplicate = new LeadDuplicatePage(driver);
		
		if (driver.getTitle().equals("vtiger"))
			System.out.println("Login page displayed");
		else
			System.out.println("Login page not displayed");

		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();

		if (driver.getTitle().equals("Home"))
			System.out.println("Home page displayed");
		else
			System.out.println("Home page not displayed");

		home.clickRequiredTab(TabNames.LEADS, webUtil);
		
		if (lead.getPageHeader().contains("Leads"))
			System.out.println("Lead page displayed");
		else
			System.out.println("Lead page not displayed");
	
		lead.clickCheckBox();
		lead.clickDeleteButton();

		webUtil.handleAlert("OK");
		home.signOutOfApp(webUtil);
		webUtil.closeAllWindows();	
	}
}
