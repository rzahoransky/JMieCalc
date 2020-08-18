package presets;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDiameterSizeParameters implements IDiameterParametersInterface {
	
	protected LinkedList<Double> sizes;
	protected boolean logarithmic;
	protected double startDiameter;
	protected double endDiameter;
	protected int steps;
	private String name;
	
	protected AbstractDiameterSizeParameters() {
		
	}
	
	public AbstractDiameterSizeParameters(double start, double end, int steps, boolean logarithmic) {
		this.logarithmic=logarithmic;
		this.startDiameter=start;
		this.endDiameter=end;
		this.steps=steps;
		sizes();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name=name;
	}

	public double getSingleStep() {
		return (getMaxDiameterSize()-getMinDiameterSize())/getSteps();
	}
	
	public double getMinDiameterSize() {
		return startDiameter;
	}

	public double getMaxDiameterSize() {
		return endDiameter;
	}
	
	public int getSteps() {
		return steps;
	}
	
	@Override
	public boolean isLogarithmic() {
		return logarithmic;
	}
	

	@Override
	public List<Double> sizes() {
		if (sizes == null || sizes.isEmpty()) {
			sizes = new LinkedList<>();
			if (!isLogarithmic()) {
				sizes = createLinearSteps();
			} else {
				sizes = createLogarithmicSteps();
			}
		}
		return sizes;
	}
	
	protected LinkedList<Double> createLogarithmicSteps() {
		LinkedList<Double> result = new LinkedList<>();
		
		double start = (Math.log(getMinDiameterSize()) / Math.log(10));
		double end = (Math.log(getMaxDiameterSize()) / Math.log(10));
		double increment = (end-start)/getSteps();
		
		for (double i=start;i<end;i+=increment) {
			result.add(Math.pow(10, i));
		}
		
		return result;
		
	}


	protected LinkedList<Double> createLinearSteps(){
		LinkedList<Double> result = new LinkedList<>();
		double increment = getSingleStep();
		
		for (double i = getMinDiameterSize(); i <= getMaxDiameterSize(); i += increment) {
			result.add(i);
		}
		
		return result;
	}
	
	public String toString() {
		String logOrLinear = (logarithmic)?"logaritmical":"linear";
		return startDiameter+"..."+endDiameter+"("+steps+" steps) ("+logOrLinear+")";
	}

	@Override
	public void setValuesTo(IDiameterParametersInterface diameters) {
		this.logarithmic=diameters.isLogarithmic();
		this.sizes=null;
		this.startDiameter=diameters.getMinDiameterSize();
		this.endDiameter=diameters.getMaxDiameterSize();
		this.steps=diameters.getSteps();
		this.name="User defined";
		
	}

}
