package actions;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.OptionPaneUI;

import calculation.CalculationAssignment;
import gui.SigmaParameterGui;
import presets.RunningSigma;

public class SigmaStepAction extends AbstractAction implements FocusListener, ChangeListener {

	private SigmaParameterGui gui;
	protected RunningSigma runningSigma;

	public SigmaStepAction(SigmaParameterGui gui) {
		this.gui = gui;
	}

	public SigmaStepAction(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public SigmaStepAction(String arg0, Icon arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		double start = Math.min(gui.getStartValue(), gui.getEndValue());
		double end = Math.max(gui.getStartValue(), gui.getEndValue());
		int steps = gui.getStepsValue();
		boolean log = gui.logarithmic();
		if (equal(start,end))
			return;
		runningSigma=new RunningSigma(start, end, steps, log);
		CalculationAssignment.getInstance().setSigmas(runningSigma);
		
		gui.setStartValue(start);
		gui.setEndValue(end);
		gui.setSteps(steps);
		gui.setLogarithmic(log);
		
	}

	private boolean equal(double start, double end) {
		if (Math.abs(end-start) <= Double.MIN_VALUE*10) {
			JOptionPane.showMessageDialog(gui, "Same start and end value");
			return true;
		}
		
		return false;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		actionPerformed(new ActionEvent(arg0.getSource(), arg0.getID(), arg0.paramString()));
		
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		actionPerformed(new ActionEvent(arg0.getSource(), arg0.hashCode(), arg0.toString()));
		
	}

}
