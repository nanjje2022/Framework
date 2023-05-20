package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadInfoPage 
{
	@FindBy(xpath = "//span[contains(text(),'Lead Information')]")
	private WebElement pageHeader;
	
	@FindBy(xpath = "//input[@title='Duplicate [Alt+U]']")
	private WebElement duplicateButton;
	
	public LeadInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
		
	public String getpageHeader() {
		return pageHeader.getText();
	}
		
	public void clickduplicateButton() {
		duplicateButton.click();
	}
}