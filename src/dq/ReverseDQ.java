package dq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import calculation.MieList;

/** List of particle matches against a DQ **/
public class ReverseDQ {

	private MieList wl1;
	private MieList wl2;
	HashMap<Double,ArrayList<ReverseDQEntry>> dqs = new HashMap<>(); //sigma -> reverse DQ Entries
	HashMap<Double, List<ReverseDQEntry>> cache = new HashMap<>();
	int cacheHits = 0;
	

	public ReverseDQ(MieList wl1, MieList wl2) {
		this.wl1=wl1;
		this.wl2=wl2;
		generateDQField();
	}
	
	private void generateDQField() {
		for (int i = 0; i<wl1.size();i++) {
			for (double sigma: wl1.get(i).getSortedSigmas()) {
				add(new ReverseDQEntry(sigma, wl1.get(i), wl2.get(i)));
			}
		}
	}
	
	private void add(ReverseDQEntry dq) {
		if (!dqs.containsKey(dq.getSigma()))
			dqs.put(dq.getSigma(), new ArrayList<>());
		dqs.get(dq.getSigma()).add(dq);
	}
	
	public List<ReverseDQEntry> getDQHits(double dq) {
		if (cache.containsKey(dq)) {
			return cache.get(dq);
		}
			
		ArrayList<ReverseDQEntry> dqHits = new ArrayList<>(20);
		for (double sigma: dqs.keySet()) {
			ArrayList<ReverseDQEntry> reverseDQForCurrentSigma = dqs.get(sigma);
			for (int i = 1; i<reverseDQForCurrentSigma.size(); i++) { //one dq may point to different particle diameters. Stopping at the first match does not work
				if (checkMatch(reverseDQForCurrentSigma, i, dq)) { //this is required since there may be more than one particle size per single dq. A TreeMap lookup may yield wrong results in this case
					dqHits.add(closestElement(reverseDQForCurrentSigma.get(i-1), reverseDQForCurrentSigma.get(i), dq));
				}
			}
		}
		cache.put(dq, dqHits);
		return dqHits;
	}

	/**test if the given dq and index match**/
	private boolean checkMatch(ArrayList<ReverseDQEntry> elem, int i, double dq) {
		if (elem.get(i-1).getDq()<=dq && elem.get(i).getDq()>=dq)
			return true;
		if (elem.get(i-1).getDq()>=dq && elem.get(i).getDq()<=dq)
			return true;
		return false;
	}

	/** return which of the two reverseDqEntrys is closer to the actual measured dq **/
	private ReverseDQEntry closestElement(ReverseDQEntry reverseDQEntry1, ReverseDQEntry reverseDQEntry2, double dq) {
		double distance1 = Math.abs(dq-reverseDQEntry1.getDq());
		double distance2 = Math.abs(dq-reverseDQEntry2.getDq());
		if (distance1<distance2)
			return reverseDQEntry1;
		return reverseDQEntry2;
	}

}
