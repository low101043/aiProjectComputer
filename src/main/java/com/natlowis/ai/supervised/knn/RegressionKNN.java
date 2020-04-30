package com.natlowis.ai.supervised.knn;

import java.util.ArrayList;
import java.util.List;

public class RegressionKNN extends KNearestNeighbour {

	public RegressionKNN() {
		super();
	}
	
	@Override
	public Double knn(ArrayList<Double> input, int kNeighbours, ArrayList<ArrayList<Double>> trainingData) {
		// TODO Auto-generated method stub
		trainingData.add(input);
		inputs = trainingData;
		addingToList();
		
		List<double[]> firstKNeighbours = D.subList(0, kNeighbours);
		
		double total = 0;
		for (double[] item: firstKNeighbours) {
			total += item[1];
		}
		double average = total/(double)firstKNeighbours.size();
		
		
		
		return average;
	}

}
