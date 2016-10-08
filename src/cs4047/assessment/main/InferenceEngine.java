package cs4047.assessment.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs4047.assessment.Rule;

public class InferenceEngine {
	private Fuzzifier fuzzifier;

	public InferenceEngine() {
	}

	public InferenceEngine(Fuzzifier fuzzifier) {
		this.fuzzifier = fuzzifier;
	}

	public Map<String, Double> calculateRule(Rule rule) {
		Map<String, Double> resultMap = new HashMap<String, Double>();
		List<Double> arguments = new ArrayList<Double>();

		for (Map.Entry<String, String> map : rule.getConditions().entrySet()) {
			double argument = fuzzifier.getFuzzySet(map.getKey()).getMembershipValues().get(map.getValue());
			arguments.add(argument);
		}

		if (rule.getLogicalConnective() != null && rule.getLogicalConnective().equals(Constants.CONJUNCTION)) {
			resultMap.put(rule.getResultValue(), Hepler.getMinimum(arguments));
		} else if (rule.getLogicalConnective() != null && rule.getLogicalConnective().equals(Constants.DISJUNCTION)) {
			resultMap.put(rule.getResultValue(), Hepler.getMaximum(arguments));
		} else {
			resultMap.put(rule.getResultValue(), arguments.get(0));
		}

		System.out.println("Rule: " + rule.getRuleNumber() + "; Result: " + resultMap);
		return resultMap;
	}
}
