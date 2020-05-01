package com.natlowis.ai.supervised.knn;

import java.util.ArrayList;

/**
 * An <Code> abstract </code> class which designates how to find the nearest
 * neighbours for the data.
 * 
 * @author low101043
 *
 */
public abstract class KNearestNeighbour {

	protected ArrayList<ArrayList<Double>> inputs;
	protected ArrayList<ArrayList<Double>> normalised;
	protected ArrayList<double[]> D;

	/**
	 * The basic constructor
	 */
	public KNearestNeighbour() {
		;
	}

	// TEST!!!
	/**
	 * This will normalise the inputs and the training data
	 */
	private void normalise() {

		normalised = new ArrayList<ArrayList<Double>>();

		int size = inputs.get(0).size();
		ArrayList<double[]> minMaxValues = new ArrayList<double[]>();

		for (int i = 0; i < size - 1; i++) {

			double[] minMaxValuePerInput = { Double.MAX_VALUE, Double.MIN_VALUE };
			minMaxValues.add(minMaxValuePerInput);
		}

		for (int place = 0; place < inputs.size(); place++) {
			ArrayList<Double> item = inputs.get(place);

			ArrayList<Double> itemsToAdd = new ArrayList<Double>();

			for (int i = 0; i < item.size() - 1; i++) { // This will not normalise the output value (WHICH YOU DON'T
														// NEED TO DO)
				double min = minMaxValues.get(i)[0];
				double max = minMaxValues.get(i)[1];

				double input = item.get(i);
				if (normalised.size() == 0) {

					double secondInput = inputs.get(1).get(i);

					int index = 2;
					while (secondInput == input && index < item.size()) {

						secondInput = inputs.get(1).get(i);
						index++;
					}

					if (input < secondInput) {
						min = input;
						max = secondInput;
					} else if (input > secondInput) {
						max = secondInput;
						min = input;
					} else {
						min = 0;
						max = input;
					}

					minMaxValues.get(i)[0] = min;
					minMaxValues.get(i)[1] = max;
				}

				else if (input > max || input < min) {

					ArrayList<Double> inputsToRenormalise = new ArrayList<Double>();
					ArrayList<Double> itemsRenormalised = new ArrayList<Double>();

					for (int j = 0; j < place; j++) {
						ArrayList<Double> itemInList = inputs.get(j);
						double inputToRenormalise = itemInList.get(i);
						inputsToRenormalise.add(inputToRenormalise);
					}

					// RENORMALISE
					if (input > max) {
						double oldMax = max;
						max = input;

						for (double unnormalisedInput : inputsToRenormalise) {
							double oldValue = min + (oldMax - min) * unnormalisedInput;
							double normalised = (oldValue - min) / (max - min);
							itemsRenormalised.add(normalised);
						}
						minMaxValues.get(i)[1] = max;

					} else if (input < min) {
						double oldMin = min;
						min = input;
						for (double unnormalisedInput : inputsToRenormalise) {
							double oldValue = oldMin + (max - oldMin) * unnormalisedInput;
							double normalised = (oldValue - min) / (max - min);
							itemsRenormalised.add(normalised);
						}
						minMaxValues.get(i)[0] = min;

					}

					for (int placeInNormalised = 0; placeInNormalised < place; placeInNormalised++) {

						ArrayList<Double> listToChange = normalised.remove(placeInNormalised);
						listToChange.remove(i);
						double normalisedAgain = itemsRenormalised.get(placeInNormalised);
						listToChange.add(i, normalisedAgain);
						normalised.add(placeInNormalised, listToChange);
					}
				}

				double normalisedInput = (input - min) / (max - min);
				itemsToAdd.add(normalisedInput);
			}
			itemsToAdd.add(item.get(item.size() - 1));
			normalised.add(itemsToAdd);
		}
	}

	/**
	 * This will work out the correct class or numerical output.
	 * 
	 * @param input        The input data which you have
	 * @param kNeighbours  The number of neighbours used
	 * @param trainingData The training data
	 * @return Either the class which the input is in or the average number. Will
	 *         return null if no majority
	 */
	abstract public Double knn(ArrayList<Double> input, int kNeighbours, ArrayList<ArrayList<Double>> trainingData); // NEED
																														// TO
																														// ADD
																														// input
																														// to
																														// end
																														// (ALSO
																														// ADD
																														// ONE
																														// FIGURE
																														// AT
																														// END)

	/**
	 * This will add the inputs to the normalised list
	 */
	protected void addingToList() { // ASSUMENED NORMALISED AND INPUTS HAVE THE INPUT IN AS WELL
		D = new ArrayList<double[]>(inputs.size() - 1);
		normalise();

		int sizeOfList = normalised.size();
		ArrayList<Double> input = normalised.remove(sizeOfList - 1);

		for (ArrayList<Double> item : normalised) {
			double distance = 0;

			for (int i = 0; i < item.size() - 1; i++) {
				double inputMetric = input.get(i);
				double trainingDataInput = item.get(i);

				double difference = inputMetric - trainingDataInput;
				double squaredDifference = Math.pow(difference, 2.0);

				distance += squaredDifference;
			}
			double euclideanDistance = Math.sqrt(distance);

			double[] neighbourAndClass = { euclideanDistance, item.get(item.size() - 1) };

			if (D.size() == 0) {
				D.add(neighbourAndClass);
			} else {
				int index = binarySearch(euclideanDistance);
				D.add(index, neighbourAndClass);
			}
		}
	}

	/**
	 * Does a binary search on the data to work out where the data should be added
	 * 
	 * @param distance The number which should be added
	 * @return The index where the data should be added
	 */
	private int binarySearch(double distance) {

		int left = 0;
		int right = D.size() - 1;
		int mid;

		if (right == 0) {
			if (distance < D.get(0)[0]) {
				mid = 0;
			} else {
				mid = 1;
			}
		} else {
			mid = -1;
		}
		while (left < right) {
			mid = (left + right) / 2;
			if (distance > D.get(mid)[0]) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}

		return mid;
	}
}
