package aoc.jk.day01;

import java.util.List;

import org.jksoft.utils.inputreader.InputReader;

public class Main {

	public static void main(String[] args) {
		List<String> lines = InputReader.readInput();
//		List<String> lines = InputReader.readTestInput();

		int value = 50;
		int count = 0;

		for (String string : lines) {
			int current = Integer.parseInt(string.replace('L', '-').replace('R', '+'));
			
			int turnovers = Math.floorDiv(Math.abs(current), 100);
			int mod = current % 100;
			
			int oldValue = value;
			
			value += mod;
			if (value < 0) {
				value += 100;
				if(oldValue != 0)
					turnovers++;
			} else if (value > 99) {
				value -= 100;
				turnovers++;
			} else if(value == 0) {
				turnovers++;
			}
			
			count += turnovers;
			
			System.out.println(value + " : " + current + " : " + turnovers);
		}

		System.out.println(count);

	}

}
