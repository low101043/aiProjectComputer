package com.natlowis.ai.supervised.regression;

/**
 * This is the interface which specifies all the methods for a Regression
 * algorithm
 * 
 * @author low101043
 *
 */
public interface Regression {

	public double[] answers();

	/**
	 * This will implement Gradient Descent for that regression
	 * 
	 * @param iterations     The number of iterations used
	 * @param alpha          The alpha value used
	 * @param polynomialSize The polynomial size to use or the number of independent
	 *                       values to use
	 */
	public void gradientDescent(int iterations, double alpha, int polynomialSize);

	/**
	 * This will get the data whether from a file or another place
	 */
	public void getData();

	/**
	 * This will calculate the what the data is
	 * 
	 * @param inputs The set of inputs for the data
	 * @return The yData for the input
	 */
	public double calculate(double[] inputs);

	/**
	 * This will check the function
	 */
	public void checkFunction();

	public double cost();

}
