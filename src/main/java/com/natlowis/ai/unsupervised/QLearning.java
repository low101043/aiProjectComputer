package com.natlowis.ai.unsupervised;

import com.natlowis.ai.graphs.Connection;
import com.natlowis.ai.graphs.Graph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * This will perform Q-learning a Reinforcement learning algorithm
 * @author low101043
 *
 */
public class QLearning {

	private double[][] qTable;
	private Graph graph;
	private int endState;
	Random rand;

	/**
	 * The Constructor for QLearning.  Takes a graph as a parameter
	 * @param graph the graph to perform Q Learning on
	 */
	public QLearning(Graph graph) {

		this.graph = graph;
		rand = new Random();
	}

	/**
	 * This will perform Q learning.  
	 * @param discountRate The discount rate for the algorithm.  Higher value will mean more emphasis on the future actions
	 * @param learningRate The learning rate for the algorithm.  Higher value will focus on the reward and the next action.  Lower values will focus on what the current Q value is
	 * @param epoch The amount of episodes to do.
	 * @param endState What the destination node for the algorithm is
	 * @return A 2D array which is the final Q table with the above variables and the given graph
	 */
	public double[][] qLearning(double discountRate, double learningRate, int epoch, int endState) {
		
		this.endState = endState;

		qTable = new double[graph.getNumberOfNodes()][graph.getNumberOfNodes()];

		for (int i = 0; i < epoch; i++) {

			int stateInt = rand.nextInt(graph.getNumberOfNodes());

			do {
				ArrayList<Connection> data = graph.getConnection(stateInt);
				int nextActionIndex = rand.nextInt(data.size());
				Connection nextAction = data.get(nextActionIndex);
				int reward = nextAction.getWeight();

				int nextState = nextAction.getDestinationNode();
				ArrayList<Connection> nextActions = graph.getConnection(nextState);
				double nextQValueFinal = Double.NEGATIVE_INFINITY;

				for (Connection nextStates : nextActions) {
					double nextQValue = qTable[nextStates.getOriginNode()][nextStates.getDestinationNode()];
					if (nextQValue > nextQValueFinal) {
						nextQValueFinal = nextQValue;
					}
				}

				double oldQValue = qTable[stateInt][nextState];
				double newQValue = ((1 - learningRate) * oldQValue)
						+ (learningRate * (reward + (discountRate * nextQValueFinal)));
				qTable[stateInt][nextState] = newQValue;

				stateInt = nextState;

			} while (endState != stateInt);
		}

		return qTable;

	}
	
	/**
	 * This will work out the final solution for the graph from the stated startNode and what has been given as the end state
	 * @param startNode The node to start from
	 * @return An array of which nodes to go to
	 */
	public Integer[] nodesToFinish(int startNode) {
		ArrayList<Integer> finalList = new ArrayList<Integer>();
		int currentNode = startNode;
		
		while (currentNode != endState) {
			double[] nextValues = qTable[currentNode];
			int nextNode = 0;
			
			for (int index = 0; index< nextValues.length; index++) {
				if (nextValues[nextNode] < nextValues[index]) {
					nextNode = index;
				}
			}
			finalList.add(nextNode);
			currentNode = nextNode;
		}
		
		return finalList.toArray(new Integer[0]);
		
	}

}
