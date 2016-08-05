package greedy.calculator;

import java.util.Currency;
import java.util.Locale;

import greedy.coins.EuroCoinSpecification;
import greedy.coins.USDollarCoinSpecification;

public class CoinCalculatorFactory {
	
	Currency USDollar;
	Currency Euro;
	
	public CoinCalculatorFactory() {
		USDollar = Currency.getInstance(Locale.US);
		Euro = Currency.getInstance(Locale.GERMANY);
	}
	
	public CoinCalculator getCoinCalculator(Currency currency) {
		if (currency.equals(USDollar)) {
			USDollarCoinSpecification coinSpec = new USDollarCoinSpecification();
			return new CoinCalculator(coinSpec.getCoinValues(), coinSpec.getCoinNameCodes());
		}
		if (currency.equals(Euro)) {
			EuroCoinSpecification coinSpec = new EuroCoinSpecification();
			return new CoinCalculator(coinSpec.getCoinValues(), coinSpec.getCoinNameCodes());
		}
		return null;
	}

}
