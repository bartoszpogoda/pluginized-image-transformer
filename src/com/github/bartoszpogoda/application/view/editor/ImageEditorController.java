package com.github.bartoszpogoda.application.view.editor;

import java.io.FileInputStream;
import java.io.IOException;

import com.github.bartoszpogoda.application.model.editor.plugin.ImageTransformPlugin;
import com.github.bartoszpogoda.application.model.editor.plugin.impl.RotateImageTransformPlugin;
import com.github.bartoszpogoda.application.model.thumbnail.Thumbnail;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ImageEditorController {
	@FXML
	ImageView imageView;
	@FXML
	Text imageNameTxt;

	public void loadImageForThumbnail(Thumbnail thumbnail) {
		imageNameTxt.setText(thumbnail.getFileName());

		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {

				Image fullSizeImage = new Image(new FileInputStream(thumbnail.getPath()), imageView.getFitWidth(),
						imageView.getFitHeight(), true, true);

				Platform.runLater(() -> {
					// test
					ImageTransformPlugin plug = new RotateImageTransformPlugin();
					Image transformedImage = plug.transform(fullSizeImage);
					imageView.setImage(transformedImage);
					// test end
					
//					imageView.setImage(fullSizeImage);
				});

				return null;
			}
		};

		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();

	}
}