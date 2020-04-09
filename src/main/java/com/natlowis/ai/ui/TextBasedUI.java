package com.natlowis.ai.ui;

import com.natlowis.ai.supervised.regression.*;
import com.natlowis.ai.unsupervised.QLearning;
import com.natlowis.ai.graphs.*;
import com.natlowis.ai.search.uninformed.BreadthFirstSearch;
import com.natlowis.ai.search.uninformed.DepthFirstSearch;
import com.natlowis.ai.search.uninformed.SearchAlgorithm;

import org.apache.log4j.Logger;

public class TextBasedUI {

	// TODO Done QLearning and 3/4 regression. DO final regression then GUI

	private static final Logger logger = Logger.getLogger(TextBasedUI.class);

	public static void main(String[] args) {

		System.out.println("This will be the Command Line UI until I sort out JavaFX");
		logger.trace("Does This work");

		Graph graph = new Graph();

		for (int i = 1; i < 8; i++) {
			graph.addNode(i);
		}

		graph.addConnection(1, 2, 1);
		graph.addConnection(1, 3, 1);

		graph.addConnection(2, 1, 1);
		graph.addConnection(2, 4, 1);

		graph.addConnection(3, 1, 1);
		graph.addConnection(3, 4, 1);
		graph.addConnection(3, 5, 1);
		graph.addConnection(3, 6, 1);

		graph.addConnection(4, 2, 1);
		graph.addConnection(4, 3, 1);

		graph.addConnection(5, 3, 1);
		graph.addConnection(5, 7, 1);

		graph.addConnection(6, 3, 1);
		graph.addConnection(6, 7, 1);

		graph.addConnection(7, 5, 1);
		graph.addConnection(7, 6, 1);

		SearchAlgorithm dfs = new DepthFirstSearch(graph);
		dfs.algorithmToImplement(1, 6);
		Integer[] a = dfs.nodesToVisit();
		
		for (int item: a) {
			System.out.println(item);
		}
	}

}
