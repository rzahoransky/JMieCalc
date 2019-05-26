package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TextWithDescription extends JPanel{
	
	private static final long serialVersionUID = 7819730348698413399L;
	protected HashMap<String, TextField> textFields = new HashMap<>();
	protected GridBagConstraints c;
	
	public static void main(String[] args) {
		ArrayList<String> test = new ArrayList<>();
		test.add("one");
		test.add("2");
		test.add("three (3)");
		JFrame frame = new JFrame("test");
		frame.setSize(new Dimension(400, 100));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TextWithDescription(test));
		frame.setVisible(true);
		frame.pack();
	}

	public TextWithDescription(ArrayList<String> texts) {
		c = new GridBagConstraints();
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createLineBorder(Color.black));
		int line = 0;
		for (String s: texts) {
			getLine(s,line);
			line++;
		}
	}
	
	public TextWithDescription(String description) {
		c = new GridBagConstraints();
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(new GridBagLayout());
		//setBorder(BorderFactory.createLineBorder(Color.black));
		getLine(description,0);
	}
	
	private void getLine(String description, int line) {
		JLabel label = new JLabel(description);
		TextField text = new TextField();
		text.setMinimumSize(new Dimension(15,35));
		//text.setPreferredSize(new Dimension(75,20));
		add(label,getLabelConstraint(line));
		add(text, getTextConstraint(line));
		textFields.put(description, text);
	}
	
	private GridBagConstraints getLabelConstraint(int line) {
		c.gridx=0;
		c.gridy=line;
		c.anchor = (c.gridx == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
		c.weightx=0;
		return c;
	}
	
	private GridBagConstraints getTextConstraint(int line) {
		c.gridx=1;
		c.gridy=line;
		c.anchor = (c.gridx == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
		c.fill=GridBagConstraints.BOTH;
		c.weightx=1;
		return c;
	}
	
	
	
	public TextField getTextField(String description) {
		return textFields.get(description);
	}
	
	public String getValue(String description) {
		return textFields.get(description).getText();
	}
	
	public double getValueAsDouble(String description) {
		return Double.parseDouble(getValue(description));
	}
	
	public void setEditable(boolean editable) {
		for (TextField field: textFields.values()) {
			field.setEditable(editable);
		}
	}

}
