package presets;

import java.util.List;

public interface IDiameterParametersInterface{
	
	public double getMinSize();
	public double getMaxSize();
	public int getSteps();
	public String getName();
	
	
	public boolean isLogarithmic();
	
	public List<Double> sizes();
	
	public void setValuesTo(IDiameterParametersInterface diameters);
	

}
