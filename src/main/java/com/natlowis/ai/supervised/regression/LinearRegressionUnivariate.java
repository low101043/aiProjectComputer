package com.natlowis.ai.supervised.regression;

import java.util.ArrayList;
import java.util.List;

public class LinearRegressionUnivariate implements Regression {

	private ArrayList<ArrayList<Double>> data;

	private double[] wValues;

	private int iterations;
	private double alpha;

	private LinearRegressionUnivariate() {
		super();
		data = new ArrayList<ArrayList<Double>>();

		int lenOfXValues = 0;
		wValues = new double[lenOfXValues];

		iterations = 0;
		alpha = 0.0;

	}

	public LinearRegressionUnivariate(int iterations, double alpha, int polynomialSize) {
		super();
		data = new ArrayList<ArrayList<Double>>();

		getData();
		wValues = new double[polynomialSize + 1];
		this.iterations = iterations;
		this.alpha = alpha;

	}

	@Override
	public void gradientDescent() {
		for (int i = 0; i < iterations; i++) { // This part of the code is the code for gradient descent.

			for (int j = 0; j < data.size(); j++) { // This goes over each pair of values in the training data

				// I have split all the parts which are repeated for both efficiency

				double xData = (double) data.get(j).get(0); // Assume only 2 inputs

				double yData = (double) data.get(j).get(1);

				double predicted = 0; // This uses the formula h(x) parameterised by W
										// to work out the predicted y values given x
										// and the vector W. The formula used here is
										// hw(x) = w0 + w1x + w2x^2

				for (int wValueIndex = 0; wValueIndex < wValues.length; wValueIndex++) {

					double wValue = wValues[wValueIndex];

					double xToPower = Math.pow(xData, wValueIndex);

					predicted += wValue * xToPower;

				}

				double difference = (yData - predicted); // The difference between the predicted value and the actual y
															// value

				for (int wValueIndex = 0; wValueIndex < wValues.length; wValueIndex++) {

					double wValue = wValues[wValueIndex];

					double xToPower = Math.pow(xData, wValueIndex);

					wValue += alpha * difference * xToPower;

					wValues[wValueIndex] = wValue;

				}
			}

		}

	}

	@Override
	public void getData() {
		// TODO Auto-generated method stub
		double[][] trainingData = { { -10, 91 }, { -3, 7 }, { 0, 1 }, { 1, 3 }, { 2, 7 }, { 3, 13 }, { 4, 21 },
				{ 10, 111 }, { -100, 9901 }, { 100, 10101 } };

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
			double xData = (double) data.get(j).get(0); // Assume only 2 inputs

			double yData = (double) data.get(j).get(1);

			double predicted = 0; // This uses the formula h(x) parameterised by W
			// to work out the predicted y values given x
			// and the vector W. The formula used here is
			// hw(x) = w0 + w1x + w2x^2

			for (int wValueIndex = 0; wValueIndex < wValues.length; wValueIndex++) {

				double wValue = wValues[wValueIndex];

				double xToPower = Math.pow(xData, wValueIndex);

				predicted += wValue * xToPower;

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

}
