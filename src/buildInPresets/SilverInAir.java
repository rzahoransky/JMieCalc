package buildInPresets;

import presets.AbstractMieParticlePreset;
import presets.Wavelengths;

public class SilverInAir extends AbstractMieParticlePreset {

	public SilverInAir() {
		setName("Silver in Air");
		checkForWavelengthMismatch=false;
		
		//how to use wavelength sensitive refractive indices:
		refractiveIndexMedium.put(0.673, 1d);
		refractiveIndexMedium.put(0.818, 1d);
		refractiveIndexMedium.put(1.313, 1d);
		
		refractiveIndexSphereReal.put(0.673, 0.14);
		refractiveIndexSphereReal.put(0.818, 0.145);
		refractiveIndexSphereReal.put(1.313, 0.36);
		
		refractiveIndexSphereImaginary.put(0.673, 4.44);
		refractiveIndexSphereImaginary.put(0.818, 5.5);
		refractiveIndexSphereImaginary.put(1.313, 8.9538);
		
		Double[] wavelengths= {0.673,0.818,1.313};
		for(double wl:wavelengths)
			holdsInformationForWavelengths.add(wl);
	}

}
