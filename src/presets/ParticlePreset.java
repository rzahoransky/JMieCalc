package presets;

public class ParticlePreset extends AbstractMieParticlePreset {

	public ParticlePreset(double refMediumWl1, double refSphereRealWl1, double refSphereImagWl1, double refMediumWl2, double refSphereRealWl2, double refSphereImagWl2, double refMediumWl3, double refSphereRealWl3, double refSphereImagWl3) {
		refractiveIndexMedium.put(Wavelengths.WL1.getValue(), refMediumWl1);
		refractiveIndexMedium.put(Wavelengths.WL2.getValue(), refMediumWl2);
		refractiveIndexMedium.put(Wavelengths.WL3.getValue(), refMediumWl3);
		
		refractiveIndexSphereReal.put(Wavelengths.WL1.getValue(), refSphereRealWl1);
		refractiveIndexSphereReal.put(Wavelengths.WL2.getValue(), refSphereRealWl2);
		refractiveIndexSphereReal.put(Wavelengths.WL3.getValue(), refSphereRealWl3);
		
		refractiveIndexSphereImaginary.put(Wavelengths.WL1.getValue(), refSphereImagWl1);
		refractiveIndexSphereImaginary.put(Wavelengths.WL2.getValue(), refSphereImagWl2);
		refractiveIndexSphereImaginary.put(Wavelengths.WL3.getValue(), refSphereImagWl3);
	}

}
