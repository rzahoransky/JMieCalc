package presets;

import java.util.TreeMap;

import errors.WavelengthMismatchException;

public abstract class AbstractMieParticlePreset implements IMieParticlePreset {
	
	protected TreeMap<Double, Double> refractiveIndexMedium = new TreeMap<>(); //wavelength in um -> refractive Index
	protected TreeMap<Double, Double> refractiveIndexSphereReal = new TreeMap<>(); //wavelength in um -> refractive Index
	protected TreeMap<Double, Double> refractiveIndexSphereImaginary = new TreeMap<>(); //wavelength in um -> refractive Index
	//protected ArrayList<Double> holdsInformationForWavelengths = new ArrayList<>();
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
		return refractiveIndexMedium.get(closestKey(wl, refractiveIndexMedium));
	}

	@Override
	public double getRefractiveIndexSphereReal(double wl) throws WavelengthMismatchException {
		checkWavelengthInformation(wl);
		return refractiveIndexSphereReal.get(closestKey(wl, refractiveIndexSphereReal));
	}

	@Override
	public double getRefractiveIndexSphereImaginaray(double wl) throws WavelengthMismatchException {
		checkWavelengthInformation(wl);
		return refractiveIndexSphereImaginary.get(closestKey(wl, refractiveIndexSphereImaginary));
	}
	
	protected void checkWavelengthInformation(double wl) throws WavelengthMismatchException {
		if (checkForWavelengthMismatch) {
			if (!refractiveIndexSphereReal.containsKey(wl)) {
				throw new WavelengthMismatchException("Wavelengths in Preset "+getName()+" do not match. "+
				getErrorString(wl));
			}
		}
	}
	
	protected String getErrorString(double wl) {
		String s;
		try {
		s = "Requested: "+wl+" containing: "+
				getWavelengthsString();
		} catch (Exception e){
			s="";
		}
		return s;
	}
	
	protected String getWavelengthsString() {
		String s = "";
		for (Double wl: refractiveIndexSphereReal.keySet()) {
			s+=wl;
			s+=" ";
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
	
	protected double maxWavelengthError() {
		return 0.030;
	}
	
	protected double closestKey(double wl, TreeMap<Double, Double> map) {
		
		if(map.containsKey(wl))
			return wl;
		
		try {
			double floorKey = map.floorKey(wl);
		} catch (NullPointerException e) {
			return map.ceilingKey(wl);
		}
		
		try {
			double ceilingKey = map.ceilingKey(wl);
		} catch (NullPointerException e) {
			return map.floorKey(wl);
		}
		
		if (Math.abs(wl - map.ceilingKey(wl))<=Math.abs(wl - map.floorKey(wl))) {
			return map.ceilingKey(wl);
		}
		return map.floorKey(wl);
		
	}

}
