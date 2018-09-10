package buildInPresets;

import presets.AbstractMieParticlePreset;
import presets.Wavelengths;

public class DieselSoot extends AbstractMieParticlePreset {

	public DieselSoot() {
		setName("Diesel Soot in Air");
		checkForWavelengthMismatch=false;
		
		//how to use wavelength sensitive refractive indices:
		refractiveIndexMedium.put(0.673, 1d);
		refractiveIndexMedium.put(0.818, 1d);
		refractiveIndexMedium.put(0.844, 1d);
		refractiveIndexMedium.put(1.313, 1d);
		refractiveIndexMedium.put(1.324, 1d);
		
		refractiveIndexSphereReal.put(0.673, 1.92);
		refractiveIndexSphereReal.put(0.818, 1.92);
		refractiveIndexSphereReal.put(0.844, 1.92);
		refractiveIndexSphereReal.put(1.313, 1.92);
		refractiveIndexSphereReal.put(1.324, 1.92);
		
		refractiveIndexSphereImaginary.put(0.673, 0.66);
		refractiveIndexSphereImaginary.put(0.818, 0.66);
		refractiveIndexSphereImaginary.put(0.844, 0.66);
		refractiveIndexSphereImaginary.put(1.313, 0.66);
		refractiveIndexSphereImaginary.put(1.324, 0.66);
		
		Double[] wavelengths= {0.673,0.818,1.313};
		for(double wl:wavelengths)
			holdsInformationForWavelengths.add(wl);
	}

}
