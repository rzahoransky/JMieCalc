package presets;

public class PresetFactory {

	private PresetFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static AbstractDiameterSizeParameters getSizePreset(double start, double end, int steps, boolean logarithmic) {
		return new SizeParameter(start, end, steps, logarithmic);
	}

}

class SizeParameter extends AbstractDiameterSizeParameters{

	public SizeParameter(double start, double end, int steps, boolean logarithmic) {
		super(start, end, steps, logarithmic);
	}

}
