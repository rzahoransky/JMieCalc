package actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import calculation.CalculationAssignment;
import gui.SigmaParameterGui;
import presets.FixedSigmaParameter;

public class SpecificSigmaAction extends AbstractAction implements FocusListener{

	private SigmaParameterGui gui;

	public SpecificSigmaAction(SigmaParameterGui gui) {
		this.gui=gui;
	}

	public SpecificSigmaAction(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public SpecificSigmaAction(String arg0, Icon arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		JTextField text = (JTextField) arg0.getSource();
		try {
		String[] values = text.getText().split(",");
		double[] sigmas = new double[values.length];
		
		for (int i = 0; i<values.length;i++)
			sigmas[i] = Double.parseDouble(values[i]);
		
		FixedSigmaParameter sigmaParameter = new FixedSigmaParameter(sigmas);
		CalculationAssignment.getInstance().setSigmas(sigmaParameter);
		System.out.println("Setting sigmas...");
		((JTextField)arg0.getSource()).setBackground(Color.WHITE);
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(text, "Cannot parse sigmas: Values have to be sperated by comma");
			((JTextField)arg0.getSource()).setBackground(Color.red);
		}
		
	}

}
