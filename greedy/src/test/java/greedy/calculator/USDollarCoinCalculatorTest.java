package greedy.calculator;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

public class USDollarCoinCalculatorTest {

	CoinCalculator coinCalculator;
	List<OutputEntry> testOutput;
	List<OutputEntry> output;

	@Before
	public void initialize() {
		coinCalculator = new USDollarCoinCalculator();
		testOutput = new ArrayList<OutputEntry> ();
	}
	
	@Test
	public void testCalculateChangeThirtyTwo() {
		int valueInCents = 32;
		testOutput.add(new OutputEntry("USDollar.25", 1));
		testOutput.add(new OutputEntry("USDollar.5", 1));
		testOutput.add(new OutputEntry("USDollar.1", 2));

		output = coinCalculator.calculateChange(valueInCents);
		
		CoinCalculatorTest.assertAllEntriesMatch(testOutput, output);
	}
	
	@Test
	public void testCalculateChangeFourHundredEleven() {
		int valueInCents = 410;
		testOutput.add(new OutputEntry("USDollar.100", 4));
		testOutput.add(new OutputEntry("USDollar.10", 1));

		output = coinCalculator.calculateChange(valueInCents);
		
		CoinCalculatorTest.assertAllEntriesMatch(testOutput, output);
	}

}
