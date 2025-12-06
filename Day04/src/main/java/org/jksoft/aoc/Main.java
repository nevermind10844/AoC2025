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
		
		int initialNumberOfRolls = grid.findTiles(TileType.AT).size();
		
		int removed = Integer.MAX_VALUE;
		
		while(removed > 0) {
			removed = removeRolls(grid);
			System.out.println(String.format("%d rolls have been removed", removed));
		}
		
		int remainingNumberOfRolls = grid.findTiles(TileType.AT).size();
		
		int totalRemoved = initialNumberOfRolls - remainingNumberOfRolls;
		
		System.out.println(String.format("A total of %d rolls have been removed", totalRemoved));

	}
	
	public static int removeRolls(Grid grid) {
		List<Tile> before = grid.findTiles(TileType.AT);

		for (int y = 0; y < grid.getHeight(); y++) {
			for (int x = 0; x < grid.getWidth(); x++) {
				Tile current = grid.getTile(x, y);
				if (current.getType().equals(TileType.AT) || current.getType().equals(TileType.HASH)) {
					List<Tile> neighbors = grid.getNeighbors(current);
					List<Tile> paperRollNeighbors = neighbors.stream()
							.filter(tile -> tile.getType().equals(TileType.AT) || tile.getType().equals(TileType.HASH))
							.toList();
					if (paperRollNeighbors.size() < 4) {
						current.setType(TileType.DOT);
					}
				}
			}
		}
		
		System.out.println(grid.toString());
		
		List<Tile> after = grid.findTiles(TileType.AT);
		
		return before.size() - after.size();
		
	}

}
