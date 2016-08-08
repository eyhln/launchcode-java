package greedy.calculator;

import java.util.*;

public abstract class CoinCalculator {

	int[] coinValues;
	String coinNameCodePrefix;
	
	private String[] coinNameCodes;
	private List<OutputEntry> coinsSelected;
	private int totalCentsRemaining;
	
	public CoinCalculator() {
		coinsSelected = new ArrayList<OutputEntry>();
	}

	public List<OutputEntry> calculateChange(int nonNegativeAmountInCents) {
  	Arrays.sort(coinValues);
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
    int currCoinIndex = coinValues.length - 1;
		while (totalCentsRemaining > 0) {
			useAsManyOfCoinAtIndexAsPossible(currCoinIndex);	
			currCoinIndex--;
		}
  }
    
  private void useAsManyOfCoinAtIndexAsPossible(int currCoinIndex) {
		int currCoin = coinValues[currCoinIndex];
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
