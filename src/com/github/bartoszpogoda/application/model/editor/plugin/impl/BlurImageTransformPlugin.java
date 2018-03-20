package com.github.bartoszpogoda.application.model.editor.plugin.impl;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import com.github.bartoszpogoda.application.model.editor.plugin.ImageTransformPlugin;

import javafx.scene.image.Image;

public class BlurImageTransformPlugin extends ImageTransformPlugin {

	public static final String NAME = "Blur";

	@Override
	public Image transform(Image image) {

		BufferedImage bfdImage = toBufferedImage(image);

		Kernel kernel = new Kernel(3, 3,
				new float[] { 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f });
		BufferedImageOp op = new ConvolveOp(kernel);
		bfdImage = op.filter(bfdImage, null);

		return toFxImage(bfdImage);
	}

	@Override
	public String getName() {
		return BlurImageTransformPlugin.NAME;
	}
}
