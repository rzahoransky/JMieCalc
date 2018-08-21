package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import presets.Wavelengths;

public class JMieCalcGui extends JFrame{
	
	PresetsGui presetGui;
	
	public static void main(String[] args) {
		JMieCalcGui gui = new JMieCalcGui();
		gui.setVisible(true);
	}

	public JMieCalcGui(){
		setTitle("JMie Calc");
		setSize(new Dimension(750, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FileGui fileGui = new FileGui("bla");
		presetGui = new PresetsGui();
		//setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		Box box = new Box(BoxLayout.Y_AXIS);
		getContentPane().add(fileGui, BorderLayout.NORTH);
		
		box.add(presetGui);
		box.add(addWavelengthParameteres());
		getContentPane().add(box,BorderLayout.CENTER);
	}
	
	private JPanel addWavelengthParameteres() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		for (Wavelengths wl: Wavelengths.values()) {
			panel.add(new MieParameterGui(wl));
		}
		return panel;
	}
	
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		//presetGui.setJlistModels();
	}

}
