package greedy.calculator;

import java.util.*;

public abstract class CoinCalculator {

	int[] coinValues;
	String coinNameCodePrefix;
	
	private String[] coinNameCodes;
	private int totalCentsRemaining;
	private List<OutputEntry> coinsSelected;

	public CoinCalculator() {
		coinsSelected = new ArrayList<OutputEntry>();
	}

	public List<OutputEntry> calculateChange(int amountInCents) {
		setUpVariables(amountInCents);
  	selectMinNumberOfCoins();
		return coinsSelected;
	}
	
	private void setUpVariables(int amountInCents) {
		Arrays.sort(coinValues);
		if (amountInCents < 0)
			throw new IllegalArgumentException("negative amount " + amountInCents);
  	totalCentsRemaining = amountInCents;
  	coinNameCodes = createNameCodes();
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
