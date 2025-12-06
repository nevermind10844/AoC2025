package org.jksoft.aoc;

import java.util.ArrayList;
import java.util.List;

public class RangeManager {
	private List<Range> rangeList;

	public RangeManager() {
		this.rangeList = new ArrayList<Range>();
	}

	private void invalidate() {
		boolean merged = false;
		if (rangeList.size() > 1) {
			for (int i = 0; i < this.rangeList.size(); i++) {
				int overlapsWith = this.findOverlaps(this.rangeList.get(i));
				if (overlapsWith >= 0) {
					System.out.println(String.format("Rangemanager found an overlap between range %d and range %d", i, overlapsWith));
					this.mergeRanges(i, overlapsWith);
					merged = true;
					break;
				}
			}
		}
		if(merged)
			this.invalidate();
	}

	private int findOverlaps(Range range) {
		for (int i = 0; i < this.rangeList.size(); i++) {
			Range other = this.rangeList.get(i);
			if (!range.equals(other)) {
				if (other.getEnd() >= range.getStart() && other.getStart() <= range.getEnd()) {
					return i;
				}
			}
		}
		return -1;
	}

	private void mergeRanges(int a, int b) {
		Range rangeA = this.rangeList.get(a);
		Range rangeB = this.rangeList.get(b);
		
		System.out.println(String.format("Rangemanager merging ranges %s and %s", rangeA, rangeB));
		
		Range newRange = new Range();
		newRange.setStart(rangeA.getStart() < rangeB.getStart() ? rangeA.getStart() : rangeB.getStart());
		newRange.setEnd(rangeA.getEnd() > rangeB.getEnd() ? rangeA.getEnd() : rangeB.getEnd());
		this.rangeList.remove(rangeA);
		this.rangeList.remove(rangeB);
		this.rangeList.add(newRange);
		
		System.out.println(String.format("Replaced ranges with merged Range %s", newRange));
	}

	public void addRange(Range range) {
		this.rangeList.add(range);
		this.invalidate();
	}
	
	public long numberOfValidIds() {
		long result = 0;
		for (Range range : rangeList) {
			result += (range.getEnd() - range.getStart() + 1);
		}
		return result;
	}
}
