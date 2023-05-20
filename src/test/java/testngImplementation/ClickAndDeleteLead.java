package testngImplementation;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.TabNames;

public class ClickAndDeleteLead extends BaseClass {
	
	@Test
	public void clickDeleteLead() {
		
		SoftAssert soft = new SoftAssert();
		home.clickRequiredTab(TabNames.LEADS, webUtil);
		soft.assertTrue(driver.getTitle().contains("Leads"));
	
		lead.clickCheckBox();
		lead.clickDeleteButton();
		webUtil.handleAlert("OK");

		soft.assertAll();
	}
}
