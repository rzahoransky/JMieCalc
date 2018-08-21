package test;

import presets.AbstractDiameterSizeParameters;
import presets.PresetFactory;

public class SizeDistributionTest {

	public SizeDistributionTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		AbstractDiameterSizeParameters preset = PresetFactory.getSizePreset(0.001, 1, 10, true);
		for(double d: preset.sizes()) {
			System.out.println(d);
		}

	}

}
