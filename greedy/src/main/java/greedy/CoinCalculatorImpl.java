package greedy;

import java.util.Arrays;

public class CoinCalculatorImpl implements CoinCalculator {
	
	private int totalRemaining;
	int[] coinValues = {1,5,10,25,100}; 
    
    public int calculateChange(int nonNegativeAmountInCents) {
    	totalRemaining = nonNegativeAmountInCents;
    	processCoinValues();
    	int currCoinIndex = 0;
    	int minNumOfCoinsNeeded = 0;
		while (totalRemaining > 0) {
			int remainder = totalRemaining % coinValues[currCoinIndex];
			minNumOfCoinsNeeded += (totalRemaining - remainder) / coinValues[currCoinIndex];
			totalRemaining = remainder;
			currCoinIndex++;
		}
		return minNumOfCoinsNeeded;
    }
    
    void processCoinValues() {
    	Arrays.sort(coinValues);
    	int[] reverse = new int[coinValues.length];
    	for (int i = 0; i < reverse.length; i++) {
    		reverse[i] = coinValues[coinValues.length-1-i];
    	}
    	coinValues = reverse;
    }

}
