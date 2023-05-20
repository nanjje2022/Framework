package practice;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericLibraries.SampleBaseClass;


@Listeners
public class TestClass extends SampleBaseClass {

	@Test 
	public void test1() {
		System.out.println("test1");
	}
	
	@Test
	public void test2() {
		System.out.println("test2");
		Assert.fail();
	}
}
