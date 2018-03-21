package com.github.bartoszpogoda.application.view.editor;

import java.io.FileInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.bartoszpogoda.application.model.editor.plugin.LoadedImageTransformPlugin;
import com.github.bartoszpogoda.application.model.editor.plugin.PluginClassLoader;
import com.github.bartoszpogoda.application.model.thumbnail.Thumbnail;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ImageEditorController implements Initializable {
	@FXML
	ImageView imageView;
	@FXML
	Text imageNameTxt;

	private Thumbnail displayedThumbnail;

	private List<LoadedImageTransformPlugin> imageTransformPlugins;

	@FXML
	ComboBox<LoadedImageTransformPlugin> pluginChooser;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//loadPlugins();

	}

	public void unloadPlugins() {
		
		pluginChooser.getItems().clear();
		pluginChooser.setValue(null);
		
		PluginClassLoader.unload();
		
	}

	public void loadPlugins() {
		imageTransformPlugins = PluginClassLoader.getInstance().loadImageTransformPlugins();

		imageTransformPlugins.forEach(plugin -> pluginChooser.getItems().add(plugin));

		if (pluginChooser.getItems().size() > 0) {
			pluginChooser.getSelectionModel().select(0);
		}
	}

	public void loadImageForThumbnail(Thumbnail thumbnail) {
		this.displayedThumbnail = thumbnail;
		imageNameTxt.setText(thumbnail.getFileName());

		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {

				Image fullSizeImage = new Image(new FileInputStream(thumbnail.getPath()), imageView.getFitWidth(),
						imageView.getFitHeight(), true, true);

				Platform.runLater(() -> {
					imageView.setImage(fullSizeImage);
				});

				return null;
			}
		};

		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();

	}

	@FXML
	public void transformBtnClicked() {
		if(pluginChooser.getSelectionModel().getSelectedItem() == null) {
			System.out.println("No plugin selected");
			return;
		}
		
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {

				LoadedImageTransformPlugin selectedPlugin = pluginChooser.getSelectionModel().getSelectedItem();

				Image transformedImage = selectedPlugin.transform(imageView.getImage());

				Platform.runLater(() -> {
					imageView.setImage(transformedImage);
				});

				return null;
			}
		};

		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
	}

//	@FXML
//	public void saveBtnClicked() {
//		// Task<Void> task = new Task<Void>() {
//		// @Override
//		// protected Void call() throws Exception {
//		// ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(), null), "jpg",
//		// new File(displayedThumbnail.getPath()));
//		//
//		// return null;
//		// }
//		// };
//		//
//		// Thread thread = new Thread(task);
//		// thread.setDaemon(true);
//		// thread.start();
//		this.unloadPlugins();
//
//		System.out.println("Not yet implemented!");
//	}

	@FXML public void loadBtnClicked() {
		this.loadPlugins();
	}

	@FXML public void unloadBtnClicked() {
		System.out.println("Unload plugins");
		this.unloadPlugins();
	}
}
