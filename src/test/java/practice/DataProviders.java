package practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviders {

	@Test(dataProvider ="data")
	public void ticketBookingTest(String src, String dest) 
	{
		System.out.println("From: "+src+"\tTo: "+dest);
	}
	
	@DataProvider
	public Object[][] data() {
		Object[][] obj = new Object[3][2];
		
		obj[0][0] = "Bangalore";
		obj[0][1] = "Kochi";
		
		obj[1][0] = "Bangalore";
		obj[1][1] = "Hyderabad";
		
		obj[2][0] = "Bangalore";
		obj[2][1] = "Chennai";
		
		return obj;
		
	}
}
