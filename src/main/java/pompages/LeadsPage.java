package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsPage
{
	@FindBy(xpath = "//a[@class='hdrLink']")
	private WebElement pageHeader;
	
	@FindBy(xpath = "//img[@alt='Create Lead...']")
	private WebElement plusButton;
	
	@FindBy(xpath = "//table[@class='lvt small']/descendant::tr[last()]/descendant::input")
	private WebElement checkBox;
	
	@FindBy(xpath = "//input[@class='crmbutton small delete']")
	private WebElement deleteButton;
	
	public LeadsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	public void plusButton() {
		plusButton.click();
	}
	
	public void clickCheckBox() {
		checkBox.click();
	}
	
	public void clickDeleteButton() {
		deleteButton.click();
		
	}
}
