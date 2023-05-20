package testngImplementation;

import java.util.Map;

import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;
import genericLibraries.TabNames;

public class CreateAndDuplicateLeadTest extends BaseClass {

	public void createAndDuplicateLead() 
	{
		SoftAssert soft = new SoftAssert();
		home.clickRequiredTab(TabNames.LEADS, webUtil);
		
		soft.assertTrue(lead.getPageHeader().contains("Leads"));
		
		lead.plusButton();
		
		Map<String, String> map = excel.getDataFromExcel("LeadsTestData", "Create and Duplicate Lead");
		soft.assertTrue(createLead.getpageHeader().contains("Creating"));	
		String lastname = map.get("Last Name") + jutil.generateRandomNumber(100);
		createLead.setLastName(lastname);
		String company = map.get("Company")+ jutil.generateRandomNumber(100);
		createLead.setCompany(company);
		createLead.clickSaveButton();
		
		soft.assertTrue(leadInfo.getpageHeader().contains(lastname));
		leadInfo.clickduplicateButton();
		
		String newLastname = map.get("New Last Name") + jutil.generateRandomNumber(100);
		leadDuplicate.setLastnameTF(newLastname);
		leadDuplicate.clickSave();
		
		soft.assertTrue(leadInfo.getpageHeader().contains(newLastname));
		if(leadInfo.getpageHeader().contains(newLastname)) 
		{
			excel.writeDataToExcel("LeadsTestData", "Create and Duplicate Lead", "Pass", IConstantPath.EXCEL_PATH);
		}
		else {
			excel.writeDataToExcel("LeadsTestData", "Create and Duplicate Lead", "Fail", IConstantPath.EXCEL_PATH);	
		}
		
		soft.assertAll();

	}
}