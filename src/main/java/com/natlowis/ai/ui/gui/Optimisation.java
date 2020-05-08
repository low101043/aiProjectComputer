package com.natlowis.ai.ui.gui;

import java.io.File;
import java.util.ArrayList;

import com.natlowis.ai.fileHandaling.CSVFiles;
import com.natlowis.ai.graphs.Connection;
import com.natlowis.ai.graphs.Graph;
import com.natlowis.ai.optimisation.antcolony.AntColonyOptimisation;
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

public class Optimisation extends Application implements Window {

	private Button backHome;
	private TextField startNode;
	private TextField epoch;
	private TextField pheromoneLevel;
	private TextField endState;
	private TextField numOfAnts;
	private Button submit;
	private Button clear;
	private Label label;

	/**
	 * The Constructor which makes the scene
	 * @param sceneChooser The ScreenController being used
	 */
	public Optimisation(ScreenController sceneChooser) {
		// Sets ups the mane pane
		BorderPane root = new BorderPane();

		// Sets up the back home button
		backHome = new Button("Go To Home Page");
		root.setLeft(backHome);

		// Creating a GridPane container
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		// Defining the alpha text field
		startNode = new TextField();
		startNode.setPromptText("Enter the start node value");
		startNode.setPrefColumnCount(10);
		startNode.getText();
		GridPane.setConstraints(startNode, 0, 0);
		grid.getChildren().add(startNode);
		
		// Defining the polynomialOrVariables text field
				endState = new TextField();
				endState.setPrefColumnCount(15);
				endState.setPromptText("Enter the end state");
				GridPane.setConstraints(endState, 0, 1);
				grid.getChildren().add(endState);

		// Defining the epoch text field
		epoch = new TextField();
		epoch.setPromptText("Enter the number of iterations");
		GridPane.setConstraints(epoch, 0, 2);
		grid.getChildren().add(epoch);

		

		// Defining the polynomialOrVariables text field
		pheromoneLevel = new TextField();
				pheromoneLevel.setPrefColumnCount(15);
				pheromoneLevel.setPromptText("Enter the evapouration rate");
				GridPane.setConstraints(pheromoneLevel, 0, 3);
				grid.getChildren().add(pheromoneLevel);
				
				// Defining the polynomialOrVariables text field
				numOfAnts = new TextField();
				numOfAnts.setPrefColumnCount(15);
				numOfAnts.setPromptText("Enter the number of ants to use");
				GridPane.setConstraints(numOfAnts, 0, 4);
				grid.getChildren().add(numOfAnts);


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
				
				//Adding all buttons and label to scene
				root.setCenter(grid);

				sceneChooser.addScreen("Optimisation", root, this);  //Adds the screen to the ScreenController
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void controls(ScreenController sceneChooser) {
		
		
		//Takes the user back to the main page
				backHome.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent t) {
						//Clears all inputs
						startNode.clear();
						epoch.clear();
						pheromoneLevel.clear();
						numOfAnts.clear();
						endState.clear();
						label.setText(null);
						sceneChooser.activate("Main Page");  //activates the main page
						return;
					}
				});

				// Setting an action for the Submit button
				submit.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent e) {
						
						if ((!startNode.getText().isEmpty() && !epoch.getText().isEmpty()
								&& !pheromoneLevel.getText().isEmpty() && !endState.getText().isEmpty() && !numOfAnts.getText().isEmpty())) {  //Checks if there is an input

							if (Double.parseDouble(pheromoneLevel.getText()) < 1 && Double.parseDouble(pheromoneLevel.getText()) > 0) {
								
								label.setText("Loading");
								ArrayList<ArrayList<String>> data = getData(sceneChooser, 3);  //TODO if don't choose a file
								
								if (data != null) {
								Graph graph = new Graph(data);  //Makes a graph
								
								AntColonyOptimisation aco = new AntColonyOptimisation(graph);
								aco.AntColonyOptimisationAlgorithm(Integer.parseInt(startNode.getText()), Integer.parseInt(endState.getText()), Integer.parseInt(epoch.getText()), Double.parseDouble(pheromoneLevel.getText()), Integer.parseInt(numOfAnts.getText()));
								
								ArrayList<Integer> route = aco.finalRoute();
								
								String output = "Nodes Visited in order: ";  //The final output
								

								for (int node : route) {  //Adds all nodes to visit
									output += node + ", ";
								}
								
								label.setText(output);}
								else {
									label.setText("Choose a File");
								}
							}
							else {
								label.setText("The evapouration needs be between 0 and 1");
							}
						} else {
							label.setText("You have not inputted all data.");
						}
					}
				});

				// Setting an action for the Clear button
				clear.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent e) {
						startNode.clear();
						epoch.clear();
						pheromoneLevel.clear();
						numOfAnts.clear();
						endState.clear();
						label.setText(null);
					}
				});
		
	}
	
	/**
	 * Gets the data needed to be used
	 * @param sceneChooser The ScreenController used
	 * @param number The number of columns it should have
	 * @return The data in an arraylist of arraylist of strings,
	 */
	private ArrayList<ArrayList<String>> getData(ScreenController sceneChooser, int number){
		//Opens the file to use
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Data File");
		Stage stage = sceneChooser.getStage();
		File files = fileChooser.showOpenDialog(stage);
		
		if (files == null) {
			return null;
		}
		CSVFiles formattor = new CSVFiles(files, number);  //makes a formatter to use
		ArrayList<ArrayList<String>> data = formattor.readCSV();  //gets the data
		
		return data;
	}

}
