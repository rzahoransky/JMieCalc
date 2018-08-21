package dq;

import calculation.MieWrapper;

public class MiePair {
	MieWrapper firstElement;
	MieWrapper secondElement;

	public MiePair(MieWrapper first, MieWrapper second) {
		firstElement=first;
		secondElement=second;
		if (firstElement.getDiameter() != secondElement.getDiameter()) {
			System.err.println("MieWrapper elements do not match!");
		}
	}
	
	public double getDiameter() {
		return firstElement.getDiameter();
	}
	
	public MieWrapper getFirstElement() {
		return firstElement;
	}
	
	public MieWrapper getSecondElement() {
		return secondElement;
	}

}
