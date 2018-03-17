package com.github.bartoszpogoda.application.view.preview.thumb;

import java.net.URL;
import java.util.ResourceBundle;

import com.github.bartoszpogoda.application.model.thumbnail.Thumbnail;
import com.github.bartoszpogoda.application.view.editor.ImageEditorController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.layout.AnchorPane;

public class ThumbnailController implements Initializable {

	@FXML ImageView imageView;
	@FXML Text imageName;
	@FXML AnchorPane anchorPane;
	
	private Thumbnail thumbnail;
	private ImageEditorController imageEditorController;
	
	public void setThumbnail(Thumbnail thumbnail) {
		this.thumbnail = thumbnail;
		
		showThumbnail();
	}
	
	private void showThumbnail() {
		if(this.thumbnail != null) {
			this.imageView.setImage(thumbnail.getImage());
			this.imageName.setText(thumbnail.getFileName());
		}
	}

	public void setImageEditorController(ImageEditorController imageEditorController) {
		this.imageEditorController = imageEditorController;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		anchorPane.setOnMouseClicked((mouseEvent)-> {
			imageEditorController.loadImageForThumbnail(thumbnail);
		});
	}
	
}
