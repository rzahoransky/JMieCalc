package calculation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import errors.IllegalMieListException;

/**
 * Holds a list of {@link MieWrapper} elements of same optical parameters.
 * Diameters and wavelengths are in um.
 * @author richard
 *
 */
public class MieList implements Iterable<MieWrapper>{

	private TreeMap<Double,MieWrapper> radiusLookupList = new TreeMap<>(); //Radius, MieElement
	private int currentIndex=0;
	private ArrayList<MieWrapper> array = new ArrayList<>(100);
	
	public MieList() {
	}
	
	public MieList(int size) {
		array = new ArrayList<>(size);
	}
	
	private void addMieElement(MieWrapper mieElement) {
		if(mieElement.getRadius()==Double.NaN) 
			throw new IllegalArgumentException("mieElement must have a diameter");
		array.add(mieElement);
		radiusLookupList.put(mieElement.getRadius(), mieElement);
	}
	
	public void addElement(MieWrapper element) {
		addMieElement(element);
	}
	
	/**
	 * Check if list is sane
	 * @throws IllegalMieListException
	 *             if the list contains elements that have different wavelengths or
	 *             refractive indices
	 */
	public void checkConsistency() throws IllegalMieListException {
		MieWrapper firstElement = array.get(0);

		for (MieWrapper check : radiusLookupList.values()) {
			if (!sameParameteres(firstElement, check))
				throw new IllegalMieListException("Parameters in list do not match! " + firstElement + " VS " + check);
		}
	}
	
	/** test if all particles in list share same optical parameters**/
	private boolean sameParameteres(MieWrapper firstElement, MieWrapper secondElement) {
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
		return array.iterator();
	}
	
	/**
	 * Return {@link MieWrapper} element at the index-position.
	 * @param index index to get with respect to insertation order
	 * @return {@link MieWrapper} element at index-position
	 */
	public MieWrapper get(int index) {
		return array.get(index);
	}
	
	public int size() {
		return radiusLookupList.size();
	}
	
	/**
	 * Returns the {@link MieWrapper} element for an exact diameter
	 * @param diameter Diameter to search for
	 * @return {@link MieWrapper} element that matches the given diameter. <code>null</code> if none is found
	 */
	public MieWrapper getElementForDiameter(double diameter) {
		if (!radiusLookupList.containsKey(diameter/2)) {
			System.out.println("Missed a MieParticle Entry!");
			return null;
		}
		return radiusLookupList.get(diameter/2);
	}
	
	/**
	 * Returns the element in the list so that |<b>diameter</b> - <b>diameter of found element</b>| is minimized
	 * @param diameter the diameter in um to search for in the list
	 * @return {@link MieWrapper} element with closes matching diameter
	 */
	public MieWrapper getClosestElementForDiameter(double diameter) {
		return radiusLookupList.get(getClosesMatchingKey(diameter/2));
	}
	
	/** get the wavelength of this list **/
	public double getWavelength() {
		return array.get(0).getWavelength();
	}
	
	/** get the refractive index of the sphere hosting medium **/
	public double getRefMedium() {
		return array.get(0).getRefractiveIndexMedium();
	}
	
	/** get the real part of the spheres refractive index **/
	public double getRefSphereReal() {
		return array.get(0).getRefractiveIndexSphereReal();
	}
	
	/** get the imaginary part of the spheres refractive index **/
	public double getRefSphereImag() {
		return array.get(0).getRefractiveIndexSpereImaginary();
	}
	
	public double getMinDiameter() {
		return array.get(0).getRadius()*2;
	}
	
	public double getMaxDiameter() {
		return array.get(radiusLookupList.size()-1).getRadius()*2;
	}
	
	public String toString() {
		return getWavelength()+"�m ("+getRefMedium()+" / "+getRefSphereReal()+"-"+getRefSphereImag()+"i)";
	}
	
	/** get the key in the list that matches most closely to provided key **/
	protected double getClosesMatchingKey(double keyToSearch) {
		if (keyToSearch>=radiusLookupList.lastKey())
			return radiusLookupList.lastKey();
		if (keyToSearch<=radiusLookupList.firstKey())
			return radiusLookupList.firstKey();
		if (Math.abs(radiusLookupList.ceilingKey(keyToSearch)-keyToSearch)<Math.abs(radiusLookupList.floorKey(keyToSearch)-keyToSearch)) {
			return radiusLookupList.ceilingKey(keyToSearch);
		} else {
			return radiusLookupList.floorKey(keyToSearch);
		}
	}

}
