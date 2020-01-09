package calculation;

public interface CalculationAssignmentListener {
	
	public void mieParticleChanged();
	
	public void wavelengthsChanged();

	public void calculationFinished();

	public void progress(double fractionOfOne);
	
	public void diametersChanged();
	
	public void sigmaChanged();
	
	public void outputFileChanged();

	public void fileWritten();

}
