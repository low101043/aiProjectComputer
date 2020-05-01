package com.natlowis.ai.ui.gui;

import com.natlowis.ai.graphs.Connection;
import com.natlowis.ai.graphs.Graph;
import com.natlowis.ai.search.uninformed.BreadthFirstSearch;
import com.natlowis.ai.search.uninformed.DepthFirstSearch;
import com.natlowis.ai.search.uninformed.SearchAlgorithm;

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

/**
 * This Screen will show all the different search algorithms
 * 
 * @author low101043
 *
 */
public class SearchProblems extends Application implements Window {

	// All the variables needed
	private Button backHome;
	private Button dfsButton;
	private Button bfsButton;
	private TextField startNode;
	private TextField endNode;
	private Label label;

	/**
	 * The Constructor which sets up the screen
	 * 
	 * @param sceneChooser The {@code ScreenController} which holds all the pages
	 *                     being used
	 */
	public SearchProblems(ScreenController sceneChooser) {

		// Sets the root page and the home button
		BorderPane root = new BorderPane();
		backHome = new Button("Go back home");
		root.setLeft(backHome);

		// Adds a label
		root.setTop(new Label("Search Algorithms"));

		// Defining the GridPane used
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		// Defining the buttons needed
		dfsButton = new Button("Depth First Search");
		bfsButton = new Button("Breadth First Search");

		// Defining the StartNode Text Field
		startNode = new TextField();
		startNode.setPromptText("Enter the start node");
		startNode.setPrefColumnCount(10);
		startNode.getText();
		GridPane.setConstraints(startNode, 0, 1);
		grid.getChildren().add(startNode);

		// Defining the endNode TextField
		endNode = new TextField();
		endNode.setPromptText("Enter the end node");
		endNode.setPrefColumnCount(10);
		endNode.getText();
		GridPane.setConstraints(endNode, 0, 2);
		grid.getChildren().add(endNode);

		// Defining the output label
		label = new Label();
		GridPane.setConstraints(label, 0, 7);
		GridPane.setColumnSpan(label, 2);
		grid.getChildren().add(label);

		// Adds everything to the scene
		HBox choices = new HBox();
		choices.getChildren().addAll(dfsButton, bfsButton);
		GridPane.setConstraints(choices, 0, 5);
		grid.getChildren().add(choices);
		root.setCenter(grid);

		sceneChooser.addScreen("Search Problems Page", root, this); // Adds it to the screen changer
	}

	@Override
	public void controls(ScreenController sceneChooser) {

		// Takes user back to the main page
		backHome.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				// Clears the screen
				startNode.clear();
				endNode.clear();
				label.setText(null);
				sceneChooser.activate("Main Page"); // Activates home page screen
				return;
			}
		});

		// TODO Get text file input for DFS and BFS

		// Does Depth First Search
		dfsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {

				if ((!startNode.getText().isEmpty() && !endNode.getText().isEmpty())) { // Checks we have inputs

					Graph graph = new Graph(); // Makes a graph
					DepthFirstSearch dfs = new DepthFirstSearch(graph);
					dfs.algorithmToImplement(Integer.parseInt(startNode.getText()),
							Integer.parseInt(endNode.getText())); // Does DFS then output it
					label.setText(outputAll(dfs));
				}

			}
		});

		// Does Breadth First Search
		bfsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {

				if ((!startNode.getText().isEmpty() && !endNode.getText().isEmpty())) { // Checks we have inputs

					Graph graph = new Graph(); // Makes a graph
					BreadthFirstSearch bfs = new BreadthFirstSearch(graph);
					bfs.algorithmToImplement(Integer.parseInt(startNode.getText()),
							Integer.parseInt(endNode.getText())); // Does BFS and output it
					label.setText(outputAll(bfs));
				}

			}
		});

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Needed to be an application

	}

	/**
	 * Will output the solution
	 * 
	 * @param object The SearchAlgorithm which has done its algorithm
	 * @return A string which shows the solution
	 */
	private String outputAll(SearchAlgorithm object) {

		String output = "Nodes Visited in order: "; // The final output
		Integer[] nodes = object.nodesToVisit(); // The nodes in the solution
		Connection[] connections = object.solutionActions(); // The connections in the solution

		for (int node : nodes) { // Adds all nodes to visit
			output += node + ", ";
		}

		output += "\n" + "Connections for the solution: ";

		for (Connection connection : connections) { // Adds each connection which you have to do
			int originNode = connection.getOriginNode();
			int destinationNode = connection.getDestinationNode();
			double weight = connection.getWeight();

			output += "Connection(" + originNode + ", " + destinationNode + ", " + weight + "),  ";

		}

		return output;
	}

}
