package practice;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners
public class TestClass2 {

	@Test 
	public void test1() {
		System.out.println("TestClass2 => test1");
	}
	
//	@Test(retryAnalyzer = genericLib.SampleRetryImp.class)
//	public void test2() {
//	System.out.println("test12");
//	Assert.fail();
//	}
}


