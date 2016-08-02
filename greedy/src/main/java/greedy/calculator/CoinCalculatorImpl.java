package greedy.calculator;

import java.util.Arrays;
import java.util.HashMap;

public class CoinCalculatorImpl implements CoinCalculator {
	
	private int totalCentsRemaining;
	HashMap<Integer,String> coinTypesAvailable;
	Object[] coinValues;
	HashMap<String,Integer> coinsSelected;
	
	public CoinCalculatorImpl(HashMap<Integer,String> coinSpecification) {
		coinTypesAvailable = coinSpecification;
		coinsSelected = new HashMap<String,Integer>();
	}

    public HashMap<String,Integer> calculateChange(int nonNegativeAmountInCents) {
    	totalCentsRemaining = nonNegativeAmountInCents;
    	initializeCoinValues();
    	processCoinValues();
    	selectMinNumberOfCoins();
		return coinsSelected;
    }
    
	private void initializeCoinValues() {
		coinValues = coinTypesAvailable.keySet().toArray();
	}
	
    void processCoinValues() {
    	Arrays.sort(coinValues);
    	Object[] reverse = new Object[coinValues.length];
    	for (int i = 0; i < reverse.length; i++) {
    		reverse[i] = (int)coinValues[coinValues.length-1-i];
    	}
    	coinValues = reverse;
    }
    
    void selectMinNumberOfCoins() {
    	int currCoinIndex = 0;
		while (totalCentsRemaining > 0) {
			int currCoin = (int)coinValues[currCoinIndex];
			int remainder = totalCentsRemaining % currCoin;
			int coinsUsed = (totalCentsRemaining - remainder) / currCoin;
			if (coinsUsed > 0) {
				coinsSelected.put(coinTypesAvailable.get(currCoin), coinsUsed);
			}
			totalCentsRemaining = remainder;
			currCoinIndex++;
		}
    }
    

}
