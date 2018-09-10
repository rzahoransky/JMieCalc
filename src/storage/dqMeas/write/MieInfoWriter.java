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
		fw.write(getInfoString(wl1, wl2, wl3));
		fw.close();
	}
	
	public static String getInfoString(MieList wl1, MieList wl2, MieList wl3) {
		formatter.setMaximumFractionDigits(8);
		StringBuilder sb = new StringBuilder();
		ArrayList<String> wls = getWavelengths(wl1, wl2, wl3);
		ArrayList<String> sigmas = getSigmas(wl1);
		ArrayList<String> refMedium = getRefIndexMedium(wl1);
		
		for (int i = 0; i<sigmas.size();i++) { 
			sb.append(getEntryOrZero(wls, i));
			sb.append("\t");
			sb.append(getEntryOrZero(refMedium, i));
			sb.append("\t");
			sb.append(getEntryOrZero(getRefIndexSphereReal(wl1), i));
			sb.append("\t");
			sb.append(getEntryOrZero(getRefIndexSphereImag(wl1), i));
			sb.append("\t");
			sb.append(getEntryOrZero(sigmas, i));
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
	
	public String getInforString() {
		return getInfoString(wl1, wl2, wl3);
	}
	
	protected static String getEntryOrZero(ArrayList<String> list, int index) {
		try {
			return list.get(index);
		} catch (Exception e) {
			return "0";
		}
	}
	
	public static ArrayList<String> getSigmas(MieList wl1) {
		ArrayList<String> sigmas = new ArrayList<>();
		for(Double sigma: wl1.get(0).getIntegratedQext().keySet())
			sigmas.add(formatter.format(sigma));
		return sigmas;
	}
	
	public static ArrayList<String> getWavelengths(MieList wl1, MieList wl2, MieList wl3) {
		ArrayList<String> wavelengths = new ArrayList<>();
		wavelengths.add(formatter.format(wl1.get(0).getWavelength()));
		wavelengths.add(formatter.format(wl2.get(0).getWavelength()));
		wavelengths.add(formatter.format(wl3.get(0).getWavelength()));
		return wavelengths;
	}
	
	public static ArrayList<String> getRefIndexMedium(MieList wl1) {
		ArrayList<String> medium = new ArrayList<>();
		medium.add(formatter.format(wl1.get(0).getRefractiveIndexMedium()));
		return medium;
	}
	
	protected static ArrayList<String> getRefIndexSphereReal(MieList wl1) {
		ArrayList<String> sphereReal = new ArrayList<>();
		sphereReal.add(formatter.format(wl1.get(0).getRefractiveIndexSphereReal()));
		return sphereReal;
	}
	
	protected static ArrayList<String> getRefIndexSphereImag(MieList wl1) {
		ArrayList<String> sphereImag = new ArrayList<>();
		sphereImag.add(formatter.format(wl1.get(0).getRefractiveIndexSpereImaginary()));
		return sphereImag;
	}

}
