package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import actions.MieParameterFocusListener;
import calculation.CalculationAssignment;
import calculation.CalculationAssignmentListener;
import errors.WavelengthMismatchException;
import presets.RefractiveIndex;
import presets.Wavelengths;

public class MieParameterGui extends JPanel implements CalculationAssignmentListener{

	private Wavelengths wl;
	private TextWithDescription textFields;
	protected static final String refMedium = "Medium";
	protected static final String refSphereReal = "Sphere Real";
	protected static final String refSphereImag = "Imaginary";
	protected JLabel label;

	public MieParameterGui(Wavelengths wl) {
		this.wl=wl;
		setLayout(new GridBagLayout());
		//setBorder(BorderFactory.createLineBorder(Color.black));
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=0;
		label = new JLabel("Ref. Index for WL "+wl.getValue()+"µm");
		add(label,c);
		c.insets = new Insets(3, 3, 3, 3);
		c.fill=GridBagConstraints.HORIZONTAL;
		c.weightx=1;
		c.gridx=0;
		c.gridy=1;
		ArrayList<String> names = new ArrayList<>();
		names.add(refMedium);
		names.add(refSphereReal);
		names.add(refSphereImag);
		textFields = new TextWithDescription(names);
		add(textFields,c);
		CalculationAssignment.getInstance().addListener(this);
		addListeners();
	}
	
	private void addListeners() {
		textFields.getTextField(refMedium).addFocusListener(new MieParameterFocusListener(wl, RefractiveIndex.RefIndexMedium));
		textFields.getTextField(refSphereImag).addFocusListener(new MieParameterFocusListener(wl, RefractiveIndex.RefIndexSphereImaginary));
		textFields.getTextField(refSphereReal).addFocusListener(new MieParameterFocusListener(wl, RefractiveIndex.RefIndexSphereReal));
	}

	public double getRefIndexMedium() {
		return textFields.getValueAsDouble(refMedium);
	}
	
	public double getRefIndexSphereReal() {
		return textFields.getValueAsDouble(refSphereReal);
	}
	
	public double getRefIndexSphereImag() {
		return textFields.getValueAsDouble(refSphereImag);
	}
	
	public Wavelengths getWl() {
		return wl;
	}

	@Override
	public void mieParticleChanged() {
		try {
			double refMediumValue = CalculationAssignment.getInstance().getParticles().getRefractiveIndexMedium(wl);
			textFields.getTextField(refMedium).setText(String.valueOf(refMediumValue));
			textFields.getTextField(refMedium).repaint();
			
			double sphereReal = CalculationAssignment.getInstance().getParticles().getRefractiveIndexSphereReal(wl);
			textFields.getTextField(refSphereReal).setText(String.valueOf(sphereReal));
			textFields.getTextField(refSphereReal).repaint();
			
			double sphereIm = CalculationAssignment.getInstance().getParticles().getRefractiveIndexSphereImaginaray(wl);
			textFields.getTextField(refSphereImag).setText(String.valueOf(sphereIm));
			textFields.getTextField(refSphereImag).repaint();
		} catch (WavelengthMismatchException e) {
			JOptionPane.showMessageDialog(this, "Wavelengths in Presets do not match: "+e.getMessage());
		}
		
	}

	@Override
	public void wavelengthsChanged() {
		label.setText(("Parameters for WL "+wl.getValue()+"um")); //Or: Wavelength in TextField, make it editable in expert mode
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
