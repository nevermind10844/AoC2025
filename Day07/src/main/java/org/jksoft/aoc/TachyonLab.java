package org.jksoft.aoc;

import org.jksoft.utils.grid.Grid;
import org.jksoft.utils.grid.Tile;
import org.jksoft.utils.grid.TileType;

public class TachyonLab implements Runnable {
	private Grid grid;

	private int result;
	private long[][] arr;

	public TachyonLab(Grid grid) {
		this.grid = grid;
		this.result = 0;

		this.arr = new long[this.grid.getWidth()][this.grid.getHeight()];
	}

	@Override
	public void run() {
		System.out.println("firing up tachyon ray emitter");
		System.out.println("heating-sequnece succesfull");
		System.out.println("aperture closed for a concentrated beam");
		System.out.println("critical beam focus detected");
		System.out.println("shutter opened");

		Tile start = null;

		for (int i = 0; i < this.grid.getWidth(); i++) {
			Tile potTile = this.grid.getTile(i, 0);
			if (potTile.getType().equals(TileType.STAR)) {
				start = potTile;
				break;
			}
		}

		if (start == null)
			return;

		this.arr[start.getX()][start.getY()] = 1;
		this.analyzeGrid(start);
	}

	private void analyzeGrid(Tile start) {
		for (int y = 1; y < this.grid.getHeight(); y++) {
			for (int x = 0; x < this.grid.getWidth(); x++) {
				Tile t = this.grid.getTile(x, y);
				if (TileType.PLUS.equals(t.getType()))
					continue;
				else {
					long value = 0;
					if (x > 1 && this.grid.getWesternNeighbor(t).getType().equals(TileType.PLUS))
						value += this.arr[x - 1][y - 1];
					if (x < this.grid.getWidth() - 1 && this.grid.getEasternNeighbor(t).getType().equals(TileType.PLUS))
						value += this.arr[x + 1][y - 1];
					if (this.grid.getNorthernNeighbor(t).getType().equals(TileType.DOT))
						value += this.arr[x][y - 1];
					this.arr[x][y] = value;
				}
			}
		}

		long result = 0;
		for (int x = 0; x < this.grid.getWidth(); x++) {
			result += this.arr[x][this.grid.getHeight() - 1];
		}

		System.out.println(result);
	}

	public int getResult() {
		return this.result;
	}
}
