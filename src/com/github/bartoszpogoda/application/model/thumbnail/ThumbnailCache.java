package com.github.bartoszpogoda.application.model.thumbnail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class ThumbnailCache {
	// maps canonical path to the image data
	private Map<String, WeakReference<Thumbnail>> cache;
	
	private ThumbnailLoader thumbnailLoader = new ThumbnailLoader();

	public ThumbnailCache() {
		cache = new HashMap<>();
	}

	public Thumbnail get(File file) throws IOException {

		WeakReference<Thumbnail> thumbnailReference = cache.get(file.getCanonicalPath());

		if (thumbnailReference == null || thumbnailReference.get() == null) {
			// cache missed
			System.out.println("Thumbnail cache miss");
			
			thumbnailReference = new WeakReference<Thumbnail>(thumbnailLoader.loadThumb(file));

			cache.put(file.getCanonicalPath(), thumbnailReference);
		} else {
			// cache hit
			System.out.println("Thumbnail cache hit");
		}

		return thumbnailReference.get();
	}
}
