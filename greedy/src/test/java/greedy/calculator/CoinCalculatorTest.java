package greedy.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.*;
import org.junit.Before;
import org.junit.Test;

public class CoinCalculatorTest {

	CoinCalculator coinCalculator;
	List<OutputEntry> testOutput;
	List<OutputEntry> output;

	@Before
	public void initialize() {
		coinCalculator = new CoinCalculatorTestImpl();
		testOutput = new ArrayList<OutputEntry>();
	}
	
	static void assertAllEntriesMatch(List<OutputEntry> testOutput,
			List<OutputEntry> output){
		for (int i = 0; i < output.size(); i++) {
			assertEquals("Failure at index " + i , 
					testOutput.get(i).toString(), output.get(i).toString());
		}
	}
	
	@Test
	public void testCreateNameCodes() {
		int[] simpleCoinValues = {0, 1};
		coinCalculator.coinValues = simpleCoinValues;
		coinCalculator.coinNameCodePrefix = "test";
		
		String[] nameCodes = coinCalculator.createNameCodes();
		
		assertEquals("test.0", nameCodes[0]);
		assertEquals("test.1", nameCodes[1]);
	}
	
	@Test
	public void testThrowsExceptionNegativeInput() {
		try {
			coinCalculator.calculateChange(-100);
			fail("Failed to throw an exception");
		} catch (IllegalArgumentException expected) {
		}
	}
	
	@Test
	public void testCalculateChangeZero() {
		assertEquals(testOutput, coinCalculator.calculateChange(0));
	}
	
	@Test
	public void testCalculateChangeEleven() {
		int valueInCents = 11;
		testOutput.add(new OutputEntry("Test.10", 1));
		testOutput.add(new OutputEntry("Test.1", 1));

		output = coinCalculator.calculateChange(valueInCents);
		
		CoinCalculatorTest.assertAllEntriesMatch(testOutput, output);
	}
		
	private class CoinCalculatorTestImpl extends CoinCalculator {
		
		public CoinCalculatorTestImpl() {
			coinValues = new int[] { 1, 10 };
			coinNameCodePrefix = "Test";
		}
	}

}
