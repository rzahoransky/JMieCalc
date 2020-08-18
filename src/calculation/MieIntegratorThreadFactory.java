package calculation;

import java.util.ArrayList;

import jdistlib.LogNormal;
import presets.ISigmaPreset;

public class MieIntegratorThreadFactory{

	private MieList list;
	private ISigmaPreset distribution;
	private ArrayList<Thread> threads = new ArrayList<>();
	
	public static void main (String[] args) {
		LogNormal test = new LogNormal(0.1,0.1);
		System.out.println(test.density(1.5, false));
	}

	public MieIntegratorThreadFactory(MieList list, ISigmaPreset distribution) {
		//System.out.println("Adding Distributions");
		for(double sd:distribution.getSDs()) {
			//System.out.println("Traversing for "+list.get(0).getWavelength()+" and SD"+sd);
			threads.add(new Thread(new MieIntegratorRunnable(list, distribution.getType(), sd)));
		}
		
	}
	
	public ArrayList<Thread> getThreads(){
		return threads;
	}


}
