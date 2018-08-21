package storage.dqMeas.write;

import java.util.Date;

import calculation.MieList;
import calculation.MieWrapper;

public class QextHeader {

	private QextHeader() {
		// TODO Auto-generated constructor stub
	}
	
	static String getMieHeader(MieList list) {
		MieWrapper mie = list.get(0);
		String newline=System.lineSeparator();
		StringBuffer sb = new StringBuffer();
		sb.append("JMieCalc v1.0.0 Richard Zahoransky");
		sb.append(newline + newline);
		sb.append("Date & Time: "+new Date().toString());
		sb.append(newline + newline);
		sb.append("Calculation Method: Mie (plane wave)");
		sb.append(newline + newline);
		sb.append("Sphere: \t"+mie.getRefractiveIndexSphereReal()+"+");
		sb.append(mie.getRefractiveIndexSpereImaginary()+"i");
		sb.append("Medium: \t"+mie.getRefractiveIndexMedium());
		sb.append(newline);
		sb.append(getSphereString(list));
		//sb.append("Refractive index (medium):\t"+mie.getRefractiveIndexMedium());
		sb.append(newline + newline);
		sb.append("Wavelength: \t"+mie.getWavelength()+"\t um");
		sb.append(newline + newline);
		sb.append("Graph type: \tQext v. radius");
		sb.append(newline + newline);
		sb.append("Diameter (µm)\tQext");
		sb.append(newline);
		return sb.toString();
	}
	
	private static String getSphereString(MieList list) {
		StringBuilder sb = new StringBuilder();
		sb.append("Refractive index (sphere) \tReal part: \t");
		sb.append(Double.toString(list.get(0).getRefractiveIndexSphereReal()));
		sb.append("\tImaginary part: \t");
		sb.append(Double.toString(list.get(0).getRefractiveIndexSpereImaginary()));
		sb.append(System.lineSeparator());
		sb.append("Refractive index (medium)\tReal part: ");
		sb.append(Double.toString(list.get(0).getRefractiveIndexMedium())+"\t");
		return sb.toString();
	}

}
