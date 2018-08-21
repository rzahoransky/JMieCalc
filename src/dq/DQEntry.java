package dq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import calculation.MieWrapper;

public class DQEntry {
	
	TreeMap<Double, NavigableSet<Double>> sigma = new TreeMap<>(); // sigma -> possible sizes
	double dq;

	public DQEntry(double dq) {
		this.dq = dq;
	}
	
	public void addSigmaEntry(double sigma, double size) {
		if(this.sigma.containsKey(sigma)) {
			this.sigma.get(sigma).add(size);
		} else {
			TreeSet<Double> list = new TreeSet<>();
			list.add(size);
			this.sigma.put(sigma, list);
		}
	}
	
	public NavigableSet<Double> getSigmas() {
		return sigma.navigableKeySet();
	}
	
	public NavigableSet<Double> getPossibleDiameterForSigma(double sigma) {
		return this.sigma.get(sigma);
	}
	
	public double getDQ() {
		return dq;
	}

}
