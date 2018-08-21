package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListModel;

import buildInPresets.DieselSoot;
import buildInPresets.LatexInWaterPreset;
import buildInPresets.NarrowLogNormalDistribution;
import buildInPresets.OilInWater;
import buildInPresets.SilverInAir;
import buildInPresets.SiOinAir;
import buildInPresets.SiInAir;
import buildInPresets.SiO2inAir;
import buildInPresets.WaterInAir;
import calculation.CalculationAssignment;
import calculation.CalculationAssignmentListener;
import errors.WavelengthMismatchException;
import presets.ISigmaPreset;
import presets.IMieParticlePreset;
import presets.ParticlePreset;
import presets.StandardDiameterParameters;
import presets.FixedSigmaParameter;
import presets.IDiameterParametersInterface;

public class PresetsGui extends JPanel {
	JComboBox<IDiameterParametersInterface> sizeParamList = new JComboBox<>();
	JComboBox<ISigmaPreset> sigmaDistributionList= new JComboBox<>();
	JComboBox<IMieParticlePreset> particelPreset = new JComboBox<>();
	CalculationAssignment calcModel = CalculationAssignment.getInstance();
	protected int constraintCounter=0;
	
	public static void main (String[] args) {
		PresetsGui gui = new PresetsGui();
		JFrame test = new JFrame();
		test.setSize(300, 400);
		test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		test.add(gui);
		test.setVisible(true);
	}

	public PresetsGui() {
		//Dimension size = new Dimension(200, 30);
		//setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setLayout(new GridBagLayout());
		//sizeParamList=sizeDistribution();
		//sigmaDistributionList = particleDistribution();
		//particelPreset = particelPreset();
		//sizeParamList.setSize(new Dimension(247, 10));
		//sizeParamList.setPreferredSize(size);
		sizeParamList.setLightWeightPopupEnabled(false); //to let dropdown list show correctly in front of jtextfields
		add(sizeParamList, getConstraint());
		//add(Box.createHorizontalStrut(3));
		//sigmaDistributionList.setSize(new Dimension(247, 10));
		//sigmaDistributionList.setPreferredSize(size);
		add(sigmaDistributionList, getConstraint());
		sigmaDistributionList.setLightWeightPopupEnabled(false);
		//add(Box.createHorizontalStrut(3));
		//particelPreset.setSize(new Dimension(247, 10));
		//particelPreset.setPreferredSize(size);
		add(particelPreset, getConstraint());
		particelPreset.setLightWeightPopupEnabled(false);
		setJlistModels();
		createListener();
	}
	
	private GridBagConstraints getConstraint() {
		GridBagConstraints c = new GridBagConstraints();
		c.fill=GridBagConstraints.HORIZONTAL;
		c.insets=new Insets(1, 3, 1, 3);
		c.gridy=0;
		c.gridx=constraintCounter;
		c.weightx=1;
		c.weighty=1;
		//c.ipadx=50;
		constraintCounter++;
		return c;
	}
	
