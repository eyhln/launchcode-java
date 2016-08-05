package greedy.calculator;

import static org.junit.Assert.*;

import java.util.Currency;

import org.junit.Before;
import org.junit.Test;

public class CoinCalculatorFactoryTest {

	CoinCalculatorFactory ccf;
	Currency USDollar;
	Currency Euro;
	USDollarCoinCalculator USDcalc;
	EuroCoinCalculator EURcalc;
	
	@Before
	public void initialize() {
		ccf = new CoinCalculatorFactory();
		USDollar = Currency.getInstance("USD");
		Euro = Currency.getInstance("EUR");
		USDcalc = new USDollarCoinCalculator();
		EURcalc = new EuroCoinCalculator();
	}
	
	@Test
	public void testReceiveCorrectClassUSD() {
		CoinCalculator coinCalc = ccf.getCoinCalculator(USDollar);
		assertSame(coinCalc.getClass(), USDcalc.getClass());
	}
	
	@Test
	public void testReceiveCorrectClassEuro() {
		CoinCalculator coinCalc = ccf.getCoinCalculator(Euro);
		assertSame(coinCalc.getClass(), EURcalc.getClass());
	}

}
