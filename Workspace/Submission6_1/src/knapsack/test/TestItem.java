package knapsack.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import knapsack.Item;

public class TestItem {

	private static final int WEIGHT1 = 10;
	private static final int WEIGHT2 = 5;
	private static final int VALUE1 = 3;
	private static final int VALUE2 = 5;
	
	private Item testItem1, testItem2;
	
	@Before
	public void setUp() {
		testItem1 = new Item(WEIGHT1, VALUE1);
		testItem2 = new Item(WEIGHT2, VALUE2);
	}
	
	@After
	public void tearDown() {
		testItem1 = null;
		testItem2 = null;
	}
	
	@Test
	public void testGetWeight() {
		Assert.assertEquals("getWeight() returns wrong value", WEIGHT1, testItem1.getWeight());
	}

	@Test
	public void testGetValue() {
		Assert.assertEquals("getValue() returns wrong value", VALUE2, testItem2.getValue());
	}

	@Test
	public void testToString() {
		String s = testItem1.toString();
		Assert.assertTrue("toString() doesn't contain weight", s.contains(String.valueOf(WEIGHT1)));
		Assert.assertTrue("toString() doesn't contain value", s.contains(String.valueOf(VALUE1)));
	}

	@Test
	public void testCompareTo() {
		System.err.println(testItem2.compareTo(testItem1));
		Assert.assertEquals("Comparing to self does not return zero", 0, testItem1.compareTo(testItem1));
		Assert.assertTrue("Comparison should be < 0", testItem1.compareTo(testItem2) < 0);
		Assert.assertTrue("Comparison should be > 0", testItem2.compareTo(testItem1) > 0);
	}

	@Test
	public void testGetId() {
		Assert.assertFalse("IDs of separate items should be different", testItem1.getId() == testItem2.getId());
	}

}
