package charts;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import calculation.MieList;

@Deprecated
public class DQChart extends ApplicationFrame {

	private MieList wl1;
	private MieList wl2;
	private MieList wl3;

	public DQChart(MieList wl1, MieList wl2, MieList wl3) {
		super("DQ Field");
		setTitle("DQ Field");
		this.wl1=wl1;
		this.wl2=wl2;
		this.wl3=wl3;
		
		checkSort(wl1, wl2);
		checkSort(wl1, wl3);
		checkSort(wl2, wl3);
		
		
		//JFreeChart chart = getXYChart();
		JFreeChart chart = getScatterChart();
		XYPlot plot = (XYPlot) chart.getPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
		renderer.setBaseShapesVisible(false);

		plot.setRenderer(renderer);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		ChartPanel chartPanel = new ChartPanel( chart );
		chartPanel.setPreferredSize( new java.awt.Dimension( 640 , 480 ) );
		setPreferredSize(new Dimension(640,480));
		setSize(new Dimension(640,480));
		setContentPane(chartPanel);
	}
	
	private JFreeChart getXYChart() {
	      JFreeChart xylineChart = ChartFactory.createXYLineChart(
	    	         "DQ Field" ,
	    	         "DQ2" ,
	    	         "DQ1" ,
	    	         createDatasetQext() ,
	    	         PlotOrientation.VERTICAL ,
	    	         true , true , false);
		return xylineChart;
		
	}
	
	private JFreeChart getScatterChart() {
		JFreeChart scatterChart = ChartFactory.createScatterPlot("Mie", "DQ1", "DQ2", createDatasetQext());
		return scatterChart;
	}
	
	private XYDataset createDatasetIntegratedQext() {
		XYSeries dq = new XYSeries("Qext",false);
		XYSeriesCollection collection = new XYSeriesCollection();
		for (Double dev:wl1.get(0).getIntegratedQext().keySet()) {
			XYSeries series = new XYSeries("Sigma: "+dev,false);
			for(int i = 0;i<wl1.size();i++) {
				//System.out.println("Qext "+dev+": "+wl1.get(i).getIntegratedQext().get(dev));
				series.add(wl1.get(i).getRadius()*2,wl1.get(i).getIntegratedQext().get(dev));	
			}
			collection.addSeries(series);
		}
		return collection;
	}

	private XYDataset createDatasetIntegratedDQ() {
		XYSeries dq = new XYSeries( "DQ",false);
		XYSeriesCollection collection = new XYSeriesCollection();
		
		for (Double dev:wl1.get(0).getIntegratedQext().keySet()) {
			XYSeries series = new XYSeries("Sigma: "+dev,false);
			for(int i = 0;i<wl1.size();i++) {
				System.out.println("Qext "+dev+": "+wl1.get(i).getIntegratedQext().get(dev));
				series.add(wl1.get(i).getIntegratedQext().get(dev) / wl2.get(i).getIntegratedQext().get(dev), 
						wl2.get(i).getIntegratedQext().get(dev) / wl3.get(i).getIntegratedQext().get(dev));	
			}
			collection.addSeries(series);
		}
		return collection;
	}
	
	private XYDataset createDatasetDQ() {
		XYSeries dq = new XYSeries( "DQ", false );
		XYSeriesCollection collection = new XYSeriesCollection();
		
			XYSeries series = new XYSeries("Qext",false);
			for(int i = 0;i<wl1.size();i++) {
				//System.out.println("Qext "+dev+": "+wl1.get(i).getIntegratedQext().get(dev));
				series.add(wl1.get(i).qext() / wl2.get(i).qext(), 
						wl2.get(i).qext() / wl3.get(i).qext());	
			}
			collection.addSeries(series);
			System.out.println("Data Points: "+series.getItems().size());
		return collection;
	}
	
	private XYDataset createDatasetQext() {
		XYSeries dq = new XYSeries( "DQ" );
		XYSeriesCollection collection = new XYSeriesCollection();
		
			XYSeries series = new XYSeries("Qext");
			for(int i = 0;i<wl1.size();i++) {
				//System.out.println("Qext "+dev+": "+wl1.get(i).getIntegratedQext().get(dev));
				series.add(wl1.get(i).getRadius()*2, wl1.get(i).qext()*Math.pow(wl1.get(i).getRadius()*2, 2));
				//series.add(wl1.get(i).getRadius(), wl1.get(i).qext());
			}
			collection.addSeries(series);
		return collection;
	}
	
	private void checkSort(MieList list1, MieList list2) {
		for(int i = 0;i<list1.size();i++) {
			if(list1.get(i).getRadius()*2!=list2.get(i).getRadius()*2)
				System.out.println("BAD SORTING: ");
		}
	}

}
