package com.github.bartoszpogoda.application.model;

import javafx.scene.image.Image;

public class Thumbnail {

	private Image image;
	private String path;
	private String fileName;

	public Thumbnail() {
	}

	public Thumbnail(String path, Image image, String fileName) {
		this.path = path;
		this.image = image;
		this.fileName = fileName;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
