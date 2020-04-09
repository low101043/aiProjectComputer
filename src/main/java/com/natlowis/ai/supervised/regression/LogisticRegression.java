package com.natlowis.ai.supervised.regression;

public abstract class LogisticRegression implements Regression {

	protected double activationFunction(double data) {
		double function = 1 / (1 + Math.exp(-data)); // This works out the sigmoid value which is needed.
		return function;
	}

}
