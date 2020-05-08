package com.natlowis.ai.optimisation.antcolony;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.apache.log4j.Logger;

import com.natlowis.ai.graphs.Connection;
import com.natlowis.ai.graphs.Graph;
import com.natlowis.ai.ui.TextBasedUI;

public class AntColonyOptimisation {  //TODO Complete
	

	Graph graph;
	Ant[] antsArray;
	double[] newPheromoneLevel;
	HashMap<int[], Integer> indexMap;
	ArrayList<Integer> finalRoute;
	
	private static Logger logger = Logger.getLogger(AntColonyOptimisation.class);
	
	/**
	 * The Constructor which makes the object
	 * @param graph The graph to be traversed
	 */
	public AntColonyOptimisation(Graph graph) {  //TODO TEST
		this.graph = graph;
	}
	
	/**
	 * The actual algorithm for Ant Colony Optimisation
	 * @param startNode The start node
	 * @param endNode the end node
	 * @param epoch the number of times you want it repeated
	 * @param pheromoneLevel the evaporation level
	 * @param numOfAnts The number of ants to use
	 */
	public void AntColonyOptimisationAlgorithm(int startNode, int endNode, int epoch, double pheromoneLevel, int numOfAnts) { //TODO need to make sure pheromomelevel is between 0 and 1
		antsArray = new Ant[numOfAnts];
		newPheromoneLevel = new double[graph.getConnections().length];
		
		indexMap = new HashMap<int[], Integer>();
		
		Object[][] connections = graph.getConnections();
		
		for (int i =0; i < connections.length; i++) {
			Object[] connection = connections[i];
			int origin = (int)connection[0];
			int destination = (int)connection[1];
			
			int[] node = {origin, destination};
			
			indexMap.put(node, i);
		}
		
		for (int i =0; i < epoch; i++) {
			construct(startNode, endNode, numOfAnts);
			update(pheromoneLevel, endNode);
		}
		
		int currentNode = startNode;
		finalRoute = new ArrayList<Integer>();
		finalRoute.add(currentNode);
		
		while (currentNode != endNode) {
			int nextNode = chooseNext(currentNode);
			finalRoute.add(nextNode);
			currentNode = nextNode;
		}
		
		
	}
	
	/**
	 * Constructs the routes which the ants have traversed
	 * @param startNode The start node
	 * @param endNode The end node
	 * @param numOfAnts The number of ants to use
	 */
	private void construct(int startNode, int endNode, int numOfAnts) {
		
		for (int i = 0; i < newPheromoneLevel.length; i++) {
			newPheromoneLevel[i] = 0;
		}
		for (int i = 0; i < numOfAnts; i++) {
			Ant antToAdd = new Ant(i, graph, startNode, endNode);
			antsArray[i] = antToAdd;
			//newPheromoneLevel[i] = 1;  
			antToAdd.start();
			
		}
		
		for (int i = 0; i < numOfAnts; i++) {
			Thread threadToWait = antsArray[i];
			
			try {
				threadToWait.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		logger.trace("Finished");
		
	}
	
	/**
	 * Will update the pheromone levels
	 * @param pheromoneLevel The evaporation rate
	 * @param endNode the end node
	 */
	private void update(double pheromoneLevel, int endNode) {
		for (Ant ant: antsArray) {
			ArrayList<Integer> route = ant.returnRoute();
			
			if (route.get(route.size() - 1) == endNode) {
				double pheromoneToAdd = 1.0 / route.size();
				for (int i = 0; i < route.size() - 2; i++) {
					
					int[] node = {route.get(i), route.get(i + 1)};
					int index = indexMap.get(node);
					
					double pheromone = newPheromoneLevel[index];
					pheromone += pheromoneToAdd;
					newPheromoneLevel[index] = pheromone;
				}
			}
		}
		updateNode(pheromoneLevel);
		
		for (int i =0; i< antsArray.length; i++) {
			antsArray[i] = null;
		}
		
		for (int i =0; i < newPheromoneLevel.length; i++) {
			newPheromoneLevel[i] = 0;
		}
	}
	
	/**
	 * Updates specific connection pheromone levels
	 * @param pheromoneLevel The evapouration rate
	 */
	private void updateNode(double pheromoneLevel) {
		Integer[] nodes = graph.getNodes();
		
		for (int node: nodes) {
			
			ArrayList<Connection> connections = graph.getConnection(node);
			
			for (int i =0; i < connections.size(); i++) {
				Connection connection = connections.get(i);
				int destination = connection.getDestinationNode();
				
				int[] key = {node, destination};
				
				double oldPheromone = connection.getSpecial();
				int index = indexMap.get(key);
				
				double newPheromoneToAdd = newPheromoneLevel[index];
				
				double newPheromone = ((1 - pheromoneLevel) * oldPheromone) + newPheromoneToAdd;
				
				graph.setSpecial(connection.getOriginNode(), connection.getDestinationNode(), newPheromone);
			}
			
		}
	}
	
	/**
	 * Will choose the next node to go to 
	 * @param currentNode The current node the algorithm is at
	 * @return The next node to go to
	 */
	private int chooseNext(int currentNode) {
		
		ArrayList<Connection> connections = graph.getConnection(currentNode);

		
		int destination = -1;  			
		double finalProbability = 0;
		for (Connection connection: connections) {
			int currentDestination = connection.getDestinationNode();
			double pheromone = connection.getSpecial();

			if (pheromone > finalProbability) {
				destination = currentDestination;
				finalProbability = pheromone;
			}

		}
		
		return destination;
		
	}
		
	/**
	 * Returns the final route
	 * @return An ArrayList which is the final route
	 */
	public ArrayList<Integer> finalRoute(){
		return finalRoute;
	}
}
