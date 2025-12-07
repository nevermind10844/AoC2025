package org.jksoft.aoc;

import java.util.ArrayList;
import java.util.List;

import org.jksoft.utils.grid.Grid;
import org.jksoft.utils.grid.MappingConfiguration;
import org.jksoft.utils.inputreader.InputReader;

public class Main {

	public static void main(String[] args) {
		List<String> lines = InputReader.readInput();
		List<Problem> problems = new ArrayList<Problem>();

		Grid grid = Grid.parse(lines, new MappingConfiguration());

		System.out.println(grid.toString());

		Problem p = new Problem();

		for (int x = grid.getWidth() - 1; x >= 0; x--) {
			String column = "";
			for (int y = 0; y < grid.getHeight(); y++) {
				char c = grid.getTile(x, y).getSymbol();
				if (c != ' ' && (c != '+' && c != '*')) {
					column += c;
				} else if (c == '+') {
					p.setProblemType(ProblemType.ADDITION);
					problems.add(p);
					p = new Problem();
				} else if (c == '*') {
					p.setProblemType(ProblemType.MULTIPLICATION);
					problems.add(p);
					p = new Problem();
				}
				if (y == grid.getHeight() - 2) {
					if (!column.isEmpty()) {
						Long number = Long.parseLong(column);
						p.addNumber(number);
					}
				}
			}
		}

		long result = problems.stream()
				.mapToLong(Problem::solve)
				.sum();

		System.out.println(result);
	}

}
