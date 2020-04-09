package com.natlowis.ai.search.uninformed;

import com.natlowis.ai.graphs.Connection;

public interface SearchAlgorithm {

	public void algorithmToImplement(int startNode, int endNode);

	public Integer[] nodesToVisit();
	
	public Connection[] solutionActions();

}
