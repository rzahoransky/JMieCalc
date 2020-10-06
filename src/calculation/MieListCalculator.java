package calculation;

public class MieListCalculator implements Runnable{

	private MieList list;

	public MieListCalculator(MieList list) {
		this.list=list;
	}

	@Override
	public void run() {
		for (MieWrapper element:list) {
			element.calcScattCoeffs();
			//System.out.println("("+this+") Size: "+element.getRadius()+" - "+element.qext());
		}
	}
	
	public Thread calculate() {
		Thread thread = new Thread(this);
		thread.start();
		//System.out.println("Thread for WL: "+list.get(0).getWavelength()+" started.");
		return thread;
	}

}
