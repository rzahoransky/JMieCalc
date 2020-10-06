package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import actions.SigmaStepAction;
import actions.SpecificSigmaAction;
import calculation.CalculationAssignment;
import calculation.CalculationAssignmentListener;
import presets.ISigmaPreset;
import presets.RunningSigma;

public class SigmaParameterGui extends JPanel implements CalculationAssignmentListener{
	private static final long serialVersionUID = 1L;
	
	GridBagConstraints c = new GridBagConstraints();
	//sigma lower than 0.001 lead to wrong calculations 
	//(distribution gets too narrow and too step to calculate in double
	JSpinner start = new JSpinner(new SpinnerNumberModel(0.005, 0.003, 10, 0.1)); 
	JSpinner end = new JSpinner(new SpinnerNumberModel(0.2, 0.003, 10, 0.1));
	JSpinner steps = new JSpinner(new SpinnerNumberModel(3, 1, 30, 1));
	JCheckBox logarithmic = new JCheckBox(null,null,true);
	JTextField specificSigmas = new JTextField();
	JButton generate = new JButton("generate distribution");
	private SigmaStepAction action = new SigmaStepAction(this);

	public SigmaParameterGui() {
		setLayout(new GridBagLayout());
		JLabel start = new JLabel("Low Sigma");
		JLabel end = new JLabel("High Sigma");
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
		
		specificSigmas.addFocusListener(new SpecificSigmaAction(this));
		
		addTextFields();
		
		addListeners();
		
		CalculationAssignment.getInstance().addListener(this);
	}
	
	private void addTextFields() {
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
		c.gridx++;
		c.gridwidth=1;
		//c.fill=GridBagConstraints.HORIZONTAL;
		add(generate,c);
		c.gridx=0;
		c.gridy++;
		c.gridwidth=4;
		c.fill=GridBagConstraints.HORIZONTAL;
		add(specificSigmas,c);
	}

	public static void main (String args[]) {
		JFrame frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new SigmaParameterGui());
		frame.setSize(new Dimension(400, 100));
		frame.setVisible(true);
	}
	
	public double getStartValue() {
		return (double) start.getValue();
	}
	
	public void setStartValue(double start) {
		this.start.setValue(start);
	}
	
	public double getEndValue() {
		return (double) end.getValue();
	}
	
	public void setEndValue(double end) {
		this.end.setValue(end);
	}
	
	public int getStepsValue() {
		int step =  (int) steps.getValue();
		return step;
	}
	
	public void setSteps(int steps) {
		this.steps.setValue(steps);
	}
	
	public boolean logarithmic() {
		return logarithmic.isSelected();
	}
	
	public void setLogarithmic(boolean log) {
		this.logarithmic.setSelected(log);
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
	public void progress(double fraction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void diametersChanged() {
	}

	@Override
	public void sigmaChanged() {
		CalculationAssignment ca = CalculationAssignment.getInstance();
		
		ISigmaPreset preset = ca.getSigmas();
		
		if(preset instanceof RunningSigma) {
			RunningSigma sigma = (RunningSigma) preset;
			start.setValue(sigma.getStart());
			end.setValue(sigma.getEnd());
			steps.setValue(sigma.getSteps());
			logarithmic.setSelected(sigma.isLogarithmic());
		} else {
		
		start.setValue(ca.getSigmas().getSDs().get(0));
		end.setValue(ca.getSigmas().getSDs().get(ca.getSigmas().getSDs().size()-1));
		steps.setValue(ca.getSigmas().getSDs().size());
		logarithmic.setSelected(false);
		//addListeners();
		}
		specificSigmas.setText(ca.getSigmas().toString());
	}
	
	protected void addListeners() {
		generate.addActionListener(action);
//		start.addFocusListener(action);
//		start.addChangeListener(action);
//		end.addFocusListener(action);
//		end.addChangeListener(action);
//		steps.addFocusListener(action);
//		steps.addChangeListener(action);
//		logarithmic.addFocusListener(action);
//		logarithmic.addChangeListener(action);
	}
	
	protected void removeListeners() {
//		start.removeChangeListener(action);
//		end.removeChangeListener(action);
//		steps.removeChangeListener(action);
//		logarithmic.removeChangeListener(action);
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
