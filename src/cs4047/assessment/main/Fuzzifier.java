package cs4047.assessment.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cs4047.assessment.FuzzySet;
import cs4047.assessment.Range;
import cs4047.assessment.Variable;

public class Fuzzifier {
	private List<Variable> fuzzySets = new ArrayList<Variable>();
	private Map<String, Double> parameters = new HashMap<String, Double>();

	public Fuzzifier() {
	}

	public Fuzzifier(List<Variable> fuzzySets, Map<String, Double> parameters) {
		this.fuzzySets = fuzzySets;
		this.parameters = parameters;
	}

	public void calculateMembership() {
		Double value = null;
		for (Entry<String, Double> entry : parameters.entrySet()) {
			Variable fs = getFuzzySet(entry.getKey());
			for (FuzzySet tuple : fs.getTuples()) {
				for (Range range : tuple.getRanges()) {
					if (entry.getValue() >= range.getStart() && entry.getValue() <= range.getEnd()) {
						if (range.getName().equals(Constants.INCREASING_RANGE)) {
							value = (double) (entry.getValue() - tuple.getA() + tuple.getAlpha()) / tuple.getAlpha();
						} else if (range.getName().equals(Constants.MAXIMUM_RANGE)) {
							value = (double) 1;
						} else {
							value = (double) (tuple.getB() + tuple.getBeta() - entry.getValue()) / tuple.getBeta();
						}
					} else {
						value = (double) 0;
					}
					fs.addMembershipValue(tuple.getName(), value);
					if (value != 0) {
						break;
					}
				}
				value = null;
			}
			System.out.println("Membership values for fuzzy sets in " + fs.getName() + " variable: " + fs.getMembershipValues());
		}
	}

	public List<Variable> getFuzzySets() {
		return fuzzySets;
	}

	public void setFuzzySets(List<Variable> fuzzySets) {
		this.fuzzySets = fuzzySets;
	}

	public Variable getFuzzySet(String name) {
		for (Variable fs : fuzzySets) {
			if (fs.getName().equals(name)) {
				return fs;
			}
		}
		return null;
	}

	public Map<String, Double> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Double> parameters) {
		this.parameters = parameters;
	}

}
