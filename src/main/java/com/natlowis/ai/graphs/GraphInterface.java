package com.natlowis.ai.graphs;

import java.util.ArrayList;

public interface GraphInterface {

	public void addNode(int nodeToAdd);

	public void addConnection(int originNode, int destinationNode, int weight);

	public void removeNode(int nodeToRemove);

	public int[][] getConnections();

	public void removeConnection(int originNode, int destinationNode);

	public int getNumberOfNodes();

	public ArrayList<Connection> getConnection(int nodeNum);

}
