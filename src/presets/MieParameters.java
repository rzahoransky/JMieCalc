package presets;

public class MieParameters {

	private IMieParticlePreset particle;
	private IDiameterParametersInterface sizes;
	
	public IMieParticlePreset getParticle() {
		return particle;
	}

//	public WavelengthPreset getWavelengths() {
//		return wavelengths;
//	}

	public IDiameterParametersInterface getSizes() {
		return sizes;
	}

	public MieParameters(IMieParticlePreset particle, IDiameterParametersInterface sizes) {

		
		this.particle=particle;
//		this.wavelengths=wavelengths;
		this.sizes=sizes;
	}

}
