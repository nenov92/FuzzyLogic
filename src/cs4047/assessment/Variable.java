package cs4047.assessment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Variable {
	private String name;
	private List<FuzzySet> tuples = new ArrayList<FuzzySet>();
	private Map<String, Double> membershipValues = new HashMap<String, Double>();

	public Variable() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FuzzySet> getTuples() {
		return tuples;
	}

	public void setTuples(List<FuzzySet> tuples) {
		this.tuples = tuples;
	}

	public void addTuple(FuzzySet t) {
		this.tuples.add(t);
	}

	public FuzzySet getTuple(String name) {
		for (FuzzySet tuple : tuples) {
			if (tuple.getName().equals(name)) {
				return tuple;
			}
		}
		return null;
	}

	public Map<String, Double> getMembershipValues() {
		return membershipValues;
	}

	public void setMembershipValues(Map<String, Double> membershipValues) {
		this.membershipValues = membershipValues;
	}

	public void addMembershipValue(String key, Double value) {
		this.membershipValues.put(key, value);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Variable Name: ");
		sb.append(getName());
		sb.append("; Fuzzy Sets: ");
		sb.append(getTuples());
		// sb.append("; Membership Values: ");
		// sb.append(getMembershipValues());
		return sb.toString();
	}
}
