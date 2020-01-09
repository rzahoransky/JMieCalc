package buildInPresets;

import presets.AbstractMieParticlePreset;

public class LatexInWaterPreset extends AbstractMieParticlePreset {

	public LatexInWaterPreset() {
		setName("Latex particles in Water");
		checkForWavelengthMismatch=false;
		
		//how to use wavelength sensitive refractive indices:
		refractiveIndexMedium.put(0.673, 1.333);
		refractiveIndexMedium.put(0.818, 1.333);
		refractiveIndexMedium.put(0.844, 1.333);
		refractiveIndexMedium.put(1.313, 1.333);
		refractiveIndexMedium.put(1.324, 1.333);
		
		refractiveIndexSphereReal.put(0.673, 1.59);
		refractiveIndexSphereReal.put(0.818, 1.59);
		refractiveIndexSphereReal.put(0.844, 1.59);
		refractiveIndexSphereReal.put(1.313, 1.59);
		refractiveIndexSphereReal.put(1.324, 1.59);
		
		refractiveIndexSphereImaginary.put(0.673, 0d);
		refractiveIndexSphereImaginary.put(0.818, 0d);
		refractiveIndexSphereImaginary.put(0.844, 0d);
		refractiveIndexSphereImaginary.put(1.313, 0d);
		refractiveIndexSphereImaginary.put(1.324, 0d);
	}

}
