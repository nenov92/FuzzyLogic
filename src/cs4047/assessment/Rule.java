package cs4047.assessment;

import java.util.HashMap;
import java.util.Map;

public class Rule {
	private int ruleNumber;
	private String logicalConnective;
	private String result;
	private String resultValue;
	private Map<String, String> conditions = new HashMap<String, String>();

	public Rule() {
	}

	public Rule(int ruleNumber, Map<String, String> conditions, String logicalConnective, String result, String resultValue) {
		this.ruleNumber = ruleNumber;
		this.setConditions(conditions);
		this.logicalConnective = logicalConnective;
		this.result = result;
		this.resultValue = resultValue;
	}

	public int getRuleNumber() {
		return ruleNumber;
	}

	public void setRuleNumber(int ruleNumber) {
		this.ruleNumber = ruleNumber;
	}

	public String getLogicalConnective() {
		return logicalConnective;
	}

	public void setLogicalConnective(String logicalConnective) {
		this.logicalConnective = logicalConnective;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResultValue() {
		return resultValue;
	}

	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}

	public Map<String, String> getConditions() {
		return conditions;
	}

	public void setConditions(Map<String, String> conditions) {
		this.conditions = conditions;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Rule Number: ");
		sb.append(getRuleNumber());
		sb.append("; Conditions: ");
		sb.append(getConditions());
		sb.append("; Logical Connective: ");
		sb.append(getLogicalConnective());
		sb.append("; Result Variable: ");
		sb.append(getResult());
		sb.append("; Result Value: ");
		sb.append(getResultValue());
		return sb.toString();
	}
}
