package presets;

import java.util.ArrayList;
import java.util.HashMap;

import errors.WavelengthMismatchException;

public abstract class AbstractMieParticlePreset implements IMieParticlePreset {
	
	protected HashMap<Wavelengths, Double> refractiveIndexMedium = new HashMap<>();
	protected HashMap<Wavelengths, Double> refractiveIndexSphereReal = new HashMap<>();
	protected HashMap<Wavelengths, Double> refractiveIndexSphereImaginary = new HashMap<>();
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
	public double getRefractiveIndexMedium(Wavelengths wl) throws WavelengthMismatchException {
		checkWavelengthInformation(wl);
		return refractiveIndexMedium.get(wl);
	}

	@Override
	public double getRefractiveIndexSphereReal(Wavelengths wl) throws WavelengthMismatchException {
		checkWavelengthInformation(wl);
		return refractiveIndexSphereReal.get(wl);
	}

	@Override
	public double getRefractiveIndexSphereImaginaray(Wavelengths wl) throws WavelengthMismatchException {
		checkWavelengthInformation(wl);
		return refractiveIndexSphereImaginary.get(wl);
	}
	
	protected void checkWavelengthInformation(Wavelengths wl) throws WavelengthMismatchException {
		if (checkForWavelengthMismatch) {
			if (!holdsInformationForWavelengths.contains(wl.getValue())) {
				throw new WavelengthMismatchException("Wavelengths in Preset "+getName()+" do not match. "+
				getErrorString(wl));
			}
		}
	}
	
	protected String getErrorString(Wavelengths wl) {
		String s;
		try {
		s = "Requested: "+wl.getValue()+" containing: "+
				holdsInformationForWavelengths.get(0)+", "+
				holdsInformationForWavelengths.get(1)+", "+
				holdsInformationForWavelengths.get(2)+".";
		} catch (Exception e){
			s="";
		}
		return s;
	}

	@Override
	public void setRefractiveIndexMedium(Wavelengths wl, double indexMedium) {
		refractiveIndexMedium.put(wl, indexMedium);
		
	}

	@Override
	public void setRefractiveIndexSphereReal(Wavelengths wl, double indexRealSphere) {
		refractiveIndexSphereReal.put(wl, indexRealSphere);
		
	}

	@Override
	public void setRefractiveIndexSphereImaginaray(Wavelengths wl, double indexImaginarySphere) {
		refractiveIndexSphereImaginary.put(wl, indexImaginarySphere);
		
	}
	
	public String toString() {
		return name;
	}
	
	
	
	public String description() {
		StringBuilder sb = new StringBuilder();
		for (Wavelengths wl : Wavelengths.values()) {
			try {
				sb.append(wl.getValue() + ": " + getRefractiveIndexMedium(wl) + " / "
						+ getRefractiveIndexSphereReal(wl) + "-"
						+ getRefractiveIndexSphereImaginaray(wl));
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
			refractiveIndexMedium.put(wl, particles.getRefractiveIndexMedium(wl));
			refractiveIndexSphereReal.put(wl, particles.getRefractiveIndexSphereReal(wl));
			refractiveIndexSphereImaginary.put(wl, particles.getRefractiveIndexSphereImaginaray(wl));
		}
		
	}

}
