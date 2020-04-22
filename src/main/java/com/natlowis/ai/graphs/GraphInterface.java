package com.natlowis.ai.graphs;

import java.util.ArrayList;

/**
 * This interface will specify the methods a graph will have.
 * 
 * @author low101043
 */
public interface GraphInterface {

	/**
	 * This will add an unconnected Node to the graph. It will have an empty Array
	 * List of Connections
	 * 
	 * @param nodeToAdd - This is the node to add
	 */
	public void addNode(int nodeToAdd);

	/**
	 * This will add a connection from the origin node to the destination node with
	 * the corresponding weight. If either node is not in graph it will be added to
	 * the graph
	 * 
	 * @param originNode      - The node to start the connection from
	 * @param destinationNode - The node the connection goes to
	 * @param weight          - The weight of the node
	 */
	public void addConnection(int originNode, int destinationNode, double weight);

	/**
	 * This will remove the node from the graph and any connections to that node
	 * 
	 * @param nodeToRemove - The node to remove from the graph
	 */
	public void removeNode(int nodeToRemove);

	/**
	 * This will get all the connections in the form of {originNode,
	 * destinationNode, weight}. This is then put in its own 2D array.
	 * 
	 * @return 2D array of all connections in the graph
	 */
	public Object[][] getConnections();

	/**
	 * This will remove the connection between the originNode and the
	 * destinationNode
	 * 
	 * @param originNode      - The originNode of the connection to remove
	 * @param destinationNode - The destinationNode of the connection to remove
	 * 
	 * 
	 */
	public void removeConnection(int originNode, int destinationNode);

	/**
	 * This will get the number of nodes which are in the graph - Like .size()
	 * 
	 * @return - The number of nodes in the graph as an integer
	 */
	public int getNumberOfNodes();

	/**
	 * This will get all the connections fir that node
	 * 
	 * @param nodeNum - The node to get the connections for
	 * @return An ArrayList of Connections of the connections for that node
	 */
	public ArrayList<Connection> getConnection(int nodeNum);

}
