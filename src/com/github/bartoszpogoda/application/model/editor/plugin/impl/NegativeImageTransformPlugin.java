package com.github.bartoszpogoda.application.model.editor.plugin.impl;

import java.awt.image.BufferedImage;

import com.github.bartoszpogoda.application.model.editor.plugin.ImageTransformPlugin;

import javafx.scene.image.Image;

/**
 * @see https://www.dyclassroom.com/image-processing-project/how-to-convert-a-color-image-into-negative
 * @author BPOGODA
 *
 */
public class NegativeImageTransformPlugin extends ImageTransformPlugin {

	public static final String NAME = "Negative";

	@Override
	public Image transform(Image image) {

		BufferedImage bfdImage = toBufferedImage(image);

		// convert to negative
		for (int y = 0; y < bfdImage.getHeight(); y++) {
			for (int x = 0; x < bfdImage.getWidth(); x++) {
				int p = bfdImage.getRGB(x, y);
				int a = (p >> 24) & 0xff;
				int r = (p >> 16) & 0xff;
				int g = (p >> 8) & 0xff;
				int b = p & 0xff;
				// subtract RGB from 255
				r = 255 - r;
				g = 255 - g;
				b = 255 - b;
				// set new RGB value
				p = (a << 24) | (r << 16) | (g << 8) | b;
				bfdImage.setRGB(x, y, p);
			}
		}

		return toFxImage(bfdImage);
	}

	@Override
	public String getName() {
		return NegativeImageTransformPlugin.NAME;
	}
}
