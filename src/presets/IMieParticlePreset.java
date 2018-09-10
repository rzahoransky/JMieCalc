package presets;

import java.util.HashMap;

import errors.WavelengthMismatchException;

public interface IMieParticlePreset {
	
	
	public double getRefractiveIndexMedium(double wl) throws WavelengthMismatchException;
	
	public void setRefractiveIndexMedium(double wl, double indexMedium);
	
	public double getRefractiveIndexSphereReal(double wl) throws WavelengthMismatchException;
	
	public void setRefractiveIndexSphereReal(double wl, double indexRealSphere);
	
	public double getRefractiveIndexSphereImaginaray(double wl) throws WavelengthMismatchException;
	
	public void setRefractiveIndexSphereImaginaray(double wl, double indexImaginarySphere);
	
	public void setValuesTo(IMieParticlePreset particles) throws WavelengthMismatchException;

	public void setName(String string);

}
