package com.natlowis.ai.ui.gui;

import com.natlowis.ai.graphs.Graph;
import com.natlowis.ai.unsupervised.QLearning;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//Code edited from: https://docs.oracle.com/javafx/2/ui_controls/text-field.htm
public class QLearningInput extends Application implements Window {

	private Button backHome;
	private TextField learningRate;
	private TextField epoch;
	private TextField discountRate;
	private TextField endState;
	private Button submit;
	private Button clear;
	private Label label;

	public QLearningInput(ScreenController sceneChooser) {
		BorderPane root = new BorderPane();
		backHome = new Button("Go back home");
		root.setLeft(backHome);

		// Creating a GridPane container
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		// Defining the Name text field
		learningRate = new TextField();
		learningRate.setPromptText("Enter your alpha value");
		learningRate.setPrefColumnCount(10);
		learningRate.getText();
		GridPane.setConstraints(learningRate, 0, 0);
		grid.getChildren().add(learningRate);

		// Defining the Last Name text field
		epoch = new TextField();
		epoch.setPromptText("Enter the number of iterations");
		GridPane.setConstraints(epoch, 0, 1);
		grid.getChildren().add(epoch);

		// Defining the Comment text field
		discountRate = new TextField();
		discountRate.setPrefColumnCount(15);
		discountRate.setPromptText("Enter the discount rate");
		GridPane.setConstraints(discountRate, 0, 2);
		grid.getChildren().add(discountRate);

		// Defining end state
		endState = new TextField();
		endState.setPromptText("Enter the end state");
		GridPane.setConstraints(endState, 0, 3);
		grid.getChildren().add(endState);

		// Defining the Submit button
		submit = new Button("Submit");
		GridPane.setConstraints(submit, 1, 0);
		grid.getChildren().add(submit);

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

		sceneChooser.addScreen("Q Learning Input", root, this);
	}

	@Override
	public void controls(ScreenController sceneChooser) {
		// TODO Auto-generated method stub
		backHome.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				learningRate.clear();
				epoch.clear();
				discountRate.clear();
				endState.clear();
				label.setText(null);
				sceneChooser.activate("Main Page");
				return;
			}
		});

		// Setting an action for the Submit button
		submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				if ((!learningRate.getText().isEmpty() && !epoch.getText().isEmpty()
						&& !discountRate.getText().isEmpty() && !endState.getText().isEmpty())) {

					Graph graph = new Graph(); // TODO THIS NEEDS TO BE GOT FROM A FILE!!

					graph.addNode(0);
					graph.addNode(1);
					graph.addNode(2);

					QLearning a = new QLearning(graph);
					a.qLearning(Double.parseDouble(discountRate.getText()), Double.parseDouble(learningRate.getText()),
							Integer.parseInt(epoch.getText()), Integer.parseInt(endState.getText()));
					label.setText(a.toString());
				} else {
					label.setText("You have not left a comment.");
				}
			}
		});

		// Setting an action for the Clear button
		clear.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				learningRate.clear();
				epoch.clear();
				discountRate.clear();
				endState.clear();
				label.setText(null);
			}
		});

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

	}

}
