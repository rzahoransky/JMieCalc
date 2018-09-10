package buildInPresets;

import errors.WavelengthMismatchException;
import presets.AbstractMieParticlePreset;
import presets.Wavelengths;

public class WaterInAir extends AbstractMieParticlePreset {

	public WaterInAir() {
		setName("Water drops in Air");
		checkForWavelengthMismatch=false;
		
		//how to use wavelength sensitive refractive indices:
		refractiveIndexMedium.put(0.673, 1d);
		refractiveIndexMedium.put(0.818, 1d);
		refractiveIndexMedium.put(1.313, 1d);
		
		refractiveIndexSphereReal.put(0.673, 1.33);
		refractiveIndexSphereReal.put(0.818, 1.33);
		refractiveIndexSphereReal.put(1.313, 1.33);
		
		refractiveIndexSphereImaginary.put(0.673, 0d);
		refractiveIndexSphereImaginary.put(0.818, 0d);
		refractiveIndexSphereImaginary.put(1.313, 0d);
		
		Double[] wavelengths= {0.673,0.818,1.313};
		for(double wl:wavelengths)
			holdsInformationForWavelengths.add(wl);
	}
}
