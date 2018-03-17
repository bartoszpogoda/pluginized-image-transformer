package com.github.bartoszpogoda.application.model.editor.plugin;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public abstract class ImageTransformPlugin {

	public abstract Image transform(Image image);

	protected BufferedImage toBufferedImage(Image fxImage) {
		return SwingFXUtils.fromFXImage(fxImage, null);
	}

	protected Image toFxImage(BufferedImage bufferedImage) {
		return SwingFXUtils.toFXImage(bufferedImage, null);
	}

	public String getName() {
		return "Default Plugin Name";
	}
}
