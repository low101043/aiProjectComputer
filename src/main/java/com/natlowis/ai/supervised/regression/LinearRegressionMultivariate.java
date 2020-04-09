package com.natlowis.ai.supervised.regression;

import java.util.ArrayList;
import java.util.List;

public class LinearRegressionMultivariate implements Regression {

	private ArrayList<ArrayList<Double>> data;

	private double[] wValues;

	private int iterations;
	private double alpha;

	private LinearRegressionMultivariate() {
		super();
		data = new ArrayList<ArrayList<Double>>();

		int lenOfXValues = 0;
		wValues = new double[lenOfXValues];

		iterations = 0;
		alpha = 0.0;

	}

	public LinearRegressionMultivariate(int iterations, double alpha, int variableSize) {
		super();
		data = new ArrayList<ArrayList<Double>>();

		getData();
		wValues = new double[variableSize + 1];
		this.iterations = iterations;
		this.alpha = alpha;

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

			cost = cost + Math.pow((yData - predicted), 2.0); // This is (y- hw(x))**2
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

	@Override
	public void gradientDescent() {
		// TODO Auto-generated method stub

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

				double difference = (yData - predicted); // Works out the difference between the actual answer and
															// the
															// predicted answer

				for (int wValueIndex = 0; wValueIndex < wValues.length; wValueIndex++) {

					double wValue = wValues[wValueIndex];

					double xData = xValues[wValueIndex];

					wValue += alpha * difference * xData;

					wValues[wValueIndex] = wValue;

				}

			}

		}

	}

	@Override
	public void getData() {
		// TODO Auto-generated method stub

		double[][] trainingData = { { 0, 0, 4 }, { 0, 1, 6 }, { 0, 2, 8 }, { 0, 3, 10 }, { 1, 0, 9 }, { 1, 1, 11 },
				{ 1, 2, 13 }, { 1, 3, 15 }, { 2, 0, 14 }, { 2, 1, 16 }, { 2, 2, 18 }, { 2, 3, 20 }, { 3, 0, 19 },
				{ 3, 1, 21 }, { 3, 2, 23 }, { 3, 3, 25 } };

		for (double[] item : trainingData) {

			ArrayList<Double> dataToAdd = new ArrayList<Double>();
			for (double number : item) {
				dataToAdd.add(number);
			}

			data.add(dataToAdd);

		}

		return;

	}

}
