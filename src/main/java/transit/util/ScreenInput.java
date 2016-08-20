package transit.util;

import transit.AppInput;
import java.util.Scanner;

public class ScreenInput implements AppInput {
	
	Scanner scanner;
	
	public ScreenInput() {
		scanner = new Scanner(System.in);
	}

	@Override
	public String getUserMetrolinkLocation() {
		String input = scanner.nextLine();
		return input;
	}
	
}
