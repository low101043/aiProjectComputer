package com.natlowis.ai.search.uninformed;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import com.natlowis.ai.graphs.Connection;
import com.natlowis.ai.graphs.Graph;

/**
 * This will implement the Depth First Search
 * 
 * @author low101043
 *
 */
public class DepthFirstSearch implements SearchAlgorithm {

	private Deque<Integer> frontier;
	private Set<Integer> visited;
	private Graph graph;
	private Graph tree;
	private Deque<Integer> answerOfNodes;
	private Deque<Connection> answerOfConnections;

	/**
	 * This will construct the object. Needs a graph to be made
	 * 
	 * @param graphToImplement The graph to do the search on.
	 */
	public DepthFirstSearch(Graph graphToImplement) {
		graph = graphToImplement;
		frontier = new ArrayDeque<Integer>();
		visited = new HashSet<Integer>();
		answerOfNodes = new ArrayDeque<Integer>();
		tree = new Graph();

	}

	@Override
	public void algorithmToImplement(int startNode, int endNode) {
		// TODO Auto-generated method stub

		if (startNode == endNode) {
			answerOfNodes.add(startNode);
		} else {

			ArrayList<Connection> connections = graph.getConnection(startNode);
			visited.add(startNode);
			for (int i = connections.size() - 1; i >= 0; i--) {
				Connection connect = connections.get(i);
				int child = connect.getDestinationNode();
				if (!visited.contains(child) && !frontier.contains(child)) {
					tree.addConnection(startNode, child, 1);
					frontier.push(child);
				}
			}

			if (!frontier.isEmpty()) {
				algorithmToImplement(frontier.pop(), endNode);
			}

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
