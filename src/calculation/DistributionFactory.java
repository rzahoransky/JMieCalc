package calculation;

import jdistlib.LogNormal;
import jdistlib.generic.GenericDistribution;
import presets.DistributionType;

public class DistributionFactory {
	
	
	static GenericDistribution getDistribution(DistributionType type, double SD, double mean) {
		GenericDistribution distribution;
		switch (type) {
		case LogNormal:
			distribution=new LogNormal(mean, SD);
			break;

		default:
			distribution=null;
			break;
		}
		
		return distribution;
		
	}
	


}
