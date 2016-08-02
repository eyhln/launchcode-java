package greedy.calculator;

import java.util.Currency;
import java.util.Locale;

import greedy.coins.CoinSpecification;
import greedy.coins.USDollarCoinSpecification;

public class CoinCalculatorFactory {
	
	Currency USDollar;
	
	public CoinCalculatorFactory() {
		USDollar = Currency.getInstance(Locale.US);
	}
	
	public CoinCalculator getCoinSpecification(Currency currency) {
		if (currency.equals(USDollar)) {
			CoinSpecification coinSpec = new USDollarCoinSpecification();
			return new CoinCalculatorImpl(coinSpec.getCoinMap());
		}
		return null;
	}

}
