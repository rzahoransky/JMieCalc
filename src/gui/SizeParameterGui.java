package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import actions.SizeAction;
import calculation.CalculationAssignment;
import calculation.CalculationAssignmentListener;
import presets.IDiameterParametersInterface;

public class SizeParameterGui extends JPanel implements CalculationAssignmentListener{
	
	GridBagConstraints c = new GridBagConstraints();
	JTextField start = new JTextField();
	JTextField end = new JTextField();
	JTextField steps = new JTextField();
	JCheckBox logarithmic = new JCheckBox(null,null,true);

	public SizeParameterGui() {
		setLayout(new GridBagLayout());
		JLabel start = new JLabel("Start diameter (µm)");
		JLabel end = new JLabel("End diameter (µm)");
		JLabel steps = new JLabel("Steps");
		JLabel logarithmic = new JLabel("Logarithmic scale");
		c.weightx=1;
		c.gridy=0;
		c.gridx=0;
		add(start,c);
		c.gridx=1;
		add(end,c);
		c.gridx=2;
		add(steps,c);
		c.gridx=3;
		add(logarithmic,c);
		
		addTextFields();
		
		CalculationAssignment.getInstance().addListener(this);
	}
	
	private void addTextFields() {
		start.addFocusListener(new SizeAction(this));
		end.addFocusListener(new SizeAction(this));
		steps.addFocusListener(new SizeAction(this));
		logarithmic.addFocusListener(new SizeAction(this));
		c.gridy=1;
		c.gridx=0;
		c.weightx=1;
		c.fill=GridBagConstraints.HORIZONTAL;
		add(start,c);
		c.gridx=1;
		add(end,c);
		c.gridx=2;
		add(steps,c);
		c.gridx=3;
		c.fill=GridBagConstraints.NONE;
		c.anchor=GridBagConstraints.CENTER;
		add(logarithmic,c);
		
	}

	public static void main (String args[]) {
		JFrame frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new SizeParameterGui());
		frame.setSize(new Dimension(400, 100));
		frame.setVisible(true);
	}
	
	public double getStartValue() {
		try {
			start.setBackground(Color.WHITE);
			return Double.parseDouble(start.getText());
		} catch (Exception e) {
			start.setBackground(Color.red);
			throw e;
		}
	}
	
	public double getEndValue() {
		try {
			end.setBackground(Color.WHITE);
			return Double.parseDouble(end.getText());
		} catch (Exception e) {
			end.setBackground(Color.red);
			throw e;
		}
	}
	
	public int getStepsValue() {
		try {
		int step = Integer.parseInt(steps.getText());
		steps.setBackground(Color.white);
		if(step<101) 
			return 100;
		return Integer.parseInt(steps.getText());
		} catch (Exception e) {
			steps.setBackground(Color.red);
			throw e;
		}

		
	}
	
	public boolean logarithmic() {
		return logarithmic.isSelected();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void diametersChanged() {
		CalculationAssignment calcAssignment = CalculationAssignment.getInstance();
		start.setText(String.valueOf(calcAssignment.getDiameters().getMinSize()));
		end.setText(String.valueOf(calcAssignment.getDiameters().getMaxSize()));
		steps.setText(String.valueOf(calcAssignment.getDiameters().getSteps()));
		logarithmic.setSelected(calcAssignment.getDiameters().isLogarithmic());
	}

	@Override
	public void sigmaChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void outputFileChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fileWritten() {
		// TODO Auto-generated method stub
		
	}
	

}
