package greedy.calculator;

import java.util.ArrayList;

public abstract class CoinCalculator {

	int[] coinValues;
	String coinNameCodePrefix;
	String[] coinNameCodes;
	ArrayList<OutputEntry> coinsSelected;
	int totalCentsRemaining;
	
	public CoinCalculator() {
		coinsSelected = new ArrayList<OutputEntry>();
	}

	public ArrayList<OutputEntry> calculateChange(int nonNegativeAmountInCents) {
    	totalCentsRemaining = nonNegativeAmountInCents;
    	coinNameCodes = createNameCodes();
    	selectMinNumberOfCoins();
		return coinsSelected;
	}
	
	String[] createNameCodes() {
		String[] nameCodes = new String[coinValues.length];
		for (int i = 0; i < nameCodes.length; i++) {
			nameCodes[i] = coinNameCodePrefix + "." + coinValues[i];
		}
		return nameCodes;
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
			OutputEntry entry = new OutputEntry(coinNameCodes[currCoinIndex], coinsUsed);
			coinsSelected.add(entry);
		}
    }
}
