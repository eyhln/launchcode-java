package greedy.calculator;

import java.util.Currency;

public class CoinCalculatorFactory {
	
	Currency USDollar;
	Currency Euro;
	
	public CoinCalculatorFactory() {
		USDollar = Currency.getInstance("USD");
		Euro = Currency.getInstance("EUR");
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
