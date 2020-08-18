package presets;

import java.util.List;

public interface IDiameterParametersInterface{
	
	public double getMinDiameterSize();
	public double getMaxDiameterSize();
	public int getSteps();
	public String getName();
	
	
	public boolean isLogarithmic();
	
	/** get the calculated size values with the required steps **/
	public List<Double> sizes();
	
	public void setValuesTo(IDiameterParametersInterface diameters);
	

}
