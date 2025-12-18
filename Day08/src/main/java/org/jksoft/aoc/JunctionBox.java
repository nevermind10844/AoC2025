package org.jksoft.aoc;

public class JunctionBox {
	private int id;
	private int x;
	private int y;
	private int z;
	
	public JunctionBox(int id, int x, int y, int z) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static JunctionBox of(int id, int[] values) {
		return new JunctionBox(id, values[0], values[1], values[2]);
	}
	
	public int getId() {
		return id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
	
	public double getDistance(JunctionBox other) {
		return JunctionBox.getDistance(this, other);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s %d [%d, %d, %d]",
				this.getClass().getSimpleName(), this.id, this.x, this.y, this.z);
	}
	
	public static double getDistance(JunctionBox a, JunctionBox b) {
		double dx = b.getX() - a.getX();
		double dy = b.getY() - a.getY();
		double dz = b.getZ() - a.getZ();
		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	
}
