package calculation;

import errors.WavelengthMismatchException;
import presets.IDiameterParametersInterface;
import presets.IMieParticlePreset;
//import presets.WavelengthPreset;
import presets.Wavelengths;

public class MieListGenerator {

	private Wavelengths wavelength;
	private IDiameterParametersInterface sizeParameters;
	private IMieParticlePreset particlePresets;
	
	public MieListGenerator(IMieParticlePreset particlePreset, IDiameterParametersInterface sizeParameters) {
		this.particlePresets = particlePreset;
		this.sizeParameters = sizeParameters;
	}
	
	public MieListGenerator() {
		
	}
	

	public IDiameterParametersInterface getSizeParameters() {
		return sizeParameters;
	}

	public Object getParticlePresets() {
		return particlePresets;
	}

	public Wavelengths getWavelength() {
		return wavelength;
	}

	public void setWavelength(Wavelengths wavelength) {
		this.wavelength = wavelength;
	}
	
	public MieList generateList(Wavelengths wl) throws WavelengthMismatchException{
		MieList list = new MieList(sizeParameters.getSteps());
		this.wavelength=wl;
		for(double curDiameter: sizeParameters.sizes()) {
			//System.out.println("Generating "+curRadius);
			MieWrapper element = new MieWrapper();
			element.setHostRefractiveIndex(particlePresets.getRefractiveIndexMedium(wl.getValue()));
			element.setRadiusWavelength(curDiameter/2, wl.getValue());
			element.setRefractiveIndex(particlePresets.getRefractiveIndexSphereReal(wl.getValue()), particlePresets.getRefractiveIndexSphereImaginaray(wl.getValue()));
			list.addElement(element);
		}
		
		return list;
	}

}
