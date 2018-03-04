package main.eroberer;

import java.io.File;
import java.util.Scanner;

public class Bayesian {
	
	private File trainingFile;
	private String positiveClass;
	private String negativeClass;

	public Bayesian(String trainingFile, String positiveClass, String negativeClass) {
		this.trainingFile = new File(trainingFile);
		this.positiveClass = positiveClass;
		this.negativeClass = negativeClass;
	}

	public String getResult(String unknownData) {
		int numberOfRecord = 0;
		String[] attributes = unknownData.split(",");
		
		int[] positiveAttribute = new int[attributes.length];
		int numberOfPositive = 0;
		
		int[] negativeAttribute = new int[attributes.length];
		
		try (Scanner scan = new Scanner(trainingFile)) {
			
			String[] row;
			while (scan.hasNextLine()) {
				
				row = scan.nextLine().split(",");
				numberOfRecord++;
				
				if (row[row.length - 1].equals(positiveClass)) {
					numberOfPositive++;
				}
				
				for (int i = 0; i < attributes.length; i++) {
					if (row[row.length - 1].equals(positiveClass)) {
						if (attributes[i].equals(row[i])) 
							positiveAttribute[i] = positiveAttribute[i] + 1;
					} else if (attributes[i].equals(row[i])) {
						negativeAttribute[i] = negativeAttribute[i] + 1;
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return calculate(numberOfRecord, numberOfPositive, positiveAttribute, negativeAttribute);
	}
	
	private String calculate(int numberOfRecord, int numberOfPositive, int[] positive, int[] negative) {
		double positiveRate = positive[0] / (numberOfPositive * 1.0);
		double negativeRate = negative[0] / (numberOfRecord - numberOfPositive * 1.0);

		for (int i = 1; i < positive.length; i++) {
			positiveRate *= positive[i] / (numberOfPositive * 1.0);
			negativeRate *= negative[i] / (numberOfRecord - numberOfPositive * 1.0);
		}
		
		positiveRate *= numberOfPositive / (numberOfRecord * 1.0);
		negativeRate *= (numberOfRecord - numberOfPositive) / (numberOfRecord * 1.0);
		
		System.out.println("Positive Rate -> " + positiveRate);
		System.out.println("Negative Rate -> " + negativeRate);
		
		return positiveRate > negativeRate ? positiveClass : negativeClass;
	}
	
}
