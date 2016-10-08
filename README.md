# Fuzzy Logic System Description

###Introduction

Fuzzy Logic is a problem-solving logical system that extends multivalued logic. Initially, this concept was presented as a mean to process data by allowing partial set membership rather than a crisp set membership or no membership at all. In general, Fuzzy Logic provides an easy way to get a precise result based upon vague and imprecise data. Fuzzy Logic’s approach to solve problems is very similar to human decision making, only much faster and accurate.
The next sections of this document will present both high-level and detailed designs of the Fuzzy Logic system, the tests performed in order to verify its correct behaviour and possible future improvements, which can be applied to it.

###High-level design of the system

My implementation of a Fuzzy Logic system aims to provide the user with a crisp value solution to a problem defied by a set of rules (in the form of IF X AND/OR Y AND/OR … THEN Z). The system can be granulated into the following high-level components Fuzzifier, Inference Engine and Defuzzifier. The role of the first component is to compute the membership value for each fuzzy set in a variable, given a crisp value for it. The Inference Engine then uses these membership values to calculate the membership value for the result fuzzy set of the rule. Finally, the Defuzzifier uses the Center of Area (CoA) defuzzification method to compute the crisp output value considering all inferred rules from the previous component.

###Detailed description of the system

In order to follow the principles of Object-oriented programming, maximise cohesion and minimise coupling, the high-level design of the fuzzy logic system was further granulated into the following core entities: Variable, FuzzySet, Range, Rule, Fuzzifier, InferenceEngine, Defuzzifier, Reader and SystemStarter.

  - Variable.java – this class represents a variable from the user input. It has a name field (e.g. driving) and a list of fuzzy sets that belong to it. In addition, this entity stores the calculated membership value for each fuzzy set.
  - FuzzySet.java – it is represented in the form of 4 tuple (a, b, α, β) and contains a list of three ranges – the increasing range of the set [a - α, a], the maximum range [a, b] and the decreasing range [b, b + β]. Also, the center of the set and the total length of the ranges are stored and can be used later on by the Defuzzifier. 
  - Range.java – describes a single range that is part of a FuzzySet. It has a type (increasing, maximum or decreasing), start and end points.
  - Rule.java – when the system reads the rules from the user input file, they are transformed into objects of this class. The Rule entity has a predicate map that stores any number of variables and their values, the logical connective used between these predicates (can be AND/OR), resultVariable and resultValue.
  - Fuzzifier.java – this entity contains calculateMembership method that uses the input variable’s crisp value and goes through all fuzzy sets of this variable to check if the value lies in the fuzzy set range. If so, the method checks which range (increasing, maximum or decreasing) the value belongs to and calculates the membership value using the relevant membership function. Otherwise, if the value is not part of the set, the membership value is 0. 
  - InferenceEngine.java – this class is responsible for the calculation of the membership value of the result variable for each rule in the user input file. InferenceEngine has a calculateRule method that takes a rule argument and gets the membership values for each variable in the rule. Next, depending on the logical connective it returns either the minimum or maximum membership value, which is the membership value of the result variable for its corresponding fuzzy set.
  - Defuzzifier.java – processes the computed fuzzy data from the InferenceEngine and produces a crisp output value given fuzzy sets and corresponding degrees of membership. The defuzzify method implements the Center of Area (CoA) function and depending on the shape of the set it uses different formulas to calculate the area of either triangle or a trapezoid. Finally, all areas multiplied by their center are added together and then divided by the sum of areas. The result is the crisp value output.
  - Reader.java – this class is used to collect all Fuzzy Logic data provided by the user in the form of text file. It reads the input file line by line and depending on the content it generates Rule objects, Variable objects with their corresponding fuzzy sets or a crisp input value pair with the variable name and its value. Also, the reader has a try/catch mechanism which notifies the user in case of improperly formatted file.
  - SystemStarter.java – the main method of the system which gets the file name from the user and then consequently runs its components – Reader, Fuzzifier, InferenceEngine and lastly Defuzzifier. 

###Testing
To test the solution, please use the "exmaple" text files.
