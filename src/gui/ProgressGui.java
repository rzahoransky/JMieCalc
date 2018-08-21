package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

import calculation.CalculationAssignment;
import calculation.CalculationAssignmentListener;

public class ProgressGui extends JPanel implements CalculationAssignmentListener{
	
	JProgressBar progressBar = new JProgressBar(0, 100);

	public ProgressGui() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx=1;
		c.fill=GridBagConstraints.HORIZONTAL;
		add(progressBar,c);
		CalculationAssignment.getInstance().addListener(this);
	}

	@Override
	public void mieParticleChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void wavelengthsChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void calculationFinished() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void progress() {
		progressBar.setValue(CalculationAssignment.getInstance().getProgress());
		progressBar.repaint();
		
	}

	@Override
	public void diametersChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sigmaChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void outputFileChanged() {
		// TODO Auto-generated method stub
		
	}

}
