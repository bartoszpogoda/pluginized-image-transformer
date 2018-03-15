package com.github.bartoszpogoda.application.model;

import java.io.File;
import java.io.FileFilter;
import java.util.stream.Stream;


import com.github.bartoszpogoda.application.view.preview.FolderPreviewController;

public class ThumbnailLoader {
	
	public void loadThumbs(FolderPreviewController folderPreviewController, File folder) {
		// load thumbs in different thread using Task
		
		File[] imagesWithingfolder = folder.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				// TODO make sth better
				return pathname.getName().contains(".jpg");
			}
		});
		
		Stream.of(imagesWithingfolder).parallel().forEach(file -> {
			System.out.println("TODO: process " + file.getName());
		});;
	}
	
}
