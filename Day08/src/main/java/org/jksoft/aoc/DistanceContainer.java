package org.jksoft.aoc;

public class DistanceContainer implements Comparable<DistanceContainer> {

	private JunctionBox a;
	private JunctionBox b;
	private double d;

	private DistanceContainer(JunctionBox a, JunctionBox b, double d) {
		this.a = a;
		this.b = b;
		this.d = d;
	}

	public static DistanceContainer of(JunctionBox a, JunctionBox b, double d) {
		return new DistanceContainer(a, b, d);
	}

	public JunctionBox getA() {
		return a;
	}

	public JunctionBox getB() {
		return b;
	}

	public double getD() {
		return d;
	}

	@Override
	public int compareTo(DistanceContainer o) {
		return Double.compare(this.d, o.getD());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof DistanceContainer other) {
			return this.a.getId() == other.getA().getId() 
					&& this.b.getId() == other.getB().getId();
		}
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("a [%d] ::  b [%d] :: c [%f]", this.a.getId(), this.b.getId(), this.d);
	}

}
