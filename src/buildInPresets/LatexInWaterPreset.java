package buildInPresets;

import errors.WavelengthMismatchException;
import presets.AbstractMieParticlePreset;
import presets.Wavelengths;

public class LatexInWaterPreset extends AbstractMieParticlePreset {

	public LatexInWaterPreset() {
		setName("Latex particles in Water");
		checkForWavelengthMismatch=false;
		
		//how to use wavelength sensitive refractive indices:
		refractiveIndexMedium.put(0.673, 1.33);
		refractiveIndexMedium.put(0.818, 1.33);
		refractiveIndexMedium.put(1.313, 1.33);
		
		refractiveIndexSphereReal.put(0.673, 1.59);
		refractiveIndexSphereReal.put(0.818, 1.59);
		refractiveIndexSphereReal.put(1.313, 1.59);
		
		refractiveIndexSphereImaginary.put(0.673, 0d);
		refractiveIndexSphereImaginary.put(0.818, 0d);
		refractiveIndexSphereImaginary.put(1.313, 0d);
		
		Double[] wavelengths= {0.673,0.818,1.313};
		for(double wl:wavelengths)
			holdsInformationForWavelengths.add(wl);
	}

}
