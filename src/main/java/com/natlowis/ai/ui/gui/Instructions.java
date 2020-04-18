package com.natlowis.ai.ui.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Instructions extends Application implements Window {

	private Button backHome;

	public Instructions(ScreenController sceneChanger) {
		BorderPane root = new BorderPane();
		backHome = new Button("Go To Home Page");
		Label label1 = new Label(
				"This is a program where you can try some different AI Algorithms. \nThese are the algorithms which you can use:");
		Label label2 = new Label("-> Regression");
		Label label3 = new Label("-> Logistic Regression");
		Label label4 = new Label("-> Breadth First Search");
		Label label5 = new Label("-> Depth First Search");
		Label label6 = new Label("-> Q-Learning");

		VBox instructions = new VBox();
		instructions.getChildren().addAll(label1, label2, label3, label4, label5, label6);
		root.setLeft(backHome);
		root.setTop(new Label("AI Instructions"));
		root.setCenter(instructions);

		sceneChanger.addScreen("Instructions", root, this);

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
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

	}

}
