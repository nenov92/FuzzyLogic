package cs4047.assessment;

public class Range {
	private String name;
	private double start;
	private double end;

	public Range() {
	}

	public Range(String name, double start, double end) {
		this.name = name;
		this.start = start;
		this.end = end;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getStart() {
		return start;
	}

	public void setStart(double start) {
		this.start = start;
	}

	public double getEnd() {
		return end;
	}

	public void setEnd(double end) {
		this.end = end;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Range Name: ");
		sb.append(getName());
		sb.append("Range Start: ");
		sb.append(getStart());
		sb.append("; Range End: ");
		sb.append(getEnd());
		return sb.toString();
	}
}
