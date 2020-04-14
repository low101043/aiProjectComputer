package com.natlowis.ai.ui;

import java.io.File;

import org.apache.log4j.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Window extends Application {
	
	private static Logger logger = Logger.getLogger(Window.class); 

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		stage.setTitle("AI Project");
		VBox root = new VBox();
		Label label1 = new Label("This is the GUI for the AI Project");
		root.getChildren().add(label1);
		Button button = new Button("Click Me");
		
		
		root.getChildren().add(button);
		
		
		stage.setScene(new Scene(root, 200, 100));
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				button.disarm();
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open image");
				File file = fileChooser.showOpenDialog(stage);
				if (file != null) {
					logger.trace("This works!!");
				}
			}
		});
		stage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
