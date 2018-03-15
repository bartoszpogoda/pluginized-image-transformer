package com.github.bartoszpogoda.application.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.github.bartoszpogoda.application.Main;
import com.github.bartoszpogoda.application.view.filetree.FileTreeController;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {

	private Stage primaryStage;
	
	@FXML BorderPane rootBorderPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			loadFileTreeComponent();
			loadFolderPreviewComponent();
		
		} catch (IOException e) {
		}
	}

	private void loadFileTreeComponent() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/filetree/FileTreeView.fxml"));		
		
		AnchorPane fileTreePane = (AnchorPane) loader.load();
		
		//FileTreeController fileTreeController = (FileTreeController) loader.getController();
		//mainController.setStage(primaryStage);
		
		this.rootBorderPane.setLeft(fileTreePane);
	}

	private void loadFolderPreviewComponent() throws IOException {

		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/preview/FolderPreviewView.fxml"));		
		
		AnchorPane folderPreviewPane = (AnchorPane) loader.load();
		
		//FileTreeController fileTreeController = (FileTreeController) loader.getController();
		//mainController.setStage(primaryStage);
		
		this.rootBorderPane.setCenter(folderPreviewPane);
	}
	
	public void setStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}
