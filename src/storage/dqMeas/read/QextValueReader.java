package storage.dqMeas.read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import calculation.MieList;

public class QextValueReader {

	private MieList ml;
	private File integratedQextFile;
	private RefIndexReader refIndexReader;

	public QextValueReader(MieList ml, File integratedQextFile) throws IOException {
		this.ml = ml;
		this.integratedQextFile = integratedQextFile;
		this.refIndexReader = new RefIndexReader(integratedQextFile.getParentFile());
	}

	public void append() throws IOException {
		Scanner s = null;
		try {
			s = new Scanner(integratedQextFile);
			s.useLocale(Locale.GERMANY);
			
			while(s.hasNext()) {
				double readDiameter = s.nextDouble();
				for(double sigma:refIndexReader.getSigmas()) {
					ml.getElementForDiameter(readDiameter).getIntegratedQext().put(sigma, s.nextDouble());
				}
			}
			
		} catch (FileNotFoundException e) {
			throw e;
		} finally {
			s.close();
		}
		
	}

}
