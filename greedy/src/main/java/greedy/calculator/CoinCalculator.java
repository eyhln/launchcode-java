package greedy.calculator;

import java.util.HashMap;

public class CoinCalculator {

	private int[] coinValues;
	private String[] coinNameCodes;
	HashMap<String,Integer> coinsSelected;
	private int totalCentsRemaining;
	
	public CoinCalculator(int[] coinValues, String[] coinNameCodes) {
		this.coinValues = coinValues;
		this.coinNameCodes = coinNameCodes;
		coinsSelected = new HashMap<String,Integer>();
	}

	 public HashMap<String,Integer> calculateChange(int nonNegativeAmountInCents) {
	    	totalCentsRemaining = nonNegativeAmountInCents;
	    	selectMinNumberOfCoins();
			return coinsSelected;
	    }
	    
	    void selectMinNumberOfCoins() {
	    	int currCoinIndex = 0;
			while (totalCentsRemaining > 0) {
				useAsManyOfCoinAtIndexAsPossible(currCoinIndex);	
				currCoinIndex++;
			}
	    }
	    
	    private void useAsManyOfCoinAtIndexAsPossible(int currCoinIndex) {
			int currCoin = (int)coinValues[currCoinIndex];
			int remainder = totalCentsRemaining % currCoin;
			int coinsUsed = (totalCentsRemaining - remainder) / currCoin;
			addCoinNameAndAmountToOutput(currCoinIndex, coinsUsed);
			totalCentsRemaining = remainder;
	    }
	    
	    private void addCoinNameAndAmountToOutput(int currCoinIndex, int coinsUsed) {
			if (coinsUsed > 0) {
				coinsSelected.put(coinNameCodes[currCoinIndex], coinsUsed);
			}
	    }
}
