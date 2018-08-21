package presets;

import java.util.LinkedList;
import java.util.List;

public class StandardDiameterParameters extends AbstractDiameterSizeParameters{



	public StandardDiameterParameters() {
		super(0.03,3,1000,true);
	}

	public StandardDiameterParameters(double start, double end, int steps, boolean logarithmic) {
		super(start,end,steps,logarithmic);
	}
}
