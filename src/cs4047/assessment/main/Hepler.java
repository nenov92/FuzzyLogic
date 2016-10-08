package cs4047.assessment.main;

import java.util.List;

public class Hepler {
	public static Double getMin(double a, double b) {
		if (a < b) {
			return a;
		}
		return b;
	}

	public static Double getMax(double a, double b) {
		if (a > b) {
			return a;
		}
		return b;
	}

	public static Double getMinimum(List<Double> doubles) {
		Double minimum = Double.MAX_VALUE;
		for (Double d : doubles) {
			if (d < minimum) {
				minimum = d;
			}
		}
		return minimum;
	}

	public static Double getMaximum(List<Double> doubles) {
		Double maximum = -Double.MIN_VALUE;
		for (Double d : doubles) {
			if (d > maximum) {
				maximum = d;
			}
		}
		return maximum;
	}
}
