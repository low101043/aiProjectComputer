package com.natlowis.ai.unsupervised;

import com.natlowis.ai.graphs.Connection;
import com.natlowis.ai.graphs.Graph;
import com.natlowis.ai.graphs.QConnection;
import com.natlowis.ai.graphs.QGraph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class QLearning {

	private double[][] qTable;
	private double discountRate;
	private double learningRate;
	private Graph graph;
	private int iterations;
	Random rand;

	public QLearning(Graph graph) {

		this.graph = graph;
		rand = new Random();
	}

	public double[][] qlearning(double discountRate, double learningRate, int epoch, int endState) {

		this.discountRate = discountRate;
		this.learningRate = learningRate;
		iterations = epoch;

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

}
