package com.natlowis.ai.ui;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.natlowis.ai.graphs.Graph;
import com.natlowis.ai.optimisation.antcolony.AntColonyOptimisation;
import com.natlowis.ai.search.SearchAlgorithm;
import com.natlowis.ai.search.informed.AStar;

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

		Graph graph = new Graph();

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

}
