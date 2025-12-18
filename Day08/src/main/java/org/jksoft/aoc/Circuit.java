package org.jksoft.aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Circuit implements Comparable<Circuit>{
	private int id;
	private List<JunctionBox> boxes;

	public Circuit(int id) {
		this.id = id;
		this.boxes = new ArrayList<>();
	}
	
	public int getId() {
		return this.id;
	}

	public void addBox(JunctionBox box) {
		this.boxes.add(box);
	}
	
	public List<JunctionBox> getBoxes(){
		return this.boxes;
	}

	public boolean contains(JunctionBox box) {
		return this.boxes.contains(box);
	}
	
	public static Circuit mergeCircuit(Circuit a, Circuit b) {
		Circuit c = new Circuit(a.getId());
		for (JunctionBox junctionBox : a.getBoxes()) {
			c.addBox(junctionBox);
		}
		for (JunctionBox junctionBox : b.getBoxes()) {
			c.addBox(junctionBox);
		}
		return c;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Circuit with id '%d' containing '%d' JunctionBoxes [\n", this.id, this.boxes.size()));
		for (JunctionBox junctionBox : boxes) {
			sb.append(String.format("  %s", junctionBox.toString()));
		}
		sb.append("\n]");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circuit other = (Circuit) obj;
		return id == other.id;
	}

	@Override
	public int compareTo(Circuit o) {
		return -Integer.compare(this.boxes.size(), o.getBoxes().size());
	}
}
