package com.natlowis.ai.ui;

import org.apache.log4j.Logger;

import com.natlowis.ai.graphs.Graph;
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

		for (int i = 0; i< 14; i++) {
			graph.addNode(i);
		}
		
		graph.setNodeSpecial(0, 366);
		graph.setNodeSpecial(1, 0);
		graph.setNodeSpecial(2, 160);
		graph.setNodeSpecial(3, 242);
		graph.setNodeSpecial(4, 176);
		graph.setNodeSpecial(5, 77);
		graph.setNodeSpecial(6, 244);
		graph.setNodeSpecial(7, 241);
		graph.setNodeSpecial(8, 380);
		graph.setNodeSpecial(9, 100);
		graph.setNodeSpecial(10, 193);
		graph.setNodeSpecial(11, 253);
		graph.setNodeSpecial(12, 329);
		graph.setNodeSpecial(13, 374);
		
		graph.addConnection(0, 13, 75);
		graph.addConnection(0, 12, 118);
		
		graph.addConnection(1, 4, 211);
		graph.addConnection(1, 5, 90);
		graph.addConnection(1, 9, 101);
		
		graph.addConnection(2, 3, 120);
		graph.addConnection(2, 10, 146);
		
		graph.addConnection(3, 2, 120);
		graph.addConnection(3, 7, 75);

		graph.addConnection(4, 1, 211);
		graph.addConnection(4, 11, 99);
		
		graph.addConnection(5, 1, 90);
		
		graph.addConnection(6, 7, 70);
		graph.addConnection(6, 12, 111);
		
		graph.addConnection(7, 6, 70);
		graph.addConnection(7, 3, 75);
		
		graph.addConnection(8, 11, 151);
		graph.addConnection(8, 13, 71);
		
		graph.addConnection(9, 1, 101);
		graph.addConnection(9, 10, 97);
		
		graph.addConnection(10, 11, 80);
		graph.addConnection(10, 9, 97);
		graph.addConnection(10, 2, 146);
		
		graph.addConnection(11, 8, 151);
		graph.addConnection(11, 4, 99);
		graph.addConnection(11, 10, 80);
		
		graph.addConnection(12, 0, 118);
		graph.addConnection(12, 6, 111);
		
		graph.addConnection(13, 8, 71);
		graph.addConnection(13, 0, 75);

		SearchAlgorithm astar = new AStar(graph);
		astar.algorithmToImplement(8, 1);
		Integer[] a = astar.nodesToVisit();
		logger.trace(a.length);
		
		for (int e:a) {
			System.out.println(e);
		}
	}

}
