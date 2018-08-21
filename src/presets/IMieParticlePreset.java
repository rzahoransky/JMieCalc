package presets;

import java.util.HashMap;

import errors.WavelengthMismatchException;

public interface IMieParticlePreset {
	
	
	public double getRefractiveIndexMedium(Wavelengths wl) throws WavelengthMismatchException;
	
	public void setRefractiveIndexMedium(Wavelengths wl, double indexMedium);
	
	public double getRefractiveIndexSphereReal(Wavelengths wl) throws WavelengthMismatchException;
	
	public void setRefractiveIndexSphereReal(Wavelengths wl, double indexRealSphere);
	
	public double getRefractiveIndexSphereImaginaray(Wavelengths wl) throws WavelengthMismatchException;
	
	public void setRefractiveIndexSphereImaginaray(Wavelengths wl, double indexImaginarySphere);
	
	public void setValuesTo(IMieParticlePreset particles) throws WavelengthMismatchException;

	public void setName(String string);

}
