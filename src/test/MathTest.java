package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jdistlib.LogNormal;
import jdistlib.Normal;
import jdistlib.generic.GenericDistribution;
import presets.AbstractDiameterSizeParameters;
import presets.PresetFactory;

class MathTest {
	GenericDistribution logNormal;
	GenericDistribution normal;

	@BeforeEach
	void setUp() throws Exception {
		logNormal = new LogNormal(0.1, 0.1);
		normal = new Normal(0.1, 0.1);
	}

	@Test
	void logNormalTest() {
		assertEquals(0.0250, logNormal.density(1.5, false), 0.00005);
		assertEquals(2.41970, logNormal.density(1, false), 0.00005);
		assertEquals(0, logNormal.density(-1, false), 0.00005);
		assertEquals(0, logNormal.density(0, false), 0.00005);
	}
	
	@Test
	void logNormalIntegrationTest() {
		assertEquals(1, testDistribution(logNormal), 0.001);
	}
	
	@Test
	void normalIntegrationTest() {
		assertEquals(1, testDistribution(normal), 0.001);
	}
	
	 double testDistribution(GenericDistribution dist) {
		double integralSum = 0;
		double step = 0.001;
		for (double i = -100;i<100;i+=step) {
			integralSum+=dist.density(i, false) * step; //rough approximation
		}
		return integralSum;
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
