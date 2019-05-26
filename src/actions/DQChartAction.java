package actions;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import calculation.CalculationAssignment;
import charts.ChartType;
import charts.Charts;

public class DQChartAction extends AbstractAction {

	private CalculationAssignment calcAssignment;

	public DQChartAction() {
		super("Show DQs");
		this.calcAssignment = CalculationAssignment.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(calcAssignment.isFinished()) {
			Charts dq = new Charts(ChartType.DQField, calcAssignment);
		dq.setVisible(true);
		} else {
			JOptionPane.showMessageDialog((Component) arg0.getSource(), "Please run calculation first");
		}

	}

}
