package org.jksoft.aoc;

import org.jksoft.utils.grid.Grid;
import org.jksoft.utils.grid.Tile;
import org.jksoft.utils.grid.TileType;

public class TachyonLab {
	private Grid grid;
	
	private int splits;
	
	public TachyonLab(Grid grid) {
		this.grid = grid;
		this.splits = 0;
	}
	
	public void fire() {
		System.out.println("firing up tachyon ray emitter");
		System.out.println("heating-sequnece succesfull");
		System.out.println("aperture closed for a concentrated beam");
		System.out.println("critical beam focus detected");
		System.out.println("shutter opened");
		Tile tile = this.grid.findTile(TileType.STAR);
		followRay(tile);
		System.out.println(this.grid);
	}
	
	private void followRay(Tile current) {
		if(current.getY() >= this.grid.getHeight() - 1) {
			return;
		}
		
		Tile below = this.grid.getSouthernNeighbor(current);
		if(below.getType().equals(TileType.DOT)) {
			below.setType(TileType.PIPE);
			followRay(below);
		} else if(below.getType().equals(TileType.PLUS)){
			this.splits++;
			Tile left = this.grid.getWesternNeighbor(below);
			if(left.getType().equals(TileType.DOT)) {
				left.setType(TileType.PIPE);
				followRay(left);
			}
			Tile right = this.grid.getEasternNeighbor(below);
			if(right.getType().equals(TileType.DOT)) {
				right.setType(TileType.PIPE);
				followRay(right);
			}
		}
	}
	
	
	public int getResult() {
		return this.splits;
	}
}
