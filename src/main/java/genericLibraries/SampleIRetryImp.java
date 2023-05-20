package genericLibraries;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class SampleIRetryImp implements IRetryAnalyzer {

	int count;
	int maxRetries=4;
	@Override 
	public boolean retry(ITestResult arg0) {
		if(count < maxRetries) {
			count++;
			return true;
		}
		
		return false;
	}
	
}
