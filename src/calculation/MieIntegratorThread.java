package calculation;

import jdistlib.generic.GenericDistribution;
import presets.DistributionType;

/**
 * Calculation of qext mie theory transmission for a non uniform particle diameter distribution.
 * Integrates a single mieList for a given distribution
 * @author richard
 *
 */
public class MieIntegratorThread implements Runnable{

	private DistributionType type;
	private MieList mieList;
	private double SD;

	public MieIntegratorThread(MieList mielist, DistributionType type, double SD) {
		this.mieList=mielist;
		this.type=type;
		this.SD=SD;
		//System.out.println("Creating Thread for "+mieList.get(0).getWavelength()+" with SD: "+SD);
	}

	@Override
	public void run() {
		double particleSizeStep = mieList.get(1).getDiameter() - mieList.get(0).getDiameter();
		GenericDistribution dist;
		for (MieWrapper element : mieList) {
			// logNormal distribution for current particle radius.
			dist = DistributionFactory.getDistribution(type, SD, Math.log(element.getDiameter()));

			// integrate over all radiuses
			double integral = 0;
			MieWrapper mie1;
			MieWrapper mie2;
			for (int i = 0; i < mieList.size(); i++) {
				double x1;
				double x2;
				double y1;
				double y2;

				try {
					mie1 = mieList.get(i - 1);
					x1 = mie1.getDiameter();
					y1 = mie1.qext() * dist.density(x1, false) * Math.pow(x1, 2);
				} catch (IndexOutOfBoundsException e) {
					x1=0;
					y1=mieList.get(i).qext() * dist.density(x1, false) * Math.pow(x1, 2); //get some reasonable value at boundaries
				}

				mie2 = mieList.get(i);

				x2 = mie2.getDiameter();
				y2 = mie2.qext() * dist.density(x2, false) * Math.pow(x2, 2);
				
				integral += simpsonRule(x1, x2, y1, y2);

				// double r=mie2.getRadius();
				// double qext=mie2.qext();
				// double length=mieList.get(i).getRadius()-mieList.get(i-1).getRadius();
				// integral+=dist.density(r, false)*qext*Math.pow(r, 2)*length;

			}
			 // System.out.println("Integral: "+integral);
			element.getIntegratedQext().put(SD, integral);
		}

	}

	public static double simpsonRule(double x1, double x2, double y1, double y2) {
		double result=0;
		result+=(x2-x1)*y1;
		result+=0.5*(x2-x1)*(y2-y1);
		return result;
		
	}
	
	public String toString() {
		return "WL "+mieList.get(0).getWavelength()+" with SD: "+SD+" calculation thread";
	}


}

