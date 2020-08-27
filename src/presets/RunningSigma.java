package presets;

import java.util.ArrayList;
import java.util.List;

public class RunningSigma extends AbstractSigmaPreset {

	private boolean logarithmic;
	private double start=0.005;
	private double end=0.7;
	private int steps=7;

	public RunningSigma(double start, double end, int steps, boolean logarithmic) {
		this.logarithmic=logarithmic;
		this.start=start;
		this.end=end;
		this.steps=steps;
		
		sizes();
	}
	
	public List<Double> sizes() {
			SDs = new ArrayList<>();
			if (!logarithmic) {
				SDs = createLinearSteps();
			} else {
				SDs = createLogarithmicSteps();
		}
		return SDs;
	}
	
	protected ArrayList<Double> createLogarithmicSteps() {
		ArrayList<Double> result = new ArrayList<>();
		
		double start = (Math.log(this.start) / Math.log(10));
		double end = (Math.log(this.end) / Math.log(10));
		
		for (double i=start;i<=end;i+=(end-start)/(steps-1)) {
			double logValue = Math.pow(10, i);
			double rounded = Math.floor(logValue*1000)/1000;
			result.add(rounded);
			//result.add(Math.pow(10, i));
		}
		
		return result;
		
	}


	protected ArrayList<Double> createLinearSteps(){
		ArrayList<Double> result = new ArrayList<>();
		for (double i = start; i <= end; i += (end-start)/(steps-1)) {
			double rounded = Math.floor(i*1000)/1000;
			result.add(rounded);
		}
		return result;
	}

	public boolean isLogarithmic() {
		return logarithmic;
	}

	public double getStart() {
		return start;
	}

	public double getEnd() {
		return end;
	}

	public int getSteps() {
		return steps;
	}

}
