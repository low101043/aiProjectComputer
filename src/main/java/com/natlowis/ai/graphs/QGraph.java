package com.natlowis.ai.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class QGraph implements GraphInterface {

	private HashMap<Integer, ArrayList<QConnection>> graph;

	public QGraph() {
		super();
		graph = new HashMap<Integer, ArrayList<QConnection>>();
	}

	@Override
	public void addNode(int nodeToAdd) {
		ArrayList<QConnection> connectionsToAdd = new ArrayList<QConnection>();

		graph.put(nodeToAdd, connectionsToAdd);
	}

	@Override
	public void addConnection(int originNode, int destinationNode, int weight) {
		List<QConnection> edgeList = graph.get(originNode);
		if (edgeList == null) {
			addNode(originNode);
			edgeList = graph.get(originNode);
		}
		edgeList.add(new QConnection(originNode, destinationNode, weight, 0));

		// now add the other node
		edgeList = graph.get(destinationNode);
		if (edgeList == null) {
			addNode(destinationNode);
		}

	}

	public void addConnection(int originNode, int destinationNode, int weight, int reward) {
		List<QConnection> edgeList = graph.get(originNode);
		if (edgeList == null) {
			addNode(originNode);
			edgeList = graph.get(originNode);
		}
		edgeList.add(new QConnection(originNode, destinationNode, weight, reward));

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

		for (Map.Entry<Integer, ArrayList<QConnection>> entry : graph.entrySet()) {
			int node = entry.getKey();
			for (QConnection edge : entry.getValue()) {
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

			ArrayList<QConnection> dataToLose = graph.get(originNode);
			Connection dataToBeLost = new Connection(originNode, destinationNode, weight);
			dataToLose.remove(dataToBeLost);
			graph.put(originNode, dataToLose);

		}

	}

	@Override
	public int[][] getConnections() {
		ArrayList<int[]> connections = new ArrayList<>();
		for (Map.Entry<Integer, ArrayList<QConnection>> entry : graph.entrySet()) {

			for (Connection edge : entry.getValue())
				connections.add(new int[] { edge.getOriginNode(), edge.getDestinationNode(), edge.getWeight(),
						edge.getWeight() });
		}

		return connections.toArray(new int[0][0]);
	}

	@Override
	public void removeConnection(int originNode, int destinationNode) {
		ArrayList<QConnection> dataWillBeHere = graph.get(originNode);
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
	public int getNumberOfNodes() {
		return graph.size();
	}

	public ArrayList<Connection> getConnection(int nodeNum) {
		// return graph.get(nodeNum);
		return null;
	}
}
