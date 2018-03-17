package com.github.bartoszpogoda.application.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.scene.image.Image;

public class ThumbnailLoader {

	private static final int IMAGE_MAX_WIDTH = 190;
	private static final int IMAGE_MAX_HEIGHT = 190;

	public ThumbnailLoader() {
	}

	public Thumbnail loadThumb(File file) throws IOException {
		Image image = new Image(new FileInputStream(file), IMAGE_MAX_WIDTH, IMAGE_MAX_HEIGHT, true, true);

		return new Thumbnail(file.getCanonicalPath(), image, file.getName());
	}

}
