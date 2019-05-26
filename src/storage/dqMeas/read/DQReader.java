package storage.dqMeas.read;

import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import calculation.MieList;
import charts.ChartType;
import charts.Charts;
import errors.WavelengthMismatchException;
import presets.Wavelengths;

public class DQReader {
	
	public static void main(String[] args) throws IOException, WavelengthMismatchException {
		DQReader read = new DQReader(new File("D:/mietemp/2018-08-08.miezip"));
		read.plot();
	}

	private MieList wl1;
	private MieList wl2;
	private MieList wl3;
	private File tempDir;

	/**reads zipped mieFile
	 * @throws WavelengthMismatchException **/
	public DQReader(File file) throws IOException, WavelengthMismatchException {
		String temp = System.getProperty("java.io.tmpdir");
		tempDir= new File(temp,"mie");
		tempDir.mkdir();
		clearDirectory(tempDir);
		Unzip.unZip(file, tempDir);
		System.out.println(tempDir);
		MieList wl1 = ReadMieList.readMieList(new File(tempDir,"wl1.txt"));
		MieList wl2 = ReadMieList.readMieList(new File(tempDir,"wl2.txt"));
		MieList wl3 = ReadMieList.readMieList(new File(tempDir,"wl3.txt"));
		
		new QextValueReader(wl1,new File(tempDir,"Integral_st_derivations.mie")).append();
		new QextValueReader(wl2,new File(tempDir,"Integral_st_derivationsWL2.mie")).append();
		new QextValueReader(wl3,new File(tempDir,"Integral_st_derivationsWL3.mie")).append();
		
		this.wl1=wl1;
		this.wl2=wl2;
		this.wl3=wl3;
		
		//RefIndexReader refIndices = new RefIndexReader(tempDir);
	}
	
	private void clearDirectory(File directory) {
		for(File file: directory.listFiles()) 
		    if (!file.isDirectory()) 
		        file.delete();
	}

	public MieList getWl1() {
		return wl1;
	}

	public MieList getWl2() {
		return wl2;
	}

	public MieList getWl3() {
		return wl3;
	}
	
	public HashMap<Wavelengths, MieList> getMieField() {
		HashMap<Wavelengths, MieList> result = new HashMap<>();
		result.put(Wavelengths.WL1, wl1);
		result.put(Wavelengths.WL2, wl2);
		result.put(Wavelengths.WL3, wl3);
		
		return result;
	}
	
	public void plot() {
		//Charts dq = new Charts(ChartType.DQField, calcAssignment);
		//Charts.getDQFieldDataset(wl1, wl2, wl3, true);
		Charts chart = new Charts(ChartType.DQField, wl1, wl2, wl3, true);
		chart.setVisible(true); 
	}
	
	public Container getPlot() {
		Charts chart = new Charts(ChartType.DQField, wl1, wl2, wl3, true);
		return chart.getContentPane();
	}
	
	public Charts getChart() {
		return new Charts(ChartType.DQField, wl1, wl2, wl3, true);
	}
	
	public RefIndexReader getrefIndexReader() throws IOException {
		return new RefIndexReader(tempDir);
	}
	
	



}
