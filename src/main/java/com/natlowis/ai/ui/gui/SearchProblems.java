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

public class SearchProblems extends Application implements Window {

	private Button backHome;
	private Button dfsButton;
	private Button bfsButton;
	private TextField startNode;
	private TextField endNode;
	private Label label;

	public SearchProblems(ScreenController sceneChooser) {

		BorderPane root = new BorderPane();
		backHome = new Button("Go back home");
		root.setLeft(backHome);

		root.setTop(new Label("Search Algorithms"));

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		dfsButton = new Button("Depth First Search");
		bfsButton = new Button("Breadth First Search");

		startNode = new TextField();
		startNode.setPromptText("Enter the start node");
		startNode.setPrefColumnCount(10);
		startNode.getText();
		GridPane.setConstraints(startNode, 0, 1);
		grid.getChildren().add(startNode);

		endNode = new TextField();
		endNode.setPromptText("Enter the end node");
		endNode.setPrefColumnCount(10);
		endNode.getText();
		GridPane.setConstraints(endNode, 0, 2);
		grid.getChildren().add(endNode);

		label = new Label();
		GridPane.setConstraints(label, 0, 7);
		GridPane.setColumnSpan(label, 2);
		grid.getChildren().add(label);

		HBox choices = new HBox();
		choices.getChildren().addAll(dfsButton, bfsButton);
		GridPane.setConstraints(choices, 0, 5);
		grid.getChildren().add(choices);
		root.setCenter(grid);

		sceneChooser.addScreen("Search Problems Page", root, this);
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

		dfsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {

				if ((!startNode.getText().isEmpty() && !endNode.getText().isEmpty())) {

					Graph graph = new Graph();
					DepthFirstSearch dfs = new DepthFirstSearch(graph);
					dfs.algorithmToImplement(Integer.parseInt(startNode.getText()),
							Integer.parseInt(endNode.getText()));
					label.setText(outputAll(dfs));
				}

			}
		});

		bfsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {

				if ((!startNode.getText().isEmpty() && !endNode.getText().isEmpty())) {

					Graph graph = new Graph();
					BreadthFirstSearch bfs = new BreadthFirstSearch(graph);
					bfs.algorithmToImplement(Integer.parseInt(startNode.getText()),
							Integer.parseInt(endNode.getText()));
					label.setText(outputAll(bfs));
				}

			}
		});

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

	}

	private String outputAll(SearchAlgorithm object) {
		String output = "Nodes Visited in order: ";
		Integer[] nodes = object.nodesToVisit();
		Connection[] connections = object.solutionActions();

		for (int node : nodes) {
			output += node + ", ";
		}

		output += "\n" + "Connections for the solution: ";

		for (Connection connection : connections) {
			int originNode = connection.getOriginNode();
			int destinationNode = connection.getDestinationNode();
			int weight = connection.getWeight();

			output += "Connection(" + originNode + ", " + destinationNode + ", " + weight + "),  ";

		}

		return output;
	}

}
