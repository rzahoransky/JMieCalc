package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import calculation.CalculationAssignment;
import calculation.CalculationAssignmentListener;
import charts.ChartType;
import charts.Charts;

public class ShowAfterCalculationGUI extends JPanel implements CalculationAssignmentListener{
	
	JCheckBox show = new JCheckBox("Show DQ after calculation");

	public ShowAfterCalculationGUI() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx=1;
		c.fill=GridBagConstraints.HORIZONTAL;
		add(show,c);
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
		if (show.isSelected()) {
			Charts dq = new Charts(ChartType.DQField, CalculationAssignment.getInstance());
			dq.setVisible(true);
		}
		
	}

	@Override
	public void progress() {
		// TODO Auto-generated method stub
		
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
