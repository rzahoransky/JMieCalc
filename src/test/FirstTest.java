package test;

import calculation.MieWrapper;

public class FirstTest {

	//sizes in ym!
	public static void main(String[] args) {
		MieWrapper test = new MieWrapper();
		test.setHostRefractiveIndex(1.33);
		test.setRefractiveIndex(1.4, 0);
		int count=0;
		double start=0.01;
		double end=100;
		//double step=(end-start)/1000;
		double step=0.00009253;
		double wavelength=0.674;
		for (double i=start;i<end;i=i+step) {
			count++;
			test.setRadiusWavelength(i,wavelength);
			test.calcScattCoeffs();
			System.out.println(i+" "+test.qext());
		}
		
		
		//System.out.println(count);
		//test = new Mie();
		test.setHostRefractiveIndex(1.33);
		test.setRadiusWavelength(0.50118723,0.674);
		test.setRefractiveIndex(1.4, 0);
		System.out.println(test.isCalculated());
		test.calcScattCoeffs();
		System.out.println(test.isCalculated());
		System.out.println(test.qext());
	}

}
