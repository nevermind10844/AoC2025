package aoc.jk.day02;

import java.util.List;

import org.jksoft.utils.inputreader.InputReader;

public class Main {

	public static void main(String[] args) {
		List<String> lines = InputReader.readInput();

		String[] ranges = lines.get(0).split(",");
		
		long result = 0;

		for (String range : ranges) {
			String[] split = range.split("-");

			long start = Long.parseLong(split[0]);
			long end = Long.parseLong(split[1]);

			for (long n = start; n <= end; n++) {
				String numberString = String.valueOf(n);
				if(numberString.length() % 2 == 0) {
					int halfLength = numberString.length() / 2;
					String firstHalf = numberString.substring(0, halfLength);
					String lastHalf = numberString.substring(halfLength, numberString.length());
					if(firstHalf.equals(lastHalf)) {
						result += n;
						System.out.println(n);
					}
				}
			}
		}
		
		System.out.println(result); //40398804950
	}

}
