package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import gui.ChangeWavelengthGui;

public class ChangeWavelengthAction extends AbstractAction {
	
	public ChangeWavelengthAction() {
		super("Change Wavelength");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new ChangeWavelengthGui().setVisible(true);

	}

}
