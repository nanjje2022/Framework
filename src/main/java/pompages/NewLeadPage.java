package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewLeadPage 
{
	@FindBy(xpath = "//span[.='Creating New Lead']")
	private WebElement pageHeader;
	
	@FindBy(name = "lastname")
	private WebElement lastNameTF;
	
	@FindBy(name = "company")
	private WebElement companyTF;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	public NewLeadPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getpageHeader() {
		return pageHeader.getText();
	}
	
	public void setLastName(String lastName) {
		lastNameTF.sendKeys(lastName);
	}
	
	public void setCompany(String Company) {
		companyTF.sendKeys(Company);
	}
	
	public void clickSaveButton() {
		saveButton.click();
	}	
}
