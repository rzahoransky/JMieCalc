package presets;

public class ParticlePreset extends AbstractMieParticlePreset {

	public ParticlePreset(double refMediumWl1, double refSphereRealWl1, double refSphereImagWl1, double refMediumWl2, double refSphereRealWl2, double refSphereImagWl2, double refMediumWl3, double refSphereRealWl3, double refSphereImagWl3) {
		refractiveIndexMedium.put(Wavelengths.WL1, refMediumWl1);
		refractiveIndexMedium.put(Wavelengths.WL2, refMediumWl2);
		refractiveIndexMedium.put(Wavelengths.WL3, refMediumWl3);
		
		refractiveIndexSphereReal.put(Wavelengths.WL1, refSphereRealWl1);
		refractiveIndexSphereReal.put(Wavelengths.WL2, refSphereRealWl2);
		refractiveIndexSphereReal.put(Wavelengths.WL3, refSphereRealWl3);
		
		refractiveIndexSphereImaginary.put(Wavelengths.WL1, refSphereImagWl1);
		refractiveIndexSphereImaginary.put(Wavelengths.WL2, refSphereImagWl2);
		refractiveIndexSphereImaginary.put(Wavelengths.WL3, refSphereImagWl3);
	}

}
