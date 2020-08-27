package storage.dqMeas.write;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import calculation.MieList;
import calculation.MieWrapper;

public class QextWriter {

	private MieList list;
	private static NumberFormat formatter = NumberFormat.getInstance(Locale.GERMANY);
	
	public QextWriter(MieList list) {
		formatter.setMaximumFractionDigits(12);
		this.list=list;
	}
	
	public void writeToFile(File file, boolean integrated) throws IOException {
		FileWriter fw = new FileWriter(file);
		fw.write(getContent(integrated));
		fw.close();
	}

	public String getContent(boolean integrated) {
		StringBuffer sb = new StringBuffer();
		if (!integrated) {
			formatter=NumberFormat.getInstance(Locale.US);
			formatter.setMaximumFractionDigits(12);
			sb.append(QextHeader.getMieHeader(list));
			for (MieWrapper mie : list) {
				sb.append(formatter.format(mie.getRadius()*2)); //output diameter
				sb.append(" \t ");
				sb.append(formatter.format(mie.qext()));
				sb.append(System.lineSeparator());
			}
		} else {
			formatter=NumberFormat.getInstance(Locale.GERMANY);
			formatter.setMaximumFractionDigits(12);
			for (MieWrapper mie : list) {
				sb.append(formatter.format(mie.getRadius()*2)); //output diameter
				sb.append(" \t ");
				for(Double sigma:mie.getSortedSigmas()) {
					sb.append(formatter.format(mie.getIntegratedQext().get(sigma)));
					sb.append("\t");
				}
				sb.deleteCharAt(sb.length()-1);
				sb.append(System.lineSeparator());
			}
		}
		return sb.toString();
	}

}
