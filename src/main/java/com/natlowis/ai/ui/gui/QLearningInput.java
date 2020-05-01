package com.natlowis.ai.ui.gui;

import java.io.File;
import java.util.ArrayList;

import com.natlowis.ai.fileHandaling.CSVFiles;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * The page which allows you to use Q Learning
 * 
 * @author low101043
 *
 */
public class QLearningInput extends Application implements Window {

	// Sets up variables which are needed
	private Button backHome;
	private TextField learningRate;
	private TextField epoch;
	private TextField discountRate;
	private TextField endState;
	private Button submit;
	private Button clear;
	private Label label;

	/**
	 * The Constructor where it constructs the page and adds it to
	 * <code> sceneChanger </code>
	 * 
	 * @param sceneChooser The {@code ScreenController} which has all the correct
	 *                     pages
	 */
	public QLearningInput(ScreenController sceneChooser) {

		// This sets up the root and then
		BorderPane root = new BorderPane();
		backHome = new Button("Go back home");
		root.setLeft(backHome);

		// Creating a GridPane container
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		// Defining the learningRate text field
		learningRate = new TextField();
		learningRate.setPromptText("Enter your alpha value");
		learningRate.setPrefColumnCount(10);
		learningRate.getText();
		GridPane.setConstraints(learningRate, 0, 0);
		grid.getChildren().add(learningRate);

		// Defining the epoch text field
		epoch = new TextField();
		epoch.setPromptText("Enter the number of iterations");
		GridPane.setConstraints(epoch, 0, 1);
		grid.getChildren().add(epoch);

		// Defining the discountRate text field
		discountRate = new TextField();
		discountRate.setPrefColumnCount(15);
		discountRate.setPromptText("Enter the discount rate");
		GridPane.setConstraints(discountRate, 0, 2);
		grid.getChildren().add(discountRate);

		// Defining endstate field
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

		// Adding all buttons and label to scene
		root.setCenter(grid);

		sceneChooser.addScreen("Q Learning Input", root, this); // Adds the screen to the ScreenController
	}

	@Override
	public void controls(ScreenController sceneChooser) {

		// Takes the user back to the main page
		backHome.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				// Clears all inputs
				learningRate.clear();
				epoch.clear();
				discountRate.clear();
				endState.clear();
				label.setText(null);
				sceneChooser.activate("Main Page"); // activates the main page
				return;
			}
		});

		// Setting an action for the Submit button
		submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if ((!learningRate.getText().isEmpty() && !epoch.getText().isEmpty()
						&& !discountRate.getText().isEmpty() && !endState.getText().isEmpty())) { // Checks if there is
																									// an input

					// Opens a file
					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("Open Data File");
					Stage stage = sceneChooser.getStage();
					File files = fileChooser.showOpenDialog(stage); // allow user to open file
					CSVFiles formattor = new CSVFiles(files, 3); // makes a formatter to use
					ArrayList<ArrayList<String>> data = formattor.readCSV(); // gets the data

					Graph graph = new Graph(data); // Makes a graph from the data

					QLearning qLearning = new QLearning(graph); // makes a QLearning object
					qLearning.qLearning(Double.parseDouble(discountRate.getText()),
							Double.parseDouble(learningRate.getText()), Integer.parseInt(epoch.getText()),
							Integer.parseInt(endState.getText())); // does qLearning
					label.setText(qLearning.toString()); // Outputs the table
				} else {
					label.setText("You have not inputted all data.");
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
