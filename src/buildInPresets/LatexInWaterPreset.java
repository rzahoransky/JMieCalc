package buildInPresets;

import errors.WavelengthMismatchException;
import presets.AbstractMieParticlePreset;
import presets.Wavelengths;

public class LatexInWaterPreset extends AbstractMieParticlePreset {

	public LatexInWaterPreset() {
		setName("Latex particles in Water");
		checkForWavelengthMismatch=false;
		
		//how to use wavelength sensitive refractive indices:
		refractiveIndexMedium.put(Wavelengths.WL1, 1.33);
		refractiveIndexMedium.put(Wavelengths.WL2, 1.33);
		refractiveIndexMedium.put(Wavelengths.WL3, 1.33);
		
		refractiveIndexSphereReal.put(Wavelengths.WL1, 1.59);
		refractiveIndexSphereReal.put(Wavelengths.WL2, 1.59);
		refractiveIndexSphereReal.put(Wavelengths.WL3, 1.59);
		
		refractiveIndexSphereImaginary.put(Wavelengths.WL1, 0d);
		refractiveIndexSphereImaginary.put(Wavelengths.WL2, 0d);
		refractiveIndexSphereImaginary.put(Wavelengths.WL3, 0d);
		
		Double[] wavelengths= {0.673,0.818,1.313};
		for(double wl:wavelengths)
			holdsInformationForWavelengths.add(wl);
	}

}
