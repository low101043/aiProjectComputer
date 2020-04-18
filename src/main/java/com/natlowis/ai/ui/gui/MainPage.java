package com.natlowis.ai.ui.gui;

import org.apache.log4j.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//TODO   Need to work out how to change screens even if bad!!!!
public class MainPage extends Application implements Window {

	private static Logger logger = Logger.getLogger(MainPage.class);
	private ScreenController sceneChooser;

	/*
	 * public MainPage(Stage stage) { start(stage); }
	 */

	@Override
	public void start(Stage stage) throws InterruptedException {
		// TODO Auto-generated method stub

		stage.setTitle("AI Project");
		BorderPane root = new BorderPane();
		Scene mainPage = new Scene(root, 800, 500);
		stage.setScene(mainPage);
		sceneChooser = new ScreenController(mainPage);

		Label label1 = new Label("This is the GUI for the AI Project");
		root.setTop(label1);
		Button algorithms = new Button("Algorithms");
		Button instructions = new Button("Instructions");

		root.setLeft(new Label("\t"));

		VBox centre = new VBox();
		centre.getChildren().addAll(algorithms, instructions);
		root.setCenter(centre);

		root.setBottom(new Label("CopyRight Nathaniel Lowis"));

		sceneChooser.addScreen("Main Page", root, this);

		addAllScreens();

		stage.show();

		algorithms.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				// newPage(stage, root);
				Window controls = sceneChooser.activate("Algorithms To Choose");
				// a.controls(sceneChooser);

				controls.controls(sceneChooser);

				// root.getChildren().addAll(itemsForMain);

			}
		});

		instructions.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				Window controls = sceneChooser.activate("Instructions");
				controls.controls(sceneChooser);
			}
		});

		logger.trace("Finished");

		// mainPage(stage, root);

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void controls(ScreenController sceneChooser) {
		// TODO Auto-generated method stub

	}

	/*
	 * public void mainPage(Stage stage, Pane root) {
	 * 
	 * removeAllItems(stage, root);
	 * 
	 * 
	 * Button buttonToCheck = new Button("Click Me");
	 * 
	 * 
	 * 
	 * button.setOnAction(new EventHandler<ActionEvent>() {
	 * 
	 * @Override public void handle(ActionEvent t) { //newPage(stage, root);
	 * ObservableList<Node> itemsForMain = root.getChildren();
	 * button.setDisable(true); stage.close(); Platform.exit();
	 * logger.trace("Closed"); AnotherPage a = new AnotherPage(stage);
	 * 
	 * 
	 * logger.trace("Is this working?"); //root.getChildren().addAll(itemsForMain);
	 * 
	 * } });
	 * 
	 * 
	 * logger.trace("Updataing"); stage.show(); }
	 * 
	 * public void algorithmsToImplement(Stage stage, Pane root) {
	 * logger.trace("Now in start");
	 * 
	 * removeAllItems(stage, root);
	 * 
	 * Label label1 = new Label("a"); Button button = new Button("Home Page");
	 * 
	 * root = new VBox();
	 * 
	 * 
	 * root.getChildren().addAll(button, label1); stage.setScene(new Scene(root,
	 * 200, 100)); button.setOnAction(new EventHandler<ActionEvent>() {
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * 
	 * logger.trace("Button Pressed"); stage.close(); //newPage(stage, root);
	 * return; //stage.close();
	 * 
	 * 
	 * } });
	 * 
	 * stage.show(); }
	 */

	private void addAllScreens() {
		Window intstructionScreen = new Instructions(sceneChooser);
		Window algorithmsScreen = new AlgorithmsToChoose(sceneChooser);
		Window qlearningScreen = new QLearningInput(sceneChooser);
		Window regressionScreen = new RegressionChoice(sceneChooser);
		Window searchScreen = new SearchProblems(sceneChooser);
	}
}
