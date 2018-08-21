package buildInPresets;

import errors.WavelengthMismatchException;
import presets.AbstractMieParticlePreset;
import presets.Wavelengths;

public class OilInWater extends AbstractMieParticlePreset {

	public OilInWater() {
		setName("Oil drops in Water");
		checkForWavelengthMismatch=false;
		
		//how to use wavelength sensitive refractive indices:
		refractiveIndexMedium.put(Wavelengths.WL1, 1.33d);
		refractiveIndexMedium.put(Wavelengths.WL2, 1.33d);
		refractiveIndexMedium.put(Wavelengths.WL3, 1.33d);
		
		refractiveIndexSphereReal.put(Wavelengths.WL1, 1.47);
		refractiveIndexSphereReal.put(Wavelengths.WL2, 1.47);
		refractiveIndexSphereReal.put(Wavelengths.WL3, 1.47);
		
		refractiveIndexSphereImaginary.put(Wavelengths.WL1, 0d);
		refractiveIndexSphereImaginary.put(Wavelengths.WL2, 0d);
		refractiveIndexSphereImaginary.put(Wavelengths.WL3, 0d);
		
		Double[] wavelengths= {0.673,0.818,1.313};
		for(double wl:wavelengths)
			holdsInformationForWavelengths.add(wl);
	}
}
