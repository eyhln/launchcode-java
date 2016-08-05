package greedy.calculator;

import java.util.Currency;
import java.util.Locale;

public class CoinCalculatorFactory {
	
	Currency USDollar;
	Currency Euro;
	
	public CoinCalculatorFactory() {
		USDollar = Currency.getInstance(Locale.US);
		Euro = Currency.getInstance(Locale.GERMANY);
	}
	
	public CoinCalculator getCoinCalculator(Currency currency) {
		if (currency.equals(USDollar)) {
			return new USDollarCoinCalculator();
		}
		if (currency.equals(Euro)) {
			return new EuroCoinCalculator();
		}
		return null;
	}

}
