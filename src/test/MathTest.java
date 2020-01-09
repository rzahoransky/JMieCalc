package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jdistlib.LogNormal;
import jdistlib.generic.GenericDistribution;
import presets.AbstractDiameterSizeParameters;
import presets.PresetFactory;

class MathTest {
	GenericDistribution test;

	@BeforeEach
	void setUp() throws Exception {
		test = new LogNormal(0.1, 0.1);
	}

	@Test
	void logNormalTest() {
		assertEquals(0.0250, test.density(1.5, false), 0.00005);
		assertEquals(2.41970, test.density(1, false), 0.00005);
		assertEquals(0, test.density(-1, false), 0.00005);
		assertEquals(0, test.density(0, false), 0.00005);
	}
	
	@Test
	void integrationTest() {
		double integralSum = 0;
		double step = 0.01;
		for (double i = 0.0;i<100;i+=step) {
			integralSum+=test.density(i, false) * step;
		}
		assertEquals(1, integralSum, 0.001);
	}
	
	@Test
	void particleDiameterTest() {
		double start = 0.001;
		double end = 1d;
		int steps = 10;
		AbstractDiameterSizeParameters preset = PresetFactory.getSizePreset(start, end, steps, false);
		assertEquals(start, preset.getMinSize());
		assertEquals(end, preset.getMaxSize());
		
		preset = PresetFactory.getSizePreset(start, end, steps, true);
		assertEquals(start, preset.getMinSize());
		assertEquals(end, preset.getMaxSize());
	}

}
