package storage.dqMeas.read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import presets.EmptyMieParticlePreset;
import presets.Wavelengths;

public class RefIndexReader {

	protected final String infoFileName = "MieFileInformation";
	protected final String patternForDouble = "\\ [0-9]{1,13}(.[0-9]+)?";
	File mieFolder;
	double start;
	double end;
	private LinkedList<Double> sigmas = new LinkedList<>();
	private EmptyMieParticlePreset refIndices = new EmptyMieParticlePreset();

	public RefIndexReader(File mieFolder) throws IOException {
		this.mieFolder = mieFolder;
		getInfoFile();
	}

	private void getInfoFile() throws IOException {

		if (infoFileExists()) {
			// read from infoFile
			readInfoFile();

		}

	}

	private boolean infoFileExists() {
		File infoFile = new File(mieFolder, infoFileName);
		return infoFile.exists();
	}

	private void readInfoFile() throws IOException {

		BufferedReader br = null;

		try {

			br = new BufferedReader(new FileReader(new File(mieFolder, infoFileName)));

			String line;
			Matcher m;
			int lineNumber = 0;
			while ((line = br.readLine()) != null) { // read all lines
				m = Pattern.compile(patternForDouble).matcher(line);

				while (m.find()) { // get all regex matches for
					if (lineNumber == 0) { // Start and end value of particle sizes
						start = LastResortDoubleParser.parse(m.group());
						m.find();
						end = LastResortDoubleParser.parse(m.group());
					} 
					
					else if (lineNumber == 1) { // Sigmas
						sigmas.add(LastResortDoubleParser.parse(m.group()));
					} 
					
					else if (lineNumber > 1) { // Wavelengths ref indices
						// ignore first number
						double wavelength = LastResortDoubleParser.parse(m.group());
						m.find();
						//m.group();
						//m.find();
						
						Wavelengths wl = getWavelengthForLine(lineNumber);
						wl.setValue(wavelength);

						double medium = LastResortDoubleParser.parse(m.group());
						m.find();
						double sphereReal = LastResortDoubleParser.parse(m.group());
						m.find();
						double sphereImag = LastResortDoubleParser.parse(m.group());
						//refIndices.addWavelength(wavelength);
						refIndices.setRefractiveIndexMedium(wl.getValue(), medium);
						refIndices.setRefractiveIndexSphereReal(wl.getValue(), sphereReal);
						refIndices.setRefractiveIndexSphereImaginaray(wl.getValue(), sphereImag);
					} 
					
//					else { //end of file should be reached by now
//						break;
//					}
				}
				lineNumber++;
			}

		} catch (IOException e) {
			throw e;
		} finally {
			br.close();
		}
	}
	
	private Wavelengths getWavelengthForLine(int lineNumber) {
		switch (lineNumber) {
		case 2:
			return Wavelengths.WL1;
		case 3:
			return Wavelengths.WL2;
		case 4:
			return Wavelengths.WL3;
		default:
			return null;
		}
	}

	public double getStart() {
		return start;
	}

	public double getEnd() {
		return end;
	}

	public LinkedList<Double> getSigmas() {
		return sigmas;
	}

	public EmptyMieParticlePreset getRefIndices() {
		return refIndices;
	}
	
	

}
