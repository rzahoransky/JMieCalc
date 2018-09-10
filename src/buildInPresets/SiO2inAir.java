package buildInPresets;

import presets.AbstractMieParticlePreset;
import presets.Wavelengths;

public class SiO2inAir extends AbstractMieParticlePreset {

	public SiO2inAir() {
		setName("SiO2 in Air");
		checkForWavelengthMismatch=false;
		
		//how to use wavelength sensitive refractive indices:
		refractiveIndexMedium.put(0.673, 1d);
		refractiveIndexMedium.put(0.818, 1d);
		refractiveIndexMedium.put(1.313, 1d);
		
		refractiveIndexSphereReal.put(0.673, 1.4559);
		refractiveIndexSphereReal.put(0.818, 1.453);
		refractiveIndexSphereReal.put(1.313, 1.4468);
		
		refractiveIndexSphereImaginary.put(0.673, 0d);
		refractiveIndexSphereImaginary.put(0.818, 0d);
		refractiveIndexSphereImaginary.put(1.313, 0d);
		
		Double[] wavelengths= {0.673,0.818,1.313};
		for(double wl:wavelengths)
			holdsInformationForWavelengths.add(wl);
	}

}
