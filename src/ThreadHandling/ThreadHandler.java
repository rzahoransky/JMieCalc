package ThreadHandling;

import java.util.ArrayList;

import calculation.CalculationAssignment;

public class ThreadHandler {

	private ArrayList<Thread> threads;
	int processors;
	private CalculationAssignment monitor;

	public ThreadHandler(ArrayList<Thread> threads) {
		this.threads=threads;
		processors = Runtime.getRuntime().availableProcessors();
	}
	
	public void addThreadMonitor(CalculationAssignment monitor) {
		this.monitor=monitor;
	}
	
	private void updateMonitor(int currentIndex) {
		if (monitor!=null)
			monitor.setProgress(currentIndex, threads.size());
	}
	
	public void runThreads () throws InterruptedException {
		boolean finished = false;
		int i = 0;
		updateMonitor(i);
		while(i<threads.size()) {
			for(int cpu = 0; (cpu < processors)&&((i+cpu)<threads.size()); cpu++) {
				threads.get(cpu+i).start();
				System.out.println("Processing Thread "+(cpu+i));
				//System.out.println("Running thread for element "+(i+cpu));
			}
			//wait for them ALL to finish (not the best to do... but still... 
			//System.out.println("Waiting for threads...");
			for(int cpu = 0; (cpu < processors)&&((i+cpu)<threads.size()); cpu++) {
				threads.get(cpu+i).join(); //wait for finish
			}
			//System.out.println("Threads finished...");	
			i=i+processors;
			updateMonitor(i);
		}
	}

}
