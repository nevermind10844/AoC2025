package aoc.jk.day02;

import java.util.List;

import org.jksoft.utils.inputreader.InputReader;

public class Main {

	public static void main(String[] args) {
//		List<String> lines = InputReader.readTestInput();
		List<String> lines = InputReader.readInput();

		String[] ranges = lines.get(0).split(",");

		long result = 0;

		// go through all ranges
		for (String range : ranges) {
			String[] split = range.split("-");

			// find start and end values
			long start = Long.parseLong(split[0]);
			long end = Long.parseLong(split[1]);

			// find the maximum length of a possible pattern
			int maxLength = split[1].length();
			int halfLengthPlusOne = maxLength / 2 + 1;

			// iterate over all the possible numbers
			for (long n = start; n <= end; n++) {
				// iterate over all possible pattern lengths
				// System.out.println("number: " + n);
				String numberString = Long.toString(n);

				for (int i = 1; i <= halfLengthPlusOne; i++) {
					int patternLength = i;
					if (numberString.length() > patternLength) {
						String pattern = numberString.substring(0, i);
						// System.out.println("pattern: " + pattern);
						// only proceed if the length of the number is evenly divisible by the length of
						// the pattern
						if (numberString.length() % patternLength == 0) {
							int reps = numberString.length() / patternLength;
							// number of reps must be at least 2 for the number to be invalid
							if (reps > 1) {
								boolean invalid = true;
								for (int r = 0; r < reps; r++) {
									String chunk = numberString.substring(r * patternLength,
											r * patternLength + patternLength);
									// System.out.println("chunk:" + chunk);
									if (!chunk.equals(pattern)) {
										invalid = false;
										break;
									}
								}
								if (invalid) {
									System.out.println(String.format("number %d is invalid using pattern %s times %d",
											n, pattern, reps));
									result += n;
									break;
								}
							}
						}
					}
				}
			}

		}

		System.out.println("result: " + result);

	}

}
