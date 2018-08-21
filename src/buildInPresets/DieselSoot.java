package buildInPresets;

import presets.AbstractMieParticlePreset;
import presets.Wavelengths;

public class DieselSoot extends AbstractMieParticlePreset {

	public DieselSoot() {
		setName("Diesel Soot in Air");
		checkForWavelengthMismatch=false;
		
		//how to use wavelength sensitive refractive indices:
		refractiveIndexMedium.put(Wavelengths.WL1, 1d);
		refractiveIndexMedium.put(Wavelengths.WL2, 1d);
		refractiveIndexMedium.put(Wavelengths.WL3, 1d);
		
		refractiveIndexSphereReal.put(Wavelengths.WL1, 1.92);
		refractiveIndexSphereReal.put(Wavelengths.WL2, 1.92);
		refractiveIndexSphereReal.put(Wavelengths.WL3, 1.92);
		
		refractiveIndexSphereImaginary.put(Wavelengths.WL1, 0.66);
		refractiveIndexSphereImaginary.put(Wavelengths.WL2, 0.66);
		refractiveIndexSphereImaginary.put(Wavelengths.WL3, 0.66);
		
		Double[] wavelengths= {0.673,0.818,1.313};
		for(double wl:wavelengths)
			holdsInformationForWavelengths.add(wl);
	}

}
