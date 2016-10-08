package cs4047.assessment;

import java.util.ArrayList;
import java.util.List;

import cs4047.assessment.main.Constants;

public class FuzzySet {
	private String name;

	private double a;
	private double b;
	private double alpha;
	private double beta;

	private double centre;
	private double totalRangeLength;

	private Range increaseRange;
	private Range maximumRange;
	private Range decreaseRange;

	private List<Range> ranges = new ArrayList<Range>();

	public FuzzySet(String name, double a, double b, double alpha, double beta) {
		this.name = name;
		this.a = a;
		this.b = b;
		this.alpha = alpha;
		this.beta = beta;

		this.centre = ((a - alpha) + (b + beta)) / 2;

		this.increaseRange = new Range(Constants.INCREASING_RANGE, (a - alpha), a);
		this.maximumRange = new Range(Constants.MAXIMUM_RANGE, a, b);
		this.decreaseRange = new Range(Constants.DECREASING_RANGE, b, b + beta);

		this.ranges.add(increaseRange);
		this.ranges.add(maximumRange);
		this.ranges.add(decreaseRange);

		this.totalRangeLength = Math.abs(decreaseRange.getEnd() - increaseRange.getStart());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public double getBeta() {
		return beta;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}

	public double getCentre() {
		return centre;
	}

	public void setCentre(double centre) {
		this.centre = centre;
	}

	public double getTotalRangeLength() {
		return totalRangeLength;
	}

	public void setTotalRangeLength(double totalRangeLength) {
		this.totalRangeLength = totalRangeLength;
	}

	public Range getIncreaseRange() {
		return increaseRange;
	}

	public void setIncreaseRange(Range increaseRange) {
		this.increaseRange = increaseRange;
	}

	public Range getMaximumRange() {
		return maximumRange;
	}

	public void setMaximumRange(Range maximumRange) {
		this.maximumRange = maximumRange;
	}

	public Range getDecreaseRange() {
		return decreaseRange;
	}

	public void setDecreaseRange(Range decreaseRange) {
		this.decreaseRange = decreaseRange;
	}

	public List<Range> getRanges() {
		return ranges;
	}

	public void setRanges(List<Range> ranges) {
		this.ranges = ranges;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Tuple Name: ");
		sb.append(getName());
		sb.append("; a: ");
		sb.append(getA());
		sb.append("; b: ");
		sb.append(getB());
		sb.append("; alpha: ");
		sb.append(getAlpha());
		sb.append("; beta: ");
		sb.append(getBeta());
		// sb.append("; Increasing Range: ");
		// sb.append(getIncreaseRange());
		// sb.append("; Maximum Range: ");
		// sb.append(getMaximumRange());
		// sb.append("; Decreasing Range: ");
		// sb.append(getDecreaseRange());
		// sb.append("; Total Range: ");
		// sb.append(getTotalRangeLength());
		// sb.append("; Range Centre: ");
		// sb.append(getCentre());
		return sb.toString();
	}
}
