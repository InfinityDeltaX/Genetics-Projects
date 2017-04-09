import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Generation {
	
	Map<Specimen, Double> population = new HashMap<>();
	
	static int generationSize = 100;
	private boolean fitnessEvaluated = false;
	private boolean ranked = false;
	
	private int tooBigToFail = 3;
	
	private ArrayList<Specimen> rankings;
	
	public Generation(ArrayList<Specimen> population) {
		
		for(Specimen s : population) {
			this.population.put(s, -1.0);
		}
		
		//this.population = Collections.unmodifiableMap(this.population);
	}
	
	public double averageFitness() {
		//System.out.println(computeFitness().values());
		return computeFitness().values().stream().collect(Collectors.averagingDouble((a) -> a));
	}

	public Map<Specimen, Double> computeFitness(){
		if(fitnessEvaluated) return population;
		
		for(Specimen s : population.keySet()) {
			population.put(s, Evolver.fitnessFunction(s));
		}
		
		fitnessEvaluated = true;
		return population;
	}
	
	public ArrayList<Specimen> rank() {
		if(ranked) return rankings;
		
		
		ArrayList<Specimen> out = new ArrayList<>();
		out.addAll(population.keySet());
		//System.out.println(out);
		out.sort(new Comparator<Specimen>(){

			@Override
			public int compare(Specimen o1, Specimen o2) {
				return Double.compare(population.get(o2), population.get(o1));
			}
		});
		
		ranked = true;
		rankings = out;
		return rank();
	}

	public Generation generateOffspring(double d) {
		double[] probabilities = new double[population.size()];
		Evolver.populateProbabilites(probabilities);
		
		ArrayList<Specimen> nextGeneration = new ArrayList<>();
		
		for(int i = 0; i < tooBigToFail; i++){
			nextGeneration.add(rank().get(i));
		}
		
		while (nextGeneration.size() < generationSize) { 
			nextGeneration.add(Evolver.selectCouple(probabilities, this, rank()).generateOffspring(d));
		}
		
		return new Generation(nextGeneration);
	}
	
	public static Generation randomGeneration(){
		ArrayList<Specimen> population = new ArrayList<Specimen>();
		
		for(int i = 0; i < generationSize; i++) {
			population.add(Specimen.getRandom());
		}
		
		return new Generation(population);
	}
	
}
