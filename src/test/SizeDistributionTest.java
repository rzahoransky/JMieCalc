package test;

import presets.AbstractDiameterSizeParameters;
import presets.PresetFactory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SizeDistributionTest {
	
	AbstractDiameterSizeParameters logPreset;
	AbstractDiameterSizeParameters normalPreset;
	double min = 0.001;
	double max = 5;
	int steps = 1000;
	List<Double> logList;
	List<Double> normalList;

	public SizeDistributionTest() {
		// TODO Auto-generated constructor stub
	}
	
	@BeforeEach
	void setup() {
		logPreset = PresetFactory.getSizePreset(min, max, steps, true);
		normalPreset = PresetFactory.getSizePreset(min, max, steps, false);
		logList = logPreset.sizes();
		normalList = normalPreset.sizes();
	}
	
	@Test
	void testSizes() {
		assertEquals(max, logList.get(logList.size()-1),(max-min)/steps + Double.MIN_NORMAL);
		assertEquals(max, normalList.get(normalList.size()-1),(max-min)/steps + Double.MIN_NORMAL);
	}
	
	@Test
	void testSteps() {
		assertEquals(logList.size()-1, steps,1);
		assertEquals(normalList.size()-1, steps,1);
	}

}
