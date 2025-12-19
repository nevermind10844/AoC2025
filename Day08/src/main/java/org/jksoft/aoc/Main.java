package org.jksoft.aoc;

import java.util.Arrays;
import java.util.List;

import org.jksoft.utils.inputreader.InputReader;

public class Main {

	public static void main(String[] args) {
		
		Lighthouse lighthouse = new Lighthouse();
		
		List<String> lines = InputReader.readInput();
		for(int i=0; i< lines.size(); i++) {
			String line = lines.get(i);
			lighthouse.addBox(JunctionBox.of(i, Arrays.stream(line.split(","))
	                .mapToInt(Integer::parseInt)
	                .toArray()));
		}
		
		lighthouse.initBoxes();
		lighthouse.connect();
		Long result = lighthouse.getResult();
		System.out.println(result);
	}

}
