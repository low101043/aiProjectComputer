package com.natlowis.ai.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Graph which implemnts {@code GraphInterface}. 
 * Will implement a directed Graph data structure
 * @author low101043
 *
 */
public class Graph implements GraphInterface {

	protected Map<Integer, ArrayList<Connection>> graph;

	/**
	 * Default Constructor for Graph Class.  Initialises the hash map needed
	 */
	public Graph() {
		graph = new HashMap<Integer, ArrayList<Connection>>();
	}
	
	@Override
	public void addNode(int nodeToAdd) {
		ArrayList<Connection> connectionsToAdd = new ArrayList<Connection>();

		graph.put(nodeToAdd, connectionsToAdd);
	}

	@Override
	public void addConnection(int originNode, int destinationNode, int weight) {

		List<Connection> edgeList = graph.get(originNode);
		if (edgeList == null) {
			addNode(originNode);
			edgeList = graph.get(originNode);
		}
		edgeList.add(new Connection(originNode, destinationNode, weight));

		// now add the other node
		edgeList = graph.get(destinationNode);
		if (edgeList == null) {
			addNode(destinationNode);
		}

	}

	@Override
	public void removeNode(int nodeToRemove) {

		graph.remove(nodeToRemove);
		LinkedList<int[]> dataToRemove = new LinkedList<int[]>();

		for (Map.Entry<Integer, ArrayList<Connection>> entry : graph.entrySet()) {
			int node = entry.getKey();
			for (Connection edge : entry.getValue()) {
				if (edge.getDestinationNode() == nodeToRemove) {
					int weight = edge.getWeight();
					int[] connectionToRemove = { node, nodeToRemove, weight };
					dataToRemove.add(connectionToRemove);

				}
			}
		}

		int sizeOfLinkedList = dataToRemove.size();

		for (int i = 0; i < sizeOfLinkedList; i++) {
			int[] dataToDelete = dataToRemove.remove();
			int originNode = dataToDelete[0];
			int destinationNode = dataToDelete[1];
			int weight = dataToDelete[2];
			removeConnection(originNode, destinationNode, weight);
		}
	}

	/**
	 * This will remove a connection if you know the connections exact distance
	 * @param originNode - The origin node of the connection
	 * @param destinationNode - the destination node of the connection
	 * @param distance - the weight of the connection
	 */
	public void removeConnection(int originNode, int destinationNode, int distance) {

		ArrayList<Connection> dataToLose = graph.get(originNode);
		Connection dataToBeLost = new Connection(originNode, destinationNode, distance);
		dataToLose.remove(dataToBeLost);
		graph.put(originNode, dataToLose);

	}

	@Override
	public void removeConnection(int originNode, int destinationNode) {

		ArrayList<Connection> dataWillBeHere = graph.get(originNode);
		Connection connectionToDelete = null;
		for (Connection edge : dataWillBeHere) {
			if (edge.getDestinationNode() == destinationNode) {
				connectionToDelete = edge;
			}

		}
		if (connectionToDelete != null) {
			dataWillBeHere.remove(connectionToDelete);
		}
	}

	@Override
	public int[][] getConnections() {
		ArrayList<int[]> connections = new ArrayList<>();
		for (Map.Entry<Integer, ArrayList<Connection>> entry : graph.entrySet()) {

			for (Connection edge : entry.getValue())
				connections.add(new int[] { edge.getOriginNode(), edge.getDestinationNode(), edge.getWeight() });
		}

		return connections.toArray(new int[0][0]);
	}

	@Override
	public int getNumberOfNodes() {
		return graph.size();
	}

	@Override
	public ArrayList<Connection> getConnection(int nodeNum) {
		return graph.get(nodeNum);
	}

}
