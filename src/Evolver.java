import java.util.ArrayList;
import java.util.Arrays;

public class Evolver {
	
	public static double fitnessFunction(Specimen a){
		double count = 0;
		for(int i = 0; i < Specimen.propertiesCount; i++){
			count += a.properties[i];
		}
		return count;
	}
	
	public static Couple selectCouple(double[] probabilities, Generation inputs, ArrayList<Specimen> ranks) {
		float a = Main.random.nextFloat();
		int specimenA = Math.abs(Arrays.binarySearch(probabilities, a));
		int specimenB = -1;
		while (true) {
			double b = Main.random.nextDouble();
			 specimenB = Math.abs(Arrays.binarySearch(probabilities, b));
			if(specimenA != specimenB) break;
		}
		
		specimenA = Math.min(specimenA, ranks.size() - 1);
		specimenB = Math.min(specimenB, ranks.size() - 1);
		
		//System.out.printf("Selected: %d and %d", specimenA, specimenB);
		
		return new Couple(ranks.get(specimenA), ranks.get(specimenB));
	}
	
	public static double getProbabilityMating(int rank) {
		return 1.0d / (65*rank);
	}
	
	public static void populateProbabilites(double[] input) {
		double totalProbability = 0;
		for(int i = 0; i < input.length; i++){
			input[i] = getProbabilityMating(i+1);
			totalProbability += input[i];
		}
		
		double runningTotal = 0;
		
		for(int i = 0; i < input.length; i++){
			input[i] = (input[i] / totalProbability) + runningTotal;
			runningTotal += input[i] - runningTotal;
		}
	}
	
}
