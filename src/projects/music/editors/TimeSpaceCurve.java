package projects.music.editors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import javafx.geometry.Point2D;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class TimeSpaceCurve {
	public List<Point2D> curvelist = new ArrayList<Point2D>();
	public int size;
	
	 final NumberAxis xAxis = new NumberAxis();
     final NumberAxis yAxis = new NumberAxis();
     final LineChart<Number,Number> lineChart;
	
	public TimeSpaceCurve (int thesize) {
		size = thesize;
	    xAxis.setLabel("time");
	    yAxis.setLabel("space");
		lineChart = new LineChart<Number,Number>(xAxis,yAxis);
	}
	
	public double timeFromSpace (double space) {
		return 100.0;
	}
	
	public double spaceFromTime (double time) {
		return 100.0;
	}
	
	
    public static Comparator<Point2D> timeordered = new Comparator<Point2D>() {

	public int compare(Point2D p1, Point2D p2) {
	   double t1 = p1.getX();
	   double t2 = p2.getX();
	   int rep;
	   if (t1 > t2) rep = 1;
	   else if (t2 > t1) rep = -1;
	   else rep =0;
	   return  rep;
   }};
	
	public void addPoint (double x, double y, boolean sort) {
		curvelist.add(new Point2D(x, y));
		if (sort ==true)
			Collections.sort(curvelist, timeordered);
	}
	
}
