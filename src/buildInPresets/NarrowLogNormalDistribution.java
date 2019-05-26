package buildInPresets;

import presets.AbstractSigmaPreset;

public class NarrowLogNormalDistribution extends AbstractSigmaPreset {

	public NarrowLogNormalDistribution() {
		//SDs.add(0.0001);
		//SDs.add(0.001);
		//SDs.add(0.005);
		SDs.add(0.01);
		SDs.add(0.05);
		SDs.add(0.07);
		SDs.add(0.1);
		SDs.add(0.2);
		SDs.add(0.4);
		SDs.add(0.8);
	}
	
//	public String toString() {
//		StringBuffer sb = new StringBuffer();
//		for(Double d:SDs)
//			sb.append(d+" ");
//		return sb.toString();
//	}

}
