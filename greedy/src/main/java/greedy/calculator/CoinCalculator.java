package greedy.calculator;

import java.util.ArrayList;

public class CoinCalculator {

	private int[] coinValues;
	private String[] coinNameCodes;
	ArrayList<Object[]> coinsSelected;
	private int totalCentsRemaining;
	
	public CoinCalculator(int[] coinValues, String[] coinNameCodes) {
		this.coinValues = coinValues;
		this.coinNameCodes = coinNameCodes;
		coinsSelected = new ArrayList<Object[]>();
	}

	 public ArrayList<Object[]> calculateChange(int nonNegativeAmountInCents) {
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
				Object[] entry = {coinNameCodes[currCoinIndex], coinsUsed};
				coinsSelected.add(entry);
			}
	    }
}
