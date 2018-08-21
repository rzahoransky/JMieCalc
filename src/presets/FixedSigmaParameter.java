package presets;

public class FixedSigmaParameter extends AbstractSigmaPreset {

	public FixedSigmaParameter(double... sigmas) {
		for (double sigma: sigmas)
			SDs.add(sigma);
	}

}
