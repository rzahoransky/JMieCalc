package storage.dqMeas.write;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import calculation.MieList;

public class StDerivationWriter {
	
	private static NumberFormat formatter = NumberFormat.getInstance(Locale.GERMANY);
	private MieList list;

	public StDerivationWriter(MieList list) {
		formatter.setMaximumFractionDigits(8);
		this.list=list;
	}
	
	public void writeToFile(File file) throws IOException {
		FileWriter fw = new FileWriter(file);
		fw.write(getContent());
		fw.close();
	}
	
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		for(double sigma: list.get(0).getSortedSigmas()) {
			sb.append(formatter.format(sigma));
			sb.append("\t");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

}
