package com.natlowis.ai.graphs;

public class Connection {

	protected int originNode;
	protected int destinationNode;
	protected int weight;

	public Connection(int node1, int node2, int weight) {
		this.originNode = node1;
		this.destinationNode = node2;
		this.weight = weight;

	}

	public void changeWeight(int newWeight) {
		weight = newWeight;
	}

	public int getOriginNode() {
		return originNode;
	}

	public int getDestinationNode() {
		return destinationNode;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + destinationNode;
		result = prime * result + originNode;
		result = prime * result + weight;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Connection other = (Connection) obj;
		if (destinationNode != other.destinationNode) {
			return false;
		}
		if (originNode != other.originNode) {
			return false;
		}
		if (weight != other.weight) {
			return false;
		}
		return true;
	}

}
