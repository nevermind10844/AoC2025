package org.jksoft.aoc;

import java.util.List;

import org.jksoft.utils.grid.Grid;
import org.jksoft.utils.grid.MappingConfiguration;
import org.jksoft.utils.grid.TileType;
import org.jksoft.utils.inputreader.InputReader;

public class Main {

	public static void main(String[] args) {

		MappingConfiguration mappingConfig = new MappingConfiguration();
		mappingConfig.addMapping('S', TileType.STAR);
		mappingConfig.addMapping('^', TileType.PLUS);
		
//		List<String> reduced = InputReader.readTestInput(2);
		
//		List<String> reduced = InputReader.readTestInput().stream()
//				.filter(line -> line.contains("S") || line.contains("^")).toList();
		
		List<String> reduced = InputReader.readInput().stream()
				.filter(line -> line.contains("S") || line.contains("^")).toList();
		
		Grid grid = Grid.parse(reduced, mappingConfig);
		
		Thread t = new Thread(new TachyonLab(grid));
		t.start();
	}

}
