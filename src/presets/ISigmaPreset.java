package presets;

import java.util.ArrayList;


public interface ISigmaPreset {

	
	public ArrayList<Double> getSDs();

	public default DistributionType getType() {
		return DistributionType.LogNormal;
	}
	
	public void setValuesTo(ISigmaPreset distribution);

}
