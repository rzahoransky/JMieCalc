package ThreadHandling;

public class ThreadHandlerProgressMonitor {
	int progress=0;

	public ThreadHandlerProgressMonitor() {
		// TODO Auto-generated constructor stub
	}
	
	public void setProgress(int current, int end) {
		progress = (int) ((current/(double)end)*100);
		System.out.println(progress+"%");
	}

}
