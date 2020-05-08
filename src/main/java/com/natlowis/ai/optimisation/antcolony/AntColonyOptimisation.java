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
	HashMap<Integer, Integer> indexMap;
	ArrayList<Integer> finalRoute;
	
	private static Logger logger = Logger.getLogger(AntColonyOptimisation.class);
	
	public AntColonyOptimisation(Graph graph) {  //TODO TEST
		this.graph = graph;
	}
	
	public void AntColonyOptimisationAlgorithm(int startNode, int endNode, int epoch, double pheromoneLevel, int numOfAnts) { //TODO need to make sure pheromomelevel is between 0 and 1
		antsArray = new Ant[numOfAnts];
		newPheromoneLevel = new double[graph.getNumberOfNodes()];
		
		indexMap = new HashMap<Integer, Integer>();
		
		Integer[] nodes = graph.getNodes();
		
		for (int i =0; i < nodes.length; i++) {
			int node = nodes[i];
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
	
	private void construct(int startNode, int endNode, int numOfAnts) {
		
		for (int i = 0; i < newPheromoneLevel.length; i++) {
			newPheromoneLevel[i] = 0;
		}
		for (int i = 0; i < numOfAnts; i++) {
			Ant antToAdd = new Ant(i, graph, startNode, endNode);
			antsArray[i] = antToAdd;
			//newPheromoneLevel[i] = 1;  //TODO should be for each NOD
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
	
	private void update(double pheromoneLevel, int endNode) {
		for (Ant ant: antsArray) {
			ArrayList<Integer> route = ant.returnRoute();
			
			if (route.get(route.size() - 1) == endNode) {
				double pheromoneToAdd = 1.0 / route.size();
				for (int node: route) {
					int index = indexMap.get(node);
					
					double pheromone = newPheromoneLevel[index];
					pheromone += pheromoneToAdd;
					newPheromoneLevel[index] = pheromone;
				}
			}
		}
		updateNode(pheromoneLevel);
	}
	
	private void updateNode(double pheromoneLevel) {
		Integer[] nodes = graph.getNodes();
		
		for (int node: nodes) {
			
			ArrayList<Connection> connections = graph.getConnection(node);
			
			for (int i =0; i < connections.size(); i++) {
				Connection connection = connections.get(i);
				double oldPheromone = connection.getSpecial();
				int index = indexMap.get(node);
				
				double newPheromoneToAdd = newPheromoneLevel[index];
				
				double newPheromone = ((1 - pheromoneLevel) * oldPheromone) + newPheromoneToAdd;
				
				graph.setSpecial(connection.getOriginNode(), connection.getDestinationNode(), newPheromone);
			}
			
		}
	}
	
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
		
	public ArrayList<Integer> finalRoute(){
		return finalRoute;
	}
}
