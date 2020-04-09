package com.natlowis.ai.supervised.regression;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.natlowis.ai.ui.TextBasedUI;

public class LogisticRegressionMultivariate extends LogisticRegression implements Regression {

	private static final Logger logger = Logger.getLogger(LogisticRegressionMultivariate.class);

	private ArrayList<ArrayList<Double>> data;

	private double[] wValues;

	private int iterations;
	private double alpha;

	private LogisticRegressionMultivariate() {
		super();
		data = new ArrayList<ArrayList<Double>>();

		int lenOfXValues = 0;
		wValues = new double[lenOfXValues];

		iterations = 0;
		alpha = 0.0;

	}

	public LogisticRegressionMultivariate(int iterations, double alpha, int variableSize) {
		super();
		data = new ArrayList<ArrayList<Double>>();

		getData();
		wValues = new double[variableSize + 1];
		this.iterations = iterations;
		this.alpha = alpha;

	}

	@Override
	public void gradientDescent() {
		for (int i = 0; i < iterations; i++) { // The gradient descent code for Logistic Regression.

			for (int j = 0; j < data.size(); j++) { // Going through each triplet of values

				double[] xValues = new double[data.get(j).size()];

				for (int place = 0; place < xValues.length; place++) {
					if (place == 0) {
						xValues[place] = 1;
					} else {
						xValues[place] = data.get(j).get(place - 1);
					}
				}
				double yData = data.get(j).get(data.get(j).size() - 1);

				double predicted = 0; // Working out the predicted value of
										// whether it is in a set or not. The
										// equation is hW(X) = g(w0 + w1x1 +
										// w2x2)

				for (int wValueIndex = 0; wValueIndex < wValues.length; wValueIndex++) {

					double wValue = wValues[wValueIndex];

					double xValue = xValues[wValueIndex];

					predicted += wValue * xValue;

				}

				double finalPredicted = activationFunction(predicted);
				double difference = (yData - finalPredicted); // Works out the difference between the actual answer and
																// the
																// predicted answer

				for (int wValueIndex = 0; wValueIndex < wValues.length; wValueIndex++) {

					double wValue = wValues[wValueIndex];

					double xData = xValues[wValueIndex];

					wValue += alpha * difference * xData;

					wValues[wValueIndex] = wValue;

				}

			}

			if (i < 10) {
				logger.trace(wValues[0] + " " + wValues[1] + " " + wValues[2] + "  Values");
			}

		}

	}

	@Override
	public void getData() {
		// TODO Auto-generated method stub
		double[][] trainingData = { { 1, 1, 0 }, { 2, 2, 0 }, { 0.3, 1.2, 0 }, { .6, .8, 0 }, { 1.2, 1, 0 },
				{ 1.3, 1, 0 }, { 1.8, 2, 0 }, { 1.5, 1.4, 0 }, { 3, 3, 1 }, { 4, 4, 1 }, { 3.1, 3.3, 1 },
				{ 3.6, 3.8, 1 }, { 3.8, 2.1, 1 }, { 3.5, 2.2, 1 }, { 3.25, 2.8, 1 } };

		for (double[] item : trainingData) {

			ArrayList<Double> dataToAdd = new ArrayList<Double>();
			for (double number : item) {
				dataToAdd.add(number);
			}

			data.add(dataToAdd);

		}

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
		double cost = 0;
		for (int j = 0; j < data.size(); j++) { // This part of the code will just output the final predicted
												// values against the actual values. Used for debugging.
			double[] xValues = new double[data.get(j).size()];

			for (int place = 0; place < xValues.length; place++) {
				if (place == 0) {
					xValues[place] = 1;
				} else {
					xValues[place] = data.get(j).get(place - 1);
				}
			}
			double yData = (double) data.get(j).get(data.get(j).size() - 1);

			double predicted = 0; // Working out the predicted value of
			// whether it is in a set or not. The
			// equation is hW(X) = g(w0 + w1x1 +
			// w2x2)

			for (int wValueIndex = 0; wValueIndex < wValues.length; wValueIndex++) {

				double wValue = wValues[wValueIndex];

				double xValue = xValues[wValueIndex];

				predicted += wValue * xValue;

			}
			double finalPredicted = activationFunction(predicted);

			if (yData == 1.0) {
				cost += yData * Math.log10(finalPredicted);
			} else {
				cost += (1 - yData) * Math.log10(1 - finalPredicted);
			}

			System.out.println("Predicted: " + predicted + " Actual: " + yData);
		}
		cost = cost / data.size(); // This will be the final cost.

		for (int wValueIndex = 0; wValueIndex < wValues.length; wValueIndex++) {

			double wValue = wValues[wValueIndex];
			System.out.println(wValue);

		}
		System.out.println(cost); // This
									// prints
									// the
									// final
									// equation
									// out
	}

}
