package org.jksoft.aoc;

import java.util.ArrayList;
import java.util.List;

public class Problem {
	private List<Long> numbers;
	private ProblemType problemType;
	
	public Problem() {
		this.numbers = new ArrayList<Long>();
	}
	
	public void addNumber(long number) {
		this.numbers.add(number);
	}
	
	public void setProblemType(ProblemType problemType) {
		this.problemType = problemType;
	}
	
	public Long solve() {
		Long result = this.problemType.equals(ProblemType.MULTIPLICATION) ? 1L : 0L;
		for (Long number : this.numbers) {
			if(this.problemType.equals(ProblemType.MULTIPLICATION))
				result *= number;
			else
				result+= number;
		}
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("Problem[%s with %d terms]", this.problemType.name(), this.numbers.size());
	}
}
