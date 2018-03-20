package com.github.bartoszpogoda.application.view.filetree;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.ResourceBundle;

import com.github.bartoszpogoda.application.model.thumbnail.ThumbnailLoader;
import com.github.bartoszpogoda.application.view.preview.FolderPreviewController;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class FileTreeController implements Initializable {

	@FXML
	TreeView<File> treeView;
	
	private FolderPreviewController folderPreviewController;
	
	public void setFolderPreviewController(FolderPreviewController folderPreviewController) {
		this.folderPreviewController = folderPreviewController;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadFolderTree("C:\\dev\\studia\\programowanie-java\\katalog obrazkow");

		treeView.setCellFactory(treeView -> {
			return new TreeCell<File>() {
				@Override
				protected void updateItem(File file, boolean empty) {
					super.updateItem(file, empty);

					if (empty) {
						setText(null);
						setGraphic(null);
					} else {
						setText(file.getName());
					}
				}
			};
		});

		treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("Selected: " + newValue.getValue().getName());
			
			folderPreviewController.loadThumbs(newValue.getValue());
		});

	}

	public void loadFolderTree(String rootPath) {
		File file = new File(rootPath);

		TreeItem<File> root = new TreeItem<File>(file);

		addChildren(root);

		this.treeView.setRoot(root);
		this.treeView.getRoot().setExpanded(true);
	}

	public void addChildren(TreeItem<File> parent) {
		File file = parent.getValue();

		File[] children = file.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		});

		for (File child : children) {
			TreeItem<File> childItem = new TreeItem<File>(child);
			addChildren(childItem);

			parent.getChildren().add(childItem);
		}
	}

}
