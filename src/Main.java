import java.util.Random;

public class Main {

	public static Random random = new Random();
	
	public static void main(String[] args) {
		
		GUI.GUI();
		
		Generation a = Generation.randomGeneration();
		System.out.println(a.averageFitness());
		
		for(int i = 0; i < 10000; i++){
			a = a.generateOffspring(0.005);
			System.out.println(a.averageFitness());
		}
		
	}

}

