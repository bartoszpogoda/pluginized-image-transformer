package com.github.bartoszpogoda.application;
	
import java.io.IOException;

import com.github.bartoszpogoda.application.view.MainController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private Stage primaryStage;
	private BorderPane mainLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Image transformer");
		//this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MainView.fxml"));
			mainLayout = (BorderPane) loader.load();
			
			MainController mainController = (MainController) loader.getController();
			mainController.setStage(primaryStage);
			
			Scene scene = new Scene(mainLayout);
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
