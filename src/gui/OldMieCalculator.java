package gui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import ThreadHandling.ThreadHandler;
import ThreadHandling.ThreadHandlerProgressMonitor;
import actions.CalculationAction;
import buildInPresets.LatexInWaterPreset;
import buildInPresets.NarrowLogNormalDistribution;
import calculation.CalculationAssignment;
import calculation.MieIntegratorThreadFactory;
import calculation.MieList;
import calculation.MieListCalculator;
import calculation.MieListGenerator;
import charts.ChartType;
import charts.Charts;
import charts.DQChart;
import errors.IllegalMieListException;
import errors.WavelengthMismatchException;
import presets.MieParameters;
import presets.StandardDiameterParameters;
import presets.Wavelengths;
import storage.dqMeas.write.OutputWriter;

public class OldMieCalculator {
	
	private CalculationAssignment parameters;
	
	public static void main(String args[]) {
		
		if(args.length==0) {
			new JMieCalcGuiGridBagLayout().setVisible(true);
		} else if(args.length==1) {
			// create DQ Field
		}
		
		
		//MieCalculator calculator = new MieCalculator(CalculationAssignment.getCalculationAssignment(new LatexInWaterPreset(), new StandardDiameterParameters(), new NarrowLogNormalDistribution()));
	}

	

	public OldMieCalculator(CalculationAssignment parameters) throws InterruptedException, IllegalMieListException, WavelengthMismatchException, IOException {
		
		this.parameters = parameters;
		
		//DQChart chart = new DQChart(wl1List, wl2List, wl3List);
		//chart.setVisible(true);
		
		/**
		
		for(ChartType type: ChartType.values()) {
			new Charts(type, wl1List, wl2List, wl3List, false).setVisible(true);
			new Charts(type, wl1List, wl2List, wl3List, true).setVisible(true);
		}
		
		OutputWriter ow = new OutputWriter(wl1List, wl2List, wl3List);
		ow.write(new File("D:/mietemp"));
		
		ow.writeAsZip(new File("D:/mietemp/mie.zip"));
		
		**/
	}

	public HashMap<Wavelengths, MieList> calculateDQ()
			throws WavelengthMismatchException, IllegalMieListException, InterruptedException {
		MieListGenerator generator = new MieListGenerator(parameters.getParticles(), parameters.getDiameters());

		MieList wl1List = generator.generateList(Wavelengths.WL1);
		MieList wl2List = generator.generateList(Wavelengths.WL2);
		MieList wl3List = generator.generateList(Wavelengths.WL3);

		wl1List.checkConsistency();

		MieListCalculator calc1 = new MieListCalculator(wl1List);
		MieListCalculator calc2 = new MieListCalculator(wl2List);
		MieListCalculator calc3 = new MieListCalculator(wl3List);

		Thread wl1Thread = calc1.calculate();
		Thread wl2Thread = calc2.calculate();
		Thread wl3Thread = calc3.calculate();

		wl1Thread.join();
		wl2Thread.join();
		wl3Thread.join();

		System.out.println("All threads finished for Qext Values");

		// Do the integration

		MieIntegratorThreadFactory integratorWl1 = new MieIntegratorThreadFactory(wl1List, parameters.getSigmas());
		MieIntegratorThreadFactory integratorWl2 = new MieIntegratorThreadFactory(wl2List, parameters.getSigmas());
		MieIntegratorThreadFactory integratorWl3 = new MieIntegratorThreadFactory(wl3List, parameters.getSigmas());

		ArrayList<Thread> threads = new ArrayList<>();

		threads.addAll(integratorWl1.getThreads());
		threads.addAll(integratorWl2.getThreads());
		threads.addAll(integratorWl3.getThreads());

		ThreadHandler handler = new ThreadHandler(threads);
		handler.addThreadMonitor(parameters);
		handler.runThreads();

		// Calculation now finied

		//parameters.getMieLists().add(wl1List);
		//parameters.getMieLists().add(wl2List);
		//parameters.getMieLists().add(wl3List);

		//parameters.calculationFinished();

		HashMap<Wavelengths, MieList> result = new HashMap<>();
		result.put(Wavelengths.WL1, wl1List);
		result.put(Wavelengths.WL2, wl2List);
		result.put(Wavelengths.WL3, wl3List);

		return result;
	}

}
