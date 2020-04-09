package com.natlowis.ai.graphs;

public class QConnection extends Connection {

	private int reward;

	public QConnection(int node1, int node2, int weight, int reward) {
		super(node1, node2, weight);
		this.reward = reward;
	}

	public void setReward(int newReward) {
		reward = newReward;
	}

	public int getReward() {
		return reward;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + reward;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj) == true) {
			if (this == obj) {
				return true;
			}
			if (!super.equals(obj)) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			QConnection other = (QConnection) obj;
			if (reward != other.reward) {
				return false;
			}
			return true;
		}
		return false;

	}

}
