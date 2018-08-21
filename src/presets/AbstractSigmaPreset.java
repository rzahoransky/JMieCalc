package presets;

import java.util.ArrayList;

public abstract class AbstractSigmaPreset implements ISigmaPreset{
	protected ArrayList<Double> SDs = new ArrayList<>();

	public AbstractSigmaPreset() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Double> getSDs() {
		return SDs;
	}
	
	public String toString() {
		String s="";
		for (double sd: SDs) {
			s+=sd+", ";
		}
		return s.substring(0, s.length()-2);
	}

	@Override
	public void setValuesTo(ISigmaPreset distribution) {
		this.SDs.clear();
		for (Double sigma:distribution.getSDs()) {
			SDs.add(sigma);
		}
			
		
	}

}
