package com.natlowis.ai.supervised.regression;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.natlowis.ai.testData.makeLogisticRegressionData;

/**
 * This class will implement Logistic Regression which is linear or non linear HOWEVER it does not work
 * @author low101043
 *
 */
public class LogisticRegressionUnivariate extends LogisticRegression implements Regression {

	private static final Logger logger = Logger.getLogger(LogisticRegressionUnivariate.class);

	private ArrayList<ArrayList<Double>> data;

	private double[] wValues;

	private int iterations;
	private double alpha;

	/*private LogisticRegressionUnivariate() {
		super();
		data = new ArrayList<ArrayList<Double>>();

		int lenOfXValues = 0;
		wValues = new double[lenOfXValues];

		iterations = 0;
		alpha = 0.0;

	}*/

	/**
	 * The class which should work but does not.
	 * @throws Exception
	 */
	public LogisticRegressionUnivariate()  {
		super();

		logger.fatal(
				"Stop here this Class does not work at the moment.  IF you want to do Logistic Regression please use the Multivariable one which can do linear seperable stuff.");

		data = new ArrayList<ArrayList<Double>>();

		getData();
		

	}
	
	/**
	 * DOES NOT WORK
	 */
	@Override
	public void gradientDescent(int iterations, double alpha, int polynomialSize) {
		
		
		wValues = new double[polynomialSize + 2];
		this.iterations = iterations;
		this.alpha = alpha;
		
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
					
					if (wValueIndex == 1) {
						predicted += wValue * x2Data;
					}

					else {

					double xToPower = Math.pow(xData, wValueIndex);

					predicted += wValue * xToPower;}

				}
				double finalPredicted = activationFunction(predicted);
				double difference = (yData - finalPredicted); // Works out the difference between the actual answer and
															// the
															// predicted answer

				for (int wValueIndex = 0; wValueIndex < wValues.length; wValueIndex++) {

					
					double wValue = wValues[wValueIndex];

					if (wValueIndex == 1) {
						wValue += alpha *difference * x2Data;
					}
					
					else {
					double xToPower = Math.pow(xData, wValueIndex);

					wValue += alpha * difference * xToPower;
					}
					wValues[wValueIndex] = wValue;

				}

			}

		}
		
		int a = 5;

	}

	@Override
	public void getData() {
		
		makeLogisticRegressionData a = new makeLogisticRegressionData();
		
		double[][] trainingData = a.makeValues();
		for (double[] item : trainingData) {

			ArrayList<Double> dataToAdd = new ArrayList<Double>();
			for (double number : item) {
				dataToAdd.add(number);
			}

			data.add(dataToAdd);
		}

	}

	@Override
	public double calculate(double inputs[]) {
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
double x2Data = data.get(j).get(1);
double yData = (double) data.get(j).get(2);
			double predicted = 0; // Working out the predicted value of
			// whether it is in a set or not. The
			// equation is hW(X) = g(w0 + w1x1 +
			// w2x2)

			for (int wValueIndex = 0; wValueIndex < wValues.length; wValueIndex++) {

				double wValue = wValues[wValueIndex];

				if (wValueIndex == 1) {
					predicted += wValue * x2Data;
				}
				else {
				double xToPower = Math.pow(xData, wValueIndex);

				predicted += wValue * xToPower;}

			}
			double finalPredicted = activationFunction(predicted);

			if (yData == 1.0) {
				cost += yData * Math.log10(finalPredicted);
			} else {
				cost += (1 - yData) * Math.log10(1 - finalPredicted);
			}

			System.out.println("Predicted: " + finalPredicted + " Actual: " + yData);
		}
		cost = -(cost / data.size()); // This will be the final cost.

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
