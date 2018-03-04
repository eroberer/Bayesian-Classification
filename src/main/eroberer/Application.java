package main.eroberer;

public class Application {

	public static void main(String[] args) {
		String dataOfLivingThings = "data.txt";
		Bayesian bayesian = new Bayesian(dataOfLivingThings, "mammals", "non-mammals");
		
		// Give birth, Can fly, Live in water, Have legs
		// 0: no, 1: yes, 2: sometimes
		String unknownType = "1,0,1,0";
		
		String result = bayesian.getResult(unknownType);
		
		System.out.println(result);
	}

}
