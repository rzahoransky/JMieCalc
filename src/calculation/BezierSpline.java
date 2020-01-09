package calculation;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


/**
 * Bezier Spline method
 *
 */
public class BezierSpline {
	ArrayList<Point2D.Double> firstControlPoints = new ArrayList<>();
	ArrayList<Point2D.Double> secondControlPoints = new ArrayList<>();

	/// <summary>
	/// Get open-ended Bezier Spline Control Points.
	/// </summary>
	/// <param name="knots">Input Knot Bezier spline points.</param>
	/// <param name="firstControlPoints">Output First Control points
	/// array of knots.Length - 1 length.</param>
	/// <param name="secondControlPoints">Output Second Control points
	/// array of knots.Length - 1 length.</param>
	/// <exception cref="ArgumentNullException"><paramref name="knots"/>
	/// parameter must be not null.</exception>
	/// <exception cref="ArgumentException"><paramref name="knots"/>
	/// array must contain at least two points.</exception>
	/**
	 * Get open-ended Bezier Spline Control Points.
	 * @param knots Knot Bezier spline points
	 */
	public BezierSpline(List<Point2D.Double> knots) {
		int n = knots.size() - 1;
		if (n == 1) { // Special case: Bezier curve should be a straight line.
			firstControlPoints.add(new Point2D.Double());
			// 3P1 = 2P0 + P3
			firstControlPoints.get(0).x = (2 * knots.get(0).x + knots.get(1).x) / 3;
			firstControlPoints.get(0).y = (2 * knots.get(0).y + knots.get(1).y) / 3;

			secondControlPoints.add(new Point2D.Double());
			// P2 = 2P1 – P0
			secondControlPoints.get(0).x = 2 * firstControlPoints.get(0).x - knots.get(0).x;
			secondControlPoints.get(0).y = 2 * firstControlPoints.get(0).y - knots.get(0).y;
			return;
		}

		// Calculate first Bezier control points
		// Right hand side vector
		double[] rhs = new double[n];

		// Set right hand side X values
		for (int i = 1; i < n - 1; ++i)
			rhs[i] = 4 * knots.get(i).x + 2 * knots.get(i + 1).x;
		rhs[0] = knots.get(0).x + 2 * knots.get(1).x;
		rhs[n - 1] = (8 * knots.get(n - 1).x + knots.get(n).x) / 2.0;
		// Get first control points X-values
		double[] x = GetFirstControlPoints(rhs);

		// Set right hand side Y values
		for (int i = 1; i < n - 1; ++i)
			rhs[i] = 4 * knots.get(i).y + 2 * knots.get(i + 1).y;
		rhs[0] = knots.get(0).y + 2 * knots.get(1).y;
		rhs[n - 1] = (8 * knots.get(n - 1).y + knots.get(n).y) / 2.0;
		// Get first control points Y-values
		double[] y = GetFirstControlPoints(rhs);

		// Fill output arrays.
		// firstControlPoints = new Point[n];
		// secondControlPoints = new Point[n];
		for (int i = 0; i < n; ++i) {
			// First control point
			firstControlPoints.add(new Point2D.Double(x[i], y[i]));
			// firstControlPoints[i] = new Point(x[i], y[i]);
			// Second control point
			if (i < n - 1) // last point
				secondControlPoints
						.add(new Point2D.Double(2 * knots.get(i + 1).x - x[i + 1], 2 * knots.get(i + 1).y - y[i + 1]));
			else
				secondControlPoints
						.add(new Point2D.Double((knots.get(n).x + x[n - 1]) / 2, (knots.get(n).y + y[n - 1]) / 2));
		}
	}

	public ArrayList<Point2D.Double> getFirstControlPoints() {
		return firstControlPoints;
	}

	public ArrayList<Point2D.Double> getSecondControlPoints() {
		return secondControlPoints;
	}

	/// <summary>
	/// Solves a tridiagonal system for one of coordinates (x or y)
	/// of first Bezier control points.
	/// </summary>
	/// <param name="rhs">Right hand side vector.</param>
	/// <returns>Solution vector.</returns>
	private double[] GetFirstControlPoints(double[] rhs) {
		int n = rhs.length;
		double[] x = new double[n]; // Solution vector.
		double[] tmp = new double[n]; // Temp workspace.

		double b = 2.0;
		x[0] = rhs[0] / b;
		for (int i = 1; i < n; i++) // Decomposition and forward substitution.
		{
			tmp[i] = 1 / b;
			b = (i < n - 1 ? 4.0 : 3.5) - tmp[i];
			x[i] = (rhs[i] - x[i - 1]) / b;
		}
		for (int i = 1; i < n; i++)
			x[n - i - 1] -= tmp[n - i] * x[n - i]; // Backsubstitution.

		return x;
	}
}
