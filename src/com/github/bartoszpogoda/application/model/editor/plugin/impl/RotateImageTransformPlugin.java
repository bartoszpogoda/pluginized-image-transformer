package com.github.bartoszpogoda.application.model.editor.plugin.impl;

import java.awt.image.BufferedImage;

import com.github.bartoszpogoda.application.model.editor.plugin.ImageTransformPlugin;

import javafx.scene.image.Image;

public class RotateImageTransformPlugin extends ImageTransformPlugin{
	
	public static final String NAME = "Rotate 180";

	@Override
	public Image transform(Image image) {
		
		BufferedImage bfdImage = toBufferedImage(image);
		
		for (int i = 0; i < bfdImage.getWidth(); i++) {
			for (int j = 0; j < bfdImage.getHeight() / 2; j++) {
				int tmp = bfdImage.getRGB(i, j);
				bfdImage.setRGB(i, j, bfdImage.getRGB(i, bfdImage.getHeight() - j - 1));
				bfdImage.setRGB(i, bfdImage.getHeight() - j - 1, tmp);
			}
		}
		
		return toFxImage(bfdImage);
	}
	
	@Override
	public String getName() {
		return RotateImageTransformPlugin.NAME;
	}
}
