package org.jksoft.aoc;

import java.util.List;

import org.jksoft.utils.grid.Grid;
import org.jksoft.utils.grid.MappingConfiguration;
import org.jksoft.utils.grid.Tile;
import org.jksoft.utils.grid.TileType;
import org.jksoft.utils.inputreader.InputReader;

public class Main {

	public static void main(String[] args) {
//		List<String> lines = InputReader.readTestInput();
		List<String> lines = InputReader.readInput();

		MappingConfiguration config = new MappingConfiguration();

		Grid grid = Grid.parse(lines, config);
		grid.setDiagonalNeighbors(true);
		
		System.out.println(grid.toString());

		for (int y = 0; y < grid.getHeight(); y++) {
			for (int x = 0; x < grid.getWidth(); x++) {
				Tile current = grid.getTile(x, y);
				if (current.getType().equals(TileType.AT) || current.getType().equals(TileType.HASH)) {
					List<Tile> neighbors = grid.getNeighbors(current);
					List<Tile> paperRollNeighbors = neighbors.stream()
							.filter(tile -> tile.getType().equals(TileType.AT) || tile.getType().equals(TileType.HASH))
							.toList();
					if (paperRollNeighbors.size() < 4) {
						current.setType(TileType.HASH);
					}
				}
			}
		}
		
		System.out.println(grid.toString());
		
		List<Tile> tiles = grid.findTiles(TileType.HASH);
		System.out.println(tiles.size());

	}

}
