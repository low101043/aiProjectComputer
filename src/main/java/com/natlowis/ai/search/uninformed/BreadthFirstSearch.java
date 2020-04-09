package com.natlowis.ai.search.uninformed;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import com.natlowis.ai.graphs.Connection;
import com.natlowis.ai.graphs.Graph;

public class BreadthFirstSearch implements SearchAlgorithm {

	private Deque<Integer> frontier;
	private Set<Integer> visited;
	private Graph graph;
	private Graph tree;
	private Deque<Integer> answerOfNodes;
	private Deque<Connection> answerOfConnections;

	public BreadthFirstSearch(Graph graphToImplement) {
		graph = graphToImplement;
		frontier = new ArrayDeque<Integer>();
		visited = new HashSet<Integer>();
		answerOfNodes = new ArrayDeque<Integer>();
		tree = new Graph();
		
	}
	
	
	@Override
	public void algorithmToImplement(int startNode, int endNode) {
		// TODO Auto-generated method stub

		ArrayList<Connection> connectionsToUse = graph.getConnection(startNode);
		visited.add(startNode);
		for (Connection item: connectionsToUse) {
			int child = item.getDestinationNode();
			
			if (!visited.contains(child) && !frontier.contains(child)) {
				tree.addConnection(startNode, child, 1);
				frontier.addLast(child);
			}
			
		}
		
		if (frontier.contains(endNode) == true) {
			answerOfNodes.push(endNode);
			answerOfNodes.push(startNode);
		}
		else if (!frontier.isEmpty()) {
			algorithmToImplement(frontier.removeFirst(), endNode);
			ArrayList<Connection> childrenOfTree = tree.getConnection(startNode);
			for (Connection item : childrenOfTree) {
				if (item.getDestinationNode() == answerOfNodes.peek()) {
					answerOfNodes.push(startNode);
					answerOfConnections.push(item);
				}
			}
		}
	}

	@Override
	public Integer[] nodesToVisit() {
		// TODO Auto-generated method stub
		return answerOfNodes.toArray(new Integer[0]);
	}


	@Override
	public Connection[] solutionActions() {
		// TODO Auto-generated method stub
		return answerOfConnections.toArray(new Connection[0]);
	}

}
