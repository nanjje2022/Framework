package genericLibImplementation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericLibraries.ExcelUtility;
import genericLibraries.IConstantPath;
import genericLibraries.PropertiesUtility;
import genericLibraries.WebDriverUtility;

public class ClickAndDeleteLead {

	public static void main(String[] args) throws InterruptedException {

		PropertiesUtility property = new PropertiesUtility();
		ExcelUtility excel = new ExcelUtility();
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

		driver.findElement(By.xpath("//a[text()='Leads' and contains(@href,'Leads&action')]")).click();
		WebElement leadpage = driver.findElement(By.xpath("//a[@class='hdrLink']"));
		if (leadpage.getText().equals("Leads"))
			System.out.println("Lead page displayed");
		else
			System.out.println("Lead page not displayed");

		WebElement checkbox = driver
				.findElement(By.xpath("//tr[2]/following-sibling::tr[1]" + "/descendant::input[@type='checkbox']"));
		checkbox.click();
		if (checkbox.isSelected())
			System.out.println("Checkbox clicked");
		else
			System.out.println("checkbox not selected");

		
		driver.findElement(By.xpath("//input[@value='Delete']")).click();
		webUtil.handleAlert("OK");
		Thread.sleep(4000);
//		Alert al = driver.switchTo().alert();
//		System.out.println(al.getText());
//		al.accept();

		WebElement AdminIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//		Actions a = new Actions(driver);
//		a.moveToElement(AdminIcon).perform();
		
		webUtil.mouseHover(AdminIcon);
		driver.findElement(By.xpath("//a[text()='Sign Out']"));
		
		webUtil.closeAllWindows();
		

	}

}
