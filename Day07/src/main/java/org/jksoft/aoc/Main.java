package org.jksoft.aoc;

import org.jksoft.utils.grid.Grid;
import org.jksoft.utils.grid.MappingConfiguration;
import org.jksoft.utils.grid.TileType;
import org.jksoft.utils.inputreader.InputReader;

public class Main {

	public static void main(String[] args) {
		
		MappingConfiguration mappingConfig = new MappingConfiguration();
		mappingConfig.addMapping('S', TileType.STAR);
		mappingConfig.addMapping('^', TileType.PLUS);
		Grid grid = Grid.parse(InputReader.readInput(), mappingConfig);
		
		TachyonLab lab = new TachyonLab(grid);
		lab.fire();
		
		int result = lab.getResult();
		System.out.println(result);
	}
	

}
