package buildInPresets;

import presets.AbstractMieParticlePreset;
import presets.Wavelengths;

public class SiInAir extends AbstractMieParticlePreset {

	public SiInAir() {
		setName("Si in Air");
		checkForWavelengthMismatch=false;
		
		//how to use wavelength sensitive refractive indices:
		refractiveIndexMedium.put(Wavelengths.WL1, 1d);
		refractiveIndexMedium.put(Wavelengths.WL2, 1d);
		refractiveIndexMedium.put(Wavelengths.WL3, 1d);
		
		refractiveIndexSphereReal.put(Wavelengths.WL1, 3.815);
		refractiveIndexSphereReal.put(Wavelengths.WL2, 3.681);
		refractiveIndexSphereReal.put(Wavelengths.WL3, 3.5007);
		
		refractiveIndexSphereImaginary.put(Wavelengths.WL1, 0.014);
		refractiveIndexSphereImaginary.put(Wavelengths.WL2, 0.006);
		refractiveIndexSphereImaginary.put(Wavelengths.WL3, 0d);
		
		Double[] wavelengths= {0.673,0.818,1.313};
		for(double wl:wavelengths)
			holdsInformationForWavelengths.add(wl);
	}

}
