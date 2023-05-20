package pomImplementation;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import genericLibraries.ExcelUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertiesUtility;
import genericLibraries.WebDriverUtility;
import pompages.CreateToDoPage;
import pompages.EventInfoPage;
import pompages.HomePage;
import pompages.LoginPage;

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

		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		CreateToDoPage createToDo = new CreateToDoPage(driver);
		EventInfoPage eventInfo = new EventInfoPage(driver);

		if (driver.getTitle().contains("vtiger"))
			System.out.println("Login page displayed");
		else
			System.out.println("Login page not displayed");
		
		String username = property.fetchDataFromProperties("username");
		String password = property.fetchDataFromProperties("password");
		login.loginToApp(username, password);

		if (driver.getTitle().contains("Home"))
			System.out.println("Home page displayed");
		else
			System.out.println("Home page not displayed");

		Map<String,String> map = excel.getDataFromExcel("EventsTestData", "Create New Event");
		home.selectFromQuickCreate(webUtil, map.get("Quick Create"));

		if (createToDo.getPageHeader().equals("Create To Do"))
			System.out.println("create event page displayed");
		else
			System.out.println(" create event page not displayed");

		String subject = map.get("Subject")+ jutil.generateRandomNumber(100);
		createToDo.setSubject(subject);
		createToDo.clickStartDatePicker();
		createToDo.datePicker(map.get("Start Date"), webUtil);
		Thread.sleep(3000);
		createToDo.clickDueDatePicker();
		createToDo.datePicker(map.get("Due Date"), webUtil);
		createToDo.clickSave();
		
		if (eventInfo.getPageHeader().contains(subject)) {
			System.out.println("New event created succesfully");
			excel.writeDataToExcel("EventsTestData", "Create New Event", "Pass", IConstantPath.EXCEL_PATH);
		}
		else {
			System.out.println("New event not created");
			excel.writeDataToExcel("EventsTestData", "Create New Event", "Fail", IConstantPath.EXCEL_PATH);
		}

		home.signOutOfApp(webUtil);
		webUtil.closeAllWindows();
		excel.closeExcel();
	}
}
