package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculation.MieWrapper;

class QextTests {
	
	double diameter = 0.5d;
	double diameter2 = 0.6d;
	double refractiveIndexMedium = 1.33d;
	double refractiveIndexSphere = 1.59d;
	double wl1=0.638;
	double wl2=0.818;
	double qExtWl1=0.753;
	
	MieWrapper mie1wl1;
	MieWrapper mie2wl2;
	MieWrapper mie3wl1;

	@BeforeEach
	void setUp() throws Exception {
		//setup MieWrappers with diameter and optical parameters for three wavelengths
		mie1wl1 = new MieWrapper();
		mie1wl1.setRadiusWavelength(diameter/2,wl1);
		mie1wl1.setRefractiveIndex(refractiveIndexSphere, 0);
		mie1wl1.setHostRefractiveIndex(refractiveIndexMedium);
		
		mie2wl2 = new MieWrapper();
		mie2wl2.setRadiusWavelength(diameter/2, wl2);
		mie2wl2.setRefractiveIndex(refractiveIndexSphere, 0);
		mie2wl2.setHostRefractiveIndex(refractiveIndexMedium);
		
		mie3wl1 = new MieWrapper();
		mie3wl1.setRadiusWavelength(diameter2/2, wl1);
		mie3wl1.setRefractiveIndex(refractiveIndexSphere, 0);
		mie3wl1.setHostRefractiveIndex(refractiveIndexMedium);
	}

	@Test
	void testQextValue() {
		assertFalse(mie1wl1.isCalculated());
		mie1wl1.calcScattCoeffs();
		assertTrue(mie1wl1.isCalculated());
		assertEquals(qExtWl1, mie1wl1.qext(), 0.001); //is Qext calculated correct?
	}
	
	@Test
	void testUnequal() {
		mie1wl1.calcScattCoeffs();
		mie2wl2.calcScattCoeffs();
		mie3wl1.calcScattCoeffs();
		
		assertNotEquals(mie1wl1.qext(), mie2wl2.qext());
		assertNotEquals(mie1wl1.qext(), mie3wl1.qext());
		assertNotEquals(mie2wl2.qext(), mie3wl1.qext());
	}

}
