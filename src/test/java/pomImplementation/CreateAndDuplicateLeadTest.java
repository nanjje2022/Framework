package pomImplementation;

import java.util.Map;

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
import pompages.NewLeadPage;

public class CreateAndDuplicateLeadTest {

	public static void main(String[] args) 
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
		LeadsPage lead = new LeadsPage(driver);
		LeadInfoPage leadInfo = new LeadInfoPage(driver);
		LeadDuplicatePage leadDuplicate = new LeadDuplicatePage(driver);
		NewLeadPage createLead = new NewLeadPage(driver);
		
		if(driver.getTitle().equals("vtiger"))
			System.out.println("Login page displayed");
		else
		System.out.println("Login page not displayed");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
	
		if(driver.getTitle().equals("Home"))
			System.out.println("Home page displayed");
		else
			System.out.println("Home page not displayed");
		
		home.clickRequiredTab(TabNames.LEADS, webUtil);
		
		if(driver.getTitle().equals("Leads"))
			System.out.println("Lead page displayed");
		else
			System.out.println("Lead page not displayed");
		
		
		lead.plusButton();
		if(driver.getTitle().contains("Creating"))
			System.out.println("Creating lead page displayed");
		else
			System.out.println("Creating Lead page not displayed");
		
		Map<String, String> map = excel.getDataFromExcel("LeadsTestData", "Create and Duplicate Lead");
		String lastname = map.get("Last Name") + jutil.generateRandomNumber(100);
		createLead.setLastName(lastname);
		
		
		String company = map.get("Company")+ jutil.generateRandomNumber(100);
		createLead.setCompany(company);
		createLead.clickSaveButton();
		
		if(driver.getTitle().contains(lastname))
			System.out.println("Lead created succesfully");
		else
			System.out.println("Lead not created");
		
		leadInfo.clickduplicateButton();
		
		if(leadDuplicate.getpageHeader().contains("Duplicating"))
			
			System.out.println("Duplicate lead page displayed");
		else
			System.out.println("Duplicating lead page not displayed");
		
		String newLastname = map.get("New Last Name") + jutil.generateRandomNumber(100);
		leadDuplicate.setLastnameTF(newLastname);
		leadDuplicate.clickSave();
		
		
		if(leadInfo.getpageHeader().contains(newLastname)) 
		{
			System.out.println("New duplicate Lead Info page opened");
	
		}
		else {
			System.out.println("Dnew uplicate lead Info not opened");
		
		}
		
		home.signOutOfApp(webUtil);
		webUtil.closeAllWindows();
		excel.closeExcel();

	}
}