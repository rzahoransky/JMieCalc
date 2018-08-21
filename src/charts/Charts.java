package charts;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.NumberFormatter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;

import calculation.CalculationAssignment;
import calculation.MieList;
import presets.Wavelengths;

public class Charts extends JFrame{

	private ChartType type;
	private boolean integrated;
	private MieList wl1;
	private MieList wl2;
	private MieList wl3;
	private JFreeChart chart;
	private ChartPanel chartPanel;

	public Charts(ChartType type, MieList wl1, MieList wl2, MieList wl3, boolean integrated) {
		this.type=type;
		this.integrated=integrated;
		this.wl1=wl1;
		this.wl2=wl2;
		this.wl3=wl3;
		setUpFrame();
		
		chart = getXYChart();
		chartPanel = new ChartPanel(chart);
		getContentPane().add(chartPanel, BorderLayout.CENTER);
		//setContentPane(chartPanel);
	}
	
	public JFreeChart getChart() {
		return chart;
	}
	
	public ChartPanel getChartPanel() {
		return chartPanel;
	}
	
	public Charts(ChartType type, CalculationAssignment calcAssignment) {
		this(type, calcAssignment.getMieLists().get(Wavelengths.WL1),calcAssignment.getMieLists().get(Wavelengths.WL2),calcAssignment.getMieLists().get(Wavelengths.WL3),true);
	}
	
	protected JFreeChart getXYChart() {
		switch (type) {
		case DQ1:
			return MieChartPanels.getDQDataset(wl1, wl2, integrated);
		case DQ2:
			return MieChartPanels.getDQDataset(wl2, wl3, integrated);
		case DQ3:
			return MieChartPanels.getDQDataset(wl1, wl3, integrated);
		case DQField:
			return MieChartPanels.getDQFieldDataset(wl1, wl2, wl3, integrated);
		case QextTimesRWl1:
			return MieChartPanels.getQextXrDataset(wl1, integrated);
		case QextTimesRWl2:
			return MieChartPanels.getQextXrDataset(wl2, integrated);
		case QextTimesRWl3:
			return MieChartPanels.getQextXrDataset(wl3, integrated);
		default:
			break;
		}
		return null;
	}

	protected void setUpFrame() {
		setSize(new Dimension(700, 500));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(generateTitel());
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getInfoPanel(), BorderLayout.SOUTH);
	}
	
	protected JLabel getInfoPanel() {
		StringBuilder sb = new StringBuilder();
		sb.append("Wavelengths: ");
		sb.append(wl1+", ");
		sb.append(wl2+", ");
		sb.append(wl3);
		sb.append(" Sizes: ");
		DecimalFormat df = new DecimalFormat("#.####");
		sb.append(df.format(wl1.getMinDiameter())+"..."+df.format(wl1.getMaxDiameter()));
		JLabel info = new JLabel(sb.toString());
		return info;
	}
	
	protected String generateTitel() {
		String title = type.getValue();
		if(integrated)
			title+=" (integrated)";
		return title;
	}

}
