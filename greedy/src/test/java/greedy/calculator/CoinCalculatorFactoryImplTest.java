package greedy.calculator;

import static org.junit.Assert.*;

import java.util.Currency;

import org.junit.Before;
import org.junit.Test;

import greedy.calculator.factory.CoinCalculatorFactoryImpl;

public class CoinCalculatorFactoryImplTest {

	CoinCalculatorFactoryImpl ccf;
	
	@Before
	public void initialize() {
		ccf = new CoinCalculatorFactoryImpl();
	}
	
	@Test
	public void testReceiveCorrectClassUSD() {
		Currency USDollar = Currency.getInstance("USD");
		USDollarCoinCalculator USDcalc = new USDollarCoinCalculator();
		
		assertClassReturnedMatchesInputCurrency(USDollar, USDcalc);
	}
	
	@Test
	public void testReceiveCorrectClassEuro() {
		Currency Euro = Currency.getInstance("EUR");
		EuroCoinCalculator EURcalc = new EuroCoinCalculator();
		
		assertClassReturnedMatchesInputCurrency(Euro, EURcalc);
	}
	
	private void assertClassReturnedMatchesInputCurrency(Currency currency, 
			CoinCalculator coinCalculator) {
		CoinCalculator testCalculator = ccf.getCoinCalculator(currency);
		assertSame(coinCalculator.getClass(), testCalculator.getClass());
	}

}
