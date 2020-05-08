package com.natlowis.ai.ui;

import java.util.ArrayList;

import org.apache.log4j.Logger;

<<<<<<< HEAD
import com.natlowis.ai.supervised.knn.KNearestNeighbour;
import com.natlowis.ai.supervised.knn.RegressionKNN;
=======
import com.natlowis.ai.graphs.Graph;
import com.natlowis.ai.optimisation.antcolony.AntColonyOptimisation;
import com.natlowis.ai.search.SearchAlgorithm;
import com.natlowis.ai.search.informed.AStar;

import com.natlowis.ai.unsupervised.QLearning;
>>>>>>> addingGraphs

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

<<<<<<< HEAD
		ArrayList<ArrayList<Double>> trainingData = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> input = new ArrayList<Double>();
		input.add(2.0);
		input.add(2.0);

		double[][] trainingData2 = { { 2, 1, 1 }, { 2, 3, 2 }, };

		for (double[] item : trainingData2) {

			ArrayList<Double> dataToAdd = new ArrayList<Double>();
			for (double number : item) {
				dataToAdd.add(number);
			}
=======
		graph.addConnection(0, 1, 1);
		graph.addConnection(0, 5, 1);
		graph.addConnection(0, 6, 1);
		
		graph.addConnection(1, 2, 1);
		graph.addConnection(1, 0, 1);
		graph.addConnection(1, 6, 1);
		
		graph.addConnection(2, 1, 1);
		graph.addConnection(2, 3, 1);
		graph.addConnection(2, 6, 1);
		
		graph.addConnection(3, 2, 1);
		graph.addConnection(3, 4, 1);
		graph.addConnection(3, 6, 1);
		
		graph.addConnection(4, 3, 1);
		graph.addConnection(4, 5, 1);
		graph.addConnection(4, 6, 1);
		
		graph.addConnection(5, 0, 1);
		graph.addConnection(5, 4, 1);
		graph.addConnection(5, 6, 1);
		
		graph.addConnection(6, 0, 1);
		graph.addConnection(6, 1, 1);
		graph.addConnection(6, 2, 1);
		graph.addConnection(6, 3, 1);
		graph.addConnection(6, 4, 1);
		graph.addConnection(6, 5, 1);
		
		AntColonyOptimisation a = new AntColonyOptimisation(graph);
		
		a.AntColonyOptimisationAlgorithm(0, 2, 100, .7, 100);
		
		ArrayList<Integer> route = a.finalRoute();
		
		for (int e: route) {
			logger.trace(e);
		}
	}
>>>>>>> addingGraphs

			trainingData.add(dataToAdd);

		}

		Double answer = a.knn(input, 1, trainingData);

		logger.trace("a" + answer);
	}
}
