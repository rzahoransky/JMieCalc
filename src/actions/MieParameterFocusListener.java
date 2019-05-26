package actions;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import calculation.CalculationAssignment;
import gui.MieParameterGui;
import presets.RefractiveIndex;
import presets.Wavelengths;

/**
 * Listener to configure the {@link CalculationAssignment} instance for the given Wavelengths wl and refractive index type
 * @author richard
 *
 */
public class MieParameterFocusListener implements FocusListener {

	private Wavelengths wl;
	private RefractiveIndex indexType;

/**
 * Let this listener change the specificied {@link Wavelengths}	<b>wl</b> for the {@link RefractiveIndex} <b>indexType</b>
 * @param wl The {@link Wavelength} that will be changed by this listener
 * @param indexType The {@link RefractiveIndex} Index Type that will be changed by this listener
 */
	public MieParameterFocusListener(Wavelengths wl, RefractiveIndex indexType) {
		this.wl=wl;
		this.indexType=indexType;
	}

	public MieParameterFocusListener(MieParameterGui mieParameterGui) {
		this.wl=mieParameterGui.getWl();
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent arg0) {
		CalculationAssignment calcAssignment = CalculationAssignment.getInstance();
		TextField source = (TextField) arg0.getSource();
		try {
		double value = Double.parseDouble(source.getText());
		((TextField)(arg0.getSource())).setBackground(Color.WHITE);
		
		calcAssignment.getParticles().setName("User defined");
		
		switch (indexType) {
		case RefIndexMedium:
			System.out.println("Setting refIndex");
			calcAssignment.getParticles().setRefractiveIndexMedium(wl.getValue(), value);
			break;
		case RefIndexSphereImaginary:
			System.out.println("Setting sphere real");
			calcAssignment.getParticles().setRefractiveIndexSphereImaginaray(wl.getValue(), value);
			break;
		case RefIndexSphereReal:
			System.out.println("Setting sphere imag");
			calcAssignment.getParticles().setRefractiveIndexSphereReal(wl.getValue(), value);
			break;
		default:
			break;
		}
		
		System.out.println("Setting RefMedium to: "+source.getText()); 
		} catch (NumberFormatException e) {
			((TextField)(arg0.getSource())).setBackground(Color.RED);
		}

	}

}
