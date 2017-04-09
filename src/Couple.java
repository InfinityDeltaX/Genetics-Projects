
public class Couple {
	Specimen a;
	Specimen b;
	
	
	public Couple(Specimen a, Specimen b) {
		this.a = a;
		this.b = b;
	}


	public Specimen generateOffspring(double mutationChance){
		double[] properties = new double[Specimen.propertiesCount];
		
		for(int i = 0; i < Specimen.propertiesCount; i++){
			double choice = Main.random.nextDouble();
			if(choice < (1 - mutationChance) / 2) properties[i] = a.properties[i];
			else if (choice < (1 - mutationChance)) properties[i] = b.properties[i];
			else properties[i] = Main.random.nextDouble()*(Specimen.propertyRanges[i][1] - Specimen.propertyRanges[i][0]) + Specimen.propertyRanges[i][0];
		}
		
		return new Specimen(properties);
	}
}
