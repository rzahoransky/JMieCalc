package calculation;

import java.awt.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import BMNum.Complex;
import BMScatt.Mie;

public class MieWrapper extends Mie implements Comparable<MieWrapper> {
	private boolean isCalculated=false;
	private double radius=Double.NaN;
	private double wavelength;
	private double refractiveIndexMedium;
	private double refractiveIndexSphereReal;
	private double refractiveIndexSpereImaginary;
	private ConcurrentHashMap<Double, Double> integratedQext = new ConcurrentHashMap<>(); //SD, integrated Value

	/** get integrated values for <deviation, value>**/
	public ConcurrentHashMap<Double, Double> getIntegratedQext() {
		return integratedQext;
	}

	public MieWrapper() {
		super();
	}
	
	public boolean isCalculated() {
		return isCalculated;
	}

	@Override
	synchronized public boolean calcScattCoeffs() {
		isCalculated=true;
		return super.calcScattCoeffs();
	}

	@Override
	public void setHostRefractiveIndex(double refractiveIndexMedium) {
		isCalculated=false;
		this.refractiveIndexMedium=refractiveIndexMedium;
		super.setHostRefractiveIndex(refractiveIndexMedium);
	}

	@Override
	/** radius of particle and wavelength. Both in um **/
	public void setRadiusWavelength(double radius, double wavelength) {
		super.setRadiusWavelength(radius, wavelength);
		isCalculated=false;
		this.radius=radius;
		this.wavelength=wavelength;
	}

	@Override
	public void setRefractiveIndex(Complex arg0) {
		isCalculated=false;
		super.setRefractiveIndex(arg0);
	}

	@Override
	public void setRefractiveIndex(double realValue, double imaginaryPart) {
		isCalculated=false;
		this.refractiveIndexSphereReal=realValue;
		this.refractiveIndexSpereImaginary=imaginaryPart;
		super.setRefractiveIndex(realValue, imaginaryPart);
	}
	
	public double getRefractiveIndexSpereImaginary() {
		return refractiveIndexSpereImaginary;
	}

	public double getDiameter() {
		return radius*2;
	}
	
	private double getRadius() {
		return radius;
	}

	@Override
	public int compareTo(MieWrapper arg0) {
		return Double.compare(radius, arg0.radius);
	}

	public double getRefractiveIndexMedium() {
		return refractiveIndexMedium;
		
	}

	public double getRefractiveIndexSphereReal() {
		return this.refractiveIndexSphereReal;
	}

	public double getWavelength() {
		return this.wavelength;
	}
	
	public String toString() {
		return "Radius: "+getDiameter()+" Medium "+ getRefractiveIndexMedium()+
				" Sphere: "+getRefractiveIndexSphereReal()+"+"+getRefractiveIndexSpereImaginary()+"i"+
				"Wavelength: "+getWavelength();
	}
	
	/**
	 * as of the threated access to the sigma HashMap inidividual sigmas may be
	 * sorted differently in each MieElement. This method returns the sorted sigmas that are containes within this list.
	 **/
	public LinkedList<Double> getSortedSigmas() {
		LinkedList<Double> result = new LinkedList<>();
		result.addAll(getIntegratedQext().keySet());
		Collections.sort(result);
		return result;

	}

	


}
