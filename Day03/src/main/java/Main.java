import java.math.BigInteger;
import java.util.List;

import org.jksoft.utils.inputreader.InputReader;

public class Main {

	public static void main(String[] args) {
//		List<String> lines = InputReader.readTestInput();
		List<String> lines = InputReader.readInput();
		lines.forEach(line -> System.out.println(line));

		int digits = 12;

		BigInteger result = new BigInteger("0");

		for (String line : lines) {
			int currentIndex = -1;
			String valueString = "";

			for (int i = 0; i < digits; i++) {
				currentIndex = Main.getIndexOfHighestDigit(line, currentIndex + 1, digits - i);
				valueString += String.valueOf(String.valueOf(line.charAt(currentIndex)));
			}
			
			BigInteger current = new BigInteger(valueString);
			System.out.println(current);
			result = result.add(current);
		}

		System.out.println(result);
	}

	public static int getIndexOfHighestDigit(String line, int startingIndex, int remainingDigits) {
		int digit = 0;
		int digitIndex = -1;
		for (int i = startingIndex; i < (line.length() - remainingDigits + 1); i++) {
			int valueAtIndex = Integer.valueOf(String.valueOf(line.charAt(i)));
			if (valueAtIndex > digit) {
				digit = valueAtIndex;
				digitIndex = i;
				System.out.println(String.format("new highest digit '%d' at index '%d'", digit, i));
				if (digit == 9)
					break;
			}
		}
		return digitIndex;
	}

}
