package com.erp.action;

/**
 * Created by IntelliJ IDEA.
 * User: Minal
 * Date: Apr 20, 2012
 * Time: 5:26:45 PM
 * To change this template use File | Settings | File Templates.
 */


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Test {

  public static void main(String arg[]) {

    // Prepare the data set
    DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
    barDataset.setValue(26, "Drinks", "Coca-Cola");
    barDataset.setValue(20, "Drinks", "Pepsi");
    barDataset.setValue(12, "Drinks", "Gold Spot");
    barDataset.setValue(14, "Drinks", "Slice");
    barDataset.setValue(18, "Drinks", "Appy Fizz");
    barDataset.setValue(10, "Drinks", "Limca");

    //Create the chart
    JFreeChart chart = ChartFactory.createBarChart(
        "Soft Drink Bar Chart", "Drink", "Share", barDataset,
        PlotOrientation.VERTICAL, false, true, false);

    //Render the frame
    ChartFrame chartFrame = new ChartFrame("Vertical Bar Chart", chart);
    chartFrame.setVisible(true);
    chartFrame.setSize(560, 350);
  }

}
