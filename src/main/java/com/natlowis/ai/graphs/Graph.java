package com.natlowis.ai.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
<<<<<<< HEAD
 * Graph which implements {@code GraphInterface}. Will implement a directed
 * Graph data structure
 * 
=======
 * Implements the GraphInterface
>>>>>>> addingGraphs
 * @author low101043
 *
 */
public class Graph implements GraphInterface {
<<<<<<< HEAD

	protected Map<Integer, ArrayList<Connection>> graph; // The hash map which will have the integer to the arraylist of
															// connections

	/**
	 * Default Constructor for Graph Class. Initialises the empty graph
	 */
	public Graph() {
		graph = new HashMap<Integer, ArrayList<Connection>>(); // Initialises an empty hash map
=======
	
	private Map<Integer, Node> graph;
	
	/**
	 * Constructor which assumes no data to add at start
	 */
	public Graph() {
		graph = new HashMap<Integer, Node>();
>>>>>>> addingGraphs
	}

	/**
	 * This one assumes it takes data from a file
	 * 
	 * @param data
	 */
	public Graph(ArrayList<ArrayList<String>> data) {

<<<<<<< HEAD
		for (ArrayList<String> list : data) { // For each line in the arraylist will add it as a connection

=======
		graph = new HashMap<Integer, Node>();
		
		for (ArrayList<String> list : data) {  //For each line in the arraylist will add it as a connection 
			
>>>>>>> addingGraphs
			addConnection(Integer.parseInt(list.get(0)), Integer.parseInt(list.get(1)),
					Double.parseDouble(list.get(2)));
		}
	}
	
	/**
	 * Used if the data has special info for each node
	 * @param dataNodes The nodes to add with the special info
	 * @param dataConnections The connections for the graph
	 */
	public Graph(ArrayList<ArrayList<String>> dataNodes, ArrayList<ArrayList<String>> dataConnections) {

		
		graph = new HashMap<Integer, Node>();
		
		for (ArrayList<String> list: dataNodes) {
			addNode(Integer.parseInt(list.get(0)));
			setNodeSpecial(Integer.parseInt(list.get(0)), Double.parseDouble(list.get(1)));
		}
		
		for (ArrayList<String> list : dataConnections) {  //For each line in the arraylist will add it as a connection 
			
			addConnection(Integer.parseInt(list.get(0)), Integer.parseInt(list.get(1)),
					Double.parseDouble(list.get(2)));
		}
	}
	
	@Override
	public void addNode(int nodeToAdd) {
<<<<<<< HEAD

		ArrayList<Connection> connectionsToAdd = new ArrayList<Connection>(); // Creates an arraylist needed

		graph.put(nodeToAdd, connectionsToAdd); // Adds it to the hashmap
=======
		// TODO Auto-generated method stub
		Node newNode = new Node(nodeToAdd);
		graph.put(nodeToAdd, newNode);
		
>>>>>>> addingGraphs
	}

	@Override
	public void addConnection(int originNode, int destinationNode, double weight) {
<<<<<<< HEAD

		List<Connection> edgeList = graph.get(originNode); // Gets the connection if in the graph. If not there gets
															// null

		if (edgeList == null) { // If the node is not in the graph
			addNode(originNode); // Adds the node to the graph
			edgeList = graph.get(originNode); // Gets the actual edge
		}
		edgeList.add(new Connection(originNode, destinationNode, weight)); // adds a new connection to the set of
																			// connections

		// now add the other node if not there
		edgeList = graph.get(destinationNode);
		if (edgeList == null) {
=======
		// TODO Auto-generated method stub
		
		Node node = graph.get(originNode);  //Gets the connection if in the graph.  If not there gets null
		
		if (node == null) {  //If the node is not in the graph
			addNode(originNode);  //Adds the node to the graph 
			node= graph.get(originNode);  //Gets the actual edge
		}
		
		node.addConnection(destinationNode, weight);
		
		Node nodeEnd = graph.get(destinationNode);
		
		if (nodeEnd == null) {
>>>>>>> addingGraphs
			addNode(destinationNode);
		}
	}

	@Override
	public void removeNode(int nodeToRemove) {
<<<<<<< HEAD

		graph.remove(nodeToRemove); // Removes the node from the graph

		// This will remove all other connections to that node in the graph
		LinkedList<Object[]> dataToRemove = new LinkedList<Object[]>();

		for (Map.Entry<Integer, ArrayList<Connection>> entry : graph.entrySet()) { // Takes each pair of values in the
																					// graph

			int node = entry.getKey(); // Gets the node

			for (Connection edge : entry.getValue()) { // Takes each connection foor that node

				if (edge.getDestinationNode() == nodeToRemove) { // If the destination node is equal to the node to
																	// remove it will be removed

					double weight = edge.getWeight(); // Gets the weigh
					Object[] connectionToRemove = { node, nodeToRemove, weight }; // The array which has the values to
																					// be removed
					dataToRemove.add(connectionToRemove); // Added to the linked list

				}
			}
		}

		int sizeOfLinkedList = dataToRemove.size(); // Finds the size and then goes through the linked list of data
													// items

		for (int i = 0; i < sizeOfLinkedList; i++) {

			Object[] dataToDelete = dataToRemove.remove(); // removes it from the linked list
			int originNode = (int) dataToDelete[0]; // Gets all the data from the array
			int destinationNode = (int) dataToDelete[1];
			double weight = (double) dataToDelete[2];
			removeConnection(originNode, destinationNode, weight); // removes the connection
		}
	}

	/**
	 * This will remove a connection if you know the connections exact distance
	 * 
	 * @param originNode      - The origin node of the connection
	 * @param destinationNode - the destination node of the connection
	 * @param distance        - the weight of the connection
	 */
	public void removeConnection(int originNode, int destinationNode, double distance) {

		// TODO what to do if weight is wrong!

		ArrayList<Connection> dataToLose = graph.get(originNode); // Gets the node to lose
		Connection dataToBeLost = new Connection(originNode, destinationNode, distance); // makes a new connection
		dataToLose.remove(dataToBeLost); // removes the data from the connection
		graph.put(originNode, dataToLose); // adds it to the graph again

	}

	@Override
	public void removeConnection(int originNode, int destinationNode) {

		// TODO assumption only 1 connection between each node. Need to enforce!

		ArrayList<Connection> dataWillBeHere = graph.get(originNode); // Gets the data
		Connection connectionToDelete = null; // Sets up the connection
		for (Connection edge : dataWillBeHere) {

			if (edge.getDestinationNode() == destinationNode) { // If the connection exists say so
				connectionToDelete = edge;
			}

		}

		if (connectionToDelete != null) { // Removes the connection
			dataWillBeHere.remove(connectionToDelete);
		}
=======
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
		
>>>>>>> addingGraphs
	}

	@Override
	public Object[][] getConnections() {
<<<<<<< HEAD

		ArrayList<Object[]> connections = new ArrayList<>(); // The object which will have all the connections

		for (Map.Entry<Integer, ArrayList<Connection>> entry : graph.entrySet()) { // For each pair of values in the
																					// hash map

			for (Connection edge : entry.getValue()) // Will add it to the connections

=======
		// TODO Auto-generated method stub#
		
	ArrayList<Object[]> connections = new ArrayList<>();  //The object which will have all the connections
		
		for (Map.Entry<Integer, Node> entry : graph.entrySet()) {  //For each pair of values in the hash map

			ArrayList<Connection> connection = entry.getValue().getConnections();
			
			for (Connection edge : connection) //Will add it to the connections
				
>>>>>>> addingGraphs
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
	
	/**
	 * Returns the special info for that node
	 * @param nodeToGet The node which has the special info to get
	 * @return the extra info for that node
	 */
	public double getNodeSpecial(int nodeToGet) {
		return graph.get(nodeToGet).getExtraInfo();
	}
	
	/**
	 * A setter which sets the extra info
	 * @param nodeToChange The node to change
	 * @param newInfo The extra info to change
	 */
	public void setNodeSpecial(int nodeToChange, double newInfo) {
		Node node = graph.get(nodeToChange);
		node.setExtraInfo(newInfo);
		graph.replace(nodeToChange, node);
	}
	
	/**
	 * Will return all the node numbers in the graph
	 * @return An integer array which has all the Nodes used
	 */
	public Integer[] getNodes() {
		ArrayList<Integer> nodes = new ArrayList<Integer>();
		for (Map.Entry<Integer, Node> entry : graph.entrySet()) {
			Integer node = entry.getKey();
			nodes.add(node);
		}
		
		
		return nodes.toArray(new Integer[0]);
	}
	
	/**
	 * Will set the special info for a connection
	 * @param originNode The origin node for the connection
	 * @param destinationNode The destination node for the connection
	 * @param newSpecial The new special info needed
	 */
	public void setSpecial(int originNode, int destinationNode, double newSpecial) {
		ArrayList<Connection> connections = this.getConnection(originNode);
		Connection connectionToFind = null;
		
		for (Connection connection: connections) {
			if (connection.getDestinationNode() == destinationNode) {
				connectionToFind = connection;
			}
		}
		connectionToFind.setSpecial(newSpecial);
		
	}
	

}
