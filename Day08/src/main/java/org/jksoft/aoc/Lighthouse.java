package org.jksoft.aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Lighthouse {

	private List<JunctionBox> boxes;
	private List<DistanceContainer> distances;
	private List<Circuit> circuits;
	
	private JunctionBox lastJunctionBoxA;
	private JunctionBox lastJunctionBoxB;

	public Lighthouse() {
		this.boxes = new ArrayList<>();
		this.distances = new ArrayList<DistanceContainer>();
		this.circuits = new ArrayList<Circuit>();
	}

	public void addBox(JunctionBox box) {
		this.boxes.add(box);
	}

	public JunctionBox getJunctionBox(int id) throws NoSuchElementException {
		return this.boxes.stream().filter(box -> box.getId() == id).findFirst()
				.orElseThrow(() -> new NoSuchElementException(String.format("no element with id '%d'", id)));
	}

	public void initBoxes() {
		for (int a = 0; a < this.boxes.size() - 1; a++) {
			for (int b = a + 1; b < this.boxes.size(); b++) {
				JunctionBox first = this.getJunctionBox(a);
				JunctionBox second = this.getJunctionBox(b);
				double d = first.getDistance(second);
				DistanceContainer dc = DistanceContainer.of(first, second, d);
				this.distances.add(dc);
			}
		}
		this.distances.sort(null);
		this.distances.forEach(System.out::println);

		for (JunctionBox junctionBox : boxes) {
			Circuit c = new Circuit(junctionBox.getId());
			c.addBox(junctionBox);
			this.circuits.add(c);
		}

	}

	public void connect() {
		int i = 0;
		while (this.circuits.size() > 1) {
			DistanceContainer dc = this.distances.get(0);
			JunctionBox a = dc.getA();
			JunctionBox b = dc.getB();
			Circuit ca = this.getCircuit(a);
			Circuit cb = this.getCircuit(b);
			if (!ca.equals(cb)) {
				System.out.println(String.format("mergeing '%s' && '%s'", a, b));
				this.lastJunctionBoxA = a;
				this.lastJunctionBoxB = b;
				this.mergeCircuits(ca, cb);
			}
			
			this.distances.remove(dc);
			i++;
			System.out.println(String.format("Iteration: [%d] with circuit count being [%d] and distance count being [%d]", i, this.circuits.size(), this.distances.size()));
		}

		this.circuits.sort(null);

		this.circuits.forEach(System.out::println);
	}

	public Long getResult() {
		Long result = Long.valueOf(this.lastJunctionBoxA.getX()) * Long.valueOf(this.lastJunctionBoxB.getX());
		
		return result;
	}

	private void mergeCircuits(Circuit ca, Circuit cb) {
		Circuit nc = Circuit.mergeCircuit(ca, cb);
		this.circuits.removeAll(List.of(ca, cb));
		this.circuits.add(nc);
	}

	private Circuit getCircuit(JunctionBox box) {
		Circuit c = this.circuits.stream().filter(circuit -> circuit.contains(box)).findFirst().get();
		return c;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (JunctionBox box : boxes) {
			sb.append(String.format("%s\n", box.toString()));
		}
		return sb.toString();
	}
}
