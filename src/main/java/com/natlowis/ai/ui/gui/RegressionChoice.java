package com.natlowis.ai.ui.gui;

import com.natlowis.ai.supervised.regression.LinearRegressionMultivariate;
import com.natlowis.ai.supervised.regression.LinearRegressionUnivariate;
import com.natlowis.ai.supervised.regression.LogisticRegressionMultivariate;
import com.natlowis.ai.supervised.regression.LogisticRegressionUnivariate;
import com.natlowis.ai.supervised.regression.Regression;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RegressionChoice extends Application implements Window {

	private Button backHome;
	private TextField alpha;
	private TextField epoch;
	private TextField polynomialOrVariables;
	private Button clear;
	private Button submitMulti;
	private Button submitUni;
	private Button submitLogisticMulti;
	private Button submitLogisticUni;
	private Label label;

	public RegressionChoice(ScreenController sceneChooser) {
		BorderPane root = new BorderPane();
		backHome = new Button("Go back home");
		root.setLeft(backHome);

		// Creating a GridPane container
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		// Defining the Name text field
		alpha = new TextField();
		alpha.setPromptText("Enter your alpha value");
		alpha.setPrefColumnCount(10);
		alpha.getText();
		GridPane.setConstraints(alpha, 0, 0);
		grid.getChildren().add(alpha);

		// Defining the Last Name text field
		epoch = new TextField();
		epoch.setPromptText("Enter the number of iterations");
		GridPane.setConstraints(epoch, 0, 1);
		grid.getChildren().add(epoch);

		// Defining the Comment text field
		polynomialOrVariables = new TextField();
		polynomialOrVariables.setPrefColumnCount(15);
		polynomialOrVariables.setPromptText("Enter the highest polynomial or the number of variables");
		GridPane.setConstraints(polynomialOrVariables, 0, 2);
		grid.getChildren().add(polynomialOrVariables);

		HBox choices = new HBox();

		// Defining the Submit button
		submitLogisticMulti = new Button("Multivariable Logistic Regression");
		choices.getChildren().add(submitLogisticMulti);

		submitLogisticUni = new Button("Univariate Logistic Regression");
		choices.getChildren().add(submitLogisticUni);

		submitMulti = new Button("Multivariable Regression");
		choices.getChildren().add(submitMulti);

		submitUni = new Button("Univariate Regression");
		choices.getChildren().add(submitUni);
		GridPane.setConstraints(choices, 0, 3);
		grid.getChildren().add(choices);

		// Defining the Clear button
		clear = new Button("Clear");
		GridPane.setConstraints(clear, 1, 1);
		grid.getChildren().add(clear);

		// Adding a Label
		label = new Label();
		GridPane.setConstraints(label, 0, 5);
		GridPane.setColumnSpan(label, 2);
		grid.getChildren().add(label);

		root.setCenter(grid);

		sceneChooser.addScreen("Regression Page", root, this);
	}

	@Override
	public void controls(ScreenController sceneChooser) {
		// TODO Auto-generated method stub
		backHome.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				sceneChooser.activate("Main Page");
				return;
			}
		});

		submitMulti.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				if ((!alpha.getText().isEmpty() && !epoch.getText().isEmpty()
						&& !polynomialOrVariables.getText().isEmpty())) {

					Regression regressionObject = new LinearRegressionMultivariate();
					label.setText("Loading Values");
					regressionObject.gradientDescent(Integer.parseInt(epoch.getText()),
							Double.parseDouble(alpha.getText()), Integer.parseInt(polynomialOrVariables.getText()));
					label.setText(output(regressionObject));
				} else {
					label.setText("You have not filled out all values.");
				}
			}
		});

		submitUni.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				if ((!alpha.getText().isEmpty() && !epoch.getText().isEmpty()
						&& !polynomialOrVariables.getText().isEmpty())) {

					Regression regressionObject = new LinearRegressionUnivariate();
					label.setText("Loading Values");
					regressionObject.gradientDescent(Integer.parseInt(epoch.getText()),
							Double.parseDouble(alpha.getText()), Integer.parseInt(polynomialOrVariables.getText()));
					label.setText(output(regressionObject));
				} else {
					label.setText("You have not filled out all values.");
				}
			}
		});

		submitLogisticMulti.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				if ((!alpha.getText().isEmpty() && !epoch.getText().isEmpty()
						&& !polynomialOrVariables.getText().isEmpty())) {

					Regression regressionObject = new LogisticRegressionMultivariate();
					label.setText("Loading Values");
					regressionObject.gradientDescent(Integer.parseInt(epoch.getText()),
							Double.parseDouble(alpha.getText()), Integer.parseInt(polynomialOrVariables.getText()));
					label.setText(output(regressionObject));
				} else {
					label.setText("You have not filled out all values.");
				}
			}
		});

		submitLogisticUni.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				if ((!alpha.getText().isEmpty() && !epoch.getText().isEmpty()
						&& !polynomialOrVariables.getText().isEmpty())) {

					Regression regressionObject = new LogisticRegressionUnivariate();
					label.setText("Loading Values");
					regressionObject.gradientDescent(Integer.parseInt(epoch.getText()),
							Double.parseDouble(alpha.getText()), Integer.parseInt(polynomialOrVariables.getText()));

					label.setText(output(regressionObject));
				} else {
					label.setText("You have not filled out all values.");
				}
			}
		});

		// Setting an action for the Clear button
		clear.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				alpha.clear();
				epoch.clear();
				polynomialOrVariables.clear();
				label.setText(null);
			}
		});

	}

	// TODO Sort out making new value! Do it from file!

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

	}

	private String output(Regression object) {
		String answer = "";

		double[] wValues = object.answers();
		for (int i = 0; i < wValues.length; i++) {

			answer += "w" + i + ": " + wValues[i] + "\n";

		}
		answer += "Cost: " + object.cost();
		return answer;
	}

}
