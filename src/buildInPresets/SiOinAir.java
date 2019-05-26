package buildInPresets;

import presets.AbstractMieParticlePreset;

public class SiOinAir extends AbstractMieParticlePreset {

	public SiOinAir() {
		setName("SiO in Air");
		checkForWavelengthMismatch=false;
		
		refractiveIndexMedium.put(0.673, 1d);
		refractiveIndexMedium.put(0.818, 1d);
		refractiveIndexMedium.put(1.313, 1d);
		
		refractiveIndexSphereReal.put(0.405, 2.14);
		refractiveIndexSphereReal.put(0.532, 1.984);
		refractiveIndexSphereReal.put(0.635, 1.9559);
		refractiveIndexSphereReal.put(0.673, 1.948);
		refractiveIndexSphereReal.put(0.818, 1.913);
		refractiveIndexSphereReal.put(1.313, 1.89);
		
		refractiveIndexSphereImaginary.put(0.405, 0.124);
		refractiveIndexSphereImaginary.put(0.532, 0.001);
		refractiveIndexSphereImaginary.put(0.635, 0.001);
		refractiveIndexSphereImaginary.put(0.673, 0.0052);
		refractiveIndexSphereImaginary.put(0.818, 0d);
		refractiveIndexSphereImaginary.put(1.313, 0d);
	}

}
