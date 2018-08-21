package actions;

import java.awt.Color;
import java.awt.Component;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import calculation.CalculationAssignment;
import gui.SizeParameterGui;
import presets.StandardDiameterParameters;

public class SizeAction extends AbstractAction implements FocusListener{

	private SizeParameterGui gui;
	CalculationAssignment ca = CalculationAssignment.getInstance();

	public SizeAction(SizeParameterGui gui) {
		this.gui=gui;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
		StandardDiameterParameters newValues = new StandardDiameterParameters(gui.getStartValue(), gui.getEndValue(),
				gui.getStepsValue(), gui.logarithmic());
		ca.setDiameters(newValues);
		//((JTextField)arg0.getSource()).setBackground(Color.WHITE);
		} catch (Exception e) {
			//JOptionPane.showMessageDialog((Component) arg0.getSource(), "Cannot parse value: "+e.getMessage());
			//((JTextField)arg0.getSource()).setBackground(Color.RED);
		}

	}
	
	public static enum sizeParameterEnum {
		end,start,steps,logarithmic;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void focusLost(FocusEvent arg0) {
		actionPerformed(new ActionEvent(arg0.getSource(), arg0.getID(), arg0.paramString()));
	}

}
