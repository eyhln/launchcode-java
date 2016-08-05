package greedy.calculator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Before;

public class EuroCoinCalculatorTest {
	
	CoinCalculator cc;
	HashMap<String,Integer> coinsNeeded;


	@Before
	public void initialize() {
		cc = new EuroCoinCalculator();
		coinsNeeded = new HashMap<String,Integer>();
	}
	
	@Test
	public void testCalculateChangeZero() {
		assertEquals(coinsNeeded, cc.calculateChange(0));
	}
	
	@Test
	public void testCalculateChangeFive() {
		coinsNeeded.put("coin.4", 1);
		assertEquals(coinsNeeded, cc.calculateChange(5));
	}
	
	@Test
	public void testCalculateChangeSix() {
		coinsNeeded.put("coin.4", 1);
		coinsNeeded.put("coin.5", 1);
		assertEquals(coinsNeeded, cc.calculateChange(6));
	}
	
	@Test
	public void testCalculateChangeThirtyTwo() {
		coinsNeeded.put("coin.4", 1);
		coinsNeeded.put("coin.2", 1);
		coinsNeeded.put("coin.5", 2);
		assertEquals(coinsNeeded, cc.calculateChange(32));
	}
	
	@Test
	public void testCalculateChangeFourHundredEleven() {
		coinsNeeded.put("coin.1",400);
		coinsNeeded.put("coin.3", 1);
		coinsNeeded.put("coin.5", 1);
		assertEquals(coinsNeeded, cc.calculateChange(40011));
	}
	

}
