package cs4047.assessment.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs4047.assessment.Variable;
import cs4047.assessment.Rule;
import cs4047.assessment.FuzzySet;

public class Reader {

	private static List<Rule> rules = new ArrayList<Rule>();
	private static List<Variable> fuzzySets = new ArrayList<Variable>();
	private static Map<String, Double> parameters = new HashMap<String, Double>();
	private static Fuzzifier fuzzifier;
	private static InferenceEngine inferenceEngine;
	public static String ruleName;

	public static void readFile(String fileName) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = reader.readLine();
			
			String[] lineContent;
			List<String> extractedContent = new ArrayList<String>();
			String logicalConnective;

			Rule rule;
			FuzzySet tuple;
			Variable set;

			Map<String, String> conditions;

			ruleName = line;
			
			line = reader.readLine();
			line = reader.readLine();
			while (line != null) {
				lineContent = line.split(" ");
				if (line.startsWith(Constants.RULE)) {

					for (String s : lineContent) {
						if (!s.equalsIgnoreCase("rule") && !s.equalsIgnoreCase("if") && !s.equalsIgnoreCase("the") && !s.equalsIgnoreCase("is")
								&& !s.equalsIgnoreCase("will") && !s.equalsIgnoreCase("be")) {
							extractedContent.add(s);
						}
					}

					if (extractedContent.contains(Constants.CONJUNCTION) || extractedContent.contains(Constants.DISJUNCTION)) {
						if (extractedContent.contains(Constants.CONJUNCTION)) {
							logicalConnective = Constants.CONJUNCTION;
							extractedContent.removeAll(Collections.singleton(Constants.CONJUNCTION));
						} else {
							logicalConnective = Constants.DISJUNCTION;
							extractedContent.removeAll(Collections.singleton(Constants.DISJUNCTION));
						}

						conditions = new HashMap<String, String>();

						for (int i = 1; i < extractedContent.indexOf("then") - 1; i+=2) {
							conditions.put(extractedContent.get(i), extractedContent.get(i + 1));
						}

						rule = new Rule(Integer.parseInt(extractedContent.get(0)), conditions, logicalConnective,
								extractedContent.get(extractedContent.indexOf("then") + 1),
								extractedContent.get(extractedContent.indexOf("then") + 2));
					} else {
						conditions = new HashMap<String, String>();
						conditions.put(extractedContent.get(1), extractedContent.get(2));
						rule = new Rule(Integer.parseInt(extractedContent.get(0)), conditions, null,
								extractedContent.get(extractedContent.size() - 2), extractedContent.get(extractedContent.size() - 1));
					}

					rules.add(rule);
					System.out.println(rule);
				} else if (lineContent.length == 1 && !lineContent[0].equals("")) {
					set = new Variable();
					set.setName(lineContent[0]);

					line = reader.readLine();
					line = reader.readLine();

					while (!line.equals("")) {
						lineContent = line.split(" ");

						for (String s : lineContent) {
							extractedContent.add(s);
						}

						tuple = new FuzzySet(extractedContent.get(0), Double.parseDouble(extractedContent.get(1)), Double.parseDouble(extractedContent
								.get(2)), Double.parseDouble(extractedContent.get(3)), Double.parseDouble(extractedContent.get(4)));
						set.addTuple(tuple);
						line = reader.readLine();
						extractedContent = new ArrayList<String>();
					}
					
					fuzzySets.add(set);
				} else {
					if (line.contains("=")) {
						lineContent = line.split(" ");
						parameters.put(lineContent[0], Double.parseDouble(lineContent[2]));
					}
				}

				line = reader.readLine();
				extractedContent = new ArrayList<String>();
			}
		} catch (Exception e) {
			System.out.println("File Format Error!");
		}
		System.out.println();
	}

	public static Variable calculateResultSet() {
		fuzzifier = new Fuzzifier(fuzzySets, parameters);
		inferenceEngine = new InferenceEngine(fuzzifier);
		
		System.out.println("Fuzzifier");
		fuzzifier.calculateMembership();
		
		System.out.println();
		System.out.println("Inference Engine");
		
		Variable resultSet = fuzzifier.getFuzzySet(rules.get(0).getResult());

		for (Rule rule : rules) {
			Map<String, Double> resultMap = inferenceEngine.calculateRule(rule);
			
			for (Map.Entry<String, Double> map : resultMap.entrySet()){
				if (resultSet.getMembershipValues().containsKey(map.getKey())) {
					Double storedValue = resultSet.getMembershipValues().get(map.getKey());
					resultSet.getMembershipValues().remove(resultMap);
					Double finalValue = Hepler.getMax(storedValue, map.getValue());
					resultMap.put(rule.getResultValue(), finalValue);
				}
			}
			
			resultSet.getMembershipValues().putAll(resultMap);
		}

		System.out.println();

		return resultSet;
	}
}
