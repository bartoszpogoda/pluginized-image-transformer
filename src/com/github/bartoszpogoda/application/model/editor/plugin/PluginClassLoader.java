package com.github.bartoszpogoda.application.model.editor.plugin;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

import com.github.bartoszpogoda.application.Main;

/**
 * TODO It needs some refactor. Currently some things are ugily hardcoded.
 * 
 * @author BPOGODA
 *
 */
public class PluginClassLoader extends ClassLoader {

	private static PluginClassLoader instance;

	private static final File PLUGIN_DIR_BASE = new File(
			"C:\\Users\\BPOGODA\\eclipse-workspace\\pluginized-image-transformer\\plugins");
	private static final File PLUGIN_DIR = new File(
			"C:\\Users\\BPOGODA\\eclipse-workspace\\pluginized-image-transformer\\plugins\\com\\github\\bartoszpogoda\\application\\model\\editor\\plugin\\impl");

	private PluginClassLoader(ClassLoader parent) {
		super(parent);
	}

	public static PluginClassLoader getInstance() {
		return instance == null ? instance = new PluginClassLoader(Main.class.getClassLoader()) : instance;
	}

	public List<LoadedImageTransformPlugin> loadImageTransformPlugins() {
		
		List<File> pluginFiles = Arrays.asList(PLUGIN_DIR.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith("ImageTransformPlugin.class");
			}
		}));

		return pluginFiles.stream().map(pluginFile -> {
			String classLastPartOfName = pluginFile.getName().substring(0, pluginFile.getName().indexOf(".class"));
			return getClass("com.github.bartoszpogoda.application.model.editor.plugin.impl." + classLastPartOfName);
		}).map(classReflection -> new LoadedImageTransformPlugin(classReflection)).collect(Collectors.toList());
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		if (name.startsWith("com.github.bartoszpogoda") && name.endsWith("ImageTransformPlugin")) {
			System.out.println("Loading Class using PluginClassLoader");
			return getClass(name);
		}
		return super.loadClass(name);
	}

	private Class<?> getClass(String name) {
		try {

			String url = PLUGIN_DIR_BASE.toURI().toURL() + name.replace('.', File.separatorChar) + ".class";
			URL myUrl = new URL(url);
			URLConnection connection = myUrl.openConnection();
			InputStream input = connection.getInputStream();
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int data = input.read();

			while (data != -1) {
				buffer.write(data);
				data = input.read();
			}

			input.close();

			byte[] classData = buffer.toByteArray();

			Class<?> defineClass = defineClass(name, classData, 0, classData.length);
			resolveClass(defineClass);

			System.out.println("Loaded " + defineClass.getName());

			return defineClass;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
