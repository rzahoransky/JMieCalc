package charts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.LinkedList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYAnnotation;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.annotations.XYShapeAnnotation;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.Layer;

import calculation.MieList;
import calculation.MieWrapper;
import calculation.PointSpline;

public class MieChartPanels {

	private MieChartPanels() {
		// TODO Auto-generated constructor stub
	}
	
	public static JFreeChart getQextXrDataset(MieList mie, boolean integrated) {
		
		
		XYSeriesCollection collection = new XYSeriesCollection();
		
		if(!integrated) {
			XYSeries qextR = new XYSeries( "Qext x r² "+mie.get(0).getWavelength(), false);
			for(int i = 0;i<mie.size();i++) {
				qextR.add(mie.get(i).getRadius()*2, mie.get(i).qext()*Math.pow(mie.get(i).getRadius(), 2));
			}
			collection.addSeries(qextR);
		} else {
			//XYSeries qextR = new XYSeries( "Qext x r² integrated", false);
			for (Double dev:mie.get(0).getSortedSigmas()) {
				XYSeries series = new XYSeries("Sigma: "+dev,false);
				series.setDescription(null);
				for(int i = 0;i<mie.size();i++) { //display Diameter in charts
					series.add(mie.get(i).getRadius()*2,mie.get(i).getIntegratedQext().get(dev));	
				}
				collection.addSeries(series);
			}
		}
		
		JFreeChart chart = getXYChart("Qext x r²", "Radius", "Qext x r²", collection);
		
		//XYPointerAnnotation test = new 
			
		return chart;
	}
	
	public static JFreeChart getDQFieldDataset(MieList wl1, MieList wl2, MieList wl3, boolean integrated) {
		XYSeriesCollection collection = new XYSeriesCollection();
		
		if(!integrated) {
			XYSeries series = new XYSeries("DQ-Field",false);
			for(int i = 0;i<wl1.size();i++) {
				series.add(wl1.get(i).qext() / wl2.get(i).qext(), 
						wl2.get(i).qext() / wl3.get(i).qext());	
			}
			collection.addSeries(series);
			
		} else {
			for (Double dev:wl1.get(0).getSortedSigmas()) {
				XYSeries series = new XYSeries("Sigma: "+dev,false);
				for(int i = 0;i<wl1.size();i++) {
					//series.add(wl1.get(i).getIntegratedQext().get(dev) / wl2.get(i).getIntegratedQext().get(dev), 
					//		wl2.get(i).getIntegratedQext().get(dev) / wl3.get(i).getIntegratedQext().get(dev));	
					series.add(dqValue(wl1.get(i),wl2.get(i), dev), dqValue(wl2.get(i),wl3.get(i), dev));
				}
				collection.addSeries(series);
			}
			
		}
		

		JFreeChart chart = getXYChart("DQ Field", "DQ1", "DQ2", collection);
		setLineRendererToThickLines(chart);
		
		drawEqualSizes(chart.getXYPlot(), wl1, wl2, wl3);
		
		getSizeAnnotations(chart.getXYPlot(), wl1, wl2, wl3);
		
		//addInformationLegend(chart, wl1, wl2, wl3);
		
		
		

		return chart;
	}
	
	private static void addInformationLegend(JFreeChart chart, MieList wl1, MieList wl2, MieList wl3) {
		StringBuilder sb = new StringBuilder();
		String start = String.format("%.4g", wl1.get(0).getRadius()*2); //use diameter to represent particle size
		String end = String.format("%.4g", wl1.get(wl1.size()-1).getRadius()*2);
		sb.append("Size from: "+start+" to "+end+"\r\n");
		sb.append("Sigmas: ");
		for (double sigma: wl1.get(0).getSortedSigmas()) {
			sb.append(String.format("%.4g",sigma));
			sb.append(", ");
		}
		sb.delete(sb.length()-3, sb.length()-1);
		sb.append("\r\n");
		sb.append("WL1 "+(wl1.get(0).getWavelength()+": Ref. Medium: "));
		sb.append(wl1.get(0).getRefractiveIndexMedium());
		sb.append(" Ref. Sphere: ");
		sb.append(wl1.get(0).getRefractiveIndexSphereReal()+" - "+wl1.get(0).getRefractiveIndexSpereImaginary()+"i");
		sb.append("\r\n");
		sb.append("WL2 "+(wl2.get(0).getWavelength()+": Ref. Medium: "));
		sb.append(wl2.get(0).getRefractiveIndexMedium());
		sb.append(" Ref. Sphere: ");
		sb.append(wl2.get(0).getRefractiveIndexSphereReal()+" - "+wl2.get(0).getRefractiveIndexSpereImaginary()+"i");
		sb.append("\r\n");
		sb.append("WL3 "+(wl3.get(0).getWavelength()+": Ref. Medium: "));
		sb.append(wl3.get(0).getRefractiveIndexMedium());
		sb.append(" Ref. Sphere: ");
		sb.append(wl3.get(0).getRefractiveIndexSphereReal()+" - "+wl3.get(0).getRefractiveIndexSpereImaginary()+"i");
		sb.append("\r\n");
		System.out.println(sb.toString());
		chart.getXYPlot().addAnnotation(new XYTextAnnotation(sb.toString(), 0, 0));
	}

