package calculation;

import java.util.ArrayList;
import java.util.Iterator;

import errors.IllegalMieListException;

public class MieList implements Iterable<MieWrapper>{

	private ArrayList<MieWrapper> list = new ArrayList<MieWrapper>();
	private int currentIndex=0;
	
	public MieList() {
	}
	
	public MieList(int size) {
		list = new ArrayList<>(size);
	}
	
	private void addMieElement(MieWrapper mieElement) {
		if(mieElement.getDiameter()==Double.NaN) 
			throw new IllegalArgumentException("mieElement must have a radius");
		list.add(mieElement);
	}
	
	public void addElement(MieWrapper element) {
		addMieElement(element);
	}
	
	public void checkConsistency() throws IllegalMieListException {
		MieWrapper firstElement = list.get(0);

		for(MieWrapper check:list) {
			if (!sameParameteres(firstElement, check))
				throw new IllegalMieListException("Parameters in list do not match! "+firstElement+" VS "+check);
		}
	}
	
	private boolean sameParameteres(MieWrapper firstElement, MieWrapper secondElement) {
		double radius = firstElement.getDiameter();
		double medium = firstElement.getRefractiveIndexMedium();
		double wavelength = firstElement.getWavelength();
		double refIndParticleReal = firstElement.getRefractiveIndexSphereReal();
		double refIndParticleImag = firstElement.getRefractiveIndexSpereImaginary();
		
		if(medium!=secondElement.getRefractiveIndexMedium())
			return false;
		if(wavelength!=secondElement.getWavelength())
			return false;
		if(refIndParticleReal!=secondElement.getRefractiveIndexSphereReal())
			return false;
		if(refIndParticleImag!=secondElement.getRefractiveIndexSpereImaginary())
			return false;
		
		return true;
	}

	@Override
	public Iterator<MieWrapper> iterator() {
		return list.iterator();
	}
	
	public MieWrapper get(int index) {
		return list.get(index);
	}
	
	public int size() {
		return list.size();
	}
	
	public MieWrapper getElementForDiameter(double diameter) {
		for(MieWrapper mie: list) {
			if(mie.getDiameter()==diameter) {
				return mie;
			}
		}
		return null;
	}
	
	public MieWrapper getClosesElementForDiameter(double diameter) {
		double distance = Double.MAX_VALUE;
		MieWrapper closestElement = null;
		for (MieWrapper mie: list) {
			if(Math.abs(mie.getDiameter()-diameter)<distance) {
				distance = Math.abs(mie.getDiameter()-diameter);
				closestElement = mie;
			}
		}
		return closestElement;
	}
	
	public double getWavelength() {
		return list.get(0).getWavelength();
	}
	
	public double getRefMedium() {
		return list.get(0).getRefractiveIndexMedium();
	}
	
	public double getRefSphereReal() {
		return list.get(0).getRefractiveIndexSphereReal();
	}
	
	public double getRefSphereImag() {
		return list.get(0).getRefractiveIndexSpereImaginary();
	}
	
	public double getMinDiameter() {
		return list.get(0).getDiameter();
	}
	
	public double getMaxDiameter() {
		return list.get(list.size()-1).getDiameter();
	}
	
	public String toString() {
		return getWavelength()+"µm ("+getRefMedium()+" / "+getRefSphereReal()+"-"+getRefSphereImag()+"i)";
	}

}
