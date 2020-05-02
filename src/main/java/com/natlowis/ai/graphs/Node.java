package com.natlowis.ai.graphs;

import java.util.ArrayList;

public class Node {

	private int nodeNum;
	private int extraInfo;
	private ArrayList<Connection> connections;
	
	public Node(int num, int info) {
		connections = new ArrayList<Connection>();
		nodeNum = num;
		extraInfo = info;
	}
	
	public Node(int num) {
		nodeNum = num;
		extraInfo = 0;
		connections = new ArrayList<Connection>();
	}
	
	public int getNode() {
		return nodeNum;
	}
	
	public int getExtraInfo() {
		return extraInfo;
	}
	
	public void setExtraInfo(int newInfo) {
		extraInfo = newInfo;
	}
	
	public void addConnection(int destinationNode, double weight) {
		
		connections.add(new Connection(nodeNum, destinationNode, weight));
	}
	
	public void removeConnection(int destinationNode) {
	
		//TODO assumption only 1 connection between each node.  Need to enforce!
		
		Connection connectionToDelete = null;  //Sets up the connection
		for (Connection edge : connections) {
			
			if (edge.getDestinationNode() == destinationNode) {  //If the connection exists say so
				connectionToDelete = edge;
			}

		}
		
		if (connectionToDelete != null) {  //Removes the connection
			connections.remove(connectionToDelete);
		}
	}
	
	public ArrayList<Connection> getConnections(){
		return connections;
	}
}
