package buildInPresets;

import presets.AbstractMieParticlePreset;
import presets.Wavelengths;

public class SiOinAir extends AbstractMieParticlePreset {

	public SiOinAir() {
		setName("SiO in Air");
		checkForWavelengthMismatch=false;
		
		//how to use wavelength sensitive refractive indices:
		refractiveIndexMedium.put(0.673, 1d);
		refractiveIndexMedium.put(0.818, 1d);
		refractiveIndexMedium.put(1.313, 1d);
		
		refractiveIndexSphereReal.put(0.673, 1.948);
		refractiveIndexSphereReal.put(0.818, 1.913);
		refractiveIndexSphereReal.put(1.313, 1.87);
		
		refractiveIndexSphereImaginary.put(0.673, 0.0052);
		refractiveIndexSphereImaginary.put(0.818, 0d);
		refractiveIndexSphereImaginary.put(1.313, 0d);
		
		Double[] wavelengths= {0.673,0.818,1.313};
		for(double wl:wavelengths)
			holdsInformationForWavelengths.add(wl);
	}

}
