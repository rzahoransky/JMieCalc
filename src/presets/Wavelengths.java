package presets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents wavelengths in µm
 * 
 * @author richard
 *
 */
public enum Wavelengths {
	WL1(0.637), WL2(0.811), WL3(1.316); // default Values

	private double wavelength;

	static {
		File f = new File("wavelengths");
		// System.out.println(f.getAbsolutePath());
		BufferedReader br = null;
		if (f.exists() && f.isFile()) {
			try {
				br = new BufferedReader(new FileReader(f));
				double wl1 = Double.parseDouble(br.readLine());
				double wl2 = Double.parseDouble(br.readLine());
				double wl3 = Double.parseDouble(br.readLine());
				Wavelengths.WL1.setValue(wl1);
				Wavelengths.WL2.setValue(wl2);
				Wavelengths.WL3.setValue(wl3);
			} catch (Exception e) {
				// do nothing, keep standard (see above)
			} finally {
				try {
					br.close();
				} catch (Exception e) {
					// do nothing
				}
			}
		}
	}

	/** get Wavelength in µm **/
	Wavelengths(double wavelength) {
		this.wavelength = wavelength;
	}

	/** get Wavelength in µm **/
	public double getValue() {
		return wavelength;
	}

	public void setWavelength(double wl) {
		this.wavelength = wl;
	}

	public void setValue(double wavelength) {
		this.wavelength = wavelength;
	}

	public static Wavelengths getWlFor(double wl) {
		for (Wavelengths w : Wavelengths.values())
			if (w.getValue() == wl)
				return w;
		return null;
	}

	public String toString() {
		return super.toString() + "(" + getValue() + ")";
	}

	public static void store() {
		File f = new File("wavelengths");
		FileWriter fw = null;
		try {
			fw = new FileWriter(f);
			fw.write(Double.toString(Wavelengths.WL1.getValue()));
			fw.write("\r\n");
			fw.write(Double.toString(Wavelengths.WL2.getValue()));
			fw.write("\r\n");
			fw.write(Double.toString(Wavelengths.WL3.getValue()));
		} catch (IOException e) {

		} finally {
			try {
				fw.close();
			} catch (Exception e) {};
		}

		
	}
}
