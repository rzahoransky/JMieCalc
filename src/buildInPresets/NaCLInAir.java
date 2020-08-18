package buildInPresets;

import presets.AbstractMieParticlePreset;

public class NaCLInAir extends AbstractMieParticlePreset {

	public NaCLInAir() {
		setName("NaCl in Air");
		checkForWavelengthMismatch=false;
		
		//how to use wavelength sensitive refractive indices:
		refractiveIndexMedium.put(0.673, 1d);
		refractiveIndexMedium.put(0.818, 1d);
		refractiveIndexMedium.put(0.844, 1d);
		refractiveIndexMedium.put(1.313, 1d);
		refractiveIndexMedium.put(1.324, 1d);
		
		refractiveIndexSphereReal.put(0.673, 1.5397);
		refractiveIndexSphereReal.put(0.818, 1.5352);
		refractiveIndexSphereReal.put(0.844, 1.5346);
		refractiveIndexSphereReal.put(1.313, 1.5292);
		refractiveIndexSphereReal.put(1.324, 1.5292);
		
		refractiveIndexSphereImaginary.put(0.673, 0d);
		refractiveIndexSphereImaginary.put(0.818, 0d);
		refractiveIndexSphereImaginary.put(0.844, 0d);
		refractiveIndexSphereImaginary.put(1.313, 0d);
		refractiveIndexSphereImaginary.put(1.324, 0d);
	}

}
