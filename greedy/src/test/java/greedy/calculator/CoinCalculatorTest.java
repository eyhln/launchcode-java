package greedy.calculator;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class CoinCalculatorTest {

	CoinCalculator cc;
	HashMap<String,Integer> coinsNeeded;


	@Before
	public void initialize() {
		cc = new CoinCalculator(null, null);
		coinsNeeded = new HashMap<String,Integer>();
	}
	
	@Test
	public void testCalculateChangeZero() {
		assertEquals(coinsNeeded, cc.calculateChange(0));
	}
	
	@Test
	public void testCalculateChangeFive() {
		coinsNeeded.put("nickel", 1);
		assertEquals(coinsNeeded, cc.calculateChange(5));
	}
	
	@Test
	public void testCalculateChangeSix() {
		coinsNeeded.put("nickel", 1);
		coinsNeeded.put("penny", 1);
		assertEquals(coinsNeeded, cc.calculateChange(6));
	}
	
	@Test
	public void testCalculateChangeThirtyTwo() {
		coinsNeeded.put("nickel", 1);
		coinsNeeded.put("quarter", 1);
		coinsNeeded.put("penny", 2);
		assertEquals(coinsNeeded, cc.calculateChange(32));
	}
	
	@Test
	public void testCalculateChangeFourHundredEleven() {
		coinsNeeded.put("dollar",400);
		coinsNeeded.put("dime", 1);
		coinsNeeded.put("penny", 1);
		assertEquals(coinsNeeded, cc.calculateChange(40011));
	}
	


}
