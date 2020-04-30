package com.natlowis.ai.supervised.knn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassificationKNN extends KNearestNeighbour {

	public ClassificationKNN() {
		super();
	}
	
	
	@Override
	public Double knn(ArrayList<Double> input, int kNeighbours, ArrayList<ArrayList<Double>> trainingData) {
		// TODO Auto-generated method stub
		
		trainingData.add(input);
		inputs = trainingData;
		addingToList();
		
		List<double[]> firstKNeighbours = D.subList(0, kNeighbours);
		
		HashMap<Double, Integer> findMax = new HashMap<Double, Integer>();
		
		for (double[] item: firstKNeighbours) {
			double classIn = item[1];
			Integer total = findMax.get(classIn);
			if (total == null) {
				findMax.put(classIn, 1);
			}
			else {
				total += 1;
				findMax.put(classIn, total);
			}
		}
		
		int max = -1;
		double classToOutput = -5;
		boolean clash = false;
		for (Map.Entry<Double, Integer> entry: findMax.entrySet()) {
			if (entry.getValue() > max) {
				max = entry.getValue();
				classToOutput = entry.getKey();
				clash = false;
			}
			else if (entry.getValue() == max) {
				clash = true;
			}
		}
		
		if (clash == true) {
			return null;
		}
		else {
			return classToOutput;
		}
	}

}
