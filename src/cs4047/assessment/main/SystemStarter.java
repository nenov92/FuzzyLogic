package cs4047.assessment.main;

import cs4047.assessment.Variable;

public class SystemStarter {

	public static void main(String[] args) {
//		if (args.length == 1) {
//			Reader.readFile(args[0]);
//			Variable resultSet = Reader.calculateResultSet();
//			Defuzzifier defuzzifier = new Defuzzifier(resultSet);
//			Double result = defuzzifier.defuzzify();
//
//			System.out.println("Defuzzifier");
//			System.out.println("Crisp Value for " + Reader.ruleName + " is: " + result);
//		}
		Reader.readFile("example.txt");
		Variable resultSet = Reader.calculateResultSet();
		Defuzzifier defuzzifier = new Defuzzifier(resultSet);
		Double result = defuzzifier.defuzzify();

		System.out.println("Defuzzifier");
		System.out.println("Crisp Value for " + Reader.ruleName + " is: " + result);
	}

}
