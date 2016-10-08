package cs4047.assessment.main;

import java.util.Map;

import cs4047.assessment.FuzzySet;
import cs4047.assessment.Variable;

public class Defuzzifier {
	private Variable resultSet;

	public Defuzzifier() {
	}

	public Defuzzifier(Variable resultSet) {
		this.resultSet = resultSet;
	}

	public Variable getResultSet() {
		return resultSet;
	}

	public void setResultSet(Variable resultSet) {
		this.resultSet = resultSet;
	}

	public Double defuzzify() {
		double output = 0;
		double area = 0;
		double localArea = 0;
		double areaByCentre = 0;

		for (Map.Entry<String, Double> entry : resultSet.getMembershipValues().entrySet()) {
			FuzzySet tuple = resultSet.getTuple(entry.getKey());
			if (tuple.getA() == tuple.getB()) {
				localArea = (0.5 * 1 * tuple.getTotalRangeLength())
						- (0.5 * (1 - entry.getValue()) * (1 - entry.getValue()) * tuple.getTotalRangeLength());
			} else {
				localArea = (0.5 * 1 * (tuple.getTotalRangeLength() + (tuple.getB() - tuple.getA())))
						- (0.5 * (1 - entry.getValue()) * ((1 - entry.getValue()) * tuple.getTotalRangeLength() + (tuple.getB() - tuple.getA())));
			}
			area += localArea;
			areaByCentre += localArea * tuple.getCentre();
		}

		output = areaByCentre / area;

		return output;
	}
}
