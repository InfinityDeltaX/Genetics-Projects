import java.util.Arrays;

public class Specimen {
	
	
	static int propertiesCount = 10;
	double[] properties;
	static double[][] propertyRanges = randomPropertyRangeFootprint(); //new double[propertiesCount][2];
	
	public Specimen(double[] properties) {
		this.properties = properties;
	}
	
	public static double[][] randomPropertyRangeFootprint(){
		double[][] propertyRanges = new double[propertiesCount][2];
		
		for(int i = 0; i < propertiesCount; i++){
			propertyRanges[i][0] = Main.random.nextDouble();
			propertyRanges[i][1] = propertyRanges[i][0] + Main.random.nextDouble();
		}
		
		return propertyRanges;
	}
	
	public static Specimen getRandom(){
		double[] properties = new double[propertiesCount];
		for(int i = 0; i < propertiesCount; i++){
			properties[i] = Main.random.nextDouble()*(propertyRanges[i][1] - propertyRanges[i][0]) + propertyRanges[i][0];
		}
		
		return new Specimen(properties);
	}
	


	@Override
	public String toString() {
		return "Specimen [properties=" + Arrays.toString(properties) + "]";
	}
	
	
}
