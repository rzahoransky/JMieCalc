package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import calculation.CalculationAssignment;

public class FileGui extends JPanel {

	protected File file;
	protected String description;
	protected TextField textField;
	protected JButton saveBtn;
	protected GridBagConstraints c;
	protected JFileChooser chooser = new JFileChooser();
	protected FileNameExtensionFilter filter = new FileNameExtensionFilter("Zipped MIE-Files (*.miezip)","miezip");
	protected DialogType type = DialogType.SAVE;

	public static void main(String[] args) {
		FileGui fg = new FileGui("Test");
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(100, 20));
		frame.add(fg);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}

	public FileGui(String description) {
		this.description = description;
		chooser.setFileFilter(filter);
		c = new GridBagConstraints();
		// setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		addTextField();
		addFileButton();
	}
	
	public FileGui(String description, FileNameExtensionFilter fileNameFilter) {
		this(description);
		filter = fileNameFilter;
		chooser.resetChoosableFileFilters();
		chooser.setFileFilter(fileNameFilter);
	}
	
	public void setDialogType(DialogType type) {
		this.type = type;
	}
	
	
	public void setFile(File file) {
		textField.setText(file.getAbsolutePath());
		CalculationAssignment.getInstance().setOutputFile(file);
	}

	private void addTextField() {
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		TextWithDescription descriptionTextField = new TextWithDescription(description);
		textField = descriptionTextField.getTextField(description);

		textField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				CalculationAssignment calcAssignment = CalculationAssignment.getInstance();

				if (checkFile())
					calcAssignment.setOutputFile(getChoosenFile());
				else
					calcAssignment.setOutputFile(null);
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				checkFile();

			}
		});
		
		textField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				checkFile();
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		add(descriptionTextField, c);
		// add (new JButton("resize Test"),c);
		// textField = new JTextField();
		// textField.setPreferredSize(new Dimension(getPreferredSize().width,30));
		// add(textField);
	}

	private boolean checkFile() {
		try {
			File file = getChoosenFile();
			file.getCanonicalFile();
			Paths.get(file.getAbsolutePath());
			if (!file.isDirectory()) {
				textField.setBackground(Color.green);
				return true;
			} else {
				textField.setBackground(Color.WHITE);
				return false;
			}
		} catch (Exception e) {
			textField.setBackground(Color.WHITE);
			return false;
		}
	}

	public File getChoosenFile() {
		return new File(textField.getText());
	}
	
	public boolean hasChoosenFile() {
		return (textField.getText()!=null && !textField.getText().isEmpty());
	}

	private void addFileButton() {
		c.gridx = 1;
		c.weightx = 0;
		c.fill = c.NONE;
		c.insets = new Insets(0, 5, 0, 0);
		saveBtn = new JButton("choose");
		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switch (type) {
				case OPEN:
					chooser.showOpenDialog(textField);
					break;
				case SAVE:
					chooser.showSaveDialog(textField);
					break;
				default:
					break;
				}
				//chooser.showSaveDialog(textField);
				
				file = chooser.getSelectedFile();
				
				if(file!=null && !file.getAbsolutePath().endsWith(filter.getExtensions()[0])){
				    file = new File(file + "."+filter.getExtensions()[0]);
				}
				
				if (file != null) {
					textField.setText(file.getAbsolutePath());
					checkFile();
					CalculationAssignment.getInstance().setOutputFile(file);
				}
			}
		});
		add(saveBtn, c);
	}
	
	public TextField getTextField() {
		return textField;
	}
	
	public enum DialogType {
		OPEN,SAVE;
	}

}
