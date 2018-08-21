package storage.dqMeas.write;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;

import calculation.CalculationAssignment;
import calculation.MieList;
import calculation.MieWrapper;
import presets.Wavelengths;

public class OutputWriter {
	
	private ArrayList<MieList> wl = new ArrayList<>();
	private MieList wl1;
	private MieList wl2;
	private MieList wl3;
	private ZipOutputStream zos;

	public OutputWriter(MieList wl1, MieList wl2, MieList wl3) {
		wl.add(wl1);
		wl.add(wl2);
		wl.add(wl3);
		
		this.wl1=wl1;
		this.wl2=wl2;
		this.wl3=wl3;
	}
	
	public OutputWriter(CalculationAssignment calcAssignment) {
		this(calcAssignment.getMieLists().get(Wavelengths.WL1),calcAssignment.getMieLists().get(Wavelengths.WL2),calcAssignment.getMieLists().get(Wavelengths.WL3));
	}
	
	public void write(File path) throws IOException {
		path.mkdirs();
		//File[] qexts = {new File("wl1.txt"), new File("wl2.txt"), new File("wl3.txt")};
		for (int i = 0;i<wl.size();i++) {
			new QextWriter(wl.get(i)).writeToFile(new File(path,"wl"+i+".txt"),false);
		}
		
		new MieInfoWriter(wl1, wl2, wl3).writeInfoFile(new File(path,"info_dev.nfo"));
		
		new DQWriter(wl1, wl2).writeToFile(new File(path,"dq1_st_derivation.mie"));
		new DQWriter(wl2, wl3).writeToFile(new File(path,"dq2_st_derivation.mie"));
		new DQWriter(wl1, wl3).writeToFile(new File(path,"dq3_st_derivation.mie"));
		new QextWriter(wl1).writeToFile(new File(path,"Integral_st_derivations.mie"), true);
		new StDerivationWriter(wl1).writeToFile(new File(path,"st_derivations.mie"));
		new StDerivationWriter(wl2).writeToFile(new File(path,"st_derivationsWL2.mie"));
		new StDerivationWriter(wl3).writeToFile(new File(path,"st_derivationsWL3.mie"));
	}
	
	public void writeAsZip(File zipFile) throws IOException {
		zipFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(zipFile);
		zos = new ZipOutputStream(fos);
		
		for (int i = 0;i<wl.size();i++) {
			String fileName = "wl"+(i+1)+".txt";
			String wlQext = new QextWriter(wl.get(i)).getContent(false);
			addToZipFile(fileName, wlQext);
		}
		
		String mieInfo = new MieInfoWriter(wl1, wl2, wl3).getInfoString();
		addToZipFile("info_dev.nfo", mieInfo);
		
		String dq1 = new DQWriter(wl1, wl2).getDqTable();
		String dq2 = new DQWriter(wl2, wl3).getDqTable();
		String dq3 = new DQWriter(wl1, wl3).getDqTable();
		addToZipFile("dq1_st_derivation.mie", dq1);
		addToZipFile("dq2_st_derivation.mie", dq2);
		addToZipFile("dq3_st_derivation.mie", dq3);
		
		String integratedQextWl1 = new QextWriter(wl1).getContent(true);
		addToZipFile("Integral_st_derivations.mie", integratedQextWl1);
		String integratedQextWl2 = new QextWriter(wl2).getContent(true);
		addToZipFile("Integral_st_derivationsWL2.mie", integratedQextWl2);
		String integratedQextWl3 = new QextWriter(wl3).getContent(true);
		addToZipFile("Integral_st_derivationsWL3.mie", integratedQextWl3);
		
		String deviations = new StDerivationWriter(wl1).getContent();
		addToZipFile("st_derivations.mie", deviations);
		
		String information = getInformation(wl1, wl2, wl3);
		addToZipFile("MieFileInformation", information);
		
		zos.finish();
		fos.close();
	}
	

	private void addToZipFile(String fileNameInZip, String content) throws FileNotFoundException, IOException {

		//System.out.println("Writing '" + fileNameInZip + "' to zip file");
		ZipEntry zipEntry = new ZipEntry(fileNameInZip);
		zos.putNextEntry(zipEntry);
		byte[] data = content.getBytes();
		zos.write(data);
		zos.closeEntry();
	}
	
	private String getInformation(MieList wl1, MieList wl2, MieList wl3) {
		StringBuilder sb = new StringBuilder();
		String start = String.format("%.4g", wl1.get(0).getDiameter());
		String end = String.format("%.4g", wl1.get(wl1.size()-1).getDiameter());
		sb.append("Size from: "+start+" to "+end+"\r\n");
		sb.append("Sigmas: ");
		for (double sigma: wl1.get(0).getSortedSigmas()) {
			sb.append(String.format("%.4g",sigma));
			sb.append(", ");
		}
		sb.delete(sb.length()-3, sb.length()-1);
		sb.append("\r\n");
		sb.append("WL1 "+(wl1.get(0).getWavelength()+": Ref. Medium: "));
		sb.append(wl1.get(0).getRefractiveIndexMedium());
		sb.append(" Ref. Sphere: ");
		sb.append(wl1.get(0).getRefractiveIndexSphereReal()+" - "+wl1.get(0).getRefractiveIndexSpereImaginary()+"i");
		sb.append("\r\n");
		sb.append("WL2 "+(wl2.get(0).getWavelength()+": Ref. Medium: "));
		sb.append(wl2.get(0).getRefractiveIndexMedium());
		sb.append(" Ref. Sphere: ");
		sb.append(wl2.get(0).getRefractiveIndexSphereReal()+" - "+wl2.get(0).getRefractiveIndexSpereImaginary()+"i");
		sb.append("\r\n");
		sb.append("WL3 "+(wl3.get(0).getWavelength()+": Ref. Medium: "));
		sb.append(wl3.get(0).getRefractiveIndexMedium());
		sb.append(" Ref. Sphere: ");
		sb.append(wl3.get(0).getRefractiveIndexSphereReal()+" - "+wl3.get(0).getRefractiveIndexSpereImaginary()+"i");
		sb.append("\r\n");
		System.out.println(sb.toString());
		return sb.toString();
	}

}
