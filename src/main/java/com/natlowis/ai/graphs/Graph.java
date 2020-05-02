package com.natlowis.ai.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph implements GraphInterface {
	
	private Map<Integer, Node> graph;
	
	public Graph() {
		graph = new HashMap<Integer, Node>();
	}

	/**
	 * This one assumes it takes data from a file
	 * @param data
	 */
	public Graph(ArrayList<ArrayList<String>> data) {

		graph = new HashMap<Integer, Node>();
		
		for (ArrayList<String> list : data) {  //For each line in the arraylist will add it as a connection 
			
			addConnection(Integer.parseInt(list.get(0)), Integer.parseInt(list.get(1)),
					Double.parseDouble(list.get(2)));
		}
	}
	
	@Override
	public void addNode(int nodeToAdd) {
		// TODO Auto-generated method stub
		Node newNode = new Node(nodeToAdd);
		graph.put(nodeToAdd, newNode);
		
	}

	@Override
	public void addConnection(int originNode, int destinationNode, double weight) {
		// TODO Auto-generated method stub
		
		Node node = graph.get(originNode);  //Gets the connection if in the graph.  If not there gets null
		
		if (node == null) {  //If the node is not in the graph
			addNode(originNode);  //Adds the node to the graph 
			node= graph.get(originNode);  //Gets the actual edge
		}
		
		node.addConnection(destinationNode, weight);
		
		Node nodeEnd = graph.get(destinationNode);
		
		if (nodeEnd == null) {
			addNode(destinationNode);
		}
	}

	@Override
	public void removeNode(int nodeToRemove) {
		// TODO Auto-generated method stub
		
		Deque<Integer> keys = new ArrayDeque<Integer>();
		Deque<Node> nodes = new ArrayDeque<Node>();
		for (Map.Entry<Integer, Node> entry : graph.entrySet()) {  //Takes each pair of values in the graph
			
			Node node = entry.getValue();
			int key = entry.getKey();
			
			node.removeConnection(nodeToRemove);
			keys.add(key);
			nodes.add(node);
			
		}
		
		for (int i =0 ; i < keys.size(); i++) {
			int key = keys.remove();
			Node node = nodes.remove();
			
			graph.replace(key, node);
		}
		
	}

	@Override
	public Object[][] getConnections() {
		// TODO Auto-generated method stub#
		
	ArrayList<Object[]> connections = new ArrayList<>();  //The object which will have all the connections
		
		for (Map.Entry<Integer, Node> entry : graph.entrySet()) {  //For each pair of values in the hash map

			ArrayList<Connection> connection = entry.getValue().getConnections();
			
			for (Connection edge : connection) //Will add it to the connections
				
				connections.add(new Object[] { edge.getOriginNode(), edge.getDestinationNode(), edge.getWeight() });
		}

		return connections.toArray(new Object[0][0]);
	}

	@Override
	public void removeConnection(int originNode, int destinationNode) {
		// TODO Auto-generated method stub
		Node origin = graph.get(originNode);
		origin.removeConnection(destinationNode);
		
	}

	@Override
	public int getNumberOfNodes() {
		// TODO Auto-generated method stub
		return graph.size();
	}

	@Override
	public ArrayList<Connection> getConnection(int nodeNum) {
		// TODO Auto-generated method stub
		return graph.get(nodeNum).getConnections();
	}
	
	public int getNodeSpecial(int nodeToGet) {
		return graph.get(nodeToGet).getExtraInfo();
	}
	
	public void setNodeSpecial(int nodeToChange, int newInfo) {
		Node node = graph.get(nodeToChange);
		node.setExtraInfo(newInfo);
		graph.replace(nodeToChange, node);
	}

}
