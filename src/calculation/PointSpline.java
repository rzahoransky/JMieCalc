package calculation;

import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

public class PointSpline {

	private ArrayList<Point2D.Double> points = new ArrayList<>();

	public PointSpline() {
		
	}
	
	public void addPoint(Point2D.Double point) {
		points.add(point);
	}
	
	public Path2D getPath() {
		Path2D result = new Path2D.Double();
		result.moveTo(points.get(0).x, points.get(0).y);
		for (int i = 1; i < points.size(); i++) {
			double x1 = points.get(i - 1).x;
			double y1 = points.get(i - 1).y;
			double x2 = points.get(i).x;
			double y2 = points.get(i).y;
			double x3;
			double y3;
			if (i < points.size() - 1) {
				x3 = points.get(i + 1).x;
				y3 = points.get(i + 1).y;
			} else {
				x3 = points.get(i).x;
				y3 = points.get(i).y;
			}
			
			double cx1a = x1 + (x2 - x1) / 3;
			double cy1a = y1 + (y2 - y1) / 3;
			double cx1b = x2 - (x3 - x1) / 3;
			double cy1b = y2 - (y3 - y1) / 3;
			
			result.curveTo(cx1a, cy1a, cx1b, cy1b, x2, y2);
		}

		return result;
	}
	
	public Path2D getBetterPath() {
		Path2D result = new Path2D.Double();
		result.moveTo(points.get(0).x, points.get(0).y);
		BezierSpline spline = new BezierSpline(points);
		ArrayList<Double> firstControlPoint = spline.getFirstControlPoints();
		ArrayList<Double> secondControlPoints = spline.getSecondControlPoints();
		for (int i = 0; i< points.size()-1;i++) {
			double x1=firstControlPoint.get(i).x;
			double y1=firstControlPoint.get(i).y;
			double x2=secondControlPoints.get(i).x;
			double y2=secondControlPoints.get(i).y;
			double x3=points.get(i+1).x;
			double y3=points.get(i+1).y;
			result.curveTo(x1, y1, x2, y2, x3, y3);
		}
		
		return result;
	}

}
