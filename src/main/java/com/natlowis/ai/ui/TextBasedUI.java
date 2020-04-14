package com.natlowis.ai.ui;

import com.natlowis.ai.supervised.regression.*;
import com.natlowis.ai.unsupervised.QLearning;
import com.natlowis.ai.graphs.*;
import com.natlowis.ai.search.uninformed.BreadthFirstSearch;
import com.natlowis.ai.search.uninformed.DepthFirstSearch;
import com.natlowis.ai.search.uninformed.SearchAlgorithm;

import org.apache.log4j.Logger;

/**
 * This is used mostly for testing purposes
 * @author low101043
 *
 */
public class TextBasedUI {

	// TODO Comments for Code.  GUI! (So get JavaFX working).  Final Regression.  See if all methods meld together

	private static final Logger logger = Logger.getLogger(TextBasedUI.class);

	
	public static void main(String[] args) {

		System.out.println("This will be the Command Line UI until I sort out JavaFX");
		logger.trace("Does This work");

		Regression a = new LogisticRegressionUnivariate();
		
		a.gradientDescent(1000000, 0.0001, 2);
		a.checkFunction();
	}

}
