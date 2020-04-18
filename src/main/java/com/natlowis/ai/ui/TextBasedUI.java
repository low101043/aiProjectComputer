package com.natlowis.ai.ui;

import org.apache.log4j.Logger;

import com.natlowis.ai.graphs.Graph;
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

		graph.addNode(0);
		graph.addNode(1);
		graph.addNode(4);

		QLearning a = new QLearning(graph);
		a.qLearning(0.0, 0.0, 0, 0);
		System.out.println(a.toString());
	}

}
