package supconmodel.model.customChart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class CustomLineChart extends ChartPanel implements Serializable {
    private static final long serialVersionUID = 1L;
    private XYSeriesCollection dataset;
    private String xLabel;
    private String yLabel;
    private String title;

    public CustomLineChart() {
        super(null);
        dataset = new XYSeriesCollection();
        JFreeChart chart = createChart(dataset);
        setChart(chart);
    }

    private JFreeChart createChart(XYSeriesCollection dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                xLabel,
                yLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        return chart;
    }

    public void addSeries(String seriesKey) {
        XYSeries series = new XYSeries(seriesKey);
        dataset.addSeries(series);
    }

    public void addData(String seriesKey, double x, double y) {
        dataset.getSeries(seriesKey).add(x, y);
    }

    public List<String> getSeriesKeys() {
        List<String> keys = new ArrayList<>();
        for (int i = 0; i < dataset.getSeriesCount(); i++) {
            keys.add(dataset.getSeriesKey(i).toString());
        }
        return keys;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getxLabel() {
        return xLabel;
    }

    public void setxLabel(String xLabel) {
        this.xLabel = xLabel;
    }

    public String getyLabel() {
        return yLabel;
    }

    public void setyLabel(String yLabel) {
        this.yLabel = yLabel;
    }

    public void resetChart(){
        JFreeChart chart = createChart(dataset);
        setChart(chart);
    }

    public void resetData(){
        dataset = new XYSeriesCollection();
        title = "";
        xLabel = "";
        yLabel = "";
    }
}