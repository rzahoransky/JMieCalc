package storage.dqMeas.read;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import calculation.MieList;
import calculation.MieWrapper;
import errors.WavelengthMismatchException;
import presets.EmptyMieParticlePreset;
import presets.Wavelengths;

public class ReadMieList {
	
//	public static void main (String args[]) throws IOException, WavelengthMismatchException {
//		readMieList(new File("D:/mietemp/wl1.txt"));
//	}
	
	public static MieList readMieList(File diameterVsQextFile) throws IOException, WavelengthMismatchException {
		
		EmptyMieParticlePreset refIndices = new RefIndexReader(diameterVsQextFile.getParentFile()).getRefIndices();

		double wavelength = 0;
		Wavelengths wl = null;

		MieList list = new MieList();

		Scanner scan = new Scanner(diameterVsQextFile);
		scan.useLocale(Locale.US);
		while (scan.hasNextLine()) {
			if (scan.hasNext("Wavelength:")) { //get wavelength from header
				scan.next();
				wavelength = scan.nextDouble();
				wl=Wavelengths.getWlFor(wavelength);
			}
			if (scan.hasNextDouble()) {
				double diameter = scan.nextDouble(); //read diameter from Mie File: Mie-File contains diameter, not radius in first column
				double qext = scan.nextDouble();
				MieWrapper mieElement = new MieWrapper();
				mieElement.setRadiusWavelength(diameter / 2, wavelength); //create MieObject with radius
				mieElement.setHostRefractiveIndex(refIndices.getRefractiveIndexMedium(wl.getValue()));
				mieElement.setRefractiveIndex(refIndices.getRefractiveIndexSphereReal(wl.getValue()), refIndices.getRefractiveIndexSphereImaginaray(wl.getValue()));
				list.addElement(mieElement);
				//qext is recalculated by MieWrapper if neccessary...
			}
			scan.nextLine();
		}

		// List<String> content = Files.readAllLines(diameterVsQextFile.toPath());

		// for(String line:content) {

		// }
		
		scan.close();

		return list;
	}
	
	private static void getRefIndexMedium(File informationFile, double wavelength) {

	}

}
