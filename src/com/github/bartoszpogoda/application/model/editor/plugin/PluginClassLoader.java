package com.github.bartoszpogoda.application.model.editor.plugin;

import java.io.DataInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * 
 * @author BPOGODA
 * @see Code is heavily inspired (parts copy-pasted) on
 *      https://www.journaldev.com/349/java-classloader
 *
 */
public class PluginClassLoader extends ClassLoader {

	private static PluginClassLoader instance;

	private PluginClassLoader() {
	}

	public static PluginClassLoader getInstance() {
		return instance == null ? instance = new PluginClassLoader() : instance;
	}

	public List<Class<? extends ImageTransformPlugin>> loadProvidedPluginClasses(File pluginDir) {

		List<File> pluginFiles = Arrays.asList(pluginDir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith("ImageTransformPlugin.class");
			}
		}));

		return pluginFiles.stream().map(pluginFile -> getClass(pluginFile)).filter(pluginClass -> pluginClass != null)
				.collect(Collectors.toList());
	}

	private Class<? extends ImageTransformPlugin> getClass(File file) {
		// String file = name.replace('.', File.separatorChar) + ".class";
		//
		// byte[] b = null;
		// try {
		// // This loads the byte code data from the file
		// b = loadClassFileData(file);
		// // defineClass is inherited from the ClassLoader class
		// // that converts byte array into a Class. defineClass is Final
		// // so we cannot override it
		// Class<?> c = defineClass(name, b, 0, b.length);
		// resolveClass(c);
		// return c;
		// } catch (IOException e) {
		// e.printStackTrace();
		// return null;
		// }
		return null;
	}

	/**
	 * Every request for a class passes through this method. If the class is in
	 * Lab3.imagemodifier.pluginsystem.plugins package, we will use this classloader
	 * or else delegate the request to parent classloader.
	 *
	 *
	 * @param name
	 *            Full class name
	 */
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		// System.out.println("Loading Class '" + name + "'");
		// if (name.startsWith("Lab3.imagemodifier.pluginsystem.plugins")) {
		// System.out.println("Loading Class using CCLoader");
		// return getClass(name);
		// }
		// return super.loadClass(name);
		return null;
	}

	/**
	 * Reads the file (.class) into a byte array. The file should be accessible as a
	 * resource and make sure that its not in Classpath to avoid any confusion.
	 *
	 * @param name
	 *            File name
	 * @return Byte array read from the file
	 * @throws IOException
	 *             if any exception comes in reading the file
	 */
	private byte[] loadClassFileData(String name) throws IOException {
		// InputStream stream = getClass().getClassLoader().getResourceAsStream(name);
		// int size = stream.available();
		// byte buff[] = new byte[size];
		// DataInputStream in = new DataInputStream(stream);
		// in.readFully(buff);
		// in.close();
		// return buff;
		return null;
	}
}
