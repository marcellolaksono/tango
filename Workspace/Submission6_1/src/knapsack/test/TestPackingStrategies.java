package knapsack.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import knapsack.BruteForceStrategy;
import knapsack.Item;
import knapsack.PackingStrategy;
import knapsack.SimpleStrategy;
import knapsack.SortingStrategy;

public class TestPackingStrategies {

	private List<Item> testItems;

	private static final int[] WEIGHTS = { 3, 10, 4, 3, 5 };
	private static final int[] VALUES = { 6, 7, 7, 10, 9 };
	private static final int CAPACITY = 12;

	@Before
	public void setUp() throws Exception {
		testItems = new ArrayList<>();
		for (int i = 0; i < WEIGHTS.length; i++) {
			testItems.add(new Item(WEIGHTS[i], VALUES[i]));
		}
	}

	@After
	public void tearDown() throws Exception {
		testItems = null;
	}

	@Test
	public void testSimpleStrategy() {
		testStrategy(new SimpleStrategy(), 10, 23);
	}

	@Test
	public void testSortingStrategy() {
		testStrategy(new SortingStrategy(), 11, 25);
	}

	@Test
	public void testBruteForceStrategy() {
		testStrategy(new BruteForceStrategy(), 12, 26);
	}

	@Test
	public void testGetName() {
		Assert.assertTrue("Simple strategy getName() should contain \"simple\"",
				new SimpleStrategy().getName().toLowerCase().contains("simple"));
		Assert.assertTrue("Sorting strategy getName() should contain \"sorting\"",
				new SortingStrategy().getName().toLowerCase().contains("sorting"));
		Assert.assertTrue("Brute-force strategy getName() should contain \"brute-force\"",
				new BruteForceStrategy().getName().toLowerCase().contains("brute-force"));
	}

	private void testStrategy(PackingStrategy s, int expectedWeight, int expectedValue) {
		List<Item> testItemCache = new ArrayList<Item>(testItems);
		List<Item> result = s.packItems(CAPACITY, testItems);
		System.err.println(s.getName() + " " + result);
		Assert.assertEquals("Total weight is wrong", expectedWeight, getTotalWeight(result));
		Assert.assertEquals("Total value is wrong", expectedValue, getTotalValue(result));
		Assert.assertEquals("Item list is changed after running packItems", testItems, testItemCache);
	}

	private int getTotalWeight(List<Item> result) {
		int weight = 0;
		for (Item item : result) {
			weight += item.getWeight();
		}
		return weight;
	}

	private int getTotalValue(List<Item> result) {
		int value = 0;
		for (Item item : result) {
			value += item.getValue();
		}
		return value;
	}

}
