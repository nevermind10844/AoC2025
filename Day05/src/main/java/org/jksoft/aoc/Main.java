package org.jksoft.aoc;

import java.util.List;

import org.jksoft.utils.inputreader.InputReader;

public class Main {

	public static void main(String[] args) {
		List<String> lines = InputReader.readTestInput();
//		List<String> lines = InputReader.readInput();

		RangeManager rangeManager = new RangeManager();

		for (String line : lines) {
			if (line.isEmpty()) {
				break;
			}

			long start = Long.parseLong(line.split("-")[0]);
			long end = Long.parseLong(line.split("-")[1]);
			Range range = new Range();
			range.setStart(start);
			range.setEnd(end);
			rangeManager.addRange(range);
		}

		System.out.println(rangeManager.numberOfValidIds());
	}
}
