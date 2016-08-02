package greedy.calculator;

import java.util.Currency;
import java.util.Locale;

import greedy.coins.CoinSpecification;
import greedy.coins.EuroCoinSpecification;
import greedy.coins.USDollarCoinSpecification;

public class CoinCalculatorFactory {
	
	Currency USDollar;
	Currency Euro;
	
	public CoinCalculatorFactory() {
		USDollar = Currency.getInstance(Locale.US);
		Euro = Currency.getInstance(Locale.GERMANY);
	}
	
	public CoinCalculator getCoinSpecification(Currency currency) {
		if (currency.equals(USDollar)) {
			CoinSpecification coinSpec = new USDollarCoinSpecification();
			return new CoinCalculatorImpl(coinSpec.getCoinMap());
		}
		if (currency.equals(Euro)) {
			CoinSpecification coinSpec = new EuroCoinSpecification();
			return new CoinCalculatorImpl(coinSpec.getCoinMap());
		}
		return null;
	}

}
