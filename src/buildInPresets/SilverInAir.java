package buildInPresets;

import presets.AbstractMieParticlePreset;
import presets.Wavelengths;

public class SilverInAir extends AbstractMieParticlePreset {

	public SilverInAir() {
		setName("Silver in Air");
		checkForWavelengthMismatch=false;
		
		//how to use wavelength sensitive refractive indices:
		refractiveIndexMedium.put(Wavelengths.WL1, 1d);
		refractiveIndexMedium.put(Wavelengths.WL2, 1d);
		refractiveIndexMedium.put(Wavelengths.WL3, 1d);
		
		refractiveIndexSphereReal.put(Wavelengths.WL1, 0.14);
		refractiveIndexSphereReal.put(Wavelengths.WL2, 0.145);
		refractiveIndexSphereReal.put(Wavelengths.WL3, 0.36);
		
		refractiveIndexSphereImaginary.put(Wavelengths.WL1, 4.44);
		refractiveIndexSphereImaginary.put(Wavelengths.WL2, 5.5);
		refractiveIndexSphereImaginary.put(Wavelengths.WL3, 8.9538);
		
		Double[] wavelengths= {0.673,0.818,1.313};
		for(double wl:wavelengths)
			holdsInformationForWavelengths.add(wl);
	}

}
