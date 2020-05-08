package com.natlowis.ai.optimisation.antcolony;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import org.apache.log4j.Logger;

import com.natlowis.ai.graphs.Connection;
import com.natlowis.ai.graphs.Graph;
import com.natlowis.ai.search.informed.AStar;

public class Ant extends Thread {// implements Runnable {

	
	private static  Logger logger = Logger.getLogger(Ant.class);
	private int id;
	private Graph graph;
	private ArrayList<Integer> route;
	private int currentNode;
	private int endNode;
	private Random rand;
	
	/**
	 * The Constructor which sets up the Ant
	 * @param id Unique Id
	 * @param graph The graph to be traversed
	 * @param startNode The start node 
	 * @param endNode The end Node
	 */
	public Ant(int id, Graph graph, int startNode, int endNode) {
		this.id = id;
		this.graph = graph;
		this.currentNode = startNode;
		this.endNode = endNode;

		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		route = new ArrayList<Integer>();
		construct();
	}
	
	/**
	 * Will construct the route to take. 
	 */
	private void construct() {
		while (currentNode != endNode) {
			int nextNode = chooseNext();
			route.add(nextNode);
			currentNode = nextNode;
		}
		eliminateLoops();
		
		//TODO Kill ants if take too long
	}
	
	/**
	 * Chooses the next node to go to probabilistically.   
	 * @return the next node to go to
	 */
	private int chooseNext() {
		
		ArrayList<Connection> connections = graph.getConnection(currentNode);
		
		rand = new Random();
		
		double denominator = 0;
		for (Connection connection: connections) {

			double pheromone = connection.getSpecial();
			denominator += pheromone;
		}
		
		double randomNumber = rand.nextDouble();
		
		int destination = -1;  			//TODO THIS IS WRONG
		double finalProbability = 0;
		for (Connection connection: connections) {
			int currentDestination = connection.getDestinationNode();
			double pheromone = connection.getSpecial();
			double upperProbability = (pheromone / denominator) + finalProbability;
			if (randomNumber <= upperProbability && randomNumber >= finalProbability) {
				destination = currentDestination;
				
			}
			finalProbability = upperProbability;
		}
		
		return destination;
	}
	
	/**
	 * Removes loops in the route chosen
	 */
	private void eliminateLoops() {
		boolean goneThroughWholeList = false;
		
		while (!goneThroughWholeList) {
			
			boolean foundData = false;
			int startPoint = -1;
			int finalPoint = -1;
			
			for (Integer node : route) {
				int firstPoint = route.indexOf(node);
				int lastIndexOfData = -1;
				boolean found = false;
				
				for (int i = route.size() -1; i > firstPoint; i--) {
					int currentNode = route.get(i);
					
					if (!found && node == currentNode) {
						lastIndexOfData = i;
						found = true;
					}
				}
				
				if (lastIndexOfData != -1 && !foundData && lastIndexOfData != firstPoint) {
					startPoint = firstPoint;
					finalPoint = lastIndexOfData;
					foundData = true;
				}
			}
			
			if (foundData == true) {
				ArrayList<Integer> firstHalf = new ArrayList<Integer>(route.subList(0, startPoint));
				ArrayList<Integer> secondHalf = new ArrayList<Integer>(route.subList(finalPoint, route.size()));
				
				route = firstHalf;
				route.addAll(secondHalf);
			}
			else {
				goneThroughWholeList = true;
			}
		}
	}
	
	/**
	 * Returns the final route
	 * @return An {@code ArrayList of Integers} which shows the route taken
	 */
	public ArrayList<Integer> returnRoute(){
		return route;
	}

}
