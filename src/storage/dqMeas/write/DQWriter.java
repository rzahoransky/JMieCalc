package storage.dqMeas.write;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;

import calculation.MieList;
import calculation.MieWrapper;

public class DQWriter {
	
	private static NumberFormat formatter = NumberFormat.getInstance(Locale.GERMANY);
	//DecimalFormat formatter = new DecimalFormat("#,000000");

	private MieList wl1;
	private MieList wl2;

	public DQWriter(MieList wl1, MieList wl2) {
		formatter.setMaximumFractionDigits(12);
		this.wl1=wl1;
		this.wl2=wl2;
	}
	
	public void writeToFile(File file) throws IOException {
		String dq=getDqTable();
		FileWriter fw = new FileWriter(file);
		fw.write(dq);
		fw.close();
	}
	
	/** generated table with radius and dq **/
	public String getDqTable() {
		StringBuffer sb = new StringBuffer();
		ArrayList<Double> sortedSigmas = wl1.get(0).getSortedSigmas();
		for (int i = 0;i<wl1.size();i++) {
			
			MieWrapper mie1 = wl1.get(i);
			MieWrapper mie2 = wl2.get(i);
			sb.append(formatter.format(mie1.getDiameter())+"\t");
			//System.out.println("adding size"+mie1.getDiameter());
			for(Double sigma: sortedSigmas) {
				double integratedQext1 = mie1.getIntegratedQext().get(sigma);
				double integratedQext2 = mie2.getIntegratedQext().get(sigma);
				double dq = integratedQext1 / integratedQext2;
				//sb.append(dq);
				sb.append(formatter.format(dq));
				sb.append("\t");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

}
