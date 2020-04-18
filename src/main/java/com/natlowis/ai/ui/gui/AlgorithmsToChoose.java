package com.natlowis.ai.ui.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlgorithmsToChoose extends Application implements Window {

	private Button backHome;
	private Button search;
	private Button regression;
	private Button qLearning;

	public AlgorithmsToChoose(ScreenController sceneChooser) {
		BorderPane root = new BorderPane();
		backHome = new Button("Go To Home Page");

		root.setLeft(backHome);

		VBox centreNodes = new VBox();
		Label label1 = new Label("Press the button to take you to which algorithm you want to try");
		search = new Button("Search Algorithms");
		regression = new Button("Supervised Learning");
		qLearning = new Button("Unsupervised Learning");

		root.setTop(label1);
		centreNodes.getChildren().addAll(search, regression, qLearning);
		root.setCenter(centreNodes);

		sceneChooser.addScreen("Algorithms To Choose", root, this);
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

		qLearning.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				Window controls = sceneChooser.activate("Q Learning Input");
				controls.controls(sceneChooser);
			}
		});

		regression.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				Window controls = sceneChooser.activate("Regression Page");
				controls.controls(sceneChooser);
			}
		});

		search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				Window controls = sceneChooser.activate("Search Problems Page");
				controls.controls(sceneChooser);
			}
		});
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

	}

}
