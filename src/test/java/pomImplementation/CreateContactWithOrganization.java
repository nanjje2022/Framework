package pomImplementation;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import genericLibraries.ExcelUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertiesUtility;
import genericLibraries.TabNames;
import genericLibraries.WebDriverUtility;
import pompages.ContactsPage;
import pompages.CreateContactPage;
import pompages.HomePage;
import pompages.LoginPage;
import pompages.NewContactInfoPage;

public class CreateContactWithOrganization {

	public static void main(String[] args) throws InterruptedException 
	{
		
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
		ContactsPage contact =new ContactsPage(driver);
		CreateContactPage createContact = new CreateContactPage(driver);
		NewContactInfoPage newContactInfo = new NewContactInfoPage(driver);
		
//		WebElement loginpage = driver.findElement(By.xpath("//a[text()='vtiger']"));
		if(driver.getTitle().equals("vtiger"))
			System.out.println("Login page displayed");
		else
		System.out.println("Login page not displayed");
		
		String username = property.fetchDataFromProperties("username");
		String password = property.fetchDataFromProperties("password");
		login.loginToApp(username, password);
		
//		WebElement homepage = driver.findElement(By.xpath("//a[@class='hdrLink']"));
		
		if(driver.getTitle().equals("Home"))
			System.out.println("Home page displayed");
		else
			System.out.println("Home page not displayed");
		
//		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
//		WebElement Contacthomepage = driver.findElement(By.xpath("//a[@class='hdrLink']"));
		
		home.clickRequiredTab(TabNames.CONTACTS, webUtil);
		
		if(driver.getTitle().contains("Contacts"))
			System.out.println("Contact page displayed succesfully");
		else
			System.out.println("Contact page not displayed");
		
		contact.clickPlusButton();
		
		
//		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
//		WebElement ContactInfopage = driver.findElement(By.xpath("//td[text()=' Contact Information']"));
		
		
		if(createContact.getPageHeader().contains("Creating"))
		System.out.println("Creating new organization page displayed");
		else
		System.out.println("Creating new organization page not displayed");
				
//		Random random = new Random();
		Map<String, String> map = excel.getDataFromExcel("ContactsTestData", "Create Contact With Organization");
		String contactName = map.get("Last Name") + jutil.generateRandomNumber(100);
		createContact.setLastName(contactName);
		createContact.selectExistingOrganization(webUtil, map.get("Organization Name"));
		createContact.clickSave();
				
		if(newContactInfo.getPageHeader().contains(contactName)) {
			System.out.println("New Contact created succesfully");
			excel.writeDataToExcel("ContactsTestData", "Create Contact With Organization", "pass", IConstantPath.EXCEL_PATH);
		} 
		else {
			System.out.println("New Contact not created");
			excel.writeDataToExcel("ContactsTestData", "Create Contact With Organization", "fail", IConstantPath.EXCEL_PATH);
		}
		
		home.signOutOfApp(webUtil);
		webUtil.closeAllWindows();
		excel.closeExcel();
	}
}
