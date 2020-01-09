package buildInPresets;

import presets.AbstractMieParticlePreset;

public class OilInAir extends AbstractMieParticlePreset {

	public OilInAir() {
		setName("Oil drops in Air");
		checkForWavelengthMismatch=false;
		
		//how to use wavelength sensitive refractive indices:
		refractiveIndexMedium.put(0.673, 1d);
		refractiveIndexMedium.put(0.818, 1d);
		refractiveIndexMedium.put(1.313, 1d);
		
		refractiveIndexSphereReal.put(0.673, 1.47);
		refractiveIndexSphereReal.put(0.818, 1.47);
		refractiveIndexSphereReal.put(1.313, 1.47);
		
		refractiveIndexSphereImaginary.put(0.673, 0d);
		refractiveIndexSphereImaginary.put(0.818, 0d);
		refractiveIndexSphereImaginary.put(1.313, 0d);
	}
}
