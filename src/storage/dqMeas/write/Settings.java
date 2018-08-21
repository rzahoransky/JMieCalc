package storage.dqMeas.write;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Settings extends Properties{
	
	private final File properties = new File("settings.properties");
	public static Settings mySettings = new Settings();

	private Settings() {
		try {
			checkPropertyFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Settings getSettings() {
		return mySettings;
	}
	
	private void checkPropertyFile() throws IOException {
		if(properties.exists()) {
			return;
		} else {
			properties.createNewFile();
		}
	}

}
