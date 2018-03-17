package com.github.bartoszpogoda.application.view.preview;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import com.github.bartoszpogoda.application.Main;
import com.github.bartoszpogoda.application.model.Thumbnail;
import com.github.bartoszpogoda.application.model.ThumbnailCache;
import com.github.bartoszpogoda.application.view.editor.ImageEditorController;
import com.github.bartoszpogoda.application.view.preview.thumb.ThumbnailController;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.ScrollPane;

public class FolderPreviewController implements Initializable {

	@FXML
	AnchorPane anchorPane;
	@FXML
	ImageView imageTest;

	private int nextImagePositionX = 0;

	private static final int THUMB_SIZE = 210;
	// private static final int X_BOUND = THUMB_SIZE*6;

	private ThumbnailCache thumbnailCache = new ThumbnailCache();

	private ImageEditorController imageEditorController;
	
	@FXML
	ScrollPane scrollPane;
	@FXML
	Pane thumbnailsPane;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	private void clearThumbnails() {
		thumbnailsPane.getChildren().clear();
		nextImagePositionX = 0;
	}

	public void addThumbnail(Thumbnail thumbnail) throws IOException {

		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/preview/thumb/ThumbnailView.fxml"));

		AnchorPane thumbnailPane = (AnchorPane) loader.load();

		ThumbnailController thumbnailController = (ThumbnailController) loader.getController();

		thumbnailController.setThumbnail(thumbnail);
		
		thumbnailController.setImageEditorController(imageEditorController);
		
		thumbnailPane.setLayoutX(nextImagePositionX);
		thumbnailsPane.getChildren().add(thumbnailPane);

		nextImagePositionX += THUMB_SIZE;
	}

	public void loadThumbs(File folder) {
		clearThumbnails();

		File[] imagesWithingfolder = folder.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				// TODO make sth better
				return pathname.getName().contains(".jpg");
			}
		});

		Stream.of(imagesWithingfolder).parallel().forEach(file -> {
			System.out.println("TODO: process " + file.getName());

			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call() throws Exception {

					Thumbnail thumbnail = thumbnailCache.get(file);

					Platform.runLater(() -> {
						// imageTest.setImage(thumbnail.getImage());
						try {
							addThumbnail(thumbnail);
						} catch (IOException e) {
							e.printStackTrace();
						}
					});

					// TODO add image to the preview, set up action listener etc.
					// maek it like on stackoverflow in other thread or sth..

					return null;
				}
			};

			Thread thread = new Thread(task);
			thread.setDaemon(true);
			thread.start();
		});
	}

	public void setImageEditorController(ImageEditorController imageEditorController) {
		this.imageEditorController = imageEditorController;
	}

}
