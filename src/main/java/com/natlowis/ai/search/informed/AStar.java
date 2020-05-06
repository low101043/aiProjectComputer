package com.natlowis.ai.search.informed;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.natlowis.ai.graphs.Connection;
import com.natlowis.ai.graphs.Graph;
import com.natlowis.ai.search.*;
import com.natlowis.ai.ui.gui.MainPage;

public class AStar implements SearchAlgorithm {
	
	private ArrayList<double[]> frontier;  //This will hold the nodes to be visited
	private Set<Integer> visited;  //This will hold the nodes which has been visited
	private Graph graph;  //This will hold the graph being processed
	private Graph tree;  //This will hold the tree which is created by the algorithm
	private Deque<Integer> answerOfNodes;  //This hold the solution of which nodes to visit
	private Deque<Connection> answerOfConnections;  //This holds the connections which need to be visited
	
	private static Logger logger = Logger.getLogger(AStar.class);
	
	/**
	 * The constructor which allows you to make the object.
	 * @param graph
	 */
	public AStar(Graph graph) {
		this.graph = graph;
		//Initialises all the needed variables
				frontier = new ArrayList<double[]>();
				visited = new HashSet<Integer>();
				answerOfNodes = new ArrayDeque<Integer>();
				answerOfConnections = new ArrayDeque<Connection>();
				tree = new Graph();

	}

	@Override
	public void algorithmToImplement(int currentNode, int endNode) {
		boolean finished = false;
		double oldFNode = 0;
		ArrayList<int[]> connectionsFinal = new ArrayList<int[]>();
		
		while (!finished) {
			ArrayList<Connection> connections = graph.getConnection(currentNode);
			
			visited.add(currentNode);
			
			for (int i =0; i< connections.size(); i++) {
				
				Connection connection = connections.get(i);
				int child = connection.getDestinationNode();
				double destinationInfo = graph.getNodeSpecial(child);
				double weight = connection.getWeight() + oldFNode;
				
				double fNode = destinationInfo + weight;
				
				if (!visited.contains(child)) {
					if (search(child) == -1) {
						double[] toAdd = {child, fNode, weight, currentNode};
						frontier.add(toAdd);
						
					}
					else {
						int index = search(child);
						double[] oldInfo = frontier.get(index);
						
						if (oldInfo[1] > fNode) {
							tree.removeNode(child);
							double[] toAdd = {child, fNode, weight, currentNode};
							frontier.remove(index);
							frontier.add(toAdd);
							
						}
					}
				}
			}
			
			sort();
		
			if (!frontier.isEmpty()) {
				double[] nextNode = frontier.remove(0);
				int[] connectionHere = {(int)nextNode[3], (int)nextNode[0]};
				connectionsFinal.add(connectionHere);
				if ((int)nextNode[0] == endNode) {
					finished = true;
					currentNode = (int)nextNode[0];
					
				}
				else {
					oldFNode = nextNode[2];
					currentNode = (int)nextNode[0];
				}
			}
			else {
				finished = true;
			}
					
		}
		
		if (currentNode == endNode) {
			answerOfNodes.push(endNode);
			
			for (int i = connectionsFinal.size() -1; i >= 0; i--) {
				if (connectionsFinal.get(i)[1] == currentNode ) {
					/*if (currentNode == endNode) {
						currentNode = connectionsFinal.get(i)[0];;
					}
					else {*/
					answerOfNodes.push(connectionsFinal.get(i)[0]);
					currentNode = connectionsFinal.get(i)[0];//}
				}
			}
		}
	}
	
	

	@Override
	public Integer[] nodesToVisit() {
		
		return answerOfNodes.toArray(new Integer[0]);
	}

	@Override
	public Connection[] solutionActions() {
		
		Integer[] answer = nodesToVisit();
		
		logger.debug(answer.length);
		
		for (int i = 1; i < answer.length; i++) {
			int originNode = answer[i -1];
			int endNode = answer[i];
			
			ArrayList<Connection> connection = graph.getConnection(originNode);
			
			Connection finalConnection = null;
			for (Connection connectionMaybe: connection) {
				if (originNode == connectionMaybe.getOriginNode() && connectionMaybe.getDestinationNode() == endNode) {
					finalConnection = connectionMaybe;
				}
			}
			
			answerOfConnections.add(finalConnection);
			
		}
		
		return answerOfConnections.toArray(new Connection[0]);
	}
	
	/**
	 * Performs a sort on the frontier
	 */
	private void sort() {
			
		frontier = mergesort(frontier, 0, frontier.size() - 1);
	}
	
	/**
	 * Performs a merge sort on the data
	 * @param data The data to be sorted
	 * @param left The left index
	 * @param right the right index
	 * @return the data sorted
	 */
	private ArrayList<double[]> mergesort(ArrayList<double[]> data, int left, int right){
		if (left < right) {
			int mid = (left + right) / 2;
			data = mergesort(data, left, mid);
			data = mergesort(data, mid + 1, right);
			data = merge(data, left, mid, right);
			
		}
		return data;
	}

	/**
	 * Merges the data in mergesort
	 * @param a The data to be merged
	 * @param left the left index
	 * @param mid the middle of the data
	 * @param right The right index 
	 * @return the data sorted
	 */
	private ArrayList<double[]> merge(ArrayList<double[]> a, int left, int mid, int right) {
		
		double[][] b = new double[right - left + 1][2];
		int bcount = 0;
		int lcount = left;
		int rcount = mid+1;
		
		while ((lcount <= mid) && (rcount <= right)) {
			if (a.get(lcount)[1] <= a.get(rcount)[1]) {
				b[bcount] = a.get(lcount);
				bcount++;
				lcount++;
			}
			else {
				b[bcount] = a.get(rcount);
				bcount++;
				rcount++;
			}
		}
		
		if (lcount > mid) {
			while (rcount <= right) {
				b[bcount] = a.get(rcount);
				bcount++;
				rcount++;
			}
		}
		else {
			while (lcount <= mid) {
				b[bcount] = a.get(lcount);
				bcount++;
				lcount++;
			}
		}
		
		for (bcount = 0; bcount<right - left + 1; bcount++) {
			a.remove(left+bcount);
			a.add(left+bcount, b[bcount]);
		}
		
		return a;
	}
	
	/**
	 * Performs a linear search on the data
	 * @param node The node to find
	 * @return The index if the node is there or -1 if it is not
	 */
	private int search(int node) {
		
		for (int i = 0; i < frontier.size(); i++) {
			if ((int)frontier.get(i)[0] == node) {
				return i;
			}
		}
		return -1;
	}

}
