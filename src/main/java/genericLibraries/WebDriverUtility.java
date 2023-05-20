package genericLibraries;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


/**
 * This method contains all reusable methods to perform operations on web driver
 * 
 * @author nanjj
 *
 */
public class WebDriverUtility {
	
	private WebDriver driver;
	

	public WebDriver launchBrowser(String browser) {
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Invalid browser data");
		}
		return driver;
	}

	/**
	 * This method is used to maxomize the browser
	 */

	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}

	/**
	 * This method is used to navigate to the application
	 * 
	 * @param url
	 */
	public void navigateToApp(String url) {
		driver.get(url);
	}

	/**
	 * This method waits until element is found
	 * 
	 * @param time
	 */
	public void waitTillElementFound(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
	public WebDriver openApplication(String browser, String url, long time) {
		WebDriver driver = launchBrowser(browser);
		maximizeBrowser();
		navigateToApp(url);
		waitTillElementFound(time);
		return driver;
	}

	/**
	 * This method is used to wait till the element is visible
	 * 
	 * @param element
	 * @param time
	 * @param element
	 */

	public WebElement explicitWait(long time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.visibilityOf(element));

	}

	/**
	 * This method returns WebElement if it is enabled
	 * 
	 * @param element
	 * @param time
	 * @param time
	 */
	public WebElement explicitWait(WebElement element, long time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * 
	 * @param element
	 * @param time
	 * @param title
	 * 
	 */
	public boolean explicitWait(long time, String title) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.titleContains(title));
	}

	/**
	 * This method is used to mousehover on an element
	 * 
	 * @param element
	 */
	public void mouseHover(WebElement element) {
		Actions a = new Actions(driver);
		a.moveToElement(element).perform();

	}

	/**
	 * This method is used to right click on an element
	 * 
	 * @param element
	 */
	public void rightClick(WebElement element) {
		Actions a = new Actions(driver);
		a.contextClick(element).perform();
	}

	/**
	 * This method is used to doubleClick on an Element
	 * 
	 * @param element
	 */
	public void doubleClickOnElement(WebElement element) {
		Actions a = new Actions(driver);
		a.doubleClick(element).perform();
	}

	/**
	 * This method is used to drag and drop an element
	 * 
	 * @param source
	 * @param target
	 */
	public void drahAndDropElement(WebElement source, WebElement target) {
		Actions a = new Actions(driver);
		a.dragAndDrop(source, target).perform();
	}

	/**
	 * This method is used to select an element from drop down using index
	 * 
	 * @param element
	 * @param index
	 */
	public void dropdown(WebElement element, int index) {
		Select s = new Select(element);
		s.selectByIndex(index);
	}

	/**
	 * This method is used to select an element from drop down using value
	 * 
	 * @param value
	 * @param element
	 */
	public void dropdown(String value, WebElement element) {
		Select s = new Select(element);
		s.selectByValue(value);
	}

	/**
	 * This method is used to select an element from drop down using text
	 * 
	 * @param element
	 * @param text
	 */
	public void dropdown(WebElement element, String text) {
		Select s = new Select(element);
		s.selectByVisibleText(text);
	}

	/**
	 * This method is uded to switch to frame using index
	 * 
	 * @param index
	 */
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * This method is uded to switch to frame using id or name Attribute
	 * 
	 * @param idorName
	 */
	public void switchToFrame(String idorName) {
		driver.switchTo().frame(idorName);
	}
	
	/**
	 * This Method is used to scroll till the element
	 * @param element
	 */
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);

	}

	/**
	 * This method captures the screenshot and returns the absolute path
	 * 
	 * @param screenshotPath
	 */
	public String getScreenshot(String className, WebDriver driver, JavaUtility jUtil) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshot/"+className+"_"+jUtil.getCurrentTime()+".png");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dest.getAbsolutePath();

	}

	public String getScreenshot(WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		return ts.getScreenshotAs(OutputType.BASE64);
		
	}
	/**
	 * This method is used to handle alert popup
	 * 
	 * @param status
	 */
	public void handleAlert(String status) {
		Alert al = driver.switchTo().alert();
		if (status.equalsIgnoreCase("OK"))
			al.accept();
		else
			al.dismiss();
	}

	/**
	 * This method is used to switch to child window
	 */
	public void handleChildBrowserPopUp() {
		Set<String> windowIDs = driver.getWindowHandles();
		for (String id : windowIDs) {
			driver.switchTo().window(id);
		}
	}

	/**
	 * This method is used to get parent window id
	 */
	public String getParentWindowID() {
		return driver.getWindowHandle();
	}

	/**
	 * This method is used to switch to required window
	 * 
	 * @param id
	 */
	public void switchToWindow(String id) {
		driver.switchTo().window(id);
	}
	
	/**
	 * This method is used to convert dynamic xpath to WebElement
	 * @param dynamicPath
	 * @param replaceData
	 * @return
	 */
	public WebElement dynamicXpathConversion(String dynamicPath, String replaceData) {
		String requiredPath = String.format(dynamicPath, replaceData);
		WebElement element = driver.findElement(By.xpath(requiredPath));
		return element;
	}
	
	/**
	 * This method is used close current tab
	 */
	public void closeCurrentTab() {
		driver.close();
	}

	/**
	 * This method is used to close all tabs and quit browser
	 */

	public void closeAllWindows() {
		driver.quit();
	}
}
