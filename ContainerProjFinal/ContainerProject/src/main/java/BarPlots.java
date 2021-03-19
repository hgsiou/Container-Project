import java.awt.Color;
import java.util.ArrayList;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class BarPlots extends Plot implements Observer {
	
	public BarPlots(String plottitle) {
		super(plottitle);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.setValue(0, "", "Temperature");
		dataset.setValue(0, "", "Pressure");
		dataset.setValue(0, "", "Volume");

		JFreeChart chart = ChartFactory.createBarChart(" Maximum change in Conditions", "Internal Status",
				"change in values overtime", dataset, PlotOrientation.VERTICAL, false, false, false);

		CategoryItemRenderer renderer = ((CategoryPlot) chart.getPlot()).getRenderer();

		ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
		renderer.setBasePositiveItemLabelPosition(position);

	
		chart.setBackgroundPaint(Color.WHITE);
//		setbackground(chart);
		//display();
	}

	public BarPlots(String plottitle, ArrayList<Integer> t, ArrayList<Integer> p, ArrayList<Integer> h) {
		super(plottitle);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.setValue(range(t), "hum", "Temperature");
		dataset.setValue(range(p), "hum", "Pressure");
		dataset.setValue(range(h), "hum", "Volume");

		JFreeChart chart = ChartFactory.createBarChart(" Maximum change in Conditions", "Internal Status",
				"change in values overtime", dataset, PlotOrientation.VERTICAL, false, false, false);

		CategoryItemRenderer renderer = ((CategoryPlot) chart.getPlot()).getRenderer();

		ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
		renderer.setBasePositiveItemLabelPosition(position);

		 ChartPanel chartPanel = new ChartPanel( chart );        
	      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
	      setContentPane( chartPanel ); 

		chart.setBackgroundPaint(Color.WHITE);
//		setbackground(chart);
		display();
	}

	public void update(ArrayList<Integer> t, ArrayList<Integer> p, ArrayList<Integer> h) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.setValue(range(t), "hum", "Temperature");
		dataset.setValue(range(p), "hum", "Pressure");
		dataset.setValue(range(h), "hum", "Volume");

		JFreeChart chart = ChartFactory.createBarChart(" Maximum change in Conditions", "Internal Status",
				"change in values overtime", dataset, PlotOrientation.VERTICAL, false, false, false);

		CategoryItemRenderer renderer = ((CategoryPlot) chart.getPlot()).getRenderer();

		ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
		renderer.setBasePositiveItemLabelPosition(position);

		 ChartPanel chartPanel = new ChartPanel( chart );        
	      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
	      setContentPane( chartPanel ); 

		chart.setBackgroundPaint(Color.WHITE);
//		setbackground(chart);
		display();
	}

}