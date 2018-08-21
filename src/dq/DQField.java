package dq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;

import calculation.MieList;
import calculation.MieWrapper;

public class DQField {
	
	//HashMap<Double, HashMap<Double, Double>> dq = new HashMap<>(); //DQ->(Sigma->ParticleSize)
	TreeMap<Double, DQEntry> dqList = new TreeMap<>(); //DQ -> possible Sigmas (-> particle sizes within Sigma)
	double firstWavelength;
	double secondWavelength;

	public DQField(MieList wl1, MieList wl2) {
		firstWavelength = wl1.getWavelength();
		secondWavelength = wl2.getWavelength();
		
		for (int i = 0; i<wl1.size();i++) {
			for (double sigma: wl1.get(i).getSortedSigmas()) {
				double dq = getDQ(wl1.get(i), wl2.get(i), sigma); //wl1 and wl2 have the same diameter at i
				add(dq, sigma, wl1.get(i).getDiameter());
			}
		}
	}
	
	private double getDQ(MieWrapper wl1, MieWrapper wl2, double sigma) {
		return wl1.getIntegratedQext().get(sigma) / wl2.getIntegratedQext().get(sigma);
	}
	
	private void add(double dq, double sigma, double size) {
		if(dqList.containsKey(dq)) {
			dqList.get(dq).addSigmaEntry(sigma, size);
		} else {
			DQEntry sigmaElement = new DQEntry(dq);
			sigmaElement.addSigmaEntry(sigma, size);
			dqList.put(dq, sigmaElement);
		}
	}
	
	public DQEntry getEntryFor(double dq) {
		//double dq_match = findClosestMatch(dq);
		//return dqList.get(dq_match);
		return findClosestMatch(dq).getValue();
	}

	private Entry<Double, DQEntry> findClosestMatch(double dq) {
		
		double floor = dqList.floorKey(dq);
		double ceiling = dqList.ceilingKey(dq);
		
		if (Math.abs(floor - dq) < Math.abs(ceiling - dq)) {
			return dqList.floorEntry(dq);
		} else {
			return dqList.ceilingEntry(dq);
		}
		
//		NavigableSet<Double> navigator = (NavigableSet<Double>) dqList.keySet();
//		dqList.keySet().
//		
//		double distance = Double.MAX_VALUE;
//		double match = Double.MAX_VALUE;
//		for (double close: dqList.keySet()) {
//			if(Math.abs(close - dq) < distance) {
//				distance = Math.abs(close - dq);
//				match = close;
//			}
//			else 
//				return close;
//		}
//		return match;
	}

	public double getFirstWavelength() {
		return firstWavelength;
	}

	public double getSecondWavelength() {
		return secondWavelength;
	}
	
	

}
