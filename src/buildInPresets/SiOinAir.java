package buildInPresets;

import presets.AbstractMieParticlePreset;
import presets.Wavelengths;

public class SiOinAir extends AbstractMieParticlePreset {

	public SiOinAir() {
		setName("SiO in Air");
		checkForWavelengthMismatch=false;
		
		//how to use wavelength sensitive refractive indices:
		refractiveIndexMedium.put(Wavelengths.WL1, 1d);
		refractiveIndexMedium.put(Wavelengths.WL2, 1d);
		refractiveIndexMedium.put(Wavelengths.WL3, 1d);
		
		refractiveIndexSphereReal.put(Wavelengths.WL1, 1.948);
		refractiveIndexSphereReal.put(Wavelengths.WL2, 1.913);
		refractiveIndexSphereReal.put(Wavelengths.WL3, 1.87);
		
		refractiveIndexSphereImaginary.put(Wavelengths.WL1, 0.0052);
		refractiveIndexSphereImaginary.put(Wavelengths.WL2, 0d);
		refractiveIndexSphereImaginary.put(Wavelengths.WL3, 0d);
		
		Double[] wavelengths= {0.673,0.818,1.313};
		for(double wl:wavelengths)
			holdsInformationForWavelengths.add(wl);
	}

}