	private static void setLineRendererToThickLines(JFreeChart plot) {
		for (int series = 0; series < plot.getXYPlot().getSeriesCount(); series++) {
			plot.getXYPlot().getRenderer().setSeriesStroke(series, new BasicStroke(2));
		}
	}
	
	private static void drawEqualSizes(XYPlot plot, MieList wl1, MieList wl2, MieList wl3) {
				
		for (int i = wl1.size()/20; i < wl1.size()-wl1.size()/20; i += wl1.size() / 100) {
			PointSpline spline = new PointSpline();
			for (Double sigma: wl1.get(i).getSortedSigmas()) {
				spline.addPoint(new Point2D.Double(dqValue(wl1.get(i), wl2.get(i), sigma), dqValue(wl2.get(i), wl3.get(i), sigma)));
			}
			XYShapeAnnotation ann = new XYShapeAnnotation(spline.getBetterPath(), new BasicStroke(1), Color.gray);
			plot.getRenderer().addAnnotation(ann, Layer.BACKGROUND);
		}
		
	}
	
	private static void getSizeAnnotations (XYPlot plot, MieList wl1, MieList wl2, MieList wl3) {
		// include particle sizes
		
		LinkedList<XYAnnotation> annotations = new LinkedList<>();

		for (int i = wl1.size()/20; i < wl1.size()-wl1.size()/20; i += wl1.size()/25) {
			double size = wl1.get(i).getRadius()*2; //Display Radius
			double middleLineValue=wl1.get(i).getSortedSigmas().get(wl1.get(i).getSortedSigmas().size()/2);
			middleLineValue = wl1.get(0).getSortedSigmas().get(0);
			double x = dqValue(wl1.get(i),wl2.get(i), middleLineValue);
			double y = dqValue(wl2.get(i),wl3.get(i), middleLineValue);
			XYPointerAnnotation annotation = new XYPointerAnnotation(String.format("%.2g%n", size), x, y, 45);
			XYTextAnnotation annotationText = new XYTextAnnotation(String.format("%.2g%n", size), x, y);
			annotation.setBaseRadius(50);
			annotation.setArrowStroke(new BasicStroke(1));
			annotation.setTipRadius(0);
			annotation.setPaint(Color.GRAY);
			annotation.setArrowPaint(Color.GRAY);
			annotations.add(annotationText); //or annotation
		}
		
		for (XYAnnotation annotation:annotations) {
			//plot.getRenderer().addAnnotation(annotation, Layer.BACKGROUND);
			plot.addAnnotation(annotation);
		}
	}
	
	private static double dqValue(MieWrapper mie1, MieWrapper mie2, double sigma) {
		double qext1;
		double qext2;
		if(sigma==0) {
			qext1=mie1.qext();
			qext2=mie2.qext();
			//return mie1.qext()/mie2.qext();
		} else {
			qext1 = mie1.getIntegratedQext().get(sigma);
			qext2 = mie2.getIntegratedQext().get(sigma);
			//return mie1.getIntegratedQext().get(sigma) / mie2.getIntegratedQext().get(sigma);
		}
		
		if(qext2==0.0)
			qext2=Double.MIN_VALUE; //workaround for small doubles that were stored as 0.0
		
		
		double dq = qext1/qext2;
		
		if(Double.isInfinite(dq))
			dq=Math.pow(10, 10); //HACK for jFreeGraph. Larger Values are regarded as infinity and an exception is thrown
			//dq=Double.MAX_VALUE-1;
		
		return dq;
	}
	
	public static JFreeChart getDQDataset(MieList wl1, MieList wl2, boolean integrated) {
		XYSeriesCollection collection = new XYSeriesCollection();
		double wl1d=wl1.get(0).getWavelength();
		double wl2d=wl2.get(0).getWavelength();
		
		if(!integrated) {
			XYSeries series = new XYSeries("DQ "+wl1d+"/"+wl2d,false);
			for(int i = 0;i<wl1.size();i++) {
				series.add(wl1.get(i).getRadius()*2, wl1.get(i).qext() / wl2.get(i).qext());
			}
			collection.addSeries(series);
		} else {
			for (Double dev:wl1.get(0).getSortedSigmas()) {
				XYSeries series = new XYSeries("Sigma: "+dev,false);
				for(int i = 0;i<wl1.size();i++) {
					//double qext1 = wl1.get(i).getIntegratedQext().get(dev);
					//double qext2 = wl2.get(i).getIntegratedQext().get(dev);
					//series.add(wl1.get(i).getDiameter(), qext1 / qext2);
					series.add(wl1.get(i).getRadius()*2, dqValue(wl1.get(i), wl2.get(i), dev));
				}
				collection.addSeries(series);
			}
			
		}
		
		JFreeChart chart = getXYChart("DQ "+wl1d+"/"+wl2d, "Radius", "DQ", collection);
		return chart;
		
	}
	
	public static JFreeChart getXYChart(String title, String xAxis, String yAxis, XYDataset dataset) {
	      JFreeChart xylineChart = ChartFactory.createXYLineChart(
	    	         title ,
	    	         xAxis ,
	    	         yAxis,
	    	         dataset ,
	    	         PlotOrientation.VERTICAL ,
	    	         true , true , false);
			XYPlot plot = (XYPlot) xylineChart.getPlot();
			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
			renderer.setBaseShapesVisible(false);
			plot.setRenderer(renderer);
	      
		return xylineChart;
		
	}

}
