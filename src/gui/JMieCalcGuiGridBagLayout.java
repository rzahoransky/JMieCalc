package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import actions.CalculationAction;
import actions.ChangeWavelengthAction;
import actions.DQChartAction;
import calculation.CalculationAssignment;
import presets.Wavelengths;

public class JMieCalcGuiGridBagLayout extends JFrame{
	
	protected GridBagConstraints c;
	protected FileGui fileGui; 
	protected PresetsGui presetGui;
	protected HashMap<Wavelengths, MieParameterGui> mieParams = new HashMap<>();
	protected SizeParameterGui sizeGui;
	protected SigmaParameterGui sigmaGui;
	
	public static void main (String[] args) {
		JMieCalcGuiGridBagLayout gui = new JMieCalcGuiGridBagLayout();
		gui.setVisible(true);
		gui.repaint();
	}

	public JMieCalcGuiGridBagLayout() {

		setLookAndFeel();

		fileGui = new FileGui("Mie-File: ");
		sizeGui = new SizeParameterGui();
		sigmaGui = new SigmaParameterGui();
		
		addBorders();

		setTitle("jMieCalc by Richard M. Zahoransky");
		setSize(new Dimension(750, 500));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		//c.fill=GridBagConstraints.HORIZONTAL;
		
		addMieFile();
		addPresets();
		addMieParameters();
		addCalcBtn();
		addShowBtns();
		addShowDQAfterCalcBtn();
		addChangeWavelengthBtn();
		addSizeParameters();
		addSigmaParameters();
		addAboutBtn();
		addProgressBar();
		//addExpertCheckBox();
		
		//prefill calculation assignment
		CalculationAssignment assignment = CalculationAssignment.getInstance();
		presetGui.selectionToCalcAssignment();
	}
	
	private void addChangeWavelengthBtn() {
		c.gridx=4;
		c.gridy=6;
		JButton changeWL = new JButton(new ChangeWavelengthAction());
		add(changeWL,c);
		
	}

	private void addBorders() {
		sigmaGui.setBorder(BorderFactory.createEtchedBorder());
		sizeGui.setBorder(BorderFactory.createEtchedBorder());
	}

	private void addAboutBtn() {
		JButton abtbtn = new JButton("about...");
		JFrame about = new JFrame("About");
		about.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JLabel text = new JLabel("<html>JMieCalc <b>by Richard Markus Zahoransky <br> r.zahoransky@gmx.de </b> for the calculation of extinction coefficients for Wizard DQ "
				+ "<br><br> this software contains the apache commons libraries and the JFreeChart library"
				+ "<br> Thanks to Bernhard Michel for his link to mie scattering in Java (http://www.lightscattering.de/MieCalc/)</html>");
		about.setSize(300, 200);
		about.add(text);
		
		abtbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				about.setVisible(true);
			}
		});
		c.gridy=7;
		c.gridx=4;
		getContentPane().add(abtbtn, c);
		
	}

	public JMieCalcGuiGridBagLayout(String path) {
		this();
		fileGui.setFile(new File(path));
	}
	
	public void addProgressBar() {
		c.gridx=0;
		c.gridy=7;
		c.fill=GridBagConstraints.HORIZONTAL;
		c.weightx=1;
		c.gridwidth=4;
		getContentPane().add(new ProgressGui(),c);
	}

	public void addSigmaParameters() {
		c.gridx=0;
		c.gridy=5;
		c.gridwidth=5;
		getContentPane().add(sigmaGui,c);
		
	}

	public void addSizeParameters() {
		c.gridx=0;
		c.gridy=3;
		c.gridwidth=4;
		getContentPane().add(sizeGui,c);
		
		
	}

	public void addShowBtns() {
		JButton showDQ = new JButton(new DQChartAction());
		c.gridx=4;
		c.gridy=2;
		c.gridwidth=1;
		c.fill=GridBagConstraints.HORIZONTAL;
		getContentPane().add(showDQ, c);
		
		//JButton showQext = new JButton("Show Qext");
		//c.gridy=3;
		//getContentPane().add(showQext, c);
	}
	
	public void addShowDQAfterCalcBtn() {
		JCheckBox showAfterCalc = new JCheckBox("Show DQ after calculation");
		showAfterCalc.setSelected(true);
		c.gridy=3;
		c.gridx=4;
		//c.anchor=GridBagConstraints.PAGE_START;
		getContentPane().add(new ShowAfterCalculationGUI(),c);
	}

	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			//ignore
		}
	}
	
	private void addExpertCheckBox() {
		c.gridwidth=1;
		c.gridx=0;
		c.gridy=c.gridy+1;
		c.weightx=1;
		c.gridwidth=GridBagConstraints.REMAINDER;
		getContentPane().add(new JCheckBox("Expert View"),c);
		
	}

	public void addMieFile() {
		//gui 
		c.gridx=0;
		c.weightx=1;
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridy=0;
		c.gridwidth=4;
		c.insets = new Insets(10, 10, 10, 10);
		getContentPane().add(fileGui,c);
	}
	
	public void addPresets() {
		c.gridx=0;
		c.gridy=1;
		c.gridwidth=c.REMAINDER;
		presetGui = new PresetsGui();
		//presetGui.setBorder(BorderFactory.createEtchedBorder());
		getContentPane().add(presetGui,c);
	}
	
	public void addCalcBtn() {
		c.gridx=4;
		c.gridy=0;
		c.gridwidth=1;
		c.fill=GridBagConstraints.HORIZONTAL;
		JButton calcBtn = new JButton(new CalculationAction());
		getContentPane().add(calcBtn, c);
	}
	
	public void addMieParameters() {
		int x = 0;
		for(Wavelengths wl:Wavelengths.values()) {
			MieParameterGui gui = new MieParameterGui(wl);
			c.gridwidth=1;
			c.gridx=x;
			c.gridy=2;
			c.weightx=1;
			c.fill=GridBagConstraints.HORIZONTAL;
			getContentPane().add(gui,c);
			mieParams.put(wl, gui);
			x++;
		}
	}
	
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		//fill calculation assignment with values from dropdownlist
		presetGui.selectionToCalcAssignment();
	}

}
