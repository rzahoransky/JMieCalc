package presets;

import java.util.ArrayList;
import java.util.HashMap;

import errors.WavelengthMismatchException;

public abstract class AbstractMieParticlePreset implements IMieParticlePreset {
	
	protected HashMap<Double, Double> refractiveIndexMedium = new HashMap<>(); //wavelength in um -> refractive Index
	protected HashMap<Double, Double> refractiveIndexSphereReal = new HashMap<>(); //wavelength in um -> refractive Index
	protected HashMap<Double, Double> refractiveIndexSphereImaginary = new HashMap<>(); //wavelength in um -> refractive Index
	protected ArrayList<Double> holdsInformationForWavelengths = new ArrayList<>();
	protected boolean checkForWavelengthMismatch = false;
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AbstractMieParticlePreset() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getRefractiveIndexMedium(double wl) throws WavelengthMismatchException {
		checkWavelengthInformation(wl);
		return refractiveIndexMedium.get(wl);
	}

	@Override
	public double getRefractiveIndexSphereReal(double wl) throws WavelengthMismatchException {
		checkWavelengthInformation(wl);
		return refractiveIndexSphereReal.get(wl);
	}

	@Override
	public double getRefractiveIndexSphereImaginaray(double wl) throws WavelengthMismatchException {
		checkWavelengthInformation(wl);
		return refractiveIndexSphereImaginary.get(wl);
	}
	
	protected void checkWavelengthInformation(double wl) throws WavelengthMismatchException {
		if (checkForWavelengthMismatch) {
			if (!holdsInformationForWavelengths.contains(wl)) {
				throw new WavelengthMismatchException("Wavelengths in Preset "+getName()+" do not match. "+
				getErrorString(wl));
			}
		}
	}
	
	protected String getErrorString(double wl) {
		String s;
		try {
		s = "Requested: "+wl+" containing: "+
				holdsInformationForWavelengths.get(0)+", "+
				holdsInformationForWavelengths.get(1)+", "+
				holdsInformationForWavelengths.get(2)+".";
		} catch (Exception e){
			s="";
		}
		return s;
	}

	@Override
	public void setRefractiveIndexMedium(double wl, double indexMedium) {
		refractiveIndexMedium.put(wl, indexMedium);
		
	}

	@Override
	public void setRefractiveIndexSphereReal(double wl, double indexRealSphere) {
		refractiveIndexSphereReal.put(wl, indexRealSphere);
		
	}

	@Override
	public void setRefractiveIndexSphereImaginaray(double wl, double indexImaginarySphere) {
		refractiveIndexSphereImaginary.put(wl, indexImaginarySphere);
		
	}
	
	public String toString() {
		return name;
	}
	
	
	
	public String description() {
		StringBuilder sb = new StringBuilder();
		for (Wavelengths wl : Wavelengths.values()) {
			try {
				sb.append(wl.getValue() + ": " + getRefractiveIndexMedium(wl.getValue()) + " / "
						+ getRefractiveIndexSphereReal(wl.getValue()) + "-"
						+ getRefractiveIndexSphereImaginaray(wl.getValue()));
				sb.append("\r\n");
			} catch (WavelengthMismatchException e) {
			}
		}
		return sb.toString();
	}

	@Override
	public void setValuesTo(IMieParticlePreset particles) throws WavelengthMismatchException {
		refractiveIndexMedium.clear();
		refractiveIndexSphereImaginary.clear();
		refractiveIndexSphereReal.clear();
		for (Wavelengths wl:Wavelengths.values()) {
			refractiveIndexMedium.put(wl.getValue(), particles.getRefractiveIndexMedium(wl.getValue()));
			refractiveIndexSphereReal.put(wl.getValue(), particles.getRefractiveIndexSphereReal(wl.getValue()));
			refractiveIndexSphereImaginary.put(wl.getValue(), particles.getRefractiveIndexSphereImaginaray(wl.getValue()));
		}
		
	}

}
