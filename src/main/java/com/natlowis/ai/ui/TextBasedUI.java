package com.natlowis.ai.ui;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.natlowis.ai.graphs.Graph;
import com.natlowis.ai.supervised.knn.ClassificationKNN;
import com.natlowis.ai.supervised.knn.KNearestNeighbour;
import com.natlowis.ai.supervised.knn.RegressionKNN;
import com.natlowis.ai.unsupervised.QLearning;

/**
 * This is used mostly for testing purposes
 * 
 * @author low101043
 *
 */
public class TextBasedUI {

	// TODO Comments for Code. GUI! (So get JavaFX working). Final Regression. See
	// if all methods meld together

	private static final Logger logger = Logger.getLogger(TextBasedUI.class);

	public static void main(String[] args) {

		System.out.println("This will be the Command Line UI until I sort out JavaFX");
		logger.trace("Does This work");

		KNearestNeighbour a = new RegressionKNN();
		
		ArrayList<ArrayList<Double>> trainingData = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> input = new ArrayList<Double>();
		input.add(2.0);
		input.add(2.0);
		
		double[][] trainingData2 = { { 2, 1, 1 },
									 { 2, 3, 2 },
									};

		for (double[] item : trainingData2) {

			ArrayList<Double> dataToAdd = new ArrayList<Double>();
			for (double number : item) {
				dataToAdd.add(number);
			}

			trainingData.add(dataToAdd);

		}
		
		Double answer = a.knn(input, 1, trainingData);
		
		logger.trace("a" + answer);
	}
}
