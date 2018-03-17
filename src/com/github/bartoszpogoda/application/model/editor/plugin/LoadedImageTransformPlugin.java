package com.github.bartoszpogoda.application.model.editor.plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import javafx.scene.image.Image;

public class LoadedImageTransformPlugin {

	private Class<?> classReflection;
	private Object plugin;

	public LoadedImageTransformPlugin(Class<?> classReflection) {
		this.classReflection = classReflection;

		try {
			Constructor<?> classConstructor = classReflection.getConstructor();
			this.plugin = classConstructor.newInstance();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public String getName() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method getNameMethod = classReflection.getMethod("getName");

		return (String) getNameMethod.invoke(plugin);
	}
	
	@Override
	public String toString() {
		try {
			return getName();
		} catch (Exception e) {
			return "Plugin load error";
		}
	}

	public Image transform(Image image) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, InstantiationException {

		Method transformMethod = classReflection.getMethod("transform", Image.class);

		Image transformedImage = (Image) transformMethod.invoke(plugin, image);

		return transformedImage;
	}

}
