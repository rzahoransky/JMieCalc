package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import calculation.CalculationAssignment;
import presets.Wavelengths;
import storage.dqMeas.read.LastResortDoubleParser;

public class ChangeWavelengthGui extends JFrame {
	GridBagConstraints c = new GridBagConstraints();
	ArrayList<WavelengthBox> wavelengthBoxes = new ArrayList<>(Wavelengths.values().length);
	JCheckBox defaultCheck;
	
	public static void main(String[] args) {
		ChangeWavelengthGui gui = new ChangeWavelengthGui();
		gui.setVisible(true);
	}
	
	public ChangeWavelengthGui() {
		defaultCheck = new JCheckBox("Set as default");
		defaultCheck.setSelected(false);
		setLayout(new GridBagLayout());
		setSize(new Dimension(300, 100));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		c.gridx=0;
		c.gridy=0;
		c.fill=GridBagConstraints.HORIZONTAL;
		c.weightx=1;
		c.weighty=1;
		
		setLocationByPlatform(true);
		
		for (Wavelengths wl: Wavelengths.values()) {
			WavelengthBox box = new WavelengthBox(wl);
			wavelengthBoxes.add(box);
			add(box,c);
			c.gridx++;
		}
		
		c.gridx=0;
		c.gridy++;
		add(defaultCheck,c);
		c.gridx++;
		add(getCancelBtn(),c);
		c.gridx++;
		add(getOkBtn(),c);
	}
	
	public JButton getOkBtn() {
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (WavelengthBox box: wavelengthBoxes) {
					try {
					CalculationAssignment.getInstance().changeWavelength(box.getWavelength(), box.getUserSetWavelength());
					} catch (Exception e1) {}; //ignore parsing exceptions
				}
				if (defaultCheck.isSelected()) {
					Wavelengths.store();
				}
				dispose();
				
			}
		});
		return ok;
	}
	
	public JButton getCancelBtn() {
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		return cancel;
	}
	
	
	class WavelengthBox extends JPanel {
		Wavelengths wl;
		JLabel descriptor;
		JTextField wavelength;
		
		public WavelengthBox(Wavelengths wl) {
			this.wl = wl;
			//descriptor = new JLabel("WL "+(wl.ordinal()+1)+": ");
			descriptor = new JLabel(wl.name()+": ");
			wavelength = new JTextField();
			wavelength.setText(Double.toString(wl.getValue()));
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.gridx=0;
			c.gridy=0;
			c.fill = GridBagConstraints.NONE;
			c.weightx=1;
			c.weighty=1;
			add(descriptor,c);
			c.gridx++;
			c.fill = GridBagConstraints.HORIZONTAL;
			add(wavelength,c);
		}
		
		public double getUserSetWavelength() {
			return LastResortDoubleParser.parse(wavelength.getText());
		}
		
		public Wavelengths getWavelength() {
			return wl;
		}
		
	}

	
	
}
