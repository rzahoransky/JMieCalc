package dq;

import calculation.MieWrapper;

/**contains a mapping between particle size and DQ**/
public class ReverseDQEntry implements Comparable<ReverseDQEntry>{

	private double sigma;
	private double dq;
	private double diameter;
	private MieWrapper wl1;
	private MieWrapper wl2;

	public ReverseDQEntry(double sigma, MieWrapper mie1, MieWrapper mie2) {
		this.sigma = sigma;
		this.dq = mie1.getIntegratedQext().get(sigma) / mie2.getIntegratedQext().get(sigma);
		this.diameter = mie1.getRadius() * 2;
		wl1=mie1;
		wl2=mie2;
	}

	public double getSigma() {
		return sigma;
	}

	public double getDq() {
		return dq;
	}

	/**returns the diameter**/
	public double getDiameter() { //DQ Entry contains diameter as this is what we want to read as particle size
		return diameter;
	}
	
	/**returns the radius**/
	public double getRadius() {
		return diameter / 2.0;
	}

	@Override
	public int compareTo(ReverseDQEntry o) {
		return Double.compare(diameter, o.getDiameter());
	}
	
	public String toString() {
		return diameter+"μm("+sigma+")";
	}
}
