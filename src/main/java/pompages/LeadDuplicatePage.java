package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadDuplicatePage 
{
	@FindBy(xpath = "//span[contains(text(),'Duplicating')]")
	private WebElement pageHeader;
	
	@FindBy(name = "lastname")
	private WebElement lastNameTF;
	
	@FindBy(name = "company")
	private WebElement companyTF;
	
	@FindBy(xpath ="//input[@class='crmButton small save']")
	private WebElement saveButton;
	
	public LeadDuplicatePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getpageHeader() {
		return pageHeader.getText();
	}
	
	public void setLastnameTF(String lastname) {
	lastNameTF.clear();
	lastNameTF.sendKeys(lastname);
	}
	
	public void setCompanyTF(String company) {
		companyTF.clear();
		companyTF.sendKeys(company);
	}
	
	public void clickSave() {
		saveButton.click();
	}
}