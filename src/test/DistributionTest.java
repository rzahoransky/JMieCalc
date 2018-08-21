package test;

import jdistlib.LogNormal;
import jdistlib.generic.GenericDistribution;
import jdistlib.rng.RandomEngine;

public class DistributionTest {
	
	public static void main(String[] args) {
		GenericDistribution test = new LogNormal(0.1,0.1);
		RandomEngine engine = test.getRandomEngine();
		System.out.println(test.density(1.5, false)); //should be 0.025040
		System.out.println(test.density(1, false)); //should be 2.49171
	}

	public DistributionTest() {
		// TODO Auto-generated constructor stub
	}

}
