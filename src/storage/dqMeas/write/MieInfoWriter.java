package storage.dqMeas.write;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import calculation.MieList;

/**Writes (in german local) the MIE Info files**/
public class MieInfoWriter {

	private MieList wl1;
	private MieList wl2;
	private MieList wl3;
	
	private static NumberFormat formatter = NumberFormat.getInstance(Locale.GERMANY);
	//private static DecimalFormat formatter = new DecimalFormat("#,000000");

	public MieInfoWriter(MieList wl1, MieList wl2, MieList wl3) {
		this.wl1=wl1;
		this.wl2=wl2;
		this.wl3=wl3;
	}
	
	public void writeInfoFile(File file) throws IOException {
		FileWriter fw = new FileWriter(file);
		fw.write(getInfoString());
		fw.close();
	}
	
	public String getInfoString() {
		formatter.setMaximumFractionDigits(8);
		StringBuilder sb = new StringBuilder();
		ArrayList<String> wls = getWavelengths();
		ArrayList<String> sigmas = getSigmas();
		ArrayList<String> refMedium = getRefIndexMedium();
		
		for (int i = 0; i<sigmas.size();i++) { 
			sb.append(getEntryOrZero(wls, i));
			sb.append("\t");
			sb.append(getEntryOrZero(refMedium, i));
			sb.append("\t");
			sb.append(getEntryOrZero(getRefIndexSphereReal(), i));
			sb.append("\t");
			sb.append(getEntryOrZero(getRefIndexSphereImag(), i));
			sb.append("\t");
			sb.append(getEntryOrZero(sigmas, i));
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
	
	protected String getEntryOrZero(ArrayList<String> list, int index) {
		try {
			return list.get(index);
		} catch (Exception e) {
			return "0";
		}
	}
	
	protected ArrayList<String> getSigmas() {
		ArrayList<String> sigmas = new ArrayList<>();
		for(Double sigma: wl1.get(0).getIntegratedQext().keySet())
			sigmas.add(formatter.format(sigma));
		return sigmas;
	}
	
	protected ArrayList<String> getWavelengths() {
		ArrayList<String> wavelengths = new ArrayList<>();
		wavelengths.add(formatter.format(wl1.get(0).getWavelength()));
		wavelengths.add(formatter.format(wl2.get(0).getWavelength()));
		wavelengths.add(formatter.format(wl3.get(0).getWavelength()));
		return wavelengths;
	}
	
	protected ArrayList<String> getRefIndexMedium() {
		ArrayList<String> medium = new ArrayList<>();
		medium.add(formatter.format(wl1.get(0).getRefractiveIndexMedium()));
		return medium;
	}
	
	protected ArrayList<String> getRefIndexSphereReal() {
		ArrayList<String> sphereReal = new ArrayList<>();
		sphereReal.add(formatter.format(wl1.get(0).getRefractiveIndexSphereReal()));
		return sphereReal;
	}
	
	protected ArrayList<String> getRefIndexSphereImag() {
		ArrayList<String> sphereImag = new ArrayList<>();
		sphereImag.add(formatter.format(wl1.get(0).getRefractiveIndexSpereImaginary()));
		return sphereImag;
	}

}
