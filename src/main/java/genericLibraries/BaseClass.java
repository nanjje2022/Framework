package genericLibraries;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import pompages.ContactsPage;
import pompages.CreateContactPage;
import pompages.CreateNewOrganizationPage;
import pompages.CreateToDoPage;
import pompages.EventInfoPage;
import pompages.HomePage;
import pompages.LeadDuplicatePage;
import pompages.LeadInfoPage;
import pompages.LeadsPage;
import pompages.LoginPage;
import pompages.NewContactInfoPage;
import pompages.NewLeadPage;
import pompages.NewOrganizationInfoPage;
import pompages.OrganizationPage;

public class BaseClass {
	
	protected PropertiesUtility property;
	protected JavaUtility jutil;
	protected ExcelUtility excel;
	protected WebDriverUtility webUtil;
	protected WebDriver driver;
	protected LoginPage login;
	protected HomePage home;
	protected ContactsPage contact;
	protected CreateContactPage createContact;
	protected NewContactInfoPage newContactInfo;
	protected CreateToDoPage createToDo;
	protected EventInfoPage eventInfo;
	protected OrganizationPage org;
	protected CreateNewOrganizationPage createOrg;
	protected NewOrganizationInfoPage newOrgInfo;
	protected LeadsPage lead;
	protected LeadInfoPage leadInfo;
	protected LeadDuplicatePage leadDuplicate;
	protected NewLeadPage createLead;
	public static WebDriver sdriver;
	public static JavaUtility sjUtil;
	
	//@BeforeSuite
	//@BeforeTest
	@BeforeClass
	public void classConfiguration() {
		 property = new PropertiesUtility();
		 excel = new ExcelUtility();
		 jutil = new JavaUtility();
		 webUtil = new WebDriverUtility();
		
		sjUtil = jutil;
		 
		property.propertiesInit(IConstantPath.PROPERTIES_PATH);
		excel.excelInit(IConstantPath.EXCEL_PATH);
		
		String browser = property.fetchDataFromProperties("browser");
		String url = property.fetchDataFromProperties("url");
		long time = Long.parseLong(property.fetchDataFromProperties("timeouts"));
		
		driver = webUtil.openApplication(browser, url, time);
		sdriver = driver;
		Assert.assertTrue(driver.getTitle().contains("vtiger"));
	}
	@BeforeMethod
	public void methodConfiguration() {
		login = new LoginPage(driver);
		home = new HomePage(driver);
		contact = new ContactsPage(driver);
		createContact = new CreateContactPage(driver);
		newContactInfo = new NewContactInfoPage(driver);
		createToDo = new CreateToDoPage(driver);
		eventInfo = new EventInfoPage(driver);
		org = new OrganizationPage(driver);
		createOrg = new CreateNewOrganizationPage(driver);
		newOrgInfo = new NewOrganizationInfoPage(driver);
		lead = new LeadsPage(driver);
		leadInfo = new LeadInfoPage(driver);
		leadDuplicate = new LeadDuplicatePage(driver);
		createLead = new NewLeadPage(driver);
		
		String username = property.fetchDataFromProperties("username");
		String password = property.fetchDataFromProperties("password");
		login.loginToApp(username, password);
		Assert.assertTrue(driver.getTitle().contains("Home"));		
	}
	@AfterMethod
	public void methodTearDown() {
		home.signOutOfApp(webUtil);
	}
	@AfterClass
	public void classTearDown() {
		webUtil.closeAllWindows();
		excel.closeExcel();
	}
	//@AfterTest
	//@AfterSuite
}
