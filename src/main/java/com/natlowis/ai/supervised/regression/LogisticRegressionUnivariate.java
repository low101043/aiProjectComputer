package com.natlowis.ai.supervised.regression;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class LogisticRegressionUnivariate extends LogisticRegression implements Regression {

	private static final Logger logger = Logger.getLogger(LogisticRegressionUnivariate.class);

	private ArrayList<ArrayList<Double>> data;

	private double[] wValues;

	private int iterations;
	private double alpha;

	private LogisticRegressionUnivariate() {
		super();
		data = new ArrayList<ArrayList<Double>>();

		int lenOfXValues = 0;
		wValues = new double[lenOfXValues];

		iterations = 0;
		alpha = 0.0;

	}

	public LogisticRegressionUnivariate(int iterations, double alpha, int polynomialSize) {
		super();

		logger.fatal(
				"Stop here this Class does not work at the moment.  IF you want to do Logistic Regression please use the Multivariable one which can do linear seperable stuff.");

		data = new ArrayList<ArrayList<Double>>();

		getData();
		wValues = new double[polynomialSize + 1];
		this.iterations = iterations;
		this.alpha = alpha;

	}

	@Override
	public void gradientDescent() {
		for (int i = 0; i < iterations; i++) { // The gradient descent code for Logistic Regression.

			for (int j = 0; j < data.size(); j++) { // Going through each triplet of values

				double xData = data.get(j).get(0);
				double x2Data = data.get(j).get(1);
				double yData = data.get(j).get(2);

				double predicted = 0; // Working out the predicted value of
										// whether it is in a set or not. The
										// equation is hW(X) = g(w0 + w1x1 +
										// w2x2)

				for (int wValueIndex = 0; wValueIndex < wValues.length; wValueIndex++) {

					double wValue = wValues[wValueIndex];

					// double xValue = xValues[wValueIndex];

					// predicted += wValue * xValue;

				}

				double difference = (yData - predicted); // Works out the difference between the actual answer and
															// the
															// predicted answer

				for (int wValueIndex = 0; wValueIndex < wValues.length; wValueIndex++) {

					double wValue = wValues[wValueIndex];

					// double xData = xValues[wValueIndex];

					wValue += alpha * difference * xData;

					wValues[wValueIndex] = wValue;

				}

			}

		}

	}

	@Override
	public void getData() {
		return;

	}

	@Override
	public double calculate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void checkFunction() {
		// TODO Auto-generated method stub

	}

}
