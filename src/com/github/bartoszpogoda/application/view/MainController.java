package com.github.bartoszpogoda.application.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.github.bartoszpogoda.application.Main;
import com.github.bartoszpogoda.application.view.editor.ImageEditorController;
import com.github.bartoszpogoda.application.view.filetree.FileTreeController;
import com.github.bartoszpogoda.application.view.preview.FolderPreviewController;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {

	private Stage primaryStage;

	private FileTreeController fileTreeController;
	private FolderPreviewController folderPreviewController;
	private ImageEditorController imageEditorController;

	@FXML
	BorderPane rootBorderPane;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			loadFileTreeComponent();
			loadFolderPreviewComponent();
			loadImageEditorComponent();

			fileTreeController.setFolderPreviewController(folderPreviewController);
			folderPreviewController.setImageEditorController(imageEditorController);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadFileTreeComponent() throws IOException {

		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/filetree/FileTreeView.fxml"));

		AnchorPane fileTreePane = (AnchorPane) loader.load();

		fileTreeController = (FileTreeController) loader.getController();

		this.rootBorderPane.setLeft(fileTreePane);
	}

	private void loadFolderPreviewComponent() throws IOException {

		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/preview/FolderPreviewView.fxml"));

		AnchorPane folderPreviewPane = (AnchorPane) loader.load();

		this.folderPreviewController = (FolderPreviewController) loader.getController();

		this.rootBorderPane.setBottom(folderPreviewPane);
	}

	private void loadImageEditorComponent() throws IOException {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/editor/ImageEditorView.fxml"));

		AnchorPane imageEditorPane = (AnchorPane) loader.load();

		imageEditorController = (ImageEditorController) loader.getController();

		this.rootBorderPane.setCenter(imageEditorPane);
	}

	public void setStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}
