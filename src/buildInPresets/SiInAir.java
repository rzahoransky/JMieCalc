package buildInPresets;

import presets.AbstractMieParticlePreset;
import presets.Wavelengths;

public class SiInAir extends AbstractMieParticlePreset {

	public SiInAir() {
		setName("Si in Air");
		checkForWavelengthMismatch=false;
		
		//how to use wavelength sensitive refractive indices:
		refractiveIndexMedium.put(0.673, 1d);
		refractiveIndexMedium.put(0.818, 1d);
		refractiveIndexMedium.put(1.313, 1d);
		
		refractiveIndexSphereReal.put(0.673, 3.815);
		refractiveIndexSphereReal.put(0.818, 3.681);
		refractiveIndexSphereReal.put(1.313, 3.5007);
		
		refractiveIndexSphereImaginary.put(0.673, 0.014);
		refractiveIndexSphereImaginary.put(0.818, 0.006);
		refractiveIndexSphereImaginary.put(1.313, 0d);
		
		Double[] wavelengths= {0.673,0.818,1.313};
		for(double wl:wavelengths)
			holdsInformationForWavelengths.add(wl);
	}

}
