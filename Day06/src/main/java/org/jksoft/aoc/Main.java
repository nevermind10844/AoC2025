package org.jksoft.aoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.jksoft.utils.inputreader.InputReader;

public class Main {

	public static void main(String[] args) {
		List<String> lines = InputReader.readInput();
		List<String> trimmed = new ArrayList<String>();
		
		for (String line : lines) {
			trimmed.add(line.replaceAll(" +", " ").trim());
		}
		
		int numberOfProblems = trimmed.get(0).split(" ").length;
		int numberOfTerms = trimmed.size();
		
		Map<Integer, String[]> splitLines = new HashMap<Integer, String[]>();
		for(int i=0; i<trimmed.size(); i++) {
			splitLines.put(i, trimmed.get(i).split(" "));
		}
		
		Map<Integer, Problem> problemList = new HashMap<>();
		
		for(int j=0; j<numberOfProblems; j++) {
			Problem p = new Problem();
			problemList.put(j, p);
			
			for(int i=0; i<numberOfTerms; i++) {
//				System.out.println(i + "::" + j + " -> " + splitLines.get(i)[j]);
				if(i == (numberOfTerms-1)) {
					if((splitLines.get(i)[j]).equals("+"))
						p.setProblemType(ProblemType.ADDITION);
					else
						p.setProblemType(ProblemType.MULTIPLICATION);
				} else {
					p.addNumber(Long.parseLong(splitLines.get(i)[j])); 
				}	
			}
		}
		
		AtomicLong result = new AtomicLong(0);
		problemList.forEach((k, v) -> {
			System.out.println(v);
			Long partial = v.solve();
			System.out.println("result: " + partial);
			result.addAndGet(partial);
		});
		
		System.out.println(result);
	}

}
