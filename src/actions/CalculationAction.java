package actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import calculation.CalculationAssignment;
import calculation.MieList;
import errors.IllegalMieListException;
import errors.WavelengthMismatchException;
import gui.OldMieCalculator;
import presets.Wavelengths;
import storage.dqMeas.write.OutputWriter;

public class CalculationAction extends AbstractAction {

	private CalculationAssignment calcAssignment;
	
	public CalculationAction() {
		super("calc");
		this.calcAssignment = CalculationAssignment.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		JButton source = (JButton) arg0.getSource();

		source.setEnabled(false);
		
		//start as Thread to keep GUI responding
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println("Calculating with: " + calcAssignment.toString());
					OldMieCalculator calc = new OldMieCalculator(calcAssignment);
					HashMap<Wavelengths, MieList> results = calc.calculateDQ();
					calcAssignment.setResultMieList(results);
					calcAssignment.informOfCalculationFinished();
					System.out.println("finished");

					if (calcAssignment.getOutputFile() != null && calcAssignment.getOutputFile().getName().length()>1) {
						OutputWriter ow = new OutputWriter(calcAssignment);
						ow.writeAsZip(calcAssignment.getOutputFile());
					}

				} catch (InterruptedException | IllegalMieListException | WavelengthMismatchException | IOException e) {
					JOptionPane.showMessageDialog((Component) arg0.getSource(), "Could not calculate: " + e.getMessage());
				} finally {
					// reenable button
					source.setEnabled(true);
				}
				
			}
		}).start();
		

	}
}
