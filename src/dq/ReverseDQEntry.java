package dq;

import calculation.MieWrapper;

public class ReverseDQEntry implements Comparable<ReverseDQEntry>{

	private double sigma;
	private double dq;
	private double diameter;
	private MieWrapper wl1;
	private MieWrapper wl2;

	public ReverseDQEntry(double sigma, MieWrapper mie1, MieWrapper mie2) {
		this.sigma = sigma;
		this.dq = mie1.getIntegratedQext().get(sigma) / mie2.getIntegratedQext().get(sigma);
		this.diameter = mie1.getDiameter();
		wl1=mie1;
		wl2=mie2;
	}

	public double getSigma() {
		return sigma;
	}

	public double getDq() {
		return dq;
	}

	public double getDiameter() {
		return diameter;
	}

	@Override
	public int compareTo(ReverseDQEntry o) {
		return Double.compare(diameter, o.getDiameter());
	}
	
	public String toString() {
		return diameter+"μm("+sigma+")";
	}
	
	

}