	private void createListener() {
		sizeParamList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//calcModel.getDiameters().setValuesTo((IDiameterParametersInterface) sizeParamList.getSelectedItem());
				calcModel.setDiameters(((IDiameterParametersInterface) sizeParamList.getSelectedItem()));
				
			}
		});
		
		sigmaDistributionList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				calcModel.setSigmas((ISigmaPreset) sigmaDistributionList.getSelectedItem());
				
			}
		});
		
		particelPreset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					calcModel.setParticles((IMieParticlePreset) particelPreset.getSelectedItem());
				} catch (WavelengthMismatchException e) {
					JOptionPane.showMessageDialog((Component) arg0.getSource(), "Wavelength Mismatch: "+e.getMessage());
				}
				
			}
		});
		
	}

	private void setJlistModels() {
		//sizeParamList.setSize(sizeParamList.getSize());
		sizeParamList.setModel(sizeDistribution());
		//calcModel.setDiameters(sizeDistribution().getElementAt(0));
		
		sigmaDistributionList.setModel(particleDistribution());
		//calcModel.setSigmas(sigmaDistributionList.getItemAt(0));
		
		particelPreset.setModel(particelPreset());
		//calcModel.setParticles(particelPreset().getElementAt(0));
	}
	
	public void selectionToCalcAssignment() {
		calcModel.setDiameters((IDiameterParametersInterface) sizeDistribution().getSelectedItem());
		calcModel.setSigmas((ISigmaPreset) sigmaDistributionList.getSelectedItem());
		try {
			calcModel.setParticles((IMieParticlePreset) particelPreset().getSelectedItem());
		} catch (WavelengthMismatchException e) {
			JOptionPane.showMessageDialog(this, "Wavelength Mismatch: "+e.getMessage());
		}
	}

	protected DefaultComboBoxModel<IDiameterParametersInterface> sizeDistribution(){
		DefaultComboBoxModel<IDiameterParametersInterface> sizeParamsModel = new DefaultComboBoxModel<>();
		sizeParamsModel.addElement(new StandardDiameterParameters());
		sizeParamsModel.addElement(new StandardDiameterParameters(0.001, 0.01, 1000, true));
		sizeParamsModel.addElement(new StandardDiameterParameters(0.001, 0.1, 1000, true));
		sizeParamsModel.addElement(new StandardDiameterParameters(0.001, 1, 1000, true));
		sizeParamsModel.addElement(new StandardDiameterParameters(0.01, 0.1, 1000, true));
		sizeParamsModel.addElement(new StandardDiameterParameters(0.01, 1, 1000, true));
		sizeParamsModel.addElement(new StandardDiameterParameters(0.01, 3, 1000, true));
		sizeParamsModel.addElement(new StandardDiameterParameters(0.1, 1, 1000, true));
		sizeParamsModel.addElement(new StandardDiameterParameters(0.1, 3, 1000, true));
		sizeParamsModel.addElement(new StandardDiameterParameters(0.1, 5, 1000, true));
		sizeParamsModel.addElement(new StandardDiameterParameters(0.3, 3, 1000, true));
		sizeParamsModel.addElement(new StandardDiameterParameters(0.5, 5, 1000, true));
		sizeParamsModel.addElement(new StandardDiameterParameters(0.001, 10, 1000, true));
		return sizeParamsModel;
	}
	
	protected DefaultComboBoxModel<ISigmaPreset> particleDistribution(){
		DefaultComboBoxModel<ISigmaPreset> particleDistModel = new DefaultComboBoxModel<>();
		particleDistModel.addElement(new FixedSigmaParameter(0.01, 0.05, 0.1, 0.13, 0.17, 0.2, 0.23, 0.27, 0.3));
		particleDistModel.addElement(new FixedSigmaParameter(0.01, 0.05, 0.07, 0.1, 0.15, 0.2, 0.3, 0.4));
		particleDistModel.addElement(new FixedSigmaParameter(0.01, 0.05, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7));
		particleDistModel.addElement(new FixedSigmaParameter(0.05, 0.1, 0.15, 0.2, 0.3, 0.5, 0.7, 0.9));
		particleDistModel.addElement(new FixedSigmaParameter(0.1, 0.15, 0.2, 0.25, 0.3, 0.4, 0.5));
		particleDistModel.addElement(new FixedSigmaParameter(0.1, 0.15, 0.2, 0.3, 0.4, 0.5, 0.7, 1));
		particleDistModel.addElement(new FixedSigmaParameter(0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.8, 1));
		particleDistModel.addElement(new FixedSigmaParameter(0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8));
		particleDistModel.addElement(new FixedSigmaParameter(0.2, 0.3, 0.4, 0.5, 0.7, 0.9, 1.1, 1.3));
		particleDistModel.addElement(new FixedSigmaParameter(0.01, 0.1, 0.2, 0.3, 0.5, 0.7, 0.9, 1.25, 1.5, 2));

		
		
		//particleDistModel.addElement(new DiameterSizeParameters(start, end, steps, logarithmic));
		//JComboBox<DistributionPreset> sizeParams = new JComboBox<>(particleDistModel);
		return particleDistModel;
	}
	
	protected DefaultComboBoxModel<IMieParticlePreset> particelPreset(){
		DefaultComboBoxModel<IMieParticlePreset> particleDistModel = new DefaultComboBoxModel<>();
		particleDistModel.addElement(new LatexInWaterPreset());
		particleDistModel.addElement(new DieselSoot());
		particleDistModel.addElement(new WaterInAir());
		particleDistModel.addElement(new OilInWater());
		particleDistModel.addElement(new SilverInAir());
		particleDistModel.addElement(new SiOinAir());
		particleDistModel.addElement(new SiO2inAir());
		particleDistModel.addElement(new SiInAir());
		//JComboBox<MieParticlePreset> sizeParams = new JComboBox<>(particleDistModel);
		return particleDistModel;
	}
	

}
