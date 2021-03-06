package calculation;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import buildInPresets.LatexInWaterPreset;
import buildInPresets.NarrowLogNormalDistribution;
import errors.WavelengthMismatchException;
import presets.IDiameterParametersInterface;
import presets.IMieParticlePreset;
import presets.ISigmaPreset;
import presets.StandardDiameterParameters;
import presets.Wavelengths;

public class CalculationAssignment {
	
	ISigmaPreset sigmas = new NarrowLogNormalDistribution(); //just any preset. It is changed on the fly
	IMieParticlePreset particles = new LatexInWaterPreset(); //just any preset. It is changed on the fly
	static CalculationAssignment myCalculationAssignment = new CalculationAssignment();
	ArrayList<CalculationAssignmentListener> listeners = new ArrayList<>();
	IDiameterParametersInterface diameters = new StandardDiameterParameters(); //just any preset
	HashMap<Wavelengths, MieList> mieLists = new HashMap<>();
	int progress;
	private File outputFile;
	private boolean isCalculated;
	
	public int getProgress() {
		return progress;
	}

	public boolean isFinished() {
		return isCalculated;
	}

	public HashMap<Wavelengths,MieList> getMieLists() {
		return mieLists;
	}
	
	public void setResultMieList(HashMap<Wavelengths, MieList> mieLists) {
		this.mieLists=mieLists;
		isCalculated=true;
	}

	public IDiameterParametersInterface getDiameters() {
		return diameters;
	}

	public void setDiameters(IDiameterParametersInterface diameters) {
		this.diameters.setValuesTo(diameters);
		isCalculated=false;
		for(CalculationAssignmentListener listener: listeners)
			listener.diametersChanged();
	}

	public ISigmaPreset getSigmas() {
		return sigmas;
	}

	public void setSigmas(ISigmaPreset distribution) {
		this.sigmas.setValuesTo(distribution);
		isCalculated=false;
		for(CalculationAssignmentListener listener: listeners)
			listener.sigmaChanged();
	}

	public IMieParticlePreset getParticles() {
		return particles;
	}

	public void setParticles(IMieParticlePreset particles) throws WavelengthMismatchException {
		this.particles.setValuesTo(particles);
		for(CalculationAssignmentListener listener: listeners)
			listener.mieParticleChanged();
		isCalculated=false;
	}

	private CalculationAssignment(IMieParticlePreset particles, IDiameterParametersInterface diameters, ISigmaPreset sigmas) {
		this.particles=particles;
		this.diameters=diameters;
		this.sigmas=sigmas;
	}
	
	private CalculationAssignment() {
		
	}
	
	protected static CalculationAssignment getCalculationAssignment(IMieParticlePreset particles, IDiameterParametersInterface diameters, ISigmaPreset sigmas) {
		return new CalculationAssignment(particles, diameters, sigmas);
	}
	
	public static CalculationAssignment getInstance() {
		return myCalculationAssignment;
	}
	
	public void addListener(CalculationAssignmentListener listener) {
		listeners.add(listener);
	}
	
	public void informOfCalculationFinished() {
		isCalculated=true;
		for(CalculationAssignmentListener listener:listeners)
			listener.calculationFinished();
	}
	
	public void setProgress(int current, int end) {
		progress = (int) ((current/(double)end)*100);
		//System.out.println(progress+"%");
		for(CalculationAssignmentListener listener:listeners)
			listener.progress(progress/100d);
	}

	public void setOutputFile(File file) {
		this.outputFile=file;
//		for(CalculationAssignmentListener listener:listeners)
//			listener.
	}
	
	public File getOutputFile() {
		return outputFile;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Assignment: \r\n");
		sb.append(getParticles().toString());
		
		sb.append("\r\nfrom:");
		sb.append(getDiameters().toString());
		
		sb.append("\r\nfor: ");
		sb.append(getSigmas().toString());

		return sb.toString();
	}

	public void changeWavelength(Wavelengths wavelength, double userSetWavelength) {
		wavelength.setValue(userSetWavelength);
		for (CalculationAssignmentListener listener: listeners) {
			listener.wavelengthsChanged();
		}
		
	}

	public void removeListener(CalculationAssignmentListener mieGUI) {
		listeners.remove(mieGUI);
	}

	public void informOfFileWritten() {
		for (CalculationAssignmentListener listener: listeners) {
			listener.fileWritten();
		}
		
	}

}
