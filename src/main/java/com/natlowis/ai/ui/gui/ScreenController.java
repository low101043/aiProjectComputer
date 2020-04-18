package com.natlowis.ai.ui.gui;

import java.util.HashMap;

import org.apache.log4j.Logger;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class ScreenController {
	private HashMap<String, Object[]> screenMap = new HashMap<>();
	private Scene main;

	private static Logger logger = Logger.getLogger(ScreenController.class);

	public ScreenController(Scene main) {
		this.main = main;
	}

	protected void addScreen(String name, Pane pane, Window controls) {
		Object[] array = { controls, pane };
		logger.trace("Added Items:" + " " + name);
		screenMap.put(name, array);
	}

	protected void removeScreen(String name) {
		screenMap.remove(name);
	}

	protected Window activate(String name) {

		Object[] data = screenMap.get(name);
		// logger.trace("Here");
		Pane sceneToSwapTo = (Pane) data[1];
		Window controlsToUse = (Window) data[0];
		main.setRoot(sceneToSwapTo);

		return controlsToUse;
	}

	public Pane getPaneOn() {
		return (Pane) main.getRoot();
	}
}