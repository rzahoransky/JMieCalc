package buildInPresets;

import errors.WavelengthMismatchException;
import presets.AbstractMieParticlePreset;
import presets.Wavelengths;

public class OilInWater extends AbstractMieParticlePreset {

	public OilInWater() {
		setName("Oil drops in Water");
		checkForWavelengthMismatch=false;
		
		//how to use wavelength sensitive refractive indices:
		refractiveIndexMedium.put(0.673, 1.33d);
		refractiveIndexMedium.put(0.818, 1.33d);
		refractiveIndexMedium.put(1.313, 1.33d);
		
		refractiveIndexSphereReal.put(0.673, 1.47);
		refractiveIndexSphereReal.put(0.818, 1.47);
		refractiveIndexSphereReal.put(1.313, 1.47);
		
		refractiveIndexSphereImaginary.put(0.673, 0d);
		refractiveIndexSphereImaginary.put(0.818, 0d);
		refractiveIndexSphereImaginary.put(1.313, 0d);
	}
}
